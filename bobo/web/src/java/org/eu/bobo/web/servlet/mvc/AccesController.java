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


package org.eu.bobo.web.servlet.mvc;

import net.sf.acegisecurity.context.ContextHolder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.web.util.SecureContextUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.5 $, $Date: 2005/04/24 22:18:59 $
 */
public class AccesController extends MultiActionController {
    //~ Champs d'instance ------------------------------------------------------

    private final Log log = LogFactory.getLog(getClass());

    //~ Méthodes ---------------------------------------------------------------

    public ModelAndView connexion(HttpServletRequest req,
        HttpServletResponse resp) {
        return new ModelAndView("acces/connexion");
    }


    public ModelAndView deconnexion(HttpServletRequest req,
        HttpServletResponse resp, HttpSession session) {
        if (!SecureContextUtils.isAuthenticated()) {
            if (log.isWarnEnabled()) {
                log.warn(
                    "Tentative de déconnexion alors que la session n'est pas authentifiée");
            }

            return new ModelAndView("redirect:/");
        }

        if (log.isInfoEnabled()) {
            log.info("Déconnexion de l'utilisateur: " +
                SecureContextUtils.getAuthentication().getName());
        }

        session.invalidate();

        // on charge un contexte vide pour Acegi Security
        // (requis pour que la déconnexion soit effective)
        ContextHolder.setContext(null);

        return new ModelAndView("acces/deconnexion");
    }


    public ModelAndView erreur(HttpServletRequest req, HttpServletResponse resp) {
        return new ModelAndView("acces/connexion", "erreur", Boolean.TRUE);
    }
}
