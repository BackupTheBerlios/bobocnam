/*
 * Copyright (c) 2005, Bobo team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *     * Neither the name of the Bobo project nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
 * @version $Revision: 1.1 $, $Date: 2005/03/13 00:53:02 $
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
