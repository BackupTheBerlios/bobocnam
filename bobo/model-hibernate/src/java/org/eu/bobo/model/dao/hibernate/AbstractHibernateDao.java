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

import net.sf.hibernate.HibernateException;
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
import java.util.Iterator;
import java.util.List;


/**
 * Implémentation abstraite de <tt>Dao</tt> et <tt>FinderDao</tt> pour
 * Hibernate.
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/13 13:38:06 $
 */
public abstract class AbstractHibernateDao extends HibernateDaoSupport
  implements Dao, FinderDao {
    //~ Champs d'instance ------------------------------------------------------

    protected final Class clazz;

    //~ Constructeurs ----------------------------------------------------------

    public AbstractHibernateDao(final Class clazz) {
        if (clazz == null) {
            throw new NullPointerException("clazz == null");
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


    protected List findByProperty(final String property, final String value) {
        return findByProperty(property, value, false);
    }


    protected List findByProperty(final String property, final String value,
        final boolean caseSensitive) {
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
        final String query = "from obj in class " + clazz.getName() +
            " where obj." + property + "=?";

        return getHibernateTemplate().find(query, value);
    }
}
