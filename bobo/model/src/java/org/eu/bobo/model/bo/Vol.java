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


package org.eu.bobo.model.bo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/02/07 14:59:51 $
 *
 * @hibernate:class
 */
public class Vol extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Boolean           cloture           = Boolean.FALSE;
    private CompagnieAerienne compagnieAerienne;
    private Date              dateArrivee;
    private Date              dateDepart;
    private Integer           nbPlacesEnVente = new Integer(0);
    private Long              volId;
    private String            code;
    private Ville             villeArrivee;
    private Ville             villeDepart;

    //~ Constructeurs ----------------------------------------------------------

    public Vol() {
        super();
    }


    public Vol(final CompagnieAerienne compagnieAerienne, final String code,
        final Date dateDepart, final Date dateArrivee, final Ville villeDepart,
        final Ville villeArrivee) {
        this();
        setCompagnieAerienne(compagnieAerienne);
        setCode(code);
        setDateDepart(dateDepart);
        setDateArrivee(dateArrivee);
        setVilleDepart(villeDepart);
        setVilleArrivee(villeArrivee);
    }

    //~ M�thodes ---------------------------------------------------------------

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


    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_arrivee" not-null="true"
     */
    public Date getDateArrivee() {
        return dateArrivee;
    }


    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_depart" not-null="true"
     */
    public Date getDateDepart() {
        return dateDepart;
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
     * @hibernate:property column="nb_places_en_vente"
     */
    public Integer getNbPlacesEnVente() {
        return nbPlacesEnVente;
    }


    public String getNumero() {
        return compagnieAerienne.getCode() + getCode();
    }


    public void setVilleArrivee(Ville villeArrivee) {
        this.villeArrivee = villeArrivee;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="ville_arrivee_id" not_null="true"
     *            cascade="all"
     */
    public Ville getVilleArrivee() {
        return villeArrivee;
    }


    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="ville_depart_id" not-null="true"
     *            cascade="all"
     */
    public Ville getVilleDepart() {
        return villeDepart;
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

        return new EqualsBuilder().append(compagnieAerienne,
            vol.compagnieAerienne).append(dateDepart, vol.dateDepart)
                                  .append(dateArrivee, vol.dateArrivee)
                                  .append(villeDepart, vol.villeDepart)
                                  .append(villeArrivee, vol.villeArrivee)
                                  .append(code, vol.code)
                                  .append(cloture, vol.cloture)
                                  .append(nbPlacesEnVente, vol.nbPlacesEnVente)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(compagnieAerienne).append(dateDepart)
                                    .append(dateArrivee).append(villeDepart)
                                    .append(villeArrivee).append(code)
                                    .append(cloture).append(nbPlacesEnVente)
                                    .toHashCode();
    }
}
