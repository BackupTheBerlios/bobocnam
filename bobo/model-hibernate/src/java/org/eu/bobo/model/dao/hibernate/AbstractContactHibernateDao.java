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

import org.eu.bobo.model.bo.Adresse;
import org.eu.bobo.model.bo.Contact;
import org.eu.bobo.model.dao.ContactDao;

import org.springframework.orm.hibernate.HibernateCallback;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/13 13:38:06 $
 */
public abstract class AbstractContactHibernateDao extends AbstractHibernateDao
  implements ContactDao {
    //~ Constructeurs ----------------------------------------------------------

    public AbstractContactHibernateDao(final Class clazz) {
        super(clazz);
    }

    //~ Méthodes ---------------------------------------------------------------

    public List findByCodePostal(final String codePostal) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final List contacts = new ArrayList();

                    for (final Iterator i = session.iterate(
                                "from contact in class " + clazz); i.hasNext();) {
                        final Contact contact = (Contact) i.next();
                        for (final Iterator j = contact.getAdresses().iterator();
                                j.hasNext();) {
                            final Adresse adresse = (Adresse) j.next();
                            if (adresse.getCodePostal().equalsIgnoreCase(codePostal)) {
                                contacts.add(contact);
                            }
                        }
                    }

                    return contacts;
                }
            });
    }


    public List findByNom(String nom) {
        return findByProperty("identite.nom", nom);
    }


    public List findByNomCodePostalVillePays(final String nom,
        final String codePostal, final String ville, final String pays) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final List contacts = new ArrayList();

                    for (final Iterator i = session.iterate(
                                "from contact in class " + clazz); i.hasNext();) {
                        final Contact contact = (Contact) i.next();
                        if ((nom != null) &&
                                nom.equalsIgnoreCase(contact.getIdentite()
                                                                .getNom())) {
                            contacts.add(contact);

                            continue;
                        }

                        for (final Iterator j = contact.getAdresses().iterator();
                                j.hasNext();) {
                            final Adresse adresse = (Adresse) j.next();
                            if (((codePostal != null) &&
                                    codePostal.equalsIgnoreCase(
                                        adresse.getCodePostal())) ||
                                    (((ville != null) &&
                                    ville.equalsIgnoreCase(adresse.getVille())) ||
                                    ((pays != null) &&
                                    pays.equalsIgnoreCase(adresse.getPays())))) {
                                contacts.add(contact);

                                continue;
                            }
                        }
                    }

                    return contacts;
                }
            });
    }


    public List findByPays(final String pays) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final List contacts = new ArrayList();

                    for (final Iterator i = session.iterate(
                                "from contact in class " + clazz); i.hasNext();) {
                        final Contact contact = (Contact) i.next();
                        for (final Iterator j = contact.getAdresses().iterator();
                                j.hasNext();) {
                            final Adresse adresse = (Adresse) j.next();
                            if (adresse.getPays().equalsIgnoreCase(pays)) {
                                contacts.add(contact);
                            }
                        }
                    }

                    return contacts;
                }
            });
    }


    public List findByVille(final String ville) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                    final List contacts = new ArrayList();

                    for (final Iterator i = session.iterate(
                                "from contact in class " + clazz); i.hasNext();) {
                        final Contact contact = (Contact) i.next();
                        for (final Iterator j = contact.getAdresses().iterator();
                                j.hasNext();) {
                            final Adresse adresse = (Adresse) j.next();
                            if (adresse.getVille().equalsIgnoreCase(ville)) {
                                contacts.add(contact);
                            }
                        }
                    }

                    return contacts;
                }
            });
    }
}
