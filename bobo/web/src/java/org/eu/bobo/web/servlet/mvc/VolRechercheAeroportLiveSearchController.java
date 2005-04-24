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
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:18:59 $
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
