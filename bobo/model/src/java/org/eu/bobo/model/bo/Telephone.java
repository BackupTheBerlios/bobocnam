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
public class Telephone extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Long   telephoneId;
    private String numero;
    private String type;

    //~ Constructeurs ----------------------------------------------------------

    public Telephone() {
        super();
    }


    public Telephone(final String type, final String numero) {
        this();
        setType(type);
        setNumero(numero);
    }

    //~ M�thodes ---------------------------------------------------------------

    public Serializable getId() {
        return getTelephoneId();
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="32" not-null="true"
     */
    public String getNumero() {
        return numero;
    }


    public void setTelephoneId(Long telephoneId) {
        this.telephoneId = telephoneId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="telephone_id" generator-class="native"
     */
    public Long getTelephoneId() {
        return telephoneId;
    }


    public void setType(String type) {
        this.type = type;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="32" not-null="true"
     */
    public String getType() {
        return type;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Telephone)) {
            return false;
        }
        final Telephone telephone = (Telephone) obj;

        return new EqualsBuilder().append(numero, telephone.numero)
                                  .append(type, telephone.type).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(numero).append(type).toHashCode();
    }
}
