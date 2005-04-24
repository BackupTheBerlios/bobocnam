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

import org.eu.bobo.model.bo.reservation.avion.CompagnieAerienne;
import org.eu.bobo.model.dao.CompagnieAerienneDao;

import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.4 $, $Date: 2005/04/24 22:17:00 $
 */
public class CompagnieAerienneHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private CompagnieAerienneDao compagnieAerienneDao;

    //~ Méthodes ---------------------------------------------------------------

    public void testFindById() {
        final String            code              = "AF";
        final CompagnieAerienne compagnieAerienne = (CompagnieAerienne) compagnieAerienneDao.findById(code);
        assertNotNull(compagnieAerienne);
        assertEquals(code, compagnieAerienne.getCompagnieAerienneId());
    }


    public void testFindByIdInexistant() {
        boolean error = false;
        try {
            compagnieAerienneDao.findById("WXCV");
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testFindByNom() {
        final String nom  = "Air France";
        final List   list = compagnieAerienneDao.findByNom(nom);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final CompagnieAerienne compagnieAerienne = (CompagnieAerienne) i.next();
            assertNotNull(compagnieAerienne);
            assertEquals(nom, compagnieAerienne.getNom());
        }
    }


    public void testFindByNomInexistant() {
        final List list = compagnieAerienneDao.findByNom("WXCV");
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }


    public void testFindByNomNull() {
        boolean error = false;
        try {
            compagnieAerienneDao.findByNom(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    protected void setUp() throws Exception {
        super.setUp();
        compagnieAerienneDao = (CompagnieAerienneDao) getBeanOfType(CompagnieAerienneDao.class);
    }


    protected void tearDown() throws Exception {
        compagnieAerienneDao = null;
        super.tearDown();
    }
}
