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


package org.eu.bobo.model.bo.reservation.avion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.AbstractBusinessObject;

import java.io.Serializable;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/03/14 00:17:37 $
 *
 * @hibernate:class
 */
public class Ligne extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private List    volsGeneriques;
    private Long    ligneId;
    private Periode validite;

    //~ Constructeurs ----------------------------------------------------------

    public Ligne() {
        super();
    }


    public Ligne(final List volsGeneriques, final Periode validite) {
        this();
        setVolsGeneriques(volsGeneriques);
        setValidite(validite);
    }

    //~ Méthodes ---------------------------------------------------------------

    public Aeroport getAeroportArrivee() {
        if ((volsGeneriques == null) || volsGeneriques.isEmpty()) {
            throw new IllegalStateException(
                "volsGeneriques doit contenir au moins un vol");
        }

        final VolGenerique vol = (VolGenerique) volsGeneriques.get(volsGeneriques.size() -
                1);

        return vol.getAeroportArrivee();
    }


    public Aeroport getAeroportDepart() {
        if ((volsGeneriques == null) || volsGeneriques.isEmpty()) {
            throw new IllegalStateException(
                "volsGeneriques doit contenir au moins un vol");
        }

        final VolGenerique vol = (VolGenerique) volsGeneriques.get(0);

        return vol.getAeroportDepart();
    }


    public Serializable getId() {
        return getLigneId();
    }


    public void setLigneId(Long ligneId) {
        this.ligneId = ligneId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="ligne_id" generator-class="native"
     */
    public Long getLigneId() {
        return ligneId;
    }


    public void setValidite(Periode validite) {
        this.validite = validite;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:component
     */
    public Periode getValidite() {
        return validite;
    }


    public void setVolsGeneriques(List volsGeneriques) {
        this.volsGeneriques = volsGeneriques;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:list table="ligne_vol_generique" cascade="save-update"
     * @hibernate:collection-key column="ligne_id"
     * @hibernate:collection-index column="indice"
     * @hibernate:collection-many-to-many column="vol_generique_id"
     *            class="org.eu.bobo.model.bo.reservation.avion.VolGenerique"
     */
    public List getVolsGeneriques() {
        return volsGeneriques;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Ligne)) {
            return false;
        }
        final Ligne ligne = (Ligne) obj;

        return new EqualsBuilder().append(volsGeneriques, ligne.volsGeneriques)
                                  .append(validite, ligne.validite).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(volsGeneriques).append(validite)
                                    .toHashCode();
    }
}
