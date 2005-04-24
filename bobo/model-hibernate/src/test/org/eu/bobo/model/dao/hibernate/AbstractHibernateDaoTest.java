/*
 * Copyright (c) 2005, Bobo team
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
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
 * @version $Revision: 1.5 $, $Date: 2005/04/24 22:17:00 $
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
