package com.asd.seven.adventticket

import com.asd.seven.adventticket.security.User
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ASSISTANT'])
class SettingsProfileController {

    def springSecurityService

    def index() {
        User user = springSecurityService.currentUser
        [user: user]
    }



}
