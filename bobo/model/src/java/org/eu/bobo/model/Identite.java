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


package org.eu.bobo.model;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/13 13:35:56 $
 */
public class Identite extends BaseObject implements Comparable, Serializable {
    //~ Champs d'instance ------------------------------------------------------

    private String nom;
    private String prenom;
    private String prenom2;
    private String suffixe;
    private String titre;

    //~ Constructeurs ----------------------------------------------------------

    public Identite(final String nom) {
        this();
        setNom(nom);
    }


    public Identite() {
        super();
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="64" not-null="true"
     */
    public String getNom() {
        return nom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="64"
     */
    public String getPrenom() {
        return prenom;
    }


    public void setPrenom2(String prenom2) {
        this.prenom2 = prenom2;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="64"
     */
    public String getPrenom2() {
        return prenom2;
    }


    public void setSuffixe(String suffixe) {
        this.suffixe = suffixe;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="16"
     */
    public String getSuffixe() {
        return suffixe;
    }


    public void setTitre(String titre) {
        this.titre = titre;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="32"
     */
    public String getTitre() {
        return titre;
    }


    public int compareTo(Object obj) {
        return CompareToBuilder.reflectionCompare(this, obj);
    }


    public boolean equals(Object o) {
        if (!(o instanceof Identite)) {
            return false;
        }
        Identite identite = (Identite) o;

        return new EqualsBuilder().append(nom, identite.nom)
                                  .append(prenom, identite.prenom)
                                  .append(prenom2, identite.prenom2)
                                  .append(suffixe, identite.suffixe)
                                  .append(titre, identite.titre).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(nom).append(prenom).append(prenom2)
                                    .append(suffixe).append(titre).toHashCode();
    }
}
