package com.asd.seven.adventticket

import com.asd.seven.adventticket.enums.GenderEnum
import com.asd.seven.adventticket.security.PendingEmailConfirmation
import com.asd.seven.adventticket.security.User
import com.asd.seven.adventticket.security.UserRole
import grails.gsp.PageRenderer
import grails.transaction.Transactional
import grails.util.Environment
import org.springframework.security.authentication.AuthenticationServiceException

import java.rmi.server.UID
import java.security.SecureRandom

@Transactional
class EnrollmentService {

    def mailService
    def grailsApplication
    static prng = new SecureRandom()
    PageRenderer groovyPageRenderer
    boolean transactional = true
    def maxAge = 1000*60*60*24*2L // 2 days
    def maxAgeConfirmation = 1000*60*60*24*1L // 1 days

    def createUser(Map params) {

        User found = User.findByUsername(params?.username)

        if(found) {
            log.error("User exist")
            throw new AuthenticationServiceException("Usuario existente, introduzca otro por favor.");
        }

        User user = new User()
        user.username = params?.username
        user.nameFull = params?.nameFull
        user.email = params?.email
        user.birthday = new Date().parse('dd/MM/yyyy', params?.birthday)
        user.gender = GenderEnum[params?.gender]
        user.password = params?.password
        user.accountLocked = true

        user.validate()
        if(user.hasErrors()) {
            log.error(user.errors)
            user.discard()
            throw new AuthenticationServiceException("Ocurrio un error al momento de guardar los datos. Intentelo mas tarde.");
        }

        if(!user.save(flush: true)) {
            log.error(user.errors)
            user.discard()
            throw new AuthenticationServiceException("Ocurrio un error al momento de guardar los datos. Intentelo mas tarde.");
        }

        sendConfirmation(user)
    }

    def checkConfirmation(String confirmationToken) {
        if (log.traceEnabled) log.trace("checkConfirmation looking for confirmation token: $confirmationToken")
        def conf = PendingEmailConfirmation.findByConfirmationToken(confirmationToken)
        if (conf && (conf.confirmationToken == confirmationToken)) {
            if (log.debugEnabled) {
                log.debug( "Notifying application of valid email confirmation for user token ${conf.userToken}, email ${conf.emailAddress}")
            }
            conf.delete()
            return [valid: true, email: conf.emailAddress, token:conf.userToken]
        } else {
            if (log.traceEnabled) log.trace("checkConfirmation did not find confirmation token: $confirmationToken")
            return [valid:false]
        }
    }

    void makeToken(PendingEmailConfirmation confirmation){
        def uid = confirmation.emailAddress + new UID().toString() + prng.nextLong() + System.currentTimeMillis()
        def hash = uid.encodeAsSHA256Bytes()
        def token = hash.encodeBase64()
        confirmation.confirmationToken = token
    }

    def makeURL(token) {
        def serverURL = grailsApplication.config.grails.serverURL
        "${serverURL}/registro/confirma/${token.encodeAsURL()}"
    }

    def sendConfirmation(User user) {
        if (log.infoEnabled) {
            log.info "Sending email confirmation mail to ${user.email}, callback events will be prefixed with user data is [${user.id}])"
        }
        def conf = new PendingEmailConfirmation(emailAddress:user.email, userToken:user.id.toString())
        makeToken(conf)

        if (log.debugEnabled) {
            log.debug "Created email confirmation token [${conf.confirmationToken}] for mail to ${conf.emailAddress}"
        }

        if (!conf.save()) {
            throw new IllegalArgumentException( "Unable to save pending confirmation: ${conf.errors}")
        }

        def uri = makeURL(conf.confirmationToken)

        if (log.infoEnabled) {
            log.info( "Sending email confirmation mail to $user.email - confirmation link is: ${uri}")
        }

        def content = groovyPageRenderer.render(view:"/mail/enrollment", model:[user:user,uri:uri])

        try {
            mailService.sendMail {
                to user.email
                from grailsApplication.config.grails.fromMailAddress
                subject "Confirma tu cuenta ADVENTicket"
                html content
            }
        } catch (Throwable t) {
            if (Environment.current == Environment.DEVELOPMENT) {
                log.warn "Mail sending failed but you're in development mode so I'm ignoring this fact, you can confirm using this link: ${uri}"
                log.error "Mail send failed", t
            } else {
                throw t
            }
        }
        return conf
    }

    void cullStaleConfirmations() {

        log.info("Checking for stale email confirmations...")

        def threshold = System.currentTimeMillis() - maxAge
        def threshold2 = System.currentTimeMillis() - maxAgeConfirmation
        def staleConfirmations = PendingEmailConfirmation.findAllByTimestampLessThan(new Date(threshold))
        def staleConfirmationsPre = PendingEmailConfirmation.findAllByTimestampLessThan(new Date(threshold2))

        def c = 0
        staleConfirmations.each() {
            if (log.debugEnabled) {
                log.debug("Notifying application of stale email confirmation for user token ${it.userToken}")
            }
            def user = User.get(it.userToken)
            UserRole.removeAll(user)
            user.delete(flush: true)
            it.delete()
            c++
        }

        log.info("Done check for stale email confirmations, found $c")

        def d = 0
        staleConfirmationsPre.each() {
            def email = it.emailAddress
            // Tell application
            log.debug("Notifying application of stale email pre confirmation for user token ${it.userToken}")
            User userp = User.get(it.userToken)
            def contenido = groovyPageRenderer.render(view: "/mail/enrollmentRemember", model: [mail: email, uri: it.userToken, name: userp.nameFull])
            mailService.sendMail {
                to email
                from grailsApplication.config.grails.fromMailAddresss
                subject "Por favor confirma tu cuenta de ADVENTicket"
                html contenido
            }
            d++
        }

        log.info("Done check for pre stale email confirmations, found $d")
    }

}
