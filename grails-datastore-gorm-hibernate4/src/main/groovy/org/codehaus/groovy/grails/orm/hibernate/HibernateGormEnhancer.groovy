/* Copyright (C) 2011 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.grails.orm.hibernate

import groovy.transform.CompileStatic

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.grails.datastore.gorm.GormInstanceApi
import org.grails.datastore.gorm.GormStaticApi
import org.grails.datastore.gorm.GormValidationApi
import org.springframework.transaction.PlatformTransactionManager

/**
 * Extended GORM Enhancer that fills out the remaining GORM for Hibernate methods
 * and implements string-based query support via HQL.
 *
 * @author Graeme Rocher
 * @since 1.0
 */
@CompileStatic
class HibernateGormEnhancer extends AbstractHibernateGormEnhancer {

    HibernateGormEnhancer(HibernateDatastore datastore, PlatformTransactionManager transactionManager, GrailsApplication grailsApplication) {
        super(datastore, transactionManager, grailsApplication)
    }

    protected <D> GormValidationApi<D> getValidationApi(Class<D> cls) {
        new HibernateGormValidationApi<D>(cls, (HibernateDatastore)datastore, classLoader)
    }

    @Override
    protected <D> GormStaticApi<D> getStaticApi(Class<D> cls) {
        new HibernateGormStaticApi<D>(cls, (HibernateDatastore)datastore, getFinders(), classLoader, transactionManager)
    }

    @Override
    protected <D> GormInstanceApi<D> getInstanceApi(Class<D> cls) {
        new HibernateGormInstanceApi<D>(cls, (HibernateDatastore)datastore, classLoader)
    }
}
