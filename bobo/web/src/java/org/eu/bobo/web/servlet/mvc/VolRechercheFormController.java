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

import org.eu.bobo.model.bo.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;
import org.eu.bobo.model.dao.VolDao;
import org.eu.bobo.web.beans.propertyeditors.AeroportEdtior;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import java.text.DateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/02/27 18:08:19 $
 */
public class VolRechercheFormController extends SimpleFormController {
    //~ Champs d'instance ------------------------------------------------------

    private final Log        log         = LogFactory.getLog(getClass());
    private AeroportDao      aeroportDao;
    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
    private VolDao           volDao;

    //~ Constructeurs ----------------------------------------------------------

    public VolRechercheFormController() {
        super();
        setFormView("recherche/vol/form");
        setSuccessView("recherche/vol/result");
        setSessionForm(true);
        setCommandClass(VolRechercheForm.class);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportDao(AeroportDao aeroportDao) {
        this.aeroportDao = aeroportDao;
    }


    public void setVolDao(VolDao volDao) {
        this.volDao = volDao;
    }


    protected void initBinder(HttpServletRequest req,
        ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class,
            new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(Aeroport.class,
            new AeroportEdtior(aeroportDao));
    }


    protected ModelAndView onSubmit(Object command) throws Exception {
        final VolRechercheForm form = (VolRechercheForm) command;

        if (log.isDebugEnabled()) {
            final String inconnu        = "<inconnu>";
            final String aeroportDepart = (form.getAeroportDepart() == null)
                ? inconnu
                : form.getAeroportDepart().getAeroportId();
            final String aeroportArrivee = (form.getAeroportArrivee() == null)
                ? inconnu
                : form.getAeroportArrivee().getAeroportId();
            final String dateDepart  = dateFormat.format(form.getDateDepart());
            final String dateArrivee = dateFormat.format(form.getDateArrivee());

            log.info("Recherche d'un vol au départ de " + aeroportDepart +
                " à destination de " + aeroportArrivee + " entre le " +
                dateDepart + " et le " + dateArrivee);
        }

        final List vols = volDao.findByAeroportDate(form.getAeroportDepart(),
                form.getAeroportArrivee(), form.getDateDepart(),
                form.getDateArrivee());
        
        return new ModelAndView(getSuccessView(), "vols", vols);
    }
}
