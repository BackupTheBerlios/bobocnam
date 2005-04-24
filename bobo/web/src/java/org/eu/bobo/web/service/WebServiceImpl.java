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


package org.eu.bobo.web.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.BusinessObject;
import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.bo.reservation.avion.Vol;
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
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:18:59 $
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

        return extractNumberBusinessObjectIds(volDao.findByAeroportPeriode(
                aeroportDepart, aeroportArrivee,
                new Periode(dateDepart, dateArrivee)));
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
