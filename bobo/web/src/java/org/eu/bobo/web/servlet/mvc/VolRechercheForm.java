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

import java.util.Date;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/03/01 22:53:32 $
 */
public class VolRechercheForm {
    //~ Champs d'instance ------------------------------------------------------

    private Aeroport aeroportArrivee;
    private Aeroport aeroportDepart;
    private Boolean  allerSimple = Boolean.FALSE;
    private Boolean  volDirect   = Boolean.FALSE;
    private Date     dateArrivee;
    private Date     dateDepart;

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportArrivee(Aeroport aeroportArrivee) {
        this.aeroportArrivee = aeroportArrivee;
    }


    public Aeroport getAeroportArrivee() {
        return aeroportArrivee;
    }


    public void setAeroportDepart(Aeroport aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }


    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }


    public void setAllerSimple(Boolean allerSimple) {
        this.allerSimple = allerSimple;
    }


    public Boolean getAllerSimple() {
        return allerSimple;
    }


    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }


    public Date getDateArrivee() {
        return dateArrivee;
    }


    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }


    public Date getDateDepart() {
        return dateDepart;
    }


    public void setVolDirect(Boolean volsDirects) {
        this.volDirect = volsDirects;
    }


    public Boolean getVolDirect() {
        return volDirect;
    }
}
