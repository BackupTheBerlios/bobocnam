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
 * @version $Revision: 1.1 $, $Date: 2005/01/13 13:41:27 $
 */
public class ServerThread extends Thread {
    //~ Initialisateurs et champs de classe ------------------------------------

    private static final long ONE_SECOND = 1000;

    //~ Champs d'instance ------------------------------------------------------

    private final Log    log    = LogFactory.getLog(getClass());
    private final Server server;

    //~ Constructeurs ----------------------------------------------------------

    public ServerThread(final Server server) {
        this.server = server;
    }

    //~ Méthodes ---------------------------------------------------------------

    public void run() {
        log.info("Démarrage");

        final String warPath = "/bobo.war";
        try {
            final URL warURL = Main.class.getResource(warPath);
            if (warURL == null) {
                throw new IllegalStateException("Ressource WAR introuvable: " +
                    warPath);
            }

            startServer(warURL, 8080);
        } catch (Exception e) {
            log.fatal("Erreur fatale", e);

            final long delay = 20;
            log.info("Fermeture automatique dans " + delay + " secondes");
            try {
                Thread.sleep(delay * ONE_SECOND);
            } catch (Exception e2) {
            }

            System.exit(1);
        }
    }


    private void openBrowser(String url) {
        log.info("Ouverture du navigateur Internet");

        try {
            // sommes-nous dans un environnement JWS ?
            if (System.getProperty("javawebstart.version") != null) {
                log.info("Environnement Java WebStart détecté");

                // utilisation de l'infrastructure JWS pour ouvrir un document HTML
                final BasicService basicService = (BasicService) ServiceManager.lookup(
                        "javax.jnlp.BasicService");
                basicService.showDocument(new URL(url));
            } else {
                // recherche manuelle du navigateur et ouverture du document HTML
                BrowserLauncher.openURL(url);
            }
        } catch (Exception e) {
            log.warn("Erreur lors du lancement du navigateur Internet", e);
        }
    }


    private void startServer(URL warURL, int port) throws Exception {
        log.info("Lancement du serveur Bobo");

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
}
