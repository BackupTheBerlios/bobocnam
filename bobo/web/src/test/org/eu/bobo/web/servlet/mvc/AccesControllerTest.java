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


package org.eu.bobo.web.servlet.mvc;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.security.SecureContextImpl;
import net.sf.acegisecurity.providers.TestingAuthenticationToken;

import org.springframework.mock.web.MockHttpSession;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:18:59 $
 */
public class AccesControllerTest extends AbstractControllerTest {
    //~ Champs d'instance ------------------------------------------------------

    private AccesController accesController;

    //~ Méthodes ---------------------------------------------------------------

    public void testConnexion() {
        final ModelAndView mav = accesController.connexion(newGet("/acces/connexion.html"),
                null);
        assertNotNull(mav);
        assertEquals("acces/connexion", mav.getViewName());
    }


    public void testDeconnexion() {
        final Authentication    authentication = new TestingAuthenticationToken("admin",
                "admin", new GrantedAuthority[0]);
        final SecureContextImpl secureContext = new SecureContextImpl();
        secureContext.setAuthentication(authentication);
        ContextHolder.setContext(secureContext);

        final MockHttpSession session = new MockHttpSession();
        assertFalse(session.isInvalid());

        final ModelAndView mav = accesController.deconnexion(newGet("/acces/deconnexion.html"),
                null, session);
        assertNotNull(mav);
        assertEquals("acces/deconnexion", mav.getViewName());
        assertTrue(session.isInvalid());
        assertNull(ContextHolder.getContext());
    }


    public void testDeconnexionNotAuthenticated() {
        ContextHolder.setContext(null);
        final ModelAndView mav = accesController.deconnexion(newGet("/acces/deconnexion.html"),
                null, new MockHttpSession());
        assertNotNull(mav);
        assertEquals("redirect:/", mav.getViewName());
    }


    public void testErreur() {
        final ModelAndView mav = accesController.erreur(newGet("/acces/erreur.html"),
                null);
        assertNotNull(mav);
        assertEquals("acces/connexion", mav.getViewName());

        final Map model = mav.getModel();
        assertNotNull(model);
        assertEquals(Boolean.TRUE, model.get("erreur"));
    }


    protected void setUp() throws Exception {
        super.setUp();
        accesController = new AccesController();
    }


    protected void tearDown() throws Exception {
        accesController = null;
        super.tearDown();
    }
}
