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


package org.eu.bobo.web.util;

import junit.framework.TestCase;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.SecureContext;
import net.sf.acegisecurity.context.SecureContextImpl;
import net.sf.acegisecurity.providers.TestingAuthenticationToken;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/20 10:28:06 $
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
