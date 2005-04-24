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

import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:18:59 $
 */
public class ErreurControllerTest extends AbstractControllerTest {
    //~ Champs d'instance ------------------------------------------------------

    private ErreurController erreurController;

    //~ Méthodes ---------------------------------------------------------------

    public void testErreur404() {
        final MockHttpServletRequest req = newGet("/acces/connexion.html");
        final ModelAndView           mav = erreurController.erreur404(req, null);
        assertNotNull(mav);
        assertEquals("erreur/404", mav.getViewName());
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
