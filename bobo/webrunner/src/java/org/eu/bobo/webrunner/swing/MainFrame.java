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


package org.eu.bobo.webrunner.swing;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.webrunner.WebServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.text.NumberFormat;

import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:19:42 $
 */
public class MainFrame extends JFrame {
    //~ Initialisateurs et champs de classe ------------------------------------

    private static final String DEFAULT_RMI_HOST          = "localhost";
    private static final int    DEFAULT_RMI_REGISTRY_PORT = 10100;
    private static final int    DEFAULT_RMI_SERVICE_PORT  = 10101;
    private static final int    DEFAULT_WEB_PORT          = 80;

    //~ Champs d'instance ------------------------------------------------------

    private final Log             log             = LogFactory.getLog(getClass());
    private final WebServer       webServer;
    private Action                startStopAction;
    private InfiniteProgressPanel progressPanel;
    private JButton               startStopButton;
    private JFormattedTextField   rmiRegistryPost;
    private JFormattedTextField   rmiServicePort;
    private JFormattedTextField   webPort;
    private JTextField            rmiHost;

    //~ Constructeurs ----------------------------------------------------------

    public MainFrame(final WebServer webServer) {
        super("Bobo WebRunner");
        this.webServer = webServer;
        init();
    }

    //~ M�thodes ---------------------------------------------------------------

    private void setSystemProperties() {
        System.setProperty("rmi.host", rmiHost.getText());
        System.setProperty("rmi.registry.port",
            rmiRegistryPost.getValue().toString());
        System.setProperty("rmi.service.port",
            rmiServicePort.getValue().toString());
    }


    private void buildUI() {
        setIconImage(new ImageIcon(getClass().getResource("/images/browser.png")).getImage());

        final JPanel logoPanel   = createLogoPanel();
        final JPanel mainPanel   = createMainPanel();
        final JPanel buttonPanel = createButtonPanel();

        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(Borders.DIALOG_BORDER);
        panel.add(logoPanel, BorderLayout.NORTH);
        panel.add(mainPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);

        progressPanel = new InfiniteProgressPanel();
        setGlassPane(progressPanel);
    }


    private JPanel createButtonPanel() {
        startStopAction     = new StartStopAction();
        startStopButton     = new JButton(startStopAction);
        startStopButton.setDefaultCapable(true);
        getRootPane().setDefaultButton(startStopButton);

        final JLabel versionLabel = new JLabel();
        versionLabel.setFont(new Font("Lucida", Font.PLAIN, 10));
        versionLabel.setForeground(Color.GRAY);
        final String versionFile = "/webrunner-build.properties";
        try {
            final Properties props = new Properties();
            props.load(getClass().getResourceAsStream(versionFile));

            final String version = props.getProperty("build.version") + " " +
                props.getProperty("build.number");
            versionLabel.setText(version);
        } catch (Exception e) {
            log.warn("Erreur lors du chargement du fichier " + versionFile, e);
        }

        final JPanel buttonPanel = ButtonBarFactory.buildOKBar(startStopButton);

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(versionLabel, BorderLayout.WEST);
        panel.setBorder(Borders.BUTTON_BAR_GAP_BORDER);

        return panel;
    }


    private JPanel createLogoPanel() {
        final Icon   icon = new ImageIcon(getClass().getResource("/images/logo.png"));

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(icon, SwingConstants.LEFT), BorderLayout.WEST);

        return panel;
    }


    private JPanel createMainPanel() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Registre RMI", createRmiForm());
        tabbedPane.addTab("Serveur web", createWebForm());

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);

        return panel;
    }


    private JPanel createRmiForm() {
        rmiHost = new JTextField(DEFAULT_RMI_HOST);
        final NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        rmiRegistryPost = new JFormattedTextField(numberFormat);
        rmiRegistryPost.setValue(new Integer(DEFAULT_RMI_REGISTRY_PORT));
        rmiServicePort = new JFormattedTextField(numberFormat);
        rmiServicePort.setValue(new Integer(DEFAULT_RMI_SERVICE_PORT));

        final FormLayout         formLayout = new FormLayout("right:max(40dlu;pref), 3dlu, default:grow",
                "");
        final DefaultFormBuilder formBuilder = new DefaultFormBuilder(formLayout);
        formBuilder.setDefaultDialogBorder();

        formBuilder.append("Nom d'h�te", rmiHost);
        formBuilder.nextLine();
        formBuilder.append("Port registre", rmiRegistryPost);
        formBuilder.nextLine();
        formBuilder.append("Port service", rmiServicePort);
        formBuilder.nextLine();

        return formBuilder.getPanel();
    }


    private JPanel createWebForm() {
        final NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        webPort = new JFormattedTextField(numberFormat);
        webPort.setValue(new Integer(DEFAULT_WEB_PORT));

        final FormLayout         formLayout = new FormLayout("right:max(40dlu;pref), 3dlu, default:grow",
                "");
        final DefaultFormBuilder formBuilder = new DefaultFormBuilder(formLayout);
        formBuilder.setDefaultDialogBorder();

        formBuilder.append("Port", webPort);
        formBuilder.nextLine();

        return formBuilder.getPanel();
    }


    private void doStart() {
        doStartWaitingProgress();

        final int port = ((Number) webPort.getValue()).intValue();

        try {
            webServer.start(port);
        } catch (Exception e) {
            log.error("Erreur lors du d�marrage du serveur", e);
            showError();
        }

        doStopWaitingProgress();
    }


    private void doStartWaitingProgress() {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    getGlassPane().setVisible(true);
                    progressPanel.start();
                }
            });
    }


    private void doStop() {
        doStartWaitingProgress();

        try {
            webServer.stop();
        } catch (Exception e) {
            log.error("Erreur lors de l'arr�t du serveur", e);
            showError();
        }

        doStopWaitingProgress();
    }


    private void doStopWaitingProgress() {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    progressPanel.stop();
                    getGlassPane().setVisible(false);
                }
            });
    }


    private void init() {
        buildUI();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }


    private void showError() {
        showError(
            "Une erreur s'est produite : consultez les logs pour plus d'informations.");
    }

    //~ Classes ----------------------------------------------------------------

    private class StartStopAction extends AbstractAction {
        //~ Champs d'instance --------------------------------------------------

        private final String startLabel = "D�marrer";
        private final String stopLabel = "Arr�ter";

        //~ Constructeurs ------------------------------------------------------

        public StartStopAction() {
            super();

            putValue(Action.NAME, startLabel);
            putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        }

        //~ M�thodes -----------------------------------------------------------

        public void actionPerformed(ActionEvent e) {
            final int    start = 0;
            final int    stop = 1;

            final int    action;
            final String label = (String) getValue(Action.NAME);
            if (startLabel.equals(label)) {
                action = start;
            } else if (stopLabel.equals(label)) {
                action = stop;
            } else {
                throw new IllegalStateException("Label inconnu: " + label);
            }

            putValue(Action.NAME, (action == start) ? stopLabel
                                                    : startLabel);

            if (action == start) {
                setSystemProperties();

                final Thread thread = new Thread() {
                        public void run() {
                            doStart();
                        }
                    };
                thread.start();
            } else {
                final Thread thread = new Thread() {
                        public void run() {
                            doStop();
                        }
                    };
                thread.start();
            }
        }
    }
}
