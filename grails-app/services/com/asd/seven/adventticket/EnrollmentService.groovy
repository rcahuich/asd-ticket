package com.asd.seven.adventticket

import com.asd.seven.adventticket.enums.GenderEnum
import com.asd.seven.adventticket.security.User
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationServiceException

@Transactional
class EnrollmentService {

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

        user.validate()
        if(user.hasErrors()) {
            log.error(user.errors)
            user.discard()
            throw new AuthenticationServiceException("Ocurrio un error al momento de guardar los datos. Intentelo mas tarde.");
        }

        user.save(flush: true);
    }

}
