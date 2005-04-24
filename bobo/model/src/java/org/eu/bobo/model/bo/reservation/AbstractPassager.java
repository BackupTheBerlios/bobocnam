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

import org.eu.bobo.model.Identite;
import org.eu.bobo.model.Siege;
import org.eu.bobo.model.bo.AbstractBusinessObject;

import java.math.BigDecimal;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:22:37 $
 */
public abstract class AbstractPassager extends AbstractBusinessObject
  implements Passager {
    //~ Champs d'instance ------------------------------------------------------

    private BigDecimal  prixReservation = new BigDecimal(0);
    private Identite    identite;
    private Reservation reservation;
    private Siege       siege;

    //~ Constructeurs ----------------------------------------------------------

    public AbstractPassager() {
        super();
    }


    public AbstractPassager(final Reservation reservation,
        final Identite identite, final BigDecimal prixReservation) {
        this();
        setReservation(reservation);
        setIdentite(identite);
        setPrixReservation(prixReservation);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:component
     */
    public Identite getIdentite() {
        return identite;
    }


    public void setPrixReservation(BigDecimal prixReservation) {
        this.prixReservation = prixReservation;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="prix_reservation" not-null="true"
     */
    public BigDecimal getPrixReservation() {
        return prixReservation;
    }


    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


    public Reservation getReservation() {
        return reservation;
    }


    public void setSiege(Siege siege) {
        this.siege = siege;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:component prefix="siege_"
     */
    public Siege getSiege() {
        return siege;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Passager)) {
            return false;
        }
        final AbstractPassager passager = (AbstractPassager) obj;

        return new EqualsBuilder().append(identite, passager.identite)
                                  .append(reservation, passager.reservation)
                                  .append(prixReservation,
            passager.prixReservation).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(identite).append(reservation)
                                    .append(prixReservation).toHashCode();
    }
}
