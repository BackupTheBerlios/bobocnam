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


package org.eu.bobo.web.beans.propertyeditors;

import org.apache.commons.lang.StringUtils;

import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;

import java.beans.PropertyEditorSupport;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:18:59 $
 */
public class AeroportEditor extends PropertyEditorSupport {
    //~ Champs d'instance ------------------------------------------------------

    private final AeroportDao aeroportDao;

    //~ Constructeurs ----------------------------------------------------------

    public AeroportEditor(final AeroportDao aeroportDao) {
        super();

        if (aeroportDao == null) {
            throw new IllegalArgumentException("aeroportDao est requis");
        }
        this.aeroportDao = aeroportDao;
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAsText(String text) {
        if (StringUtils.isBlank(text)) {
            setValue(null);

            return;
        }

        final Aeroport aeroport = (Aeroport) aeroportDao.findById(text);
        setValue(aeroport);
    }


    public String getAsText() {
        final Aeroport aeroport = (Aeroport) getValue();
        if (aeroport == null) {
            return null;
        }

        return aeroport.getAeroportId();
    }
}
