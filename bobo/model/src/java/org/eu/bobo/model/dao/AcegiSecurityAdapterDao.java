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
 * @version $Revision: 1.4 $, $Date: 2005/01/26 14:13:44 $
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

        int                      index = 0;
        for (final Iterator i = user.getAutorites().iterator(); i.hasNext();) {
            final Autorite autorite = (Autorite) i.next();
            authorities[index++] = new GrantedAuthorityImpl(autorite.getNom());
        }

        return new User(login, password, enabled, accountNonExpired,
            credentialsNonExpired, authorities);
    }
}
