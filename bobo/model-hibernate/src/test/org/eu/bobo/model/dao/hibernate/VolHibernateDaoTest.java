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

import org.eu.bobo.model.bo.Aeroport;
import org.eu.bobo.model.bo.CompagnieAerienne;
import org.eu.bobo.model.bo.Vol;
import org.eu.bobo.model.dao.AeroportDao;
import org.eu.bobo.model.dao.CompagnieAerienneDao;
import org.eu.bobo.model.dao.VolDao;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.6 $, $Date: 2005/02/24 09:27:32 $
 */
public class VolHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private AeroportDao          aeroportDao;
    private CompagnieAerienneDao compagnieAerienneDao;
    private VolDao               volDao;

    //~ M�thodes ---------------------------------------------------------------

    public void testCreate() {
        final CompagnieAerienne compagnieAerienne = (CompagnieAerienne) compagnieAerienneDao.findById(
                "AF");
        final Aeroport          aeroportDepart = (Aeroport) aeroportDao.findById(
                "A1");
        final Aeroport          aeroportArrivee = (Aeroport) aeroportDao.findById(
                "A2");

        final Date   dateDepart = createDate(2005, Calendar.FEBRUARY, 7, 12, 0,
                0);
        final Date   dateArrivee = createDate(2005, Calendar.FEBRUARY, 7, 15,
                0, 0);
        final String code = "200";

        final Vol    vol = new Vol(compagnieAerienne, code, dateDepart,
                dateArrivee, aeroportDepart, aeroportArrivee);
        volDao.create(vol);
    }


    public void testFindByAeroportDate() {
        final Date     dateDepart  = createDate(2005, Calendar.FEBRUARY, 5);
        final Date     dateArrivee = createDate(2005, Calendar.FEBRUARY, 8);

        final Aeroport aeroportDepart  = (Aeroport) aeroportDao.findById("A1");
        final Aeroport aeroportArrivee = (Aeroport) aeroportDao.findById("A2");

        final List     list = volDao.findByAeroportDate(aeroportDepart,
                aeroportArrivee, dateDepart, dateArrivee);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final Vol vol = (Vol) i.next();
            assertNotNull(vol);
            assertTrue(vol.getDateDepart().getTime() >= dateDepart.getTime());
            assertTrue(vol.getDateArrivee().getTime() <= dateArrivee.getTime());
            assertEquals(aeroportDepart, vol.getAeroportDepart());
            assertEquals(aeroportArrivee, vol.getAeroportArrivee());
        }
    }


    public void testFindByNumero() {
        final String numero = "AF100";
        final List   list = volDao.findByNumero(numero);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final Vol vol = (Vol) i.next();
            assertNotNull(vol);
            assertEquals(numero, vol.getNumero());
        }
    }


    public void testFindByNumeroInexistant() {
        final List list = volDao.findByNumero("WX12345");
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }


    public void testFindByNumeroNull() {
        boolean error = false;
        try {
            volDao.findByNumero(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testFindByVilleDate() {
        final Date   dateDepart  = createDate(2005, Calendar.FEBRUARY, 5);
        final Date   dateArrivee = createDate(2005, Calendar.FEBRUARY, 8);

        final String villeDepart  = "Toulon";
        final String villeArrivee = "Paris";

        final List   list = volDao.findByVilleDate(villeDepart, villeArrivee,
                dateDepart, dateArrivee);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final Vol vol = (Vol) i.next();
            assertNotNull(vol);
            assertTrue(vol.getDateDepart().getTime() >= dateDepart.getTime());
            assertTrue(vol.getDateArrivee().getTime() <= dateArrivee.getTime());
            assertEquals(villeDepart,
                vol.getAeroportDepart().getVille().getNom());
            assertEquals(villeArrivee,
                vol.getAeroportArrivee().getVille().getNom());
        }
    }


    public void testFindByVilleDateNull() {
        final List list        = volDao.findByVilleDate(null, null, null, null);
        final List tousLesVols = volDao.findAll();
        assertNotNull(list);
        assertEquals(tousLesVols.size(), list.size());
        assertTrue(tousLesVols.containsAll(list));
    }


    public void testGetNbPlacesEnVenteDisponibles() {
        final Vol vol1 = (Vol) volDao.findById(new Long(10000));
        assertNotNull(vol1);
        assertEquals(vol1.getNbPlacesEnVente(),
            volDao.getNbPlacesEnVenteDisponibles(vol1));

        final Vol vol2 = (Vol) volDao.findById(new Long(10001));
        assertNotNull(vol2);
        assertEquals(new Integer(vol2.getNbPlacesEnVente().intValue() - 1),
            volDao.getNbPlacesEnVenteDisponibles(vol2));
    }


    protected void setUp() throws Exception {
        super.setUp();
        volDao                   = (VolDao) getBeanOfType(VolDao.class);
        aeroportDao              = (AeroportDao) getBeanOfType(AeroportDao.class);
        compagnieAerienneDao     = (CompagnieAerienneDao) getBeanOfType(CompagnieAerienneDao.class);
    }


    protected void tearDown() throws Exception {
        volDao                   = null;
        aeroportDao              = null;
        compagnieAerienneDao     = null;
        super.tearDown();
    }
}
