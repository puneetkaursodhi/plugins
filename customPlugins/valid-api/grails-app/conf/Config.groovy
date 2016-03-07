println "Executing Valid API Plugin default config"

log4j = {
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }

    error 'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'

    warn 'org.mortbay.log'
    info 'grails.app'
}

validAPI {
    api {
        key = "DIN_API_NYCKEL"
        street.suggest.url = "http://valid.postnummerservice.se/11.45/api/suggest/street/?api_key=${key}&response_format=json"
        postalcode.suggest.url = "http://valid.postnummerservice.se/11.45/api/suggest/postalcode/?api_key=${key}&response_format=json"
        locality.suggest.url = "http://valid.postnummerservice.se/11.45/api/suggest/locality/?api_key=${key}&response_format=json"
        verify.url = "http://valid.postnummerservice.se/11.45/api/validate/?api_key=${key}&response_format=json"
    }
}
