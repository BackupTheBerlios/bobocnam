/*
 * Copyright (c) 2005, Bobo team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *     * Neither the name of the Bobo project nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package org.eu.bobo.model.dao.hibernate;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import org.dbunit.operation.DatabaseOperation;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.sql.DataSource;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/02/07 15:14:16 $
 */
public abstract class AbstractHibernateDaoTest extends TestCase {
    //~ Champs d'instance ------------------------------------------------------

    private AbstractApplicationContext applicationContext;

    //~ Méthodes ---------------------------------------------------------------

    protected Object getBean(String id) {
        assertNotNull("Identifiant null", id);
        assertNotNull("Contexte Spring non initialisé", applicationContext);
        final Object bean = applicationContext.getBean(id);
        assertNotNull("Aucun bean retourné depuis le contexte Spring", bean);

        return bean;
    }


    protected Object getBeanOfType(Class clazz) {
        assertNotNull("Classe null", clazz);
        assertNotNull("Contexte Spring non initialisé", applicationContext);
        final Map objects = applicationContext.getBeansOfType(clazz);

        if ((objects == null) || objects.isEmpty()) {
            fail("Aucun bean de type " + clazz.getName() +
                " dans le contexte Spring");
        }

        return objects.values().iterator().next();
    }


    protected void setUp() throws Exception {
        super.setUp();

        applicationContext = createApplicationContext();
        assertNotNull("Contexte Spring non initialisé", applicationContext);

        DatabaseOperation.CLEAN_INSERT.execute(openDatabaseConnection(),
            getTestDataSet());
    }


    protected Date createDate(int annee, int mois, int jour, int heure,
        int min, int sec) {
        final Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, annee);
        cal.set(Calendar.MONTH, mois);
        cal.set(Calendar.DATE, jour);
        cal.set(Calendar.HOUR_OF_DAY, heure);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, sec);

        return cal.getTime();
    }


    protected Date createDate(int annee, int mois, int jour) {
        return createDate(annee, mois, jour, 0, 0, 0);
    }


    protected void tearDown() throws Exception {
        applicationContext.close();
        applicationContext = null;

        super.tearDown();
    }


    private static AbstractApplicationContext createApplicationContext() {
        final Log log = LogFactory.getLog(AbstractHibernateDaoTest.class);
        log.info("Initialisation du contexte Spring");

        final String basePath = "/" +
            AbstractHibernateDaoTest.class.getPackage().getName().replace('.',
                '/');
        final String[] contextPath = {
                "/model-hibernate-application-context.xml",
                "/tx-model-hibernate-application-context.xml",
                basePath + "/application-context.xml",
            };

        try {
            return new ClassPathXmlApplicationContext(contextPath);
        } catch (Exception e) {
            log.error("Erreur lors du chargement du contexte Spring", e);
            throw new RuntimeException(e);
        }
    }


    private IDataSet getTestDataSet() throws Exception {
        final InputStream input = getClass().getResourceAsStream("testData.xml");
        assertNotNull("Impossible de charger les données de test", input);

        return new XmlDataSet(input);
    }


    private IDatabaseConnection openDatabaseConnection()
      throws Exception {
        final DataSource dataSource = (DataSource) getBean("dataSource");
        assertNotNull("Source de données non configurée", dataSource);

        final IDatabaseConnection conn = new DatabaseConnection(dataSource.getConnection());
        conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
            new HsqlDataTypeFactory());

        return conn;
    }
}
