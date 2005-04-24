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
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:16:09 $
 *
 * @hibernate:class
 */
public class Utilisateur extends AbstractBusinessObject {
    //~ Champs d'instance ------------------------------------------------------

    private Boolean    compteVerrouille             = Boolean.FALSE;
    private Boolean    derniereConnexionReussie     = Boolean.FALSE;
    private Collection autorites                    = new HashSet(0);
    private Date       dateCreation                 = new Date();
    private Date       dateDerniereConnexion;
    private Date       dateDerniereConnexionEchouee;
    private Date       dateModification;
    private Integer    nbConnexionsEchouees         = NumberUtils.INTEGER_ZERO;
    private Long       utilisateurId;
    private Map        proprietes                   = new HashMap(0);
    private String     commentaire;
    private String     email;
    private String     login;
    private String     motDePasse;
    private String     nom;

    //~ Constructeurs ----------------------------------------------------------

    public Utilisateur() {
        super();
    }


    public Utilisateur(final String login, final String motDePasse) {
        this();
        setLogin(login);
        setMotDePasse(motDePasse);
    }

    //~ Méthodes ---------------------------------------------------------------

    public void setAutorites(Collection authorites) {
        this.autorites = authorites;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:set table="autorite_utilisateur" cascade="save-update"
     * @hibernate:collection-key column="util_id"
     * @hibernate:collection-many-to-many column="autorite_id"
     *            class="org.eu.bobo.model.bo.Autorite"
     */
    public Collection getAutorites() {
        return autorites;
    }


    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property type="text" length="1024"
     */
    public String getCommentaire() {
        return commentaire;
    }


    public void setCompteVerrouille(Boolean compteVerrouille) {
        this.compteVerrouille = compteVerrouille;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="compte_verrouille"
     */
    public Boolean getCompteVerrouille() {
        return compteVerrouille;
    }


    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_creat" not-null="true"
     */
    public Date getDateCreation() {
        return dateCreation;
    }


    public void setDateDerniereConnexion(Date dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_derniere_connexion"
     */
    public Date getDateDerniereConnexion() {
        return dateDerniereConnexion;
    }


    public void setDateDerniereConnexionEchouee(Date dateDernierEchec) {
        this.dateDerniereConnexionEchouee = dateDernierEchec;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_derniere_connexion_echouee"
     */
    public Date getDateDerniereConnexionEchouee() {
        return dateDerniereConnexionEchouee;
    }


    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="date_modif"
     */
    public Date getDateModification() {
        return dateModification;
    }


    public void setDerniereConnexionReussie(Boolean derniereConnexionReussie) {
        this.derniereConnexionReussie = derniereConnexionReussie;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="derniere_connexion_reussie"
     */
    public Boolean getDerniereConnexionReussie() {
        return derniereConnexionReussie;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="64"
     */
    public String getEmail() {
        return email;
    }


    public Serializable getId() {
        return getUtilisateurId();
    }


    public void setLogin(String login) {
        this.login = login;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="16" unique="true" not-null="true"
     */
    public String getLogin() {
        return login;
    }


    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="mot_de_passe" length="255" not-null="true"
     */
    public String getMotDePasse() {
        return motDePasse;
    }


    public void setNbConnexionsEchouees(Integer nbDernieresConnexionsEchouees) {
        this.nbConnexionsEchouees = nbDernieresConnexionsEchouees;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property column="nb_connexions_echouees"
     */
    public Integer getNbConnexionsEchouees() {
        return nbConnexionsEchouees;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:property length="64"
     */
    public String getNom() {
        return nom;
    }


    public void setProprietes(Map proprietes) {
        this.proprietes = proprietes;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:map table="propriete_utilisateur" order-by="nom"
     * @hibernate:collection-key column="util_id"
     * @hibernate:collection-index column="nom" type="string" length="128"
     * @hibernate:collection-element column="valeur" type="string"
     *            not-null="true" length="128"
     */
    public Map getProprietes() {
        return proprietes;
    }


    public void setUtilisateurId(Long id) {
        this.utilisateurId = id;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:id column="util_id" generator-class="native"
     */
    public Long getUtilisateurId() {
        return utilisateurId;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Utilisateur)) {
            return false;
        }
        final Utilisateur utilisateur = (Utilisateur) obj;

        return new EqualsBuilder().append(compteVerrouille,
            utilisateur.compteVerrouille)
                                  .append(derniereConnexionReussie,
            utilisateur.derniereConnexionReussie)
                                  .append(autorites, utilisateur.autorites)
                                  .append(proprietes, utilisateur.proprietes)
                                  .append(dateCreation, utilisateur.dateCreation)
                                  .append(dateDerniereConnexion,
            utilisateur.dateDerniereConnexion)
                                  .append(dateDerniereConnexionEchouee,
            utilisateur.dateDerniereConnexionEchouee)
                                  .append(dateModification,
            utilisateur.dateModification)
                                  .append(nbConnexionsEchouees,
            utilisateur.nbConnexionsEchouees)
                                  .append(commentaire, utilisateur.commentaire)
                                  .append(email, utilisateur.email)
                                  .append(login, utilisateur.login)
                                  .append(motDePasse, utilisateur.motDePasse)
                                  .append(nom, utilisateur.nom).isEquals();
    }


    public int hashCode() {
        return new HashCodeBuilder().append(compteVerrouille)
                                    .append(derniereConnexionReussie)
                                    .append(autorites).append(proprietes)
                                    .append(dateCreation)
                                    .append(dateDerniereConnexion)
                                    .append(dateDerniereConnexionEchouee)
                                    .append(dateModification)
                                    .append(nbConnexionsEchouees)
                                    .append(commentaire).append(email)
                                    .append(login).append(motDePasse).append(nom)
                                    .toHashCode();
    }
}
