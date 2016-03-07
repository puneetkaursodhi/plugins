package com.grails.plugin.commonFeatures

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.context.ApplicationContext
import org.springframework.orm.hibernate3.HibernateCallback
import org.hibernate.criterion.Projections
import org.hibernate.criterion.ProjectionList
import org.hibernate.Criteria
import org.hibernate.Session
import org.springframework.orm.hibernate3.HibernateTemplate
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class DomainClassEnhancer {
    GrailsApplication application
    ApplicationContext applicationContext

    DomainClassEnhancer(GrailsApplication application, ApplicationContext applicationContext) {
        this.application = application
        this.applicationContext = applicationContext
    }

    public void injectMetaMethods() {
        def sessionFactory = application.mainContext.getBean('sessionFactory')
        application.domainClasses.each {DefaultGrailsDomainClass gc ->
            injectStaticMethods(gc, sessionFactory)
        }
    }

    private void injectStaticMethods(DefaultGrailsDomainClass dc, sessionFactory) {
        dc.clazz.metaClass.'static'.getAllFields = {List fields -> getAllFields(dc, sessionFactory, fields)}
        dc.clazz.metaClass.'static'.getAllIdentifiers = {-> getAllFields(dc, sessionFactory, [dc.identifier.name])}
    }

    private List getAllFields(DefaultGrailsDomainClass dc, sessionFactory, List fields) {
        def hibernateTemplate = new HibernateTemplate(sessionFactory)
        hibernateTemplate.execute({Session session ->
            Criteria criteria = session.createCriteria(dc.clazz)
            ProjectionList proList = Projections.projectionList();
            fields.each {String field -> proList.add(Projections.property(field));}
            criteria.setProjection(proList);
            return criteria.list();
        } as HibernateCallback)
    }
}
