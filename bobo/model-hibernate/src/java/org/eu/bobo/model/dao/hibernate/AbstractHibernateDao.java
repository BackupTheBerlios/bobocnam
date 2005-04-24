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

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.eu.bobo.model.bo.BusinessObject;
import org.eu.bobo.model.dao.Dao;
import org.eu.bobo.model.dao.FinderDao;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Implémentation abstraite de <tt>Dao</tt> et <tt>FinderDao</tt> pour
 * Hibernate.
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:17:00 $
 */
public abstract class AbstractHibernateDao extends HibernateDaoSupport
  implements Dao, FinderDao {
    //~ Champs d'instance ------------------------------------------------------

    protected final Class clazz;

    //~ Constructeurs ----------------------------------------------------------

    public AbstractHibernateDao(final Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz est requis");
        }
        this.clazz = clazz;
    }

    //~ Méthodes ---------------------------------------------------------------

    public void create(final BusinessObject bo) {
        checkClass(bo);
        getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    session.save(bo);
                    session.flush();

                    return null;
                }
            });
    }


    public void delete(final BusinessObject bo) {
        checkClass(bo);
        getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    session.delete(bo);
                    session.flush();

                    return null;
                }
            });
    }


    public boolean exists(final Serializable id) {
        return getHibernateTemplate().get(clazz, id) != null;
    }


    public List findAll() {
        return getHibernateTemplate().loadAll(clazz);
    }


    public List findById(final Collection ids) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final List list = new ArrayList();
                    for (final Iterator i = ids.iterator(); i.hasNext();) {
                        final Serializable id = (Serializable) i.next();
                        list.add(session.load(clazz, id));
                    }

                    return list;
                }
            });
    }


    public BusinessObject findById(Serializable id) {
        return (BusinessObject) getHibernateTemplate().load(clazz, id);
    }


    public void update(final BusinessObject bo) {
        checkClass(bo);
        getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    session.update(bo);
                    session.flush();

                    return null;
                }
            });
    }


    protected void checkClass(BusinessObject bo) {
        if (!clazz.isInstance(bo)) {
            throw new IllegalArgumentException("L'objet n'est pas du type " +
                clazz.getName() + ": " + bo);
        }
    }


    protected Collection filter(final Collection collection, final String filter) {
        return (Collection) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final Collection result = session.filter(collection, filter);
                    session.flush();

                    return result;
                }
            });
    }


    protected List findByProperty(final String property, final List list) {
        if (property == null) {
            throw new IllegalArgumentException("property est requis");
        }
        if (list == null) {
            throw new IllegalArgumentException("list est requis");
        }

        if (list.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final Query query = session.createQuery(
                            "from obj in class " + clazz.getName() +
                            " where obj." + property + " in (:list)");
                    query.setParameterList("list", list);

                    return query.list();
                }
            });
    }


    protected List findByProperty(final String property, final String value) {
        return findByProperty(property, value, false);
    }


    protected List findByProperty(final String property, final String value,
        final boolean caseSensitive) {
        if (property == null) {
            throw new IllegalArgumentException("property est requis");
        }
        if (value == null) {
            throw new IllegalArgumentException("value est requis");
        }

        final String query;
        final String testValue;
        if (!caseSensitive) {
            query     = "from obj in class " + clazz.getName() +
                " where lower(obj." + property + ")=?";
            testValue = value.toLowerCase();
        } else {
            query     = "from obj in class " + clazz.getName() + " where obj." +
                property + "=?";
            testValue = value;
        }

        return getHibernateTemplate().find(query, testValue);
    }


    protected List findByProperty(final String property, final Object value) {
        if (property == null) {
            throw new IllegalArgumentException("property est requis");
        }
        if (value == null) {
            throw new IllegalArgumentException("value est requis");
        }

        final String query = "from obj in class " + clazz.getName() +
            " where obj." + property + "=?";

        return getHibernateTemplate().find(query, value);
    }
}
