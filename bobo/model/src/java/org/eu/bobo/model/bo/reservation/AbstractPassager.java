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
 * @version $Revision: 1.2 $, $Date: 2005/03/14 00:13:34 $
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
