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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mortbay.jetty.Server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/13 13:41:27 $
 */
public class ConsoleFrame extends JFrame {
    //~ Champs d'instance ------------------------------------------------------

    private final Log    log    = LogFactory.getLog(getClass());
    private final Server server;

    //~ Constructeurs ----------------------------------------------------------

    public ConsoleFrame(final Server server) {
        super();
        this.server = server;
        init();
        buildUI();
    }

    //~ Méthodes ---------------------------------------------------------------

    private void buildUI() {
        final JButton exitButton = new JButton("Cliquez ici pour quitter");
        exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    exit();
                }
            });

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(exitButton, BorderLayout.CENTER);
        setContentPane(panel);
    }


    private void exit() {
        try {
            server.stop(true);
            System.exit(0);
        } catch (Exception e) {
            log.warn("Erreur lors de l'arrêt du serveur", e);
        }
    }


    private void init() {
        setTitle("Bobo Console");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
