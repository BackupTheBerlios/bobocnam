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

import org.eu.bobo.model.bo.Pays;
import org.eu.bobo.model.dao.PaysDao;

import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/02/20 15:11:14 $
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
