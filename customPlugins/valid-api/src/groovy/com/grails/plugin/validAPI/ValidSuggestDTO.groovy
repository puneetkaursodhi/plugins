package com.grails.plugin.validAPI

import com.grails.plugin.validAPI.ValidAPIException
import groovy.json.JsonSlurper

class ValidSuggestDTO {

    public static final String STREET_SUGGEST = "street"
    public static final String POSTAL_CODE_SUGGEST = "postalcode"
    public static final String LOCALITY_CODE_SUGGEST = "locality"

    String query = ''
    List columns = [STREET_SUGGEST]
    String suggestField


    String getSuggestUrl() {
        String url = ""
        if (query) {
            if (suggestField) {
                url = ApplicationHolder.grailsApplication.config.validAPI.api."${suggestField}".suggest.url
                url += "&q=${query.encodeAsURL()}&cols=${columns.join(",")}"
            } else {
                throw new ValidAPIException("Suggest field is missing. Should be either street, postalcode or locality")
            }
        } else {
            throw new ValidAPIException("Query term is missing.")
        }
        return url
    }

    ValidResponseDTO getResponseFromValid() {
        def response =  (new JsonSlurper().parseText(ValidRestCallHelper.getJSONFromUrl(suggestUrl)))
        return new ValidResponseDTO(response)
    }

    List getStreetSuggestions() {
        extractSuggestions()
    }

    List getPostalCodeSuggestions() {
        extractSuggestions()
    }

    List getLocalitySuggestions() {
        extractSuggestions()
    }

    List<ValidAPISuggestion> extractSuggestions() {
        return responseFromValid.suggestions
    }
}
