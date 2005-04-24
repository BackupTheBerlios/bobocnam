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

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.MatchMode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.bo.reservation.avion.ReservationVol;
import org.eu.bobo.model.bo.reservation.avion.Vol;
import org.eu.bobo.model.dao.VolDao;

import org.springframework.orm.hibernate.HibernateCallback;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.7 $, $Date: 2005/04/24 22:17:00 $
 */
public class VolHibernateDao extends AbstractHibernateDao implements VolDao {
    //~ Champs d'instance ------------------------------------------------------

    private final Log log = LogFactory.getLog(getClass());

    //~ Constructeurs ----------------------------------------------------------

    public VolHibernateDao() {
        super(Vol.class);
    }

    //~ Méthodes ---------------------------------------------------------------

    public Integer getNbPlacesEnVenteDisponibles(final Vol vol) {
        if (vol == null) {
            throw new IllegalArgumentException("vol est requis");
        }

        return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    // TODO faire une implémentation plus rapide 
                    int        nbPlaces = 0;

                    final List reservations = session.createQuery(
                            "from reservation in class " +
                            ReservationVol.class.getName() +
                            " where reservation.annule = false").list();
                    for (final Iterator i = reservations.iterator();
                            i.hasNext();) {
                        final ReservationVol reservation = (ReservationVol) i.next();

                        for (final Iterator j = reservation.getVols().iterator();
                                j.hasNext();) {
                            final Vol volResa = (Vol) j.next();
                            if (vol.equals(volResa)) {
                                ++nbPlaces;
                            }
                        }
                    }

                    return new Integer(vol.getNbPlacesEnVente().intValue() -
                        nbPlaces);
                }
            });
    }


    public List findByAeroportPeriode(final Aeroport aeroportDepart,
        final Aeroport aeroportArrivee, final Periode periode) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final Criteria crit = session.createCriteria(Vol.class);

                    if ((aeroportDepart != null) || (aeroportArrivee != null)) {
                        final Criteria volGeneriqueCrit = crit.createCriteria(
                                "volGenerique");

                        if (aeroportDepart != null) {
                            volGeneriqueCrit.add(Expression.eq(
                                    "aeroportDepart", aeroportDepart));
                        }
                        if (aeroportArrivee != null) {
                            volGeneriqueCrit.add(Expression.eq(
                                    "aeroportArrivee", aeroportArrivee));
                        }
                    }
                    if (periode != null) {
                        if (periode.getDateDebut() != null) {
                            crit.add(Expression.ge("periode.dateDebut",
                                    periode.getDateDebut()));
                        }
                        if (periode.getDateFin() != null) {
                            crit.add(Expression.le("periode.dateFin",
                                    periode.getDateFin()));
                        }
                    }

                    return crit.list();
                }
            });
    }


    public List findByNumero(final String numero) {
        if (numero == null) {
            throw new IllegalArgumentException("numero est requis");
        }

        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final List list = new ArrayList();

                    // l'implémentation est simple, mais demande à être optimisée :
                    // on recherche parmi tous les vols ceux pour qui la propriété
                    // numéro correspond au paramètre passé en argument
                    final List tousLesVols = findAll();
                    for (final Iterator i = tousLesVols.iterator();
                            i.hasNext();) {
                        final Vol vol = (Vol) i.next();
                        if (numero.equalsIgnoreCase(vol.getVolGenerique()
                                                           .getNumero())) {
                            list.add(vol);
                        }
                    }

                    return list;
                }
            });
    }


    public List findByVillePeriode(final String villeDepart,
        final String villeArrivee, final Periode periode) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final Criteria crit             = session.createCriteria(Vol.class);
                    final Criteria volGeneriqueCrit = crit.createCriteria(
                            "volGenerique");

                    if (villeDepart != null) {
                        volGeneriqueCrit.createCriteria("aeroportDepart")
                                        .createCriteria("ville").add(Expression.like(
                                "nom", villeDepart, MatchMode.ANYWHERE));
                    }
                    if (villeArrivee != null) {
                        volGeneriqueCrit.createCriteria("aeroportArrivee")
                                        .createCriteria("ville").add(Expression.like(
                                "nom", villeArrivee, MatchMode.ANYWHERE));
                    }
                    if (periode != null) {
                        if (periode.getDateDebut() != null) {
                            crit.add(Expression.ge("periode.dateDebut",
                                    periode.getDateDebut()));
                        }
                        if (periode.getDateFin() != null) {
                            crit.add(Expression.le("periode.dateFin",
                                    periode.getDateFin()));
                        }
                    }

                    return crit.list();
                }
            });
    }
}
