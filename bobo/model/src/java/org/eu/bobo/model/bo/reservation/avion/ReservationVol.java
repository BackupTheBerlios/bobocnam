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


package org.eu.bobo.model.bo.reservation.avion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import org.eu.bobo.model.bo.contact.Client;
import org.eu.bobo.model.bo.reservation.AbstractReservation;

import java.io.Serializable;

import java.util.Collection;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:22:37 $
 *
 * @hibernate:class table="reservation_vol"
 */
public class ReservationVol extends AbstractReservation {
    //~ Champs d'instance ------------------------------------------------------

    private Collection vols;
    private Long       reservationVolId;

    //~ Constructeurs ----------------------------------------------------------

    public ReservationVol() {
        super();
    }


    public ReservationVol(final Collection vols, final Client client,
        final Collection passagers) {
        super(client, passagers);
        setVols(vols);
    }

    //~ Méthodes ---------------------------------------------------------------

    public Serializable getId() {
        return getReservationVolId();
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set inverse="true" cascade="all-delete-orphan"
     * @hibernate:collection-key column="reservation_vol_id"
     * @hibernate:collection-one-to-many column="passager_vol_id"
     *            class="org.eu.bobo.model.bo.reservation.avion.PassagerVol"
     */
    public Collection getPassagers() {
        return super.getPassagers();
    }


    public void setReservationVolId(Long reservationVolId) {
        this.reservationVolId = reservationVolId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="reservation_vol_id" generator-class="native"
     */
    public Long getReservationVolId() {
        return reservationVolId;
    }


    public void setVols(Collection vols) {
        this.vols = vols;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:list table="vol_reservation_vol" cascade="save-update"
     * @hibernate:collection-key column="reservation_vol_id"
     * @hibernate:collection-index column="indice"
     * @hibernate:collection-many-to-many column="vol_id"
     *            class="org.eu.bobo.model.bo.reservation.avion.Vol"
     */
    public Collection getVols() {
        return vols;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof ReservationVol)) {
            return false;
        }
        final ReservationVol reservationVol = (ReservationVol) obj;

        return new EqualsBuilder().appendSuper(super.equals(obj)).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).toHashCode();
    }
}
