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
import org.eu.bobo.model.bo.Lieu;
import org.eu.bobo.model.bo.Ville;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 *
 * @hibernate:class
 */
public class Aeroport extends AbstractBusinessObject implements Lieu {
    //~ Champs d'instance ------------------------------------------------------

    private String aeroportId;
    private String nom;
    private Ville  ville;

    //~ Constructeurs ----------------------------------------------------------

    public Aeroport() {
        super();
    }


    public Aeroport(final String aeroportId, final Ville ville) {
        this();
        setAeroportId(aeroportId);
        setVille(ville);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportId(String aeroportId) {
        this.aeroportId = aeroportId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="aeroport_id" length="8"
     *            generator-class="assigned"
     */
    public String getAeroportId() {
        return aeroportId;
    }


    public Serializable getId() {
        return getAeroportId();
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


    public void setVille(Ville ville) {
        this.ville = ville;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="ville_id" not-null="true"
     *            cascade="save-update"
     */
    public Ville getVille() {
        return ville;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Aeroport)) {
            return false;
        }
        final Aeroport aeroport = (Aeroport) obj;

        return new EqualsBuilder().append(nom, aeroport.nom)
                                  .append(ville, aeroport.ville).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(nom).append(ville).toHashCode();
    }
}
