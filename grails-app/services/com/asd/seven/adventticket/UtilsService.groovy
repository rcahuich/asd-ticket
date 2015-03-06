package com.asd.seven.adventticket

import grails.transaction.Transactional

@Transactional
class UtilsService {

    def springSecurityService

    def getUsername() {
        def username = ''

        if (springSecurityService.isLoggedIn()) {
                username = springSecurityService.authentication.name.encodeAsHTML()
        }

        return username
    }

}
