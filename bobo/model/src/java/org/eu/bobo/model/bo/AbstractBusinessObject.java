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


package org.eu.bobo.model.bo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.model.BaseObject;

import java.io.Serializable;


/**
 * Impl�mentation abstraite d'un objet m�tier.
 *
 * @author alex
 * @version $Revision: 1.6 $, $Date: 2005/04/24 22:16:09 $
 */
public abstract class AbstractBusinessObject extends BaseObject
  implements BusinessObject, Serializable {
    //~ Initialisateurs et champs de classe ------------------------------------

    private static final Long VERSION_UNSAVED_VALUE = new Long(-1);

    //~ Champs d'instance ------------------------------------------------------

    private final transient Log log     = LogFactory.getLog(getClass());
    private Long                version = VERSION_UNSAVED_VALUE;

    //~ M�thodes ---------------------------------------------------------------

    public void setVersion(Long version) {
        this.version = version;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @hibernate:version unsaved-value="negative"
     */
    public Long getVersion() {
        return version;
    }


    public boolean equals(Object obj) {
        hashCodeEqualsWarning();

        return super.equals(obj);
    }


    public int hashCode() {
        hashCodeEqualsWarning();

        return super.hashCode();
    }


    private void hashCodeEqualsWarning() {
        if (log.isWarnEnabled()) {
            log.warn(
                "Les m�thodes equals et hashCode doivent �tre red�finies dans chaque classe d�rivant de " +
                AbstractBusinessObject.class.getName() +
                ", en incluant toutes les propri�t�s sauf la cl� utilis�e par l'ORM en base de donn�es. " +
                "L'impl�mentation de ces m�thodes ne doit pas utiliser l'impl�mentation originale de la classe Object.");
        }
    }
}
