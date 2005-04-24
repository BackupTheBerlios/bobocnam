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

import org.eu.bobo.model.bo.reservation.AbstractPassager;
import org.eu.bobo.model.bo.reservation.Reservation;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 *
 * @hibernate:class table="passager_vol"
 */
public class PassagerVol extends AbstractPassager {
    //~ Champs d'instance ------------------------------------------------------

    private Long passagerVolId;

    //~ Méthodes ---------------------------------------------------------------

    public Serializable getId() {
        return getPassagerVolId();
    }


    public void setPassagerVolId(Long passagerVolId) {
        this.passagerVolId = passagerVolId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="passager_vol_id" generator-class="native"
     */
    public Long getPassagerVolId() {
        return passagerVolId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="reservation_vol_id" not-null="true"
     *            cascade="save-update"
     *            class="org.eu.bobo.model.bo.reservation.avion.ReservationVol"
     */
    public Reservation getReservation() {
        return super.getReservation();
    }
}
