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

import org.eu.bobo.model.bo.AbstractBusinessObject;

import java.io.Serializable;

import java.util.Collection;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:22:37 $
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
