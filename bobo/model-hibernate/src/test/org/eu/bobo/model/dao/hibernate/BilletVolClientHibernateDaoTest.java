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

import org.eu.bobo.model.bo.BilletVolClient;
import org.eu.bobo.model.bo.Client;
import org.eu.bobo.model.bo.Vol;
import org.eu.bobo.model.dao.BilletVolClientDao;
import org.eu.bobo.model.dao.ClientDao;
import org.eu.bobo.model.dao.VolDao;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/02/08 08:01:38 $
 */
public class BilletVolClientHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private BilletVolClientDao billetVolClientDao;

    //~ Méthodes ---------------------------------------------------------------

    public void testFindByClient() {
        final ClientDao clientDao = (ClientDao) getBeanOfType(ClientDao.class);
        final Client    client = (Client) clientDao.findById(new Long(10000));

        final List      list = billetVolClientDao.findByClient(client);
        assertNotNull(list);
        assertEquals(1, list.size());

        final BilletVolClient billet = (BilletVolClient) list.get(0);
        assertNotNull(billet);
        assertEquals(client, billet.getClient());
    }


    public void testFindByClientNull() {
        boolean error = false;
        try {
            billetVolClientDao.findByClient(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testFindByNom() {
        final String nom  = "durand";
        final List   list = billetVolClientDao.findByNom(nom);
        assertNotNull(list);
        assertEquals(1, list.size());
    }


    public void testFindByNomInexistant() {
        final List list = billetVolClientDao.findByNom("WXCV");
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }


    public void testFindByNomNull() {
        boolean error = false;
        try {
            billetVolClientDao.findByNom(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testFindByVol() {
        final VolDao volDao = (VolDao) getBeanOfType(VolDao.class);
        final Vol    vol = (Vol) volDao.findById(new Long(10001));

        final List   list = billetVolClientDao.findByVol(vol);
        assertNotNull(list);
        assertEquals(1, list.size());

        final BilletVolClient billet = (BilletVolClient) list.get(0);
        assertEquals(vol, billet.getVol());
    }


    public void testFindByVolNull() {
        boolean error = false;
        try {
            billetVolClientDao.findByVol(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testFindByVols() {
        final VolDao volDao = (VolDao) getBeanOfType(VolDao.class);
        final Vol    vol1 = (Vol) volDao.findById(new Long(10000));
        final Vol    vol2 = (Vol) volDao.findById(new Long(10001));

        final List   list = billetVolClientDao.findByVols(Arrays.asList(
                    new Vol[] { vol1, vol2 }));
        assertNotNull(list);
        assertEquals(1, list.size());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final BilletVolClient billet = (BilletVolClient) i.next();
            assertNotNull(billet);
            final Vol vol = billet.getVol();
            assertNotNull(vol);

            if (!vol.equals(vol1) && !vol.equals(vol2)) {
                fail("Le billet " + billet.getId() +
                    " ne correspond pas aux vols " + vol1.getId() + " et " +
                    vol2.getId());

                break;
            }
        }
    }


    public void testFindByVolsNull() {
        boolean error = false;
        try {
            billetVolClientDao.findByVols(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testFindByVolsVide() {
        final List list = billetVolClientDao.findByVols(Collections.EMPTY_LIST);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }


    protected void setUp() throws Exception {
        super.setUp();
        billetVolClientDao = (BilletVolClientDao) getBeanOfType(BilletVolClientDao.class);
    }


    protected void tearDown() throws Exception {
        billetVolClientDao = null;
        super.tearDown();
    }
}
