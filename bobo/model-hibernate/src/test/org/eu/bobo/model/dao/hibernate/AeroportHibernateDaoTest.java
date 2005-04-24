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

import org.eu.bobo.model.bo.Ville;
import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;
import org.eu.bobo.model.dao.VilleDao;

import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:17:00 $
 */
public class AeroportHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private AeroportDao aeroportDao;
    private VilleDao    villeDao;

    //~ Méthodes ---------------------------------------------------------------

    public void testFindById() {
        final String   id       = "CDG";
        final Aeroport aeroport = (Aeroport) aeroportDao.findById(id);
        assertNotNull(aeroport);
        assertEquals(id, aeroport.getAeroportId());
    }


    public void testFindByNomVille() {
        final String nomVille = "Toulon";
        final List   list = aeroportDao.findByNomVille(nomVille);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final Aeroport aeroport = (Aeroport) i.next();
            assertNotNull(aeroport);
            assertEquals(nomVille, aeroport.getVille().getNom());
        }
    }


    public void testFindByVille() {
        final Ville ville = (Ville) villeDao.findById(new Long(10000));
        final List  list = aeroportDao.findByVille(ville);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final Aeroport aeroport = (Aeroport) i.next();
            assertNotNull(aeroport);
            assertEquals(ville, aeroport.getVille());
        }
    }


    protected void setUp() throws Exception {
        super.setUp();
        aeroportDao     = (AeroportDao) getBeanOfType(AeroportDao.class);
        villeDao        = (VilleDao) getBeanOfType(VilleDao.class);
    }


    protected void tearDown() throws Exception {
        aeroportDao     = null;
        villeDao        = null;
        super.tearDown();
    }
}
