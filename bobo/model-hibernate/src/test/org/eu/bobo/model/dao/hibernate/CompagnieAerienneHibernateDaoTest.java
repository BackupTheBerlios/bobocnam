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

import org.eu.bobo.model.bo.reservation.avion.CompagnieAerienne;
import org.eu.bobo.model.dao.CompagnieAerienneDao;

import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/03/13 00:54:59 $
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
