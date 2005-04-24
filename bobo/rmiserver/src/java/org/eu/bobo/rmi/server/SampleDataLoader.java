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
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:17:44 $
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
