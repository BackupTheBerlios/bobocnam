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


package org.eu.bobo.model.dao;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.GrantedAuthorityImpl;
import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.dao.AuthenticationDao;
import net.sf.acegisecurity.providers.dao.User;
import net.sf.acegisecurity.providers.dao.UsernameNotFoundException;

import org.eu.bobo.model.bo.Autorite;
import org.eu.bobo.model.bo.Utilisateur;

import org.springframework.dao.DataAccessException;

import java.util.Iterator;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.6 $, $Date: 2005/04/24 22:22:37 $
 */
public class AcegiSecurityAdapterDao implements AuthenticationDao {
    //~ Champs d'instance ------------------------------------------------------

    private final UtilisateurDao utilisateurDao;

    //~ Constructeurs ----------------------------------------------------------

    public AcegiSecurityAdapterDao(final UtilisateurDao utilisateurDao) {
        if (utilisateurDao == null) {
            throw new IllegalArgumentException("utilisateurDao est requis");
        }
        this.utilisateurDao = utilisateurDao;
    }

    //~ Méthodes ---------------------------------------------------------------

    public UserDetails loadUserByUsername(String userLogin)
      throws UsernameNotFoundException, DataAccessException {
        if (userLogin == null) {
            throw new IllegalArgumentException("userLogin == null");
        }

        final Utilisateur user = utilisateurDao.findByLogin(userLogin);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur inconnu: " +
                userLogin);
        }

        final String  login    = user.getLogin();
        final String  password = user.getMotDePasse();
        final boolean enabled;
        if (user.getCompteVerrouille() == null) {
            enabled = true;
        } else {
            enabled = !user.getCompteVerrouille().booleanValue();
        }

        final GrantedAuthority[] authorities = new GrantedAuthority[user.getAutorites()
                                                                        .size()];
        final boolean            accountNonExpired     = true;
        final boolean            credentialsNonExpired = true;
        final boolean            accountNonLocked      = true;

        int                      index = 0;
        for (final Iterator i = user.getAutorites().iterator(); i.hasNext();) {
            final Autorite autorite = (Autorite) i.next();
            authorities[index++] = new GrantedAuthorityImpl(autorite.getNom());
        }

        return new User(login, password, enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked, authorities);
    }
}
