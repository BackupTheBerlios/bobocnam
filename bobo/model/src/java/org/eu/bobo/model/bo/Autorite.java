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
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:16:09 $
 *
 * @hibernate:class
 */
public class Autorite extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Long   autoriteId;
    private String nom;

    //~ Constructeurs ----------------------------------------------------------

    public Autorite() {
        super();
    }


    public Autorite(final String nom) {
        this();
        setNom(nom);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAutoriteId(Long autoriteId) {
        this.autoriteId = autoriteId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="autorite_id" generator-class="native"
     */
    public Long getAutoriteId() {
        return autoriteId;
    }


    public Serializable getId() {
        return getAutoriteId();
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="32" not-null="true"
     */
    public String getNom() {
        return nom;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Autorite)) {
            return false;
        }
        final Autorite autorite = (Autorite) obj;

        return new EqualsBuilder().append(nom, autorite.nom).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(nom).toHashCode();
    }
}
