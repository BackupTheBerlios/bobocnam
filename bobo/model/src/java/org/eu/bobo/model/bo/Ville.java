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
 * @version $Revision: 1.3 $, $Date: 2005/02/24 09:08:35 $
 *
 * @hibernate:class
 */
public class Ville extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Long   villeId;
    private Pays   pays;
    private String codePostal;
    private String nom;

    //~ Constructeurs ----------------------------------------------------------

    public Ville() {
        super();
    }


    public Ville(final Pays pays, final String codePostal, final String nom) {
        this();
        setPays(pays);
        setCodePostal(codePostal);
        setNom(nom);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="code_postal" length="16" not-null="true"
     */
    public String getCodePostal() {
        return codePostal;
    }


    public Serializable getId() {
        return getVilleId();
    }


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


    public void setPays(Pays pays) {
        this.pays = pays;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:many-to-one column="pays_id" not-null="true"
     */
    public Pays getPays() {
        // si l'on met un attribut cascade="save-update" sur cette propriété,
        // le chargement et la sauvegarde des objets contenant des villes
        // échouent dans les tests unitaires
        return pays;
    }


    public void setVilleId(Long villeId) {
        this.villeId = villeId;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="ville_id" generator-class="native"
     */
    public Long getVilleId() {
        return villeId;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Ville)) {
            return false;
        }
        final Ville ville = (Ville) obj;

        return new EqualsBuilder().append(nom, ville.nom)
                                  .append(pays, ville.pays)
                                  .append(codePostal, ville.codePostal)
                                  .isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(nom).append(pays).append(codePostal)
                                    .toHashCode();
    }
}
