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


package org.eu.bobo.model.bo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.model.BaseObject;

import java.io.Serializable;


/**
 * Impl�mentation abstraite d'un objet m�tier.
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/02/14 22:19:05 $
 */
public abstract class AbstractBusinessObject extends BaseObject
  implements BusinessObject, Serializable {
    //~ Initialisateurs et champs de classe ------------------------------------

    private static final Long VERSION_UNSAVED_VALUE = new Long(-1);
    private static final long serialVersionUID = 1;

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
