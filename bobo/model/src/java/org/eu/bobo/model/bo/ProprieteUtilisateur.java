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
 * @hibernate:class table="propriete_utilisateur"
 */
public class ProprieteUtilisateur extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Long        proprieteUtilisateurId;
    private String      nom;
    private String      valeur;
    private Utilisateur utilisateur;

    //~ Constructeurs ----------------------------------------------------------

    public ProprieteUtilisateur() {
        super();
    }


    public ProprieteUtilisateur(final Utilisateur utilisateur,
        final String nom, final String valeur) {
        this();
        setUtilisateur(utilisateur);
        setNom(nom);
        setValeur(valeur);
    }

    //~ Méthodes ---------------------------------------------------------------

    public Serializable getId() {
        return getProprieteUtilisateurId();
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property type="text" not-null="true"
     */
    public String getNom() {
        return nom;
    }


    public void setProprieteUtilisateurId(Long proprieteId) {
        this.proprieteUtilisateurId = proprieteId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="prop_util_id" generator-class="native"
     */
    public Long getProprieteUtilisateurId() {
        return proprieteUtilisateurId;
    }


    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="util_id" not-null="true"
     *            cascade="save-update"
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }


    public void setValeur(String valeur) {
        this.valeur = valeur;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property type="text" not-null="true"
     */
    public String getValeur() {
        return valeur;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof ProprieteUtilisateur)) {
            return false;
        }
        final ProprieteUtilisateur proprieteUtilisateur = (ProprieteUtilisateur) obj;

        return new EqualsBuilder().append(nom, proprieteUtilisateur.nom)
                                  .append(valeur, proprieteUtilisateur.valeur)
                                  .append(utilisateur,
            proprieteUtilisateur.utilisateur).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(nom).append(valeur)
                                    .append(utilisateur).toHashCode();
    }
}
