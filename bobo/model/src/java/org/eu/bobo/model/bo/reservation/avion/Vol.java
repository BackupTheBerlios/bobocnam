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


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/03/13 00:53:02 $
 *
 * @hibernate:class
 */
public class Vol extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Boolean      cloture         = Boolean.FALSE;
    private Integer      nbPlacesEnVente = new Integer(0);
    private Long         volId;
    private Periode      periode;
    private VolGenerique volGenerique;

    //~ Constructeurs ----------------------------------------------------------

    public Vol() {
        super();
    }


    public Vol(final VolGenerique volGenerique, final Periode periode) {
        this();
        setVolGenerique(volGenerique);
        setPeriode(periode);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setCloture(Boolean cloture) {
        this.cloture = cloture;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true"
     */
    public Boolean getCloture() {
        return cloture;
    }


    public Serializable getId() {
        return getVolId();
    }


    public void setNbPlacesEnVente(Integer nbPlacesEnVente) {
        this.nbPlacesEnVente = nbPlacesEnVente;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="nb_places_en_vente" not-null="true"
     */
    public Integer getNbPlacesEnVente() {
        return nbPlacesEnVente;
    }


    public void setPeriode(Periode periode) {
        this.periode = periode;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:component
     */
    public Periode getPeriode() {
        return periode;
    }


    public void setVolGenerique(VolGenerique volGenerique) {
        this.volGenerique = volGenerique;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="vol_generique_id" not-null="true"
     *            cascade="save-update"
     */
    public VolGenerique getVolGenerique() {
        return volGenerique;
    }


    public void setVolId(Long volId) {
        this.volId = volId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="vol_id" generator-class="native"
     */
    public Long getVolId() {
        return volId;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Vol)) {
            return false;
        }
        final Vol vol = (Vol) obj;

        return new EqualsBuilder().append(volGenerique, vol.volGenerique)
                                  .append(periode, vol.periode)
                                  .append(cloture, vol.cloture)
                                  .append(nbPlacesEnVente, vol.nbPlacesEnVente)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(volGenerique).append(periode)
                                    .append(cloture).append(nbPlacesEnVente)
                                    .toHashCode();
    }
}
