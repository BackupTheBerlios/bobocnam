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

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.MatchMode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eu.bobo.model.bo.Vol;
import org.eu.bobo.model.dao.VolDao;

import org.springframework.orm.hibernate.HibernateCallback;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/02/06 20:22:58 $
 */
public class VolHibernateDao extends AbstractHibernateDao implements VolDao {
    //~ Champs d'instance ------------------------------------------------------

    private final Log log = LogFactory.getLog(getClass());

    //~ Constructeurs ----------------------------------------------------------

    public VolHibernateDao() {
        super(Vol.class);
    }

    //~ M�thodes ---------------------------------------------------------------

    public List findByNumero(final String numero) {
        if (numero == null) {
            throw new IllegalArgumentException("numero est requis");
        }

        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final List list = new ArrayList();

                    // l'impl�mentation est simple, mais demande � �tre optimis�e :
                    // on recherche parmi tous les vols ceux pour qui la propri�t�
                    // num�ro correspond au param�tre pass� en argument
                    final List tousLesVols = findAll();
                    for (final Iterator i = tousLesVols.iterator();
                            i.hasNext();) {
                        final Vol vol = (Vol) i.next();
                        if (numero.equalsIgnoreCase(vol.getNumero())) {
                            list.add(vol);
                        }
                    }

                    return list;
                }
            });
    }


    public List findByVilleDate(final String villeDepart,
        final String villeArrivee, final Date dateDepart, final Date dateArrivee) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final Criteria crit = session.createCriteria(Vol.class);
                    if (villeDepart != null) {
                        crit.createCriteria("villeDepart").add(Expression.like(
                                "nom", villeDepart, MatchMode.ANYWHERE));
                    }
                    if (villeArrivee != null) {
                        crit.createCriteria("villeArrivee").add(Expression.like(
                                "nom", villeArrivee, MatchMode.ANYWHERE));
                    }
                    if (dateDepart != null) {
                        crit.add(Expression.ge("dateDepart", dateDepart));
                    }
                    if (dateArrivee != null) {
                        crit.add(Expression.le("dateArrivee", dateArrivee));
                    }

                    return crit.list();
                }
            });
    }
}