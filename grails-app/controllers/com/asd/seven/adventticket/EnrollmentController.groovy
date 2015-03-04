package com.asd.seven.adventticket

import com.asd.seven.adventticket.enums.GenderEnum
import com.asd.seven.adventticket.security.Role
import com.asd.seven.adventticket.security.User
import com.asd.seven.adventticket.security.UserRole
import com.megatome.grails.RecaptchaService
import grails.converters.JSON
import grails.gsp.PageRenderer
import org.springframework.security.authentication.AuthenticationServiceException

class EnrollmentController {

    def mailService
    def enrollmentService
    def springSecurityService
    PageRenderer groovyPageRenderer
    RecaptchaService recaptchaService

    def index() {}

    def saveEnroll() {
        try {
            def recaptchaOK = true
            if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
                recaptchaOK = false
            }

            if(!recaptchaOK) {
                recaptchaService.cleanUp(session)
                throw new AuthenticationServiceException("Error en Verificación Humana. Intenta de nuevo.");
            }
            enrollmentService.createUser(params)
            render ([username:params.username] as JSON)
        } catch (Exception e){
            recaptchaService.cleanUp(session)
            response.status = 422
            render (e.getMessage())
        }
    }

    def confirm(){
        def result = enrollmentService.checkConfirmation(params.id)
        if(!result.valid){
            render view: '/home/index'
            return
        }

        def user = User.get(result.token.toLong())
        if(!user){
            render view: '/home/index'
            return
        }

        user.accountLocked = false
        user.save(flush: true)
        def roles2 = [] as Set
        roles2 << Role.findByAuthority('ROLE_ASSISTANT')
        for(rol in roles2) {
            UserRole.create(user, rol, false)
        }

        def contenido = groovyPageRenderer.render(view:"/mail/welcome", model:[user:user])
        mailService.sendMail {
            to user.email
            from grailsApplication.config.grails.fromMailAddress
            subject message(code: "enrollment.welcome.subject.label")
            html contenido
        }

        springSecurityService.reauthenticate user.username

        flash.message = message(code: 'enrollment.welcome.new')
        redirect(uri: "/perfil/${user.username}")
    }

    def reloadCaptcha(){
        render(template: '/layouts/recaptcha')
    }

}
