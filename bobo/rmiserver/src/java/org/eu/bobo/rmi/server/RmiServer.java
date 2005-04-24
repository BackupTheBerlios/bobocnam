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

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:17:44 $
 */
public final class RmiServer {
    //~ Champs d'instance ------------------------------------------------------

    private AbstractApplicationContext applicationContext;
    private final Log                  log = LogFactory.getLog(getClass());

    //~ Méthodes ---------------------------------------------------------------

    public void loadSampleData() {
        log.info("Chargement du jeu de données prédéfini");

        try {
            final SampleDataLoader sampleDataLoader = (SampleDataLoader) applicationContext.getBean("sampleDataLoader",
                    SampleDataLoader.class);
            sampleDataLoader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void start() {
        log.info("Démarrage du serveur RMI");

        try {
            doStart();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void stop() {
        log.info("Arrêt du serveur RMI");

        try {
            doStop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void doStart() throws Exception {
        final String[] contextPath = {
                "/application-context.xml",
                "/tx-model-hibernate-application-context.xml",
                "/model-hibernate-application-context.xml",
                "/rmi-server-application-context.xml",
            };
        applicationContext = new ClassPathXmlApplicationContext(contextPath);
    }


    private void doStop() throws Exception {
        if (applicationContext == null) {
            return;
        }

        applicationContext.close();
        applicationContext = null;
    }
}
