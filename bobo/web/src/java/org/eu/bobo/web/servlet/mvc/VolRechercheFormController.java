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
 * @version $Revision: 1.6 $, $Date: 2005/04/24 22:18:59 $
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
