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

import org.eu.bobo.model.bo.contact.Client;
import org.eu.bobo.model.bo.reservation.AbstractReservation;

import java.io.Serializable;

import java.util.Collection;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/03/14 00:14:30 $
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

    //~ M�thodes ---------------------------------------------------------------

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

        return new EqualsBuilder().appendSuper(super.equals(obj))
                                  .append(vols, reservationVol.vols).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(vols)
                                    .toHashCode();
    }
}
