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


package org.eu.bobo.web.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eu.bobo.web.util.SecureContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/01/20 09:12:29 $
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
        if(!SecureContextUtils.isAuthenticated()) {
            if(log.isWarnEnabled()) {
                log.warn("Tentative de déconnexion alors que la session n'est pas authentifiée");
            }
            
            return new ModelAndView("redirect:index");
        }
        
        if (log.isInfoEnabled()) {
            log.info("Déconnexion de l'utilisateur: " +
                SecureContextUtils.getAuthentication().getName());
        }

        session.invalidate();

        return new ModelAndView("acces/deconnexion");
    }


    public ModelAndView erreur(HttpServletRequest req, HttpServletResponse resp) {
        return new ModelAndView("acces/connexion", "erreur", Boolean.TRUE);
    }
}
