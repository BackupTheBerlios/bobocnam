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


package org.eu.bobo.model.bo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:16:09 $
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
