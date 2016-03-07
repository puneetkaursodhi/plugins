package com.grails.plugin.validAPI

class ValidAPIService {
    def grailsApplication

    void createOrUpdateStatistic() {
        Date today = new Date().clearTime()
        ValidAPIStatistics validAPIStatistics = ValidAPIStatistics.findOrCreateWhere(dateCreated: today)
        validAPIStatistics.validApiCallCount++
        validAPIStatistics.save(flush: true,failOnError: true)
    }
}
