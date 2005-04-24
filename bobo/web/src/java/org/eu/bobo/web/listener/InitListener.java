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


package org.eu.bobo.web.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Locale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:18:59 $
 */
public class InitListener implements ServletContextListener {
    //~ Champs d'instance ------------------------------------------------------

    private final Log log = LogFactory.getLog(getClass());

    //~ Méthodes ---------------------------------------------------------------

    public void contextDestroyed(ServletContextEvent evt) {
    }


    public void contextInitialized(ServletContextEvent evt) {
        if (!log.isInfoEnabled()) {
            return;
        }

        final ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(evt.getServletContext());

        final String             name = getMessage(applicationContext,
                "build.name");
        final String             version = getMessage(applicationContext,
                "build.version");
        final String             build = getMessage(applicationContext,
                "build.number");
        final String             date = getMessage(applicationContext,
                "build.date");

        log.info(name + " " + version + " build " + build + " (" + date + ")");
    }


    private String getMessage(ApplicationContext applicationContext, String code) {
        return applicationContext.getMessage(code, null, Locale.getDefault());
    }
}
