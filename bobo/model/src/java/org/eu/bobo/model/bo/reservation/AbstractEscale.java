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


package org.eu.bobo.model.bo.reservation;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.AbstractBusinessObject;
import org.eu.bobo.model.bo.Lieu;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 */
public abstract class AbstractEscale extends AbstractBusinessObject
  implements Escale {
    //~ Champs d'instance ------------------------------------------------------

    private Lieu    lieu;
    private Periode periode;

    //~ Constructeurs ----------------------------------------------------------

    public AbstractEscale() {
        super();
    }


    public AbstractEscale(final Lieu lieu, final Periode periode) {
        this();
        this.lieu = lieu;
        setPeriode(periode);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }


    public Lieu getLieu() {
        return lieu;
    }


    public void setPeriode(Periode periode) {
        this.periode = periode;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:component
     */
    public Periode getPeriode() {
        return periode;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEscale)) {
            return false;
        }
        final AbstractEscale escale = (AbstractEscale) obj;

        return new EqualsBuilder().append(periode, escale.periode)
                                  .append(lieu, escale.lieu).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(periode).append(lieu).toHashCode();
    }
}
