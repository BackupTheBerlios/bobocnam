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

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:18:59 $
 */
public abstract class AbstractControllerTest extends TestCase {
    //~ Méthodes ---------------------------------------------------------------

    protected MockHttpServletRequest newGet(String url) {
        return new MockHttpServletRequest(null, "GET", url);
    }


    protected MockHttpServletRequest newPost(String url) {
        return new MockHttpServletRequest(null, "POST", url);
    }


    protected void objectToRequestParameters(Object o,
        MockHttpServletRequest request) throws Exception {
        final Class   clazz  = o.getClass();
        final Field[] fields = clazz.getDeclaredFields();

        AccessibleObject.setAccessible(fields, true);

        for (int i = 0; i < fields.length; i++) {
            if (!(fields[i].get(o) instanceof List)) {
                request.addParameter(fields[i].getName(),
                    String.valueOf(fields[i].get(o)));
            }
        }
    }
}
