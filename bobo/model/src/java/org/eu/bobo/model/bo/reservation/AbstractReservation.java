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


package org.eu.bobo.model.bo.reservation;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import org.eu.bobo.model.bo.AbstractBusinessObject;
import org.eu.bobo.model.bo.contact.Client;

import java.util.Collection;
import java.util.Date;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 20:45:19 $
 */
public abstract class AbstractReservation extends AbstractBusinessObject
  implements Reservation {
    //~ Champs d'instance ------------------------------------------------------

    private Boolean    annule          = Boolean.FALSE;
    private Boolean    confirme        = Boolean.FALSE;
    private Client     client;
    private Collection passagers;
    private Date       dateLimite;
    private Date       dateReservation = new Date();

    //~ Constructeurs ----------------------------------------------------------

    public AbstractReservation(final Client client, final Collection passagers) {
        this();
        setClient(client);
        setPassagers(passagers);
    }


    public AbstractReservation() {
        super();
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


    public void setClient(Client client) {
        this.client = client;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="client_id" not-null="true"
     *            cascade="save-update"
     */
    public Client getClient() {
        return client;
    }


    public void setConfirme(Boolean confirme) {
        this.confirme = confirme;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true"
     */
    public Boolean getConfirme() {
        return confirme;
    }


    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_limite"
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


    public void setPassagers(Collection passagers) {
        this.passagers = passagers;
    }


    public Collection getPassagers() {
        return passagers;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractReservation)) {
            return false;
        }
        final AbstractReservation reservation = (AbstractReservation) obj;

        return new EqualsBuilder().append(client, reservation.client)
                                  .append(dateLimite, reservation.dateLimite)
                                  .append(dateReservation,
            reservation.dateReservation).append(annule, reservation.annule)
                                  .append(confirme, reservation.confirme)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(client)
                                    .append(dateLimite).append(dateReservation)
                                    .append(annule).append(confirme).toHashCode();
    }
}
