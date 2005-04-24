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


package org.eu.bobo.web.util;

import junit.framework.TestCase;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.security.SecureContext;
import net.sf.acegisecurity.context.security.SecureContextImpl;
import net.sf.acegisecurity.providers.TestingAuthenticationToken;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:18:59 $
 */
public class SecureContextUtilsTest extends TestCase {
    //~ Méthodes ---------------------------------------------------------------

    public void testGetAuthentication() {
        final SecureContextImpl secureContext  = new SecureContextImpl();
        final Authentication    authentication = new TestingAuthenticationToken("admin",
                "admin", new GrantedAuthority[0]);
        secureContext.setAuthentication(authentication);
        ContextHolder.setContext(secureContext);
        assertEquals(authentication, SecureContextUtils.getAuthentication());
    }


    public void testGetAuthenticationNull() {
        ContextHolder.setContext(new SecureContextImpl());
        boolean error = false;
        try {
            SecureContextUtils.getAuthentication();
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testGetAuthenticationSecureContextNull() {
        ContextHolder.setContext(null);
        boolean error = false;
        try {
            SecureContextUtils.getAuthentication();
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testGetSecureContext() {
        final SecureContext secureContext = new SecureContextImpl();
        ContextHolder.setContext(secureContext);
        assertEquals(secureContext, SecureContextUtils.getSecureContext());
    }


    public void testGetSecureContextNull() {
        ContextHolder.setContext(null);
        boolean error = false;
        try {
            SecureContextUtils.getSecureContext();
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }
}
