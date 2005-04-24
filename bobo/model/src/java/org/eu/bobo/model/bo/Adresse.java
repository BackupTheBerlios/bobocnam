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
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:16:09 $
 *
 * @hibernate:class
 */
public class Adresse extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Long   adresseId;
    private String codePostal;
    private String pays;
    private String region;
    private String rue;
    private String type;
    private String ville;

    //~ Constructeurs ----------------------------------------------------------

    public Adresse() {
        super();
    }


    public Adresse(final String type) {
        this();
        setType(type);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="adresse_id" generator-class="native"
     */
    public Long getAdresseId() {
        return adresseId;
    }


    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="cp" length="16"
     */
    public String getCodePostal() {
        return codePostal;
    }


    public Serializable getId() {
        return getAdresseId();
    }


    public void setPays(String pays) {
        this.pays = pays;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="pays" length="64"
     */
    public String getPays() {
        return pays;
    }


    public void setRegion(String region) {
        this.region = region;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="64"
     */
    public String getRegion() {
        return region;
    }


    public void setRue(String rue) {
        this.rue = rue;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property type="text"
     */
    public String getRue() {
        return rue;
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


    public void setVille(String ville) {
        this.ville = ville;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="64"
     */
    public String getVille() {
        return ville;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Adresse)) {
            return false;
        }
        final Adresse adresse = (Adresse) obj;

        return new EqualsBuilder().append(codePostal, adresse.codePostal)
                                  .append(pays, adresse.pays)
                                  .append(region, adresse.region)
                                  .append(rue, adresse.rue)
                                  .append(type, adresse.type)
                                  .append(ville, adresse.ville).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(codePostal).append(pays)
                                    .append(region).append(rue).append(type)
                                    .append(ville).toHashCode();
    }
}
