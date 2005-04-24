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
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:17:00 $
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
