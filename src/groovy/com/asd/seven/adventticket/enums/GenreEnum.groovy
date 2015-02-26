package com.asd.seven.adventticket.enums

public enum GenderEnum implements org.springframework.context.MessageSourceResolvable{
    M,
    F

    Object[] getArguments() { [] as Object[] }

    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }

    String getDefaultMessage() { name() }
}
