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
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:22:37 $
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
        return new HashCodeBuilder().append(client).append(dateLimite)
                                    .append(dateReservation).append(annule)
                                    .append(confirme).toHashCode();
    }
}
