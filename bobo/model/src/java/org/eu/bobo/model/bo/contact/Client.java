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


package org.eu.bobo.model.bo.contact;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import org.eu.bobo.model.Identite;

import java.io.Serializable;

import java.util.Collection;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 *
 * @hibernate:class
 */
public class Client extends AbstractContact {
    //~ Champs d'instance ------------------------------------------------------

    private Collection reservationsVol;
    private Long       clientId;

    //~ Constructeurs ----------------------------------------------------------

    public Client() {
        super();
    }


    public Client(final Identite identite) {
        super(identite);
    }

    //~ Méthodes ---------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set table="adresse_client" cascade="save-update"
     * @hibernate:collection-key column="client_id"
     * @hibernate:collection-many-to-many column="adresse_id"
     *            class="org.eu.bobo.model.bo.Adresse"
     */
    public Collection getAdresses() {
        return super.getAdresses();
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set table="adresse_electronique_client"
     *            cascade="save-update"
     * @hibernate:collection-key column="client_id"
     * @hibernate:collection-many-to-many column="adresse_electronique_id"
     *            class="org.eu.bobo.model.bo.AdresseElectronique"
     */
    public Collection getAdressesElectroniques() {
        return super.getAdressesElectroniques();
    }


    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="client_id" generator-class="native"
     */
    public Long getClientId() {
        return clientId;
    }


    public Serializable getId() {
        return getClientId();
    }


    public void setReservationsVol(Collection reservationsVol) {
        this.reservationsVol = reservationsVol;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set inverse="true" cascade="all-delete-orphan"
     * @hibernate:collection-key column="client_id"
     * @hibernate:collection-one-to-many column="reservation_vol_id"
     *            class="org.eu.bobo.model.bo.reservation.avion.ReservationVol"
     */
    public Collection getReservationsVol() {
        return reservationsVol;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set table="telephone_client" cascade="save-update"
     * @hibernate:collection-key column="client_id"
     * @hibernate:collection-many-to-many column="telephone_id"
     *            class="org.eu.bobo.model.bo.Telephone"
     */
    public Collection getTelephones() {
        return super.getTelephones();
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Client)) {
            return false;
        }
        final Client client = (Client) obj;

        return new EqualsBuilder().appendSuper(super.equals(obj)).isEquals();
    }


    public int hashCode() {
        // même remarque que dans equals concernant billetsVolClient
        return new HashCodeBuilder().appendSuper(super.hashCode()).toHashCode();
    }
}
