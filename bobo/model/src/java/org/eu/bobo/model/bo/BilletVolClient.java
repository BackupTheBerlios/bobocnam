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

import org.eu.bobo.model.Identite;

import java.io.Serializable;

import java.util.Date;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/02/07 14:58:16 $
 *
 * @hibernate:class table="billet_vol_client"
 */
public class BilletVolClient extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Boolean  annule            = Boolean.FALSE;
    private Boolean  utilise           = Boolean.FALSE;
    private Client   client;
    private Date     dateLimite;
    private Date     dateReservation   = new Date();
    private Identite identite;
    private Long     billetVolClientId;
    private Vol      vol;

    //~ Constructeurs ----------------------------------------------------------

    public BilletVolClient() {
        super();
    }


    public BilletVolClient(final Identite identite, final Client client,
        final Vol vol) {
        this();
        setIdentite(identite);
        setClient(client);
        setVol(vol);
        setDateLimite(vol.getDateDepart());
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAnnule(Boolean annule) {
        this.annule = annule;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true"
     */
    public Boolean getAnnule() {
        return annule;
    }


    public void setBilletVolClientId(Long billetVolClientId) {
        this.billetVolClientId = billetVolClientId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="billet_vol_client_id" generator-class="native"
     */
    public Long getBilletVolClientId() {
        return billetVolClientId;
    }


    public void setClient(Client client) {
        this.client = client;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="client_id" not-null="true" cascade="all"
     */
    public Client getClient() {
        return client;
    }


    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_limite" not-null="true"
     */
    public Date getDateLimite() {
        return dateLimite;
    }


    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_reservation" not-null="true"
     */
    public Date getDateReservation() {
        return dateReservation;
    }


    public Serializable getId() {
        return getBilletVolClientId();
    }


    public void setIdentite(Identite identite) {
        this.identite = identite;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:component
     */
    public Identite getIdentite() {
        return identite;
    }


    public void setUtilise(Boolean utilise) {
        this.utilise = utilise;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true"
     */
    public Boolean getUtilise() {
        return utilise;
    }


    public void setVol(Vol vol) {
        this.vol = vol;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="vol_id" not-null="true" cascade="all"
     */
    public Vol getVol() {
        return vol;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof BilletVolClient)) {
            return false;
        }
        final BilletVolClient billet = (BilletVolClient) obj;

        return new EqualsBuilder().append(vol, billet.vol)
                                  .append(client, billet.client)
                                  .append(dateLimite, billet.dateLimite)
                                  .append(dateReservation,
            billet.dateReservation).append(utilise, billet.utilise)
                                  .append(annule, billet.annule)
                                  .append(identite, billet.identite).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(vol).append(client)
                                    .append(dateLimite).append(dateReservation)
                                    .append(utilise).append(annule)
                                    .append(identite).toHashCode();
    }
}
