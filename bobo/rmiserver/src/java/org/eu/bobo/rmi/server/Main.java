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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/02/14 19:25:21 $
 */
public class Main {
    //~ Méthodes ---------------------------------------------------------------

    public static void main(String[] args) {
        final Log       log = LogFactory.getLog(Main.class);

        final RmiServer rmiServer = new RmiServer();
        Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        rmiServer.stop();
                    } catch (Exception e) {
                        log.error("Erreur lors de l'arrêt du serveur", e);
                    }
                }
            });

        log.info("Démarrage du serveur");
        try {
            rmiServer.start();
        } catch (Exception e) {
            log.fatal("Erreur lors du démarrage du serveur", e);
            System.exit(1);
        }

        // construction d'une interface graphique
        final JFrame frame = new JFrame("Bobo RMI Server");
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Serveur RMI opérationnel"), BorderLayout.NORTH);
        panel.add(new JButton(new ExitAction(rmiServer)), BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }

    //~ Classes ----------------------------------------------------------------

    private static class ExitAction extends AbstractAction {
        //~ Champs d'instance --------------------------------------------------

        private final Log       log       = LogFactory.getLog(getClass());
        private final RmiServer rmiServer;

        //~ Constructeurs ------------------------------------------------------

        public ExitAction(final RmiServer rmiServer) {
            super("Quitter");
            this.rmiServer = rmiServer;
        }

        //~ Méthodes -----------------------------------------------------------

        public void actionPerformed(ActionEvent evt) {
            try {
                rmiServer.stop();
            } catch (Exception e) {
                log.error("Erreur lors de l'arrêt du serveur", e);
                System.exit(1);
            }

            System.exit(0);
        }
    }
}
