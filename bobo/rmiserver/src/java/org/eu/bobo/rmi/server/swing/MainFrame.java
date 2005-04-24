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


package org.eu.bobo.rmi.server.swing;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.rmi.server.RmiServer;

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
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
 * @version $Revision: 1.9 $, $Date: 2005/04/24 22:17:44 $
 */
public class MainFrame extends JFrame {
    //~ Initialisateurs et champs de classe ------------------------------------

    private static final String[] DEFAULT_JDBC_URL_VALUES = {
            "jdbc:hsqldb:mem:bobo", "jdbc:postgresql://localhost/bobo",
            "jdbc:mysql://localhost/bobo", "jdbc:oracle:thin:@localhost/bobo"
        };
    private static final String[] DEFAULT_JDBC_DRIVER_VALUES = {
            "org.hsqldb.jdbcDriver", "org.postgresql.Driver",
            "com.mysql.jdbc.Driver", "oracle.jdbc.OracleDriver"
        };
    private static final String[] DEFAULT_JDBC_HIBERNATE_DIALECT_VALUES = {
            "net.sf.hibernate.dialect.HSQLDialect",
            "net.sf.hibernate.dialect.PostgreSQLDialect",
            "net.sf.hibernate.dialect.MySQLDialect",
            "net.sf.hibernate.dialect.Oracle9Dialect"
        };
    private static final String DEFAULT_JDBC_USERNAME     = "sa";
    private static final String DEFAULT_JDBC_PASSWORD     = "";
    private static final int    DEFAULT_RMI_REGISTRY_PORT = 10100;
    private static final int    DEFAULT_RMI_SERVICE_PORT  = 10101;

    //~ Champs d'instance ------------------------------------------------------

    private final Log             log                  = LogFactory.getLog(getClass());
    private final RmiServer       rmiServer;
    private Action                loadSampleDataAction;
    private Action                startStopAction;
    private InfiniteProgressPanel progressPanel;
    private JButton               startStopButton;
    private JComboBox             jdbcDriver;
    private JComboBox             jdbcHibernateDialect;
    private JComboBox             jdbcUrl;
    private JFormattedTextField   rmiRegistryPost;
    private JFormattedTextField   rmiServicePort;
    private JTextField            jdbcPassword;
    private JTextField            jdbcUsername;

    //~ Constructeurs ----------------------------------------------------------

    public MainFrame(final RmiServer rmiServer) {
        super("Bobo RmiServer");
        this.rmiServer = rmiServer;
        init();
    }

    //~ Méthodes ---------------------------------------------------------------

    private void setSystemProperties() {
        System.setProperty("jdbc.url", jdbcUrl.getSelectedItem().toString());
        System.setProperty("jdbc.driver",
            jdbcDriver.getSelectedItem().toString());
        System.setProperty("jdbc.hibernate.dialect",
            jdbcHibernateDialect.getSelectedItem().toString());
        System.setProperty("jdbc.username", jdbcUsername.getText());
        System.setProperty("jdbc.password", jdbcPassword.getText());

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

        loadSampleDataAction = new LoadSampleDataAction();
        loadSampleDataAction.setEnabled(false);

        final JLabel versionLabel = new JLabel();
        versionLabel.setFont(new Font("Lucida", Font.PLAIN, 10));
        versionLabel.setForeground(Color.GRAY);
        final String versionFile = "/rmiserver-build.properties";
        try {
            final Properties props = new Properties();
            props.load(getClass().getResourceAsStream(versionFile));

            final String version = props.getProperty("build.version") + " " +
                props.getProperty("build.number");
            versionLabel.setText(version);
        } catch (Exception e) {
            log.warn("Erreur lors du chargement du fichier " + versionFile, e);
        }

        final JPanel buttonPanel = ButtonBarFactory.buildOKHelpBar(startStopButton,
                new JButton(loadSampleDataAction));

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(versionLabel, BorderLayout.WEST);
        panel.setBorder(Borders.BUTTON_BAR_GAP_BORDER);

        return panel;
    }


    private JPanel createJdbcForm() {
        jdbcUrl = new JComboBox(DEFAULT_JDBC_URL_VALUES);
        jdbcUrl.setEditable(true);
        jdbcDriver = new JComboBox(DEFAULT_JDBC_DRIVER_VALUES);
        jdbcDriver.setEditable(true);
        jdbcHibernateDialect = new JComboBox(DEFAULT_JDBC_HIBERNATE_DIALECT_VALUES);
        jdbcHibernateDialect.setEditable(true);
        jdbcUsername     = new JTextField(DEFAULT_JDBC_USERNAME);
        jdbcPassword     = new JPasswordField(DEFAULT_JDBC_PASSWORD);

        final FormLayout         formLayout = new FormLayout("right:max(40dlu;pref), 3dlu, default:grow",
                "");
        final DefaultFormBuilder formBuilder = new DefaultFormBuilder(formLayout);
        formBuilder.setDefaultDialogBorder();

        formBuilder.append("URL", jdbcUrl);
        formBuilder.nextLine();
        formBuilder.append("Pilote", jdbcDriver);
        formBuilder.nextLine();
        formBuilder.append("Dialecte Hibernate", jdbcHibernateDialect);
        formBuilder.nextLine();
        formBuilder.append("Identifiant", jdbcUsername);
        formBuilder.nextLine();
        formBuilder.append("Mot de passe", jdbcPassword);
        formBuilder.nextLine();

        return formBuilder.getPanel();
    }


    private JPanel createLogoPanel() {
        final Icon   icon = new ImageIcon(getClass().getResource("/images/logo.png"));

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(icon, SwingConstants.LEFT), BorderLayout.WEST);

        return panel;
    }


    private JPanel createMainPanel() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Base de données", createJdbcForm());
        tabbedPane.addTab("Registre RMI", createRmiForm());

        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);

        return panel;
    }


    private JPanel createRmiForm() {
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

        formBuilder.append("Port registre", rmiRegistryPost);
        formBuilder.nextLine();
        formBuilder.append("Port service", rmiServicePort);
        formBuilder.nextLine();

        return formBuilder.getPanel();
    }


    private void doLoadSampleData() {
        doStartWaitingProgress();

        try {
            rmiServer.loadSampleData();
        } catch (Exception e) {
            log.error("Erreur lors du chargement du jeu de données préfini", e);
            showError();
        }

        doStopWaitingProgress();
    }


    private void doStart() {
        doStartWaitingProgress();
        loadSampleDataAction.setEnabled(true);

        try {
            rmiServer.start();
        } catch (Exception e) {
            log.error("Erreur lors du démarrage du serveur", e);
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
        loadSampleDataAction.setEnabled(false);

        try {
            rmiServer.stop();
        } catch (Exception e) {
            log.error("Erreur lors de l'arrêt du serveur", e);
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

    private class LoadSampleDataAction extends AbstractAction {
        //~ Constructeurs ------------------------------------------------------

        public LoadSampleDataAction() {
            super();

            putValue(Action.NAME, "Charger un exemple");
        }

        //~ Méthodes -----------------------------------------------------------

        public void actionPerformed(ActionEvent e) {
            final Thread thread = new Thread() {
                    public void run() {
                        doLoadSampleData();
                    }
                };
            thread.start();
        }
    }


    private class StartStopAction extends AbstractAction {
        //~ Champs d'instance --------------------------------------------------

        private final String startLabel = "Démarrer";
        private final String stopLabel = "Arrêter";

        //~ Constructeurs ------------------------------------------------------

        public StartStopAction() {
            super();

            putValue(Action.NAME, startLabel);
            putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        }

        //~ Méthodes -----------------------------------------------------------

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
