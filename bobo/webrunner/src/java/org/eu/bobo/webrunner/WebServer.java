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


package org.eu.bobo.webrunner;

import edu.stanford.ejalbert.BrowserLauncher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mortbay.http.SocketListener;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.WebApplicationContext;

import java.net.URL;

import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:19:42 $
 */
public class WebServer {
    //~ Champs d'instance ------------------------------------------------------

    private final Log log    = LogFactory.getLog(getClass());
    private Server    server;

    //~ Méthodes ---------------------------------------------------------------

    public void start(int port) {
        log.info("Démarrage du serveur web");

        try {
            doStart(port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void stop() {
        log.info("Arrêt du serveur web");

        try {
            doStop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void doStart(int port) throws Exception {
        final String warPath = "/bobo.war";
        final URL    warURL = getClass().getResource(warPath);
        if (warURL == null) {
            throw new IllegalStateException("Ressource WAR introuvable: " +
                warPath);
        }

        server = new Server();

        final SocketListener socketListener = new SocketListener();
        socketListener.setPort(port);
        server.addListener(socketListener);

        final WebApplicationContext webAppContext = server.addWebApplication("/",
                warURL.toString());

        // on choisit d'extraire le contenu du .war car le compilateur
        // JSP Jasper rencontrera autrement des problèmes
        webAppContext.setExtractWAR(true);

        server.start();

        log.info("Serveur Bobo en écoute sur le port " + port);

        openBrowser("http://localhost:" + port + "/");
    }


    private void doStop() throws Exception {
        if (server != null) {
            server.stop();
            server.destroy();
            server = null;
        }
    }


    private void openBrowser(String url) {
        log.info("Ouverture du navigateur Internet");

        try {
            // sommes-nous dans un environnement JWS ?
            if (System.getProperty("javawebstart.version") != null) {
                log.info("Environnement Java WebStart détecté");

                // utilisation de l'infrastructure JWS pour ouvrir une URL
                final BasicService basicService = (BasicService) ServiceManager.lookup(
                        "javax.jnlp.BasicService");
                basicService.showDocument(new URL(url));
            } else {
                // recherche manuelle du navigateur et ouverture de l'URL
                BrowserLauncher.openURL(url);
            }
        } catch (Exception e) {
            log.warn("Erreur lors du lancement du navigateur Internet", e);
        }
    }
}
