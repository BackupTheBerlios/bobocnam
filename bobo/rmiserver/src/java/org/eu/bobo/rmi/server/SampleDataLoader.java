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


package org.eu.bobo.rmi.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import org.dbunit.operation.DatabaseOperation;

import org.eu.bobo.model.dao.hibernate.HsqlDataTypeFactory;

import java.io.InputStream;

import java.sql.Connection;

import javax.sql.DataSource;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/02/14 22:40:59 $
 */
public class SampleDataLoader {
    //~ Champs d'instance ------------------------------------------------------

    private final DataSource dataSource;
    private final Log        log        = LogFactory.getLog(getClass());
    private final String     dataSetUrl;

    //~ Constructeurs ----------------------------------------------------------

    public SampleDataLoader(final DataSource dataSource, final String dataSetUrl) {
        if (dataSource == null) {
            throw new IllegalArgumentException("dataSource est requis");
        }
        if (dataSetUrl == null) {
            throw new IllegalArgumentException("dataSet est requis");
        }
        this.dataSource     = dataSource;
        this.dataSetUrl     = dataSetUrl;
    }

    //~ Méthodes ---------------------------------------------------------------

    public void load() throws Exception {
        log.info("Chargement des données d'exemple...");

        Connection  conn        = null;
        InputStream inputStream = null;
        try {
            // configuration de la connexion à la base de données
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            final IDatabaseConnection dbConn = new DatabaseConnection(conn);
            dbConn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new HsqlDataTypeFactory());

            // chargement des données
            inputStream = getClass().getResourceAsStream(dataSetUrl);
            final IDataSet dataSet = new XmlDataSet(inputStream);
            DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);

            // validation de la transaction si tout s'est bien passé
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception ignore) {
                }
            }

            // dans tous les cas, il est important de fermer toute connexion ouverte
            if (conn != null) {
                conn.close();
            }
        }

        log.info("Chargement des données d'exemple terminé");
    }
}
