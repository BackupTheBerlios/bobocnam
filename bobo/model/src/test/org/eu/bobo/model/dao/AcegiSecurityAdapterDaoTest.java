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

import junit.framework.TestCase;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.UserDetails;

import org.easymock.MockControl;

import org.eu.bobo.model.bo.Autorite;
import org.eu.bobo.model.bo.Utilisateur;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:22:37 $
 */
public class AcegiSecurityAdapterDaoTest extends TestCase {
    //~ Champs d'instance ------------------------------------------------------

    private AcegiSecurityAdapterDao acegiSecurityAdapter;
    private MockControl             control;
    private UtilisateurDao          mock;

    //~ Méthodes ---------------------------------------------------------------

    public void testConstructeur() {
        boolean error = false;
        try {
            new AcegiSecurityAdapterDao(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testLoadByUserNameInconnu() {
        final String login = "test1";
        mock.findByLogin(login);
        control.setReturnValue(null);
        control.replay();

        boolean error = false;
        try {
            acegiSecurityAdapter.loadUserByUsername(login);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);

        control.verify();
    }


    public void testLoadUserByUserNameAvecCompteVerrouilleNull() {
        final String      login       = "alex";
        final Utilisateur utilisateur = new Utilisateur(login, "abc");
        utilisateur.setCompteVerrouille(null);
        control.expectAndReturn(mock.findByLogin(login), utilisateur);
        control.replay();

        final UserDetails util = acegiSecurityAdapter.loadUserByUsername(login);

        assertNotNull(util);
        assertEquals(login, util.getUsername());
        assertTrue(util.isEnabled());

        control.verify();
    }


    public void testLoadUserByUserNameConnu() {
        final String login = "alex";
        control.expectAndReturn(mock.findByLogin(login),
            new Utilisateur(login, "abc"));
        control.replay();

        final UserDetails util = acegiSecurityAdapter.loadUserByUsername(login);

        assertNotNull(util);
        assertEquals(login, util.getUsername());

        control.verify();
    }


    public void testLoadUserByUserNameConnuAvecAutorites() {
        final String      login          = "root";
        final String      password       = "abc";
        final boolean     enabled        = true;
        final String      adminAuthority = "admin";

        final Utilisateur utilisateur = new Utilisateur(login, password);
        utilisateur.setCompteVerrouille(Boolean.valueOf(enabled));
        utilisateur.getAutorites().add(new Autorite(adminAuthority));

        control.expectAndReturn(mock.findByLogin(login), utilisateur);
        control.replay();

        final UserDetails util = acegiSecurityAdapter.loadUserByUsername(login);

        assertNotNull(util);
        assertEquals(login, util.getUsername());

        final GrantedAuthority[] authorities = util.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.length);

        final GrantedAuthority authority = authorities[0];
        assertNotNull(authority);
        assertEquals(adminAuthority, authority.getAuthority());

        control.verify();
    }


    public void testLoadUserByUserNameNull() {
        boolean error = false;
        try {
            acegiSecurityAdapter.loadUserByUsername(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    protected void setUp() throws Exception {
        super.setUp();
        control                  = MockControl.createControl(UtilisateurDao.class);
        mock                     = (UtilisateurDao) control.getMock();
        acegiSecurityAdapter     = new AcegiSecurityAdapterDao(mock);
    }


    protected void tearDown() throws Exception {
        acegiSecurityAdapter     = null;
        mock                     = null;
        control                  = null;
        super.tearDown();
    }
}
