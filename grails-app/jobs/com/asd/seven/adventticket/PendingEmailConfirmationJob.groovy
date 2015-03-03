package com.asd.seven.adventticket

class PendingEmailConfirmationJob {

    def cronExpression = "0 14 3 * * ? *"  // Once every twenty four hours at 3am
    def enrollmentService

    def execute() {
        if (log.infoEnabled) {
            log.info( "Eliminando correos de confirmacion de registro")
        }
        try {
            enrollmentService.cullStaleConfirmations()
        } catch (Throwable t) {
            log.error( "Inhabilitado la ejecucion de muerte de correos de confirmacion de registro", t)
        }
    }
}
