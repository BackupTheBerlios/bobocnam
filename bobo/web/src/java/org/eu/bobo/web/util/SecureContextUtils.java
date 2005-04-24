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

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.context.Context;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.security.SecureContext;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:18:59 $
 */
public final class SecureContextUtils {
    //~ Constructeurs ----------------------------------------------------------

    private SecureContextUtils() {
    }

    //~ Méthodes ---------------------------------------------------------------

    public static boolean isAuthenticated() {
        try {
            getAuthentication();
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    public static Authentication getAuthentication() {
        final SecureContext  secureContext  = getSecureContext();
        final Authentication authentication = secureContext.getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException(
                "Pas d'objet Authentication disponible dans le SecureContext");
        }

        return authentication;
    }


    public static SecureContext getSecureContext() {
        final Context context = (Context) ContextHolder.getContext();
        if ((context == null) || !(context instanceof SecureContext)) {
            throw new IllegalStateException("Pas de SecureContext disponible");
        }

        return (SecureContext) context;
    }
}
