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
 * @version $Revision: 1.1 $, $Date: 2005/02/19 22:44:07 $
 *
 * @hibernate:class
 */
public class Aeroport extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private String aeroportId;
    private Ville  ville;

    //~ Constructeurs ----------------------------------------------------------

    public Aeroport() {
        super();
    }


    public Aeroport(final String aeroportId, final Ville ville) {
        this();
        setAeroportId(aeroportId);
        setVille(ville);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportId(String aeroportId) {
        this.aeroportId = aeroportId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="aeroport_id" generator-class="assigned"
     */
    public String getAeroportId() {
        return aeroportId;
    }


    public Serializable getId() {
        return getAeroportId();
    }


    public void setVille(Ville ville) {
        this.ville = ville;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="ville_id" not-null="true"
     *            cascade="save-update"
     */
    public Ville getVille() {
        return ville;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Aeroport)) {
            return false;
        }
        final Aeroport aeroport = (Aeroport) obj;

        return new EqualsBuilder().append(ville, aeroport.ville).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(ville).toHashCode();
    }
}
