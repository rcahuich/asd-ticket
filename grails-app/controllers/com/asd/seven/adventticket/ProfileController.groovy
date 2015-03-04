package com.asd.seven.adventticket

import com.asd.seven.adventticket.security.User
import grails.plugin.springsecurity.annotation.Secured

class ProfileController {

    def springSecurityService

    @Secured(['ROLE_ASSISTANT'])
    def index() {
        User user = springSecurityService.currentUser
        [user: user]
    }



}
