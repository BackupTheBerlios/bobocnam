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


package org.eu.bobo.model.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.model.bo.Utilisateur;
import org.eu.bobo.model.dao.UtilisateurDao;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:17:00 $
 */
public class UtilisateurHibernateDao extends AbstractHibernateDao
  implements UtilisateurDao {
    //~ Champs d'instance ------------------------------------------------------

    private final Log log = LogFactory.getLog(getClass());

    //~ Constructeurs ----------------------------------------------------------

    public UtilisateurHibernateDao() {
        super(Utilisateur.class);
    }

    //~ Méthodes ---------------------------------------------------------------

    public Utilisateur findByLogin(String login) {
        final List list = findByProperty("login", login);
        if (list.isEmpty()) {
            if (log.isDebugEnabled()) {
                log.debug("Aucun utilisateur avec l'identifiant: " + login);
            }

            return null;
        }

        if (list.size() > 1) {
            throw new IllegalStateException(
                "Plusieurs utilisateurs ont le même identifiant: " + login);
        }

        return (Utilisateur) list.get(0);
    }


    public List findByNom(String nom) {
        return findByProperty("nom", nom);
    }
}
