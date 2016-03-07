package com.grails.plugin.validAPI

import grails.validation.Validateable

import groovy.util.logging.Log4j
import groovy.json.JsonSlurper
import groovy.json.JsonException

@Validateable
@Log4j
class ValidAPIAddressVerifyCO {

    String street
    String zipCode
    String city

    ValidResponseDTO getResponse() {
        ValidResponseDTO responseDTO = new ValidResponseDTO()
        try {
            def response = new JsonSlurper().parseText(ValidRestCallHelper.getJSONFromUrl(verificationURL))
            validAPIService.createOrUpdateStatistic()
            responseDTO.populateResponse(response)
        } catch (JsonException e) {
            log.error("Encountered exception when parsing response from Valid API: ${e}: ${e.message}")
            responseDTO.setResponseError(StatusCode.API_NOT_RESPONDING)
        }
        return responseDTO
    }

    String getVerificationURL() {
        String url = ApplicationHolder.grailsApplication.config.validAPI.api.verify.url
        url += "&street=${street.encodeAsURL()}"
        url += "&postalcode=${zipCode.encodeAsURL()}"
        url += "&locality=${city.encodeAsURL()}"
        url += "&debug=true&suggestions=1"
        log.error "Accessing url ${url}"
        return url
    }

    def getValidAPIService() {
        return ApplicationHolder.getBean('validAPIService')
    }

}
