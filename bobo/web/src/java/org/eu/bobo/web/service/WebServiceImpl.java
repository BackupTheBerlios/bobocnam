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


package org.eu.bobo.web.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.model.bo.Aeroport;
import org.eu.bobo.model.bo.BusinessObject;
import org.eu.bobo.model.bo.Vol;
import org.eu.bobo.model.dao.AeroportDao;
import org.eu.bobo.model.dao.VolDao;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/03/10 02:01:03 $
 */
public class WebServiceImpl extends ServletEndpointSupport implements WebService {
    //~ Champs d'instance ------------------------------------------------------

    private final Log   log         = LogFactory.getLog(getClass());
    private AeroportDao aeroportDao;
    private VolDao      volDao;

    //~ Méthodes ---------------------------------------------------------------

    public Aeroport getAeroport(String id) {
        return (Aeroport) aeroportDao.findById(id);
    }


    public Vol getVol(Number id) {
        return (Vol) volDao.findById(new Long(id.longValue()));
    }


    public String[] findAeroportsByVille(String ville) {
        return extractStringBusinessObjectIds(aeroportDao.findByNomVille(ville));
    }


    public Number[] findVolsByAeroportDate(String codeAeroportDepart,
        String codeAeroportArrivee, Date dateDepart, Date dateArrivee) {
        final Aeroport aeroportDepart  = (Aeroport) aeroportDao.findById(codeAeroportDepart);
        final Aeroport aeroportArrivee = (Aeroport) aeroportDao.findById(codeAeroportArrivee);

        return extractNumberBusinessObjectIds(volDao.findByAeroportDate(
                aeroportDepart, aeroportArrivee, dateDepart, dateArrivee));
    }


    public String version() {
        final String version = getMessage("build.version");
        final String build = getMessage("build.number");

        if (StringUtils.hasText(build)) {
            return version + "." + build.trim();
        }

        return version;
    }


    protected void onInit() throws ServiceException {
        aeroportDao     = (AeroportDao) getBeanOfType(AeroportDao.class);
        volDao          = (VolDao) getBeanOfType(VolDao.class);
    }


    private Object getBeanOfType(Class clazz) {
        final Map map = getWebApplicationContext().getBeansOfType(clazz);
        if (map.isEmpty()) {
            throw new IllegalStateException("Aucun bean de type " +
                clazz.getName() + " trouvé dans le contexte Spring");
        }

        final Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();

        if (map.size() > 1) {
            if (log.isWarnEnabled()) {
                log.warn("Plusieurs beans de type " + clazz.getName() +
                    " trouvé dans le contexte Spring: utilisation du bean nommé " +
                    entry.getKey());
            }
        }

        return entry.getValue();
    }


    private String getMessage(String code) {
        return getWebApplicationContext().getMessage(code, null,
            Locale.getDefault());
    }


    private Number[] extractNumberBusinessObjectIds(List list) {
        final Number[] ids   = new Number[list.size()];
        int            index = 0;
        for (final Iterator i = list.iterator(); i.hasNext(); ++index) {
            final BusinessObject bo = (BusinessObject) i.next();
            ids[index] = (Number) bo.getId();
        }

        return ids;
    }


    private String[] extractStringBusinessObjectIds(List list) {
        final String[] ids   = new String[list.size()];
        int            index = 0;
        for (final Iterator i = list.iterator(); i.hasNext(); ++index) {
            final BusinessObject bo = (BusinessObject) i.next();
            ids[index] = (String) bo.getId();
        }

        return ids;
    }
}
