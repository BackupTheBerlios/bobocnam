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


package org.eu.bobo.model.bo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/02/08 07:55:06 $
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
