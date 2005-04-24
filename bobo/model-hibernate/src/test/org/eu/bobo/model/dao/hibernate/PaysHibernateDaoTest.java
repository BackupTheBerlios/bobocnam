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

import org.eu.bobo.model.bo.Pays;
import org.eu.bobo.model.dao.PaysDao;

import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:17:00 $
 */
public class PaysHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private PaysDao paysDao;

    //~ Méthodes ---------------------------------------------------------------

    public void testFindByNom() {
        final String nom  = "France";
        final List   list = paysDao.findByNom(nom);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final Pays pays = (Pays) i.next();
            assertNotNull(pays);
            assertEquals(nom, pays.getNom());
        }
    }


    protected void setUp() throws Exception {
        super.setUp();
        paysDao = (PaysDao) getBeanOfType(PaysDao.class);
    }


    protected void tearDown() throws Exception {
        paysDao = null;
        super.tearDown();
    }
}
