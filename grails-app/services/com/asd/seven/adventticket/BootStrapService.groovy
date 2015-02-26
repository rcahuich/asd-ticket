package com.asd.seven.adventticket

import com.asd.seven.adventticket.enums.GenderEnum
import com.asd.seven.adventticket.security.Role
import com.asd.seven.adventticket.security.User
import com.asd.seven.adventticket.security.UserRole
import grails.transaction.Transactional

@Transactional
class BootStrapService {

    def insertData() {
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
                User superAdmin = new User(username: 'superadmin', password: 'admin00', nameFull: "Super Administrador", email: "rcc@um.edu.mx", birthday: new Date().parse("dd/MM/yyyy", "24/03/1991"), gender: GenderEnum.F).save(flush: true, failOnError: true)
                User organization = new User(username: 'organization', password: 'orga00', nameFull: "Costa Organizador", email: "rcc@um.edu.mx", birthday: new Date().parse("dd/MM/yyyy", "07/10/1988"), gender: GenderEnum.M).save(flush: true, failOnError: true)
                User assistant = new User(username: 'assistant', password: 'asistente00', nameFull: "Julio Asistente", email: "rcc@um.edu.mx", birthday: new Date().parse("dd/MM/yyyy", "13/05/1985"), gender: GenderEnum.M).save(flush: true, failOnError: true)

                new UserRole(user: superAdmin, role: rolSuperAdmin).save(flush: true, failOnError: true)
                new UserRole(user: organization, role: rolOrganization).save(flush: true, failOnError: true)
                new UserRole(user: assistant, role: rolAssistant).save(flush: true, failOnError: true)
            }

        }
    }
}
