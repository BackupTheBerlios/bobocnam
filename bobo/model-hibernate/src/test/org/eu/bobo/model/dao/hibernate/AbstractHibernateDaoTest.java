/*                                                   *\
 * Copyright © 2004 Centre Informatique Multimédia   *
 *                                                   *
 * $Id: AbstractHibernateDaoTest.java,v 1.1 2005/01/13 13:38:06 romale Exp $
\*                                                   */


package org.eu.bobo.model.dao.hibernate;

import java.io.InputStream;

import javax.sql.DataSource;

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


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/13 13:38:06 $
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


    protected void setUp() throws Exception {
        super.setUp();

        applicationContext = createApplicationContext();
        assertNotNull("Contexte Spring non initialisé", applicationContext);

        DatabaseOperation.CLEAN_INSERT.execute(openDatabaseConnection(),
            getTestDataSet());
    }


    protected void tearDown() throws Exception {
        applicationContext.close();
        applicationContext = null;

        super.tearDown();
    }


    private static AbstractApplicationContext createApplicationContext() {
        final Log log = LogFactory.getLog(AbstractHibernateDaoTest.class);
        log.info("Initialisation du contexte Spring");

        final String path = "/" +
            AbstractHibernateDaoTest.class.getPackage().getName().replace('.',
                '/') + "/application-context.xml";

        try {
            return new ClassPathXmlApplicationContext(path);
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
