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


package org.eu.bobo.model.bo.contact;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import org.eu.bobo.model.Identite;
import org.eu.bobo.model.bo.AbstractBusinessObject;

import java.util.Collection;
import java.util.HashSet;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 */
public abstract class AbstractContact extends AbstractBusinessObject
  implements Contact {
    //~ Champs d'instance ------------------------------------------------------

    private Collection adresses              = new HashSet(1);
    private Collection adressesElectroniques = new HashSet(1);
    private Collection telephones            = new HashSet(1);
    private Identite   identite;
    private String     commentaire;

    //~ Constructeurs ----------------------------------------------------------

    public AbstractContact() {
        super();
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

        return new EqualsBuilder().append(adresses, contact.adresses)
                                  .append(adressesElectroniques,
            contact.adressesElectroniques).append(telephones, contact.telephones)
                                  .append(identite, contact.identite)
                                  .append(commentaire, contact.commentaire)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(adresses)
                                    .append(adressesElectroniques)
                                    .append(telephones).append(identite)
                                    .append(commentaire).toHashCode();
    }
}
