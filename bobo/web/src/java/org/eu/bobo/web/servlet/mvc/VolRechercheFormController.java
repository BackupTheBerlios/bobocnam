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

import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;
import org.eu.bobo.web.beans.propertyeditors.AeroportEditor;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import java.text.DateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.5 $, $Date: 2005/03/13 01:19:13 $
 */
public class VolRechercheFormController extends SimpleFormController {
    //~ Champs d'instance ------------------------------------------------------

    private final Log        log         = LogFactory.getLog(getClass());
    private AeroportDao      aeroportDao;
    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

    //~ Constructeurs ----------------------------------------------------------

    public VolRechercheFormController() {
        super();
        setSessionForm(true);
        setCommandClass(VolRechercheForm.class);
        setSuccessView("recherche/vol/redirect");
        setFormView("recherche/vol/form");
        setBindOnNewForm(true);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportDao(AeroportDao aeroportDao) {
        this.aeroportDao = aeroportDao;
    }


    protected void initBinder(HttpServletRequest req,
        ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class,
            new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(Aeroport.class,
            new AeroportEditor(aeroportDao));
    }


    protected ModelAndView onSubmit(Object command) throws Exception {
        final VolRechercheForm form = (VolRechercheForm) command;

        final Map              model = new HashMap();
        model.put("aeroportDepart", form.getAeroportDepart());
        model.put("aeroportArrivee", form.getAeroportArrivee());
        model.put("dateDepart", form.getDateDepart());
        model.put("dateArrivee", form.getDateArrivee());

        return new ModelAndView(getSuccessView(), model);
    }
}
