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

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.AbstractBusinessObject;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 *
 * @hibernate:class
 */
public class Vol extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Boolean      cloture         = Boolean.FALSE;
    private Integer      nbPlacesEnVente = new Integer(0);
    private Long         volId;
    private Periode      periode;
    private VolGenerique volGenerique;

    //~ Constructeurs ----------------------------------------------------------

    public Vol() {
        super();
    }


    public Vol(final VolGenerique volGenerique, final Periode periode) {
        this();
        setVolGenerique(volGenerique);
        setPeriode(periode);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setCloture(Boolean cloture) {
        this.cloture = cloture;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true"
     */
    public Boolean getCloture() {
        return cloture;
    }


    public Serializable getId() {
        return getVolId();
    }


    public void setNbPlacesEnVente(Integer nbPlacesEnVente) {
        this.nbPlacesEnVente = nbPlacesEnVente;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="nb_places_en_vente" not-null="true"
     */
    public Integer getNbPlacesEnVente() {
        return nbPlacesEnVente;
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


    public void setVolGenerique(VolGenerique volGenerique) {
        this.volGenerique = volGenerique;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="vol_generique_id" not-null="true"
     *            cascade="save-update"
     */
    public VolGenerique getVolGenerique() {
        return volGenerique;
    }


    public void setVolId(Long volId) {
        this.volId = volId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="vol_id" generator-class="native"
     */
    public Long getVolId() {
        return volId;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Vol)) {
            return false;
        }
        final Vol vol = (Vol) obj;

        return new EqualsBuilder().append(volGenerique, vol.volGenerique)
                                  .append(periode, vol.periode)
                                  .append(cloture, vol.cloture)
                                  .append(nbPlacesEnVente, vol.nbPlacesEnVente)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(volGenerique).append(periode)
                                    .append(cloture).append(nbPlacesEnVente)
                                    .toHashCode();
    }
}
