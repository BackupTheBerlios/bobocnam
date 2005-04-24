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

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.AbstractBusinessObject;

import java.io.Serializable;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 *
 * @hibernate:class
 */
public class Ligne extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private List    volsGeneriques;
    private Long    ligneId;
    private Periode validite;

    //~ Constructeurs ----------------------------------------------------------

    public Ligne() {
        super();
    }


    public Ligne(final List volsGeneriques, final Periode validite) {
        this();
        setVolsGeneriques(volsGeneriques);
        setValidite(validite);
    }

    //~ Méthodes ---------------------------------------------------------------

    public Aeroport getAeroportArrivee() {
        if ((volsGeneriques == null) || volsGeneriques.isEmpty()) {
            throw new IllegalStateException(
                "volsGeneriques doit contenir au moins un vol");
        }

        final VolGenerique vol = (VolGenerique) volsGeneriques.get(volsGeneriques.size() -
                1);

        return vol.getAeroportArrivee();
    }


    public Aeroport getAeroportDepart() {
        if ((volsGeneriques == null) || volsGeneriques.isEmpty()) {
            throw new IllegalStateException(
                "volsGeneriques doit contenir au moins un vol");
        }

        final VolGenerique vol = (VolGenerique) volsGeneriques.get(0);

        return vol.getAeroportDepart();
    }


    public Serializable getId() {
        return getLigneId();
    }


    public void setLigneId(Long ligneId) {
        this.ligneId = ligneId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="ligne_id" generator-class="native"
     */
    public Long getLigneId() {
        return ligneId;
    }


    public void setValidite(Periode validite) {
        this.validite = validite;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:component
     */
    public Periode getValidite() {
        return validite;
    }


    public void setVolsGeneriques(List volsGeneriques) {
        this.volsGeneriques = volsGeneriques;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:list table="ligne_vol_generique" cascade="save-update"
     * @hibernate:collection-key column="ligne_id"
     * @hibernate:collection-index column="indice"
     * @hibernate:collection-many-to-many column="vol_generique_id"
     *            class="org.eu.bobo.model.bo.reservation.avion.VolGenerique"
     */
    public List getVolsGeneriques() {
        return volsGeneriques;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Ligne)) {
            return false;
        }
        final Ligne ligne = (Ligne) obj;

        return new EqualsBuilder().append(volsGeneriques, ligne.volsGeneriques)
                                  .append(validite, ligne.validite).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(volsGeneriques).append(validite)
                                    .toHashCode();
    }
}
