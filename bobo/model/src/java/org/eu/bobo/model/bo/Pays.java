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


package org.eu.bobo.model.bo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.5 $, $Date: 2005/04/24 22:16:09 $
 *
 * @hibernate:class
 */
public class Pays extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private String nom;
    private String paysId;

    //~ Constructeurs ----------------------------------------------------------

    public Pays() {
        super();
    }


    public Pays(final String paysId, final String nom) {
        this();
        setPaysId(paysId);
        setNom(nom);
    }

    //~ Méthodes ---------------------------------------------------------------

    public Serializable getId() {
        return getPaysId();
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="64" not-null="true"
     */
    public String getNom() {
        return nom;
    }


    public void setPaysId(String paysId) {
        this.paysId = paysId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="pays_id" length="4" generator-class="assigned"
     */
    public String getPaysId() {
        return paysId;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Pays)) {
            return false;
        }
        final Pays pays = (Pays) obj;

        return new EqualsBuilder().append(nom, pays.nom).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(nom).toHashCode();
    }
}
