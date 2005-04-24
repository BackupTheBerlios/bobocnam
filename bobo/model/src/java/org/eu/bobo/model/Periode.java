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

import java.util.Date;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:16:09 $
 */
public class Periode extends BaseObject implements Comparable, Serializable {
    //~ Champs d'instance ------------------------------------------------------

    private Date dateDebut;
    private Date dateFin;

    //~ Constructeurs ----------------------------------------------------------

    public Periode() {
        super();
    }


    public Periode(final Date dateDebut, final Date dateFin) {
        this();
        setDateDebut(dateDebut);
        setDateFin(dateFin);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_debut" not-null="true"
     */
    public Date getDateDebut() {
        return dateDebut;
    }


    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_fin" not-null="true"
     */
    public Date getDateFin() {
        return dateFin;
    }


    public Long getDuree() {
        if ((dateDebut == null) || (dateFin == null)) {
            throw new IllegalStateException("dateDebut et dateFin sont requis");
        }

        final long debut = dateDebut.getTime();
        final long fin = dateFin.getTime();

        return new Long(fin - debut);
    }


    public int compareTo(Object obj) {
        return CompareToBuilder.reflectionCompare(this, obj);
    }


    public boolean contains(Date date) {
        final long timestamp = date.getTime();

        return (dateDebut.getTime() <= timestamp) &&
        (timestamp <= dateFin.getTime());
    }


    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }


    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
