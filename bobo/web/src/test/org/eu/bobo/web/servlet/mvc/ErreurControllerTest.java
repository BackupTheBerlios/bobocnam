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

import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/20 10:28:06 $
 */
public class ErreurControllerTest extends AbstractControllerTest {
    //~ Champs d'instance ------------------------------------------------------

    private ErreurController erreurController;

    //~ M�thodes ---------------------------------------------------------------

    public void testErreur404() {
        final MockHttpServletRequest req = newGet("/acces/connexion.html");
        final ModelAndView           mav = erreurController.erreur404(req, null);
        assertNotNull(mav);
        assertEquals("erreur/404", mav.getViewName());

        final Map model = mav.getModel();
        assertNotNull(model);
        assertEquals(req.getRequestURL().toString(), model.get("url"));
    }


    public void testErreur500() {
        final ModelAndView mav = erreurController.erreur500(newGet("/acces/connexion.html"),
                null);
        assertNotNull(mav);
        assertEquals("erreur/500", mav.getViewName());

        final Map model = mav.getModel();
        assertNotNull(model);
        assertNotNull(model.get("date"));
    }


    protected void setUp() throws Exception {
        super.setUp();
        erreurController = new ErreurController();
    }


    protected void tearDown() throws Exception {
        erreurController = null;
        super.tearDown();
    }
}