package com.grails.plugin.validAPI

class ValidAPIStatistics {
    Date dateCreated
    Date lastUpdated
    Integer validApiCallCount = 0

    static mapping = {
        dateCreated type: 'date'
    }
}
