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
 * @version $Revision: 1.1 $, $Date: 2005/02/23 19:34:36 $
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
