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


package org.eu.bobo.web.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.XmlDataSet;

import org.dbunit.operation.DatabaseOperation;

import org.springframework.context.ApplicationContext;

import org.springframework.core.io.Resource;

import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.Types;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.sql.DataSource;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/02/14 16:29:55 $
 */
public class SampleDataLoaderListener implements ServletContextListener {
    //~ Initialisateurs et champs de classe ------------------------------------

    public static final String DATA_SOURCE_BEAN_ID_DEFAULT_VALUE = "dataSource";
    public static final String SAMPLE_DATA_URL_DEFAULT_VALUE = "classpath:/sample-data.xml";

    //~ Champs d'instance ------------------------------------------------------

    private final Log log              = LogFactory.getLog(getClass());
    private String    dataSourceBeanId = DATA_SOURCE_BEAN_ID_DEFAULT_VALUE;
    private String    sampleDataUrl    = SAMPLE_DATA_URL_DEFAULT_VALUE;

    //~ M�thodes ---------------------------------------------------------------

    public void contextDestroyed(ServletContextEvent evt) {
    }


    public void contextInitialized(ServletContextEvent evt) {
        final ServletContext servletContext = evt.getServletContext();
        dataSourceBeanId     = getContextParameter(servletContext,
                "dataSourceBeanId", DATA_SOURCE_BEAN_ID_DEFAULT_VALUE);
        sampleDataUrl = getContextParameter(servletContext,
                "sampleDataSourceUrl", SAMPLE_DATA_URL_DEFAULT_VALUE);

        final ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        final DataSource         dataSource = getDataSource(applicationContext);
        if (dataSource == null) {
            log.info(
                "Aucune source de donn�es disponible: aucun chargement de donn�es n'est effectu�");

            return;
        }

        final Resource sampleDataResource = applicationContext.getResource(sampleDataUrl);
        if (!sampleDataResource.exists()) {
            if (log.isWarnEnabled()) {
                log.warn("Le fichier de donn�es (" + sampleDataUrl +
                    ") est introuvable: aucune donn�e d'exemple n'est charg�e");

                return;
            }
        }

        if (log.isInfoEnabled()) {
            log.info("Chargement des donn�es d'exemple...");
        }

        try {
            loadSampleData(dataSource,
                applicationContext.getResource(sampleDataUrl).getInputStream());

            if (log.isInfoEnabled()) {
                log.info("Chargement termin�");
            }
        } catch (Exception e) {
            log.error("Erreur lors du chargement des donn�es d'exemple", e);
        }
    }


    private String getContextParameter(ServletContext servletContext,
        String parameter, String defaultValue) {
        final String value = servletContext.getInitParameter(parameter);

        return (value == null) ? defaultValue
                               : value;
    }


    private DataSource getDataSource(ApplicationContext applicationContext) {
        DataSource dataSource = null;

        log.info("Connexion � la source de donn�es par RMI");
        try {
            dataSource = (DataSource) applicationContext.getBean("rmiDataSource",
                    DataSource.class);
            if (dataSource == null) {
                log.warn(
                    "Aucun source de donn�es export�e dans le registre RMI");
            }
        } catch (Exception e) {
            log.error("Erreur lors de la r�cup�ration par RMI de la source de donn�es",
                e);
        }

        return dataSource;
    }


    private String getMessage(ApplicationContext applicationContext, String code) {
        return applicationContext.getMessage(code, null, Locale.getDefault());
    }


    private void loadSampleData(DataSource dataSource, InputStream inputStream)
      throws Exception {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            final IDataSet            dataSet = new XmlDataSet(inputStream);
            final IDatabaseConnection dbConn = new DatabaseConnection(conn);
            dbConn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new InternalDataTypeFactory());

            DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);

            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //~ Classes ----------------------------------------------------------------

    private class InternalDataTypeFactory extends DefaultDataTypeFactory {
        //~ M�thodes -----------------------------------------------------------

        public DataType createDataType(int sqlType, String sqlTypeName)
          throws DataTypeException {
            if (Types.BOOLEAN == sqlType) {
                return createDataType(Types.INTEGER, sqlTypeName);
            }

            return super.createDataType(sqlType, sqlTypeName);
        }
    }
}
