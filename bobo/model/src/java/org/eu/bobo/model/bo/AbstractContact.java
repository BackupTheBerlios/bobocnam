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

import java.util.Collection;
import java.util.HashSet;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/01/21 10:04:30 $
 */
public abstract class AbstractContact extends AbstractBusinessObject
  implements Contact {
    //~ Champs d'instance ------------------------------------------------------

    private Collection adresses              = new HashSet();
    private Collection adressesElectroniques = new HashSet();
    private Collection telephones            = new HashSet();
    private Identite   identite;
    private String     commentaire;

    //~ Constructeurs ----------------------------------------------------------

    public AbstractContact() {
    }


    public AbstractContact(final Identite identite) {
        this();
        setIdentite(identite);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAdresses(Collection adresses) {
        this.adresses = adresses;
    }


    public Collection getAdresses() {
        return adresses;
    }


    public void setAdressesElectroniques(Collection adressesElectroniques) {
        this.adressesElectroniques = adressesElectroniques;
    }


    public Collection getAdressesElectroniques() {
        return adressesElectroniques;
    }


    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property type="text"
     */
    public String getCommentaire() {
        return commentaire;
    }


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


    public void setTelephones(Collection telephones) {
        this.telephones = telephones;
    }


    public Collection getTelephones() {
        return telephones;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractContact)) {
            return false;
        }
        final AbstractContact contact = (AbstractContact) obj;

        return new EqualsBuilder().appendSuper(super.equals(obj))
                                  .append(adresses, contact.adresses)
                                  .append(adressesElectroniques,
            contact.adressesElectroniques).append(telephones, contact.telephones)
                                  .append(identite, contact.identite)
                                  .append(commentaire, contact.commentaire)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode())
                                    .append(adresses)
                                    .append(adressesElectroniques)
                                    .append(telephones).append(identite)
                                    .append(commentaire).toHashCode();
    }
}
