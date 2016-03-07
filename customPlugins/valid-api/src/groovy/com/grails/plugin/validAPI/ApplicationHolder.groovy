package com.grails.plugin.validAPI

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.context.ApplicationContext

import javax.servlet.ServletContext
import org.springframework.context.ApplicationContextAware

@Singleton
class ApplicationHolder implements ApplicationContextAware {

    private ApplicationContext ctx

    void setApplicationContext(ApplicationContext applicationContext) {
        println "Setting app context1: " + applicationContext
        ctx = applicationContext
    }

    static ApplicationContext getApplicationContext() {
        getInstance().ctx
    }

    static Object getBean(String name) {
        getApplicationContext().getBean(name)
    }

    static GrailsApplication getGrailsApplication() {
        getBean('grailsApplication')
    }

    static ConfigObject getConfig() {
        getGrailsApplication().config
    }

    static ServletContext getServletContext() {
        getBean('servletContext')
    }
}
