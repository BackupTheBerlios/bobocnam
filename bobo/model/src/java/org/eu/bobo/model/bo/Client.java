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

import org.eu.bobo.model.Identite;

import java.io.Serializable;

import java.util.Collection;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/01/21 10:04:30 $
 *
 * @hibernate:class
 */
public class Client extends AbstractContact {
    //~ Champs d'instance ------------------------------------------------------

    private Boolean exonereTva = Boolean.FALSE;
    private Long    clientId;

    //~ Constructeurs ----------------------------------------------------------

    public Client() {
        super();
    }


    public Client(final Identite identite) {
        super(identite);
    }

    //~ Méthodes ---------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set table="adresse_client" cascade="save-update"
     * @hibernate:collection-key column="client_id"
     * @hibernate:collection-many-to-many column="adresse_id"
     *            class="org.eu.bobo.model.bo.Adresse"
     */
    public Collection getAdresses() {
        return super.getAdresses();
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set table="adresse_electronique_client"
     *            cascade="save-update"
     * @hibernate:collection-key column="client_id"
     * @hibernate:collection-many-to-many column="adresse_electronique_id"
     *            class="org.eu.bobo.model.bo.AdresseElectronique"
     */
    public Collection getAdressesElectroniques() {
        return super.getAdressesElectroniques();
    }


    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="client_id" generator-class="native"
     */
    public Long getClientId() {
        return clientId;
    }


    public void setExonereTva(Boolean exonereTva) {
        this.exonereTva = exonereTva;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="exonere_tva" not-null="true"
     */
    public Boolean getExonereTva() {
        return exonereTva;
    }


    public Serializable getId() {
        return getClientId();
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set table="telephone_client" cascade="save-update"
     * @hibernate:collection-key column="client_id"
     * @hibernate:collection-many-to-many column="telephone_id"
     *            class="org.eu.bobo.model.bo.Telephone"
     */
    public Collection getTelephones() {
        return super.getTelephones();
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Client)) {
            return false;
        }
        final Client client = (Client) obj;

        return new EqualsBuilder().appendSuper(super.equals(obj))
                                  .append(exonereTva, client.exonereTva)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode())
                                    .append(exonereTva).toHashCode();
    }
}
