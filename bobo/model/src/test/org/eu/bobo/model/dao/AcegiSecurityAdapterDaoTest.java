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
 * @version $Revision: 1.2 $, $Date: 2005/01/26 14:14:28 $
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
    }


    public void testLoadUserByUserNameConnu() {
        final String login = "alex";
        control.expectAndReturn(mock.findByLogin(login),
            new Utilisateur(login, "abc"));
        control.replay();

        final UserDetails util = acegiSecurityAdapter.loadUserByUsername(login);

        assertNotNull(util);
        assertEquals(login, util.getUsername());
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
    }


    public void testLoadUserByUserNameNull() {
        control.expectAndReturn(mock.findByLogin(null), null);
        control.replay();

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
