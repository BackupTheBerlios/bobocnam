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

import org.eu.bobo.model.bo.AbstractBusinessObject;

import java.io.Serializable;

import java.util.Collection;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/03/14 00:16:06 $
 *
 * @hibernate:class table="vol_generique"
 */
public class VolGenerique extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Aeroport          aeroportArrivee;
    private Aeroport          aeroportDepart;
    private Collection        vols;
    private CompagnieAerienne compagnieAerienne;
    private Long              volGeneriqueId;
    private String            code;

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportArrivee(Aeroport aeroportArrivee) {
        this.aeroportArrivee = aeroportArrivee;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="aeroport_arrivee_id" not-null="true"
     *            cascade="save-update"
     */
    public Aeroport getAeroportArrivee() {
        return aeroportArrivee;
    }


    public void setAeroportDepart(Aeroport aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="aeroport_depart_id" not-null="true"
     *            cascade="save-update"
     */
    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }


    public void setCode(String code) {
        this.code = code;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true"
     */
    public String getCode() {
        return code;
    }


    public void setCompagnieAerienne(CompagnieAerienne compagnieAerienne) {
        this.compagnieAerienne = compagnieAerienne;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="compagnie_aerienne_id" not-null="true"
     *            cascade="all"
     */
    public CompagnieAerienne getCompagnieAerienne() {
        return compagnieAerienne;
    }


    public Serializable getId() {
        return getVolGeneriqueId();
    }


    public String getNumero() {
        return compagnieAerienne.getCompagnieAerienneId() + getCode();
    }


    public void setVolGeneriqueId(Long volGeneriqueId) {
        this.volGeneriqueId = volGeneriqueId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="vol_generique_id" generator-class="native"
     */
    public Long getVolGeneriqueId() {
        return volGeneriqueId;
    }


    public void setVols(Collection vols) {
        this.vols = vols;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set inverse="true" cascade="all-delete-orphan"
     * @hibernate:collection-key column="vol_generique_id"
     * @hibernate:collection-one-to-many column="vol_id"
     *            class="org.eu.bobo.model.bo.reservation.avion.Vol"
     */
    public Collection getVols() {
        return vols;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof VolGenerique)) {
            return false;
        }
        final VolGenerique vol = (VolGenerique) obj;

        return new EqualsBuilder().append(compagnieAerienne,
            vol.compagnieAerienne).append(aeroportDepart, vol.aeroportDepart)
                                  .append(aeroportArrivee, vol.aeroportArrivee)
                                  .append(code, vol.code).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(compagnieAerienne)
                                    .append(aeroportDepart)
                                    .append(aeroportArrivee).append(code)
                                    .toHashCode();
    }
}
