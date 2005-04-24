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


package org.eu.bobo.model;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:16:09 $
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
        return EqualsBuilder.reflectionEquals(this, o);
    }


    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
