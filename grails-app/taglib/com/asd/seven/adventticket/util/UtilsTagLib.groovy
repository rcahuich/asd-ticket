package com.asd.seven.adventticket.util

class UtilsTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def utilsService

    def username = {
        out << utilsService.getUsername()
    }

}
