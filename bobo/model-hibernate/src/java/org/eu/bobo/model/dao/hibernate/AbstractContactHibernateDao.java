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
import net.sf.hibernate.Session;

import org.eu.bobo.model.bo.Adresse;
import org.eu.bobo.model.bo.contact.Contact;
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
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:17:00 $
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
