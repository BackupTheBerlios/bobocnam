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
 * @version $Revision: 1.1 $, $Date: 2005/02/06 20:18:21 $
 *
 * @hibernate:class table="compagnie_aerienne"
 */
public class CompagnieAerienne extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Long   compagnieAerienneId;
    private String code;
    private String nom;

    //~ Constructeurs ----------------------------------------------------------

    public CompagnieAerienne() {
        super();
    }


    public CompagnieAerienne(final String code, final String nom) {
        this();
        setCode(code);
        setNom(nom);
    }

    //~ M�thodes ---------------------------------------------------------------

    public void setCode(String code) {
        this.code = code;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true" unique="true"
     */
    public String getCode() {
        return code;
    }


    public void setCompagnieAerienneId(Long compagnieAerienneId) {
        this.compagnieAerienneId = compagnieAerienneId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="compagnie_aerienne_id" generator-class="native"
     */
    public Long getCompagnieAerienneId() {
        return compagnieAerienneId;
    }


    public Serializable getId() {
        return getCompagnieAerienneId();
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property not-null="true" length="64" unique="true"
     */
    public String getNom() {
        return nom;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof CompagnieAerienne)) {
            return false;
        }
        final CompagnieAerienne compagnieAerienne = (CompagnieAerienne) obj;

        return new EqualsBuilder().append(nom, compagnieAerienne.nom)
                                  .append(code, compagnieAerienne.code)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(nom).append(code).toHashCode();
    }
}
