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


package org.eu.bobo.web.beans.propertyeditors;

import org.apache.commons.lang.StringUtils;

import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;

import java.beans.PropertyEditorSupport;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/03/13 01:19:13 $
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
