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

import com.jgoodies.plaf.LookUtils;
import com.jgoodies.plaf.plastic.PlasticXPLookAndFeel;
import com.jgoodies.plaf.plastic.theme.ExperienceBlue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.webrunner.swing.MainFrame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:19:42 $
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
