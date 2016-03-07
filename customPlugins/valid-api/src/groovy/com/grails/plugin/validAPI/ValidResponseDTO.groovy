package com.grails.plugin.validAPI

import groovy.util.logging.Log4j

@Log4j
class ValidResponseDTO {
    Boolean isValid
    String status
    List<ValidError> errors
    List<ValidAPISuggestion> suggestions
    String extraInformation

    ValidResponseDTO() {}

    ValidResponseDTO(def response) {
        populateResponse(response)
    }

    void populateResponse(def response) {
        if (isAPIKeyValid(response['response']['status'])) {
            isValid = response['response']['is_valid'] ? Integer.parseInt(response['response']['is_valid']) as Boolean : null
            status = response['response']['status']
            errors = response['response']['errors'].collect {new ValidError(statusCode: StatusCode.findByCode(it['error_code']))}
            suggestions = response['response']['suggestions'].collect { new ValidAPISuggestion(street: it.street, postalCode: it.postalcode, locality: it.locality, gateNumber: it.number)}
            extraInformation = response['response']['interpretation'] ? response['response']['interpretation']['extra'] : ""
        } else {
            log.error "Valid API key is invalid"
            setResponseError(StatusCode.API_NOT_RESPONDING)
        }
    }

    void setResponseError(StatusCode status) {
        this.errors = [new ValidError(statusCode: status)]
        this.status = status.statusCode
        this.suggestions = []
        this.isValid = false
    }

    boolean isAPIKeyValid(def status) {
        return (status != -1)
    }
}
