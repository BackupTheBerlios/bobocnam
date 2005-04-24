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

import org.eu.bobo.model.bo.Ville;
import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;

import org.springframework.orm.hibernate.HibernateCallback;

import java.sql.SQLException;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:17:00 $
 */
public class AeroportHibernateDao extends AbstractHibernateDao
  implements AeroportDao {
    //~ Constructeurs ----------------------------------------------------------

    public AeroportHibernateDao() {
        super(Aeroport.class);
    }

    //~ Méthodes ---------------------------------------------------------------

    public List findByNomVille(final String nomVille) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final Criteria crit = session.createCriteria(Aeroport.class);
                    crit.createCriteria("ville").add(Expression.ilike("nom",
                            nomVille, MatchMode.ANYWHERE));

                    return crit.list();
                }
            });
    }


    public List findByVille(Ville ville) {
        return findByProperty("ville", ville);
    }
}
