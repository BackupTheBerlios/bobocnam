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
 * @version $Revision: 1.2 $, $Date: 2005/01/21 10:04:30 $
 *
 * @hibernate:class table="adresse_electronique"
 */
public class AdresseElectronique extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Long   adresseElectroniqueId;
    private String adresse;
    private String type;

    //~ Constructeurs ----------------------------------------------------------

    public AdresseElectronique() {
        super();
    }


    public AdresseElectronique(final String type, final String adresse) {
        this();
        setType(type);
        setAdresse(adresse);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="255" not-null="true"
     */
    public String getAdresse() {
        return adresse;
    }


    public void setAdresseElectroniqueId(Long adresseElectroniqueId) {
        this.adresseElectroniqueId = adresseElectroniqueId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="adresse_electronique_id" generator-class="native"
     */
    public Long getAdresseElectroniqueId() {
        return adresseElectroniqueId;
    }


    public Serializable getId() {
        return getAdresseElectroniqueId();
    }


    public void setType(String type) {
        this.type = type;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="16" not-null="true"
     */
    public String getType() {
        return type;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof AdresseElectronique)) {
            return false;
        }
        final AdresseElectronique email = (AdresseElectronique) obj;

        return new EqualsBuilder().append(adresse, email.adresse)
                                  .append(type, email.type).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(adresse).append(type).toHashCode();
    }
}
