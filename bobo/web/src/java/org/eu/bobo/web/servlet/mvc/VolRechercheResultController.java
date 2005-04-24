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

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;
import org.eu.bobo.model.dao.VolDao;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:18:59 $
 */
public class VolRechercheResultController extends MultiActionController {
    //~ Champs d'instance ------------------------------------------------------

    private AeroportDao      aeroportDao;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private VolDao           volDao;

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportDao(AeroportDao aeroportDao) {
        this.aeroportDao = aeroportDao;
    }


    public void setVolDao(VolDao volDao) {
        this.volDao = volDao;
    }


    public ModelAndView afficherPageHtml(HttpServletRequest req,
        HttpServletResponse resp) throws Exception {
        return new ModelAndView("recherche/vol/result", createModel(req));
    }


    public ModelAndView afficherPageRss(HttpServletRequest req,
        HttpServletResponse resp) throws Exception {
        final StringBuffer absoluteUrl = new StringBuffer("http://").append(req.getServerName());
        final int          port = req.getServerPort();
        if (port != 80) {
            absoluteUrl.append(":").append(port);
        }

        final Map model = new HashMap(createModel(req));
        model.put("absoluteUrl", absoluteUrl.toString());

        return new ModelAndView("recherche/vol/rss", model);
    }


    private Map createModel(HttpServletRequest req) throws Exception {
        final String aeroportDepartId = RequestUtils.getRequiredStringParameter(req,
                "ad");
        final String aeroportArriveeId = RequestUtils.getRequiredStringParameter(req,
                "aa");
        final String dateDepartValue = RequestUtils.getRequiredStringParameter(req,
                "dd");
        final String dateArriveeValue = RequestUtils.getRequiredStringParameter(req,
                "da");

        final Date     dateDepart  = dateFormat.parse(dateDepartValue);
        final Date     dateArrivee = dateFormat.parse(dateArriveeValue);

        final Aeroport aeroportDepart  = (Aeroport) aeroportDao.findById(aeroportDepartId);
        final Aeroport aeroportArrivee = (Aeroport) aeroportDao.findById(aeroportArriveeId);

        final List     vols = volDao.findByAeroportPeriode(aeroportDepart,
                aeroportArrivee, new Periode(dateDepart, dateArrivee));

        final Map model = new HashMap();
        model.put("vols", vols);
        model.put("aeroportDepart", aeroportDepart);
        model.put("aeroportArrivee", aeroportArrivee);
        model.put("dateDepart", dateDepart);
        model.put("dateArrivee", dateArrivee);

        return model;
    }
}
