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


package org.eu.bobo.model.dao.hibernate;

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.bo.reservation.avion.Ligne;
import org.eu.bobo.model.bo.reservation.avion.Vol;
import org.eu.bobo.model.dao.LigneDao;

import java.util.ArrayList;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/03/14 00:19:35 $
 */
public class LigneHibernateDao extends AbstractHibernateDao implements LigneDao {
    //~ Constructeurs ----------------------------------------------------------

    public LigneHibernateDao() {
        super(Ligne.class);
    }

    //~ Méthodes ---------------------------------------------------------------

    public List findByAeroportValidite(final Aeroport aeroportDepart,
        final Aeroport aeroportArrivee, final Periode validite) {
        final List         params = new ArrayList(4);

        final StringBuffer query = new StringBuffer(
                "select ligne from ligne in class " + Ligne.class.getName() +
                ", vol in class " + Vol.class +
                " where ligne.ligneId is not null");
        if (validite != null) {
            if (validite.getDateDebut() != null) {
                query.append(" and ligne.validite.dateDebut >= ?");
                params.add(validite.getDateDebut());
            }
            if (validite.getDateFin() != null) {
                query.append(" and ligne.validite.dateFin <= ?");
                params.add(validite.getDateFin());
            }
        }
        if (aeroportDepart != null) {
            query.append(" and vol.volGenerique.aeroportDepart = ?");
            params.add(aeroportDepart);
        }
        if (aeroportArrivee != null) {
            query.append(" and vol.volGenerique.aeroportArrivee = ?");
            params.add(aeroportArrivee);
        }

        query.append(" and vol.volGenerique in ligne.volsGeneriques");

        return getHibernateTemplate().find(query.toString(), params);
    }
}
