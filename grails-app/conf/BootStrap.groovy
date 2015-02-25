class BootStrap {

    def bootStrapService

    def init = { servletContext ->

        bootStrapService.insertData()

    }
    def destroy = {
    }
}
