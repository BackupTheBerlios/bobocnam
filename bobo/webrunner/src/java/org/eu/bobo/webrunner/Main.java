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

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eu.bobo.webrunner.swing.MainFrame;

import com.jgoodies.plaf.LookUtils;
import com.jgoodies.plaf.plastic.PlasticXPLookAndFeel;
import com.jgoodies.plaf.plastic.theme.ExperienceBlue;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/02/23 19:36:47 $
 */
public class Main {
    //~ Méthodes ---------------------------------------------------------------

    public static void main(String[] args) {
        final Log log = LogFactory.getLog(Main.class);
        
        // désactivation du Security Manager :
        // dans un environnement JWS, le gestionnaire de sécurité empêche la
        // bonne exécution du serveur Jetty
        System.setSecurityManager(null);
        
        installLookAndFeel();
        
        final WebServer webServer = new WebServer();

        final JFrame    mainFrame = new MainFrame(webServer);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        webServer.stop();
                    } catch (Exception e) {
                        log.error("Erreur lors de l'arrêt du serveur", e);
                    }
                }
            });
    }
    
    
    private static void installLookAndFeel() {
        try {
            LookUtils.setLookAndTheme(new PlasticXPLookAndFeel(),
                new ExperienceBlue());
        } catch (Exception e) {
            final Log log = LogFactory.getLog(Main.class);
            log.warn("Erreur au chargement du look & feel", e);
        }

        UIManager.put("Panel.background", Color.WHITE);
    }    
}
