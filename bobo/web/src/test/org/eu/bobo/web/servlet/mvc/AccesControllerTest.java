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


package org.eu.bobo.web.servlet.mvc;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.SecureContextImpl;
import net.sf.acegisecurity.providers.TestingAuthenticationToken;

import org.springframework.mock.web.MockHttpSession;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/01/24 10:03:48 $
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
