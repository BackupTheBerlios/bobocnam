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

import org.eu.bobo.model.bo.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;
import org.eu.bobo.model.dao.VolDao;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

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
 * @version $Revision: 1.1 $, $Date: 2005/02/27 23:48:52 $
 */
public class VolRechercheResultController implements Controller {
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


    public ModelAndView handleRequest(HttpServletRequest req,
        HttpServletResponse resp) throws Exception {
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

        final List     vols = volDao.findByAeroportDate(aeroportDepart,
                aeroportArrivee, dateDepart, dateArrivee);

        final Map model = new HashMap();
        model.put("vols", vols);
        model.put("aeroportDepart", aeroportDepart);
        model.put("aeroportArrivee", aeroportArrivee);
        model.put("dateDepart", dateDepart);
        model.put("dateArrivee", dateArrivee);

        return new ModelAndView("recherche/vol/result", model);
    }
}
