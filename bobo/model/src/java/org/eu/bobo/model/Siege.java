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


package org.eu.bobo.model;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:16:09 $
 */
public class Siege extends BaseObject implements Comparable, Serializable {
    //~ Champs d'instance ------------------------------------------------------

    private String couloir;
    private String numero;

    //~ Constructeurs ----------------------------------------------------------

    public Siege() {
        super();
    }


    public Siege(final String couloir, final String numero) {
        this();
        setCouloir(couloir);
        setNumero(numero);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setCouloir(String couloir) {
        this.couloir = couloir;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="4"
     */
    public String getCouloir() {
        return couloir;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="4"
     */
    public String getNumero() {
        return numero;
    }


    public int compareTo(Object obj) {
        return CompareToBuilder.reflectionCompare(this, obj);
    }


    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }


    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
