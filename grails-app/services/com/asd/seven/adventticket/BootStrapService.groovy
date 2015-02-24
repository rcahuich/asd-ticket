package com.asd.seven.adventticket

import com.asd.seven.adventticket.security.Requestmap
import com.asd.seven.adventticket.security.Role
import com.asd.seven.adventticket.security.User
import com.asd.seven.adventticket.security.UserRole
import grails.transaction.Transactional

@Transactional
class BootStrapService {

    def serviceMethod() {
        try {
            insertSecurityData()
        } catch (all) {
            log.error(all)
        }
    }

    private void insertSecurityData() {

        if (!Role.list()) {

            Role rolSuperAdmin = new Role(authority: "ROLE_SUPERADMIN").save(flush: true, failOnError: true)
            Role rolOrganization = new Role(authority: "ROLE_ORGANIZATION").save(flush: true, failOnError: true)
            Role rolAssistant = new Role(authority: "ROLE_ASSISTANT").save(flush: true, failOnError: true)

            if (!User.list()) {
                User superAdmin = new User(username: 'superadmin', password: 'admin00').save(flush: true, failOnError: true)
                User organization = new User(username: 'organization', password: 'orga00').save(flush: true, failOnError: true)
                User assistant = new User(username: 'assistant', password: 'asistente00').save(flush: true, failOnError: true)

                new UserRole(user: superAdmin, role: rolSuperAdmin).save(flush: true, failOnError: true)
                new UserRole(user: organization, role: rolOrganization).save(flush: true, failOnError: true)
                new UserRole(user: assistant, role: rolAssistant).save(flush: true, failOnError: true)
            }

            new Requestmap(configAttribute: 'ROLE_SUPERADMIN', url: '/**').save(flush: true, failOnError: true)

            new Requestmap(configAttribute: 'ROLE_MEMBER,ROLE_CALLCENTER', url: '/insurance/**').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'ROLE_MEMBER,ROLE_CALLCENTER', url: '/insuranceConsultation/**').save(flush: true, failOnError: true)

            new Requestmap(configAttribute: 'permitAll', url: '/insuranceConsultation/index').save(flush: true, failOnError: true)

            new Requestmap(configAttribute: 'permitAll', url: '/').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/index').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/index.gsp').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/assets/**').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/**/js/**').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/**/css/**').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/**/images/**').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/**/favicon.ico').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/login').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/login.*').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/login/*').save(flush: true, failOnError: true)

            new Requestmap(configAttribute: 'permitAll', url: '/logout').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/logout.*').save(flush: true, failOnError: true)
            new Requestmap(configAttribute: 'permitAll', url: '/logout/*').save(flush: true, failOnError: true)


        }
    }
}
