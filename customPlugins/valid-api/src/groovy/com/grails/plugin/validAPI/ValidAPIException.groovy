package com.grails.plugin.validAPI

class ValidAPIException extends Exception {
    ValidAPIException() {}

    ValidAPIException(String message) {
        super(message)
    }
}
