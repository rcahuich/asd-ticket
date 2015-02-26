package com.asd.seven.adventticket

import grails.converters.JSON

class EnrollmentController {

    def enrollmentService

    def index() {}

    def saveEnroll() {

        try {
            enrollmentService.createUser(params)
            render ([username:params.username] as JSON)
        } catch (Exception e){
            response.status = 422
            render (e.getMessage())
        }
    }
}
