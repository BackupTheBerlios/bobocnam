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


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 *
 * @hibernate:class table="compagnie_aerienne"
 */
public class CompagnieAerienne extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private String compagnieAerienneId;
    private String nom;

    //~ Constructeurs ----------------------------------------------------------

    public CompagnieAerienne() {
        super();
    }


    public CompagnieAerienne(final String compagnieAerienneId, final String nom) {
        this();
        setCompagnieAerienneId(compagnieAerienneId);
        setNom(nom);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setCompagnieAerienneId(String compagnieAerienneId) {
        this.compagnieAerienneId = compagnieAerienneId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="compagnie_aerienne_id" length="4"
     *            generator-class="assigned"
     */
    public String getCompagnieAerienneId() {
        return compagnieAerienneId;
    }


    public Serializable getId() {
        return getCompagnieAerienneId();
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true" length="64"
     */
    public String getNom() {
        return nom;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof CompagnieAerienne)) {
            return false;
        }
        final CompagnieAerienne compagnieAerienne = (CompagnieAerienne) obj;

        return new EqualsBuilder().append(nom, compagnieAerienne.nom).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(nom).toHashCode();
    }
}
