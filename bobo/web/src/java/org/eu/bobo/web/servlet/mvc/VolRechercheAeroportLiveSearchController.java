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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.model.dao.AeroportDao;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/03/01 18:34:31 $
 */
public class VolRechercheAeroportLiveSearchController
  extends MultiActionController {
    //~ Champs d'instance ------------------------------------------------------

    private AeroportDao aeroportDao;
    private final Log   log = LogFactory.getLog(getClass());

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportDao(AeroportDao aeroportDao) {
        this.aeroportDao = aeroportDao;
    }


    public ModelAndView aeroportArrivee(HttpServletRequest req,
        HttpServletResponse resp) throws Exception {
        return new ModelAndView("recherche/vol/aeroports",
            createModel(req, "aeroportArrivee", "aeroportArriveeResult"));
    }


    public ModelAndView aeroportDepart(HttpServletRequest req,
        HttpServletResponse resp) throws Exception {
        return new ModelAndView("recherche/vol/aeroports",
            createModel(req, "aeroportDepart", "aeroportDepartResult"));
    }


    private Map createModel(HttpServletRequest req, String aeroportSearchField,
        String aeroportResultField) throws Exception {
        final String query = RequestUtils.getRequiredStringParameter(req, "q");

        if (log.isDebugEnabled()) {
            log.debug("Requête LiveSearch sur un aéroport: " + query);
        }

        final List aeroports = aeroportDao.findByNomVille(query);

        final Map  model = new HashMap(3);
        model.put("aeroports", aeroports);
        model.put("aeroportSearchField", aeroportSearchField);
        model.put("aeroportResultField", aeroportResultField);

        return model;
    }
}
