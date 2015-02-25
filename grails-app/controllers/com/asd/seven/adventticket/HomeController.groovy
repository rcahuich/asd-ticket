package com.asd.seven.adventticket

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ASSISTANT'])
class HomeController {

    def index() {}

}
