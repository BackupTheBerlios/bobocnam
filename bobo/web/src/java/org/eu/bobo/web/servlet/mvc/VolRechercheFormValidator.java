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

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:18:59 $
 */
public class VolRechercheFormValidator implements Validator {
    //~ Méthodes ---------------------------------------------------------------

    public boolean supports(Class clazz) {
        return VolRechercheForm.class.isAssignableFrom(clazz);
    }


    public void validate(Object obj, Errors errors) {
        final VolRechercheForm form = (VolRechercheForm) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aeroportDepart",
            "required", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aeroportArrivee",
            "required", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateDepart",
            "required", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateArrivee",
            "required", "required");
        ValidationUtils.rejectIfEmpty(errors, "allerSimple", "required",
            "required");
        ValidationUtils.rejectIfEmpty(errors, "volDirect", "required",
            "required");
    }
}
