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

import org.eu.bobo.model.Periode;
import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.bo.reservation.avion.Vol;
import org.eu.bobo.model.bo.reservation.avion.VolGenerique;
import org.eu.bobo.model.dao.AeroportDao;
import org.eu.bobo.model.dao.VolDao;
import org.eu.bobo.model.dao.VolGeneriqueDao;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.7 $, $Date: 2005/03/13 00:54:59 $
 */
public class VolHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private AeroportDao     aeroportDao;
    private VolDao          volDao;
    private VolGeneriqueDao volGeneriqueDao;

    //~ Méthodes ---------------------------------------------------------------

    public void testCreate() {
        final VolGenerique volGenerique = (VolGenerique) volGeneriqueDao.findById(new Long(
                    10000));
        final Date         dateDepart = createDate(2005, Calendar.FEBRUARY, 7,
                12, 0, 0);
        final Date         dateArrivee = createDate(2005, Calendar.FEBRUARY, 7,
                15, 0, 0);

        final Vol vol = new Vol(volGenerique,
                new Periode(dateDepart, dateArrivee));
        volDao.create(vol);
    }


    public void testFindByAeroportPeriode() {
        final Date     dateDepart  = createDate(2005, Calendar.FEBRUARY, 5);
        final Date     dateArrivee = createDate(2005, Calendar.FEBRUARY, 8);

        final Aeroport aeroportDepart  = (Aeroport) aeroportDao.findById("A1");
        final Aeroport aeroportArrivee = (Aeroport) aeroportDao.findById("A2");

        final List     list = volDao.findByAeroportPeriode(aeroportDepart,
                aeroportArrivee, new Periode(dateDepart, dateArrivee));
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final Vol vol = (Vol) i.next();
            assertNotNull(vol);
            assertTrue(vol.getPeriode().getDateDebut().getTime() >= dateDepart.getTime());
            assertTrue(vol.getPeriode().getDateFin().getTime() <= dateArrivee.getTime());
            assertEquals(aeroportDepart,
                vol.getVolGenerique().getAeroportDepart());
            assertEquals(aeroportArrivee,
                vol.getVolGenerique().getAeroportArrivee());
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
            assertEquals(numero, vol.getVolGenerique().getNumero());
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


    public void testFindByVillePeriode() {
        final Date   dateDepart  = createDate(2005, Calendar.FEBRUARY, 5);
        final Date   dateArrivee = createDate(2005, Calendar.FEBRUARY, 8);

        final String villeDepart  = "Toulon";
        final String villeArrivee = "Paris";

        final List   list = volDao.findByVillePeriode(villeDepart,
                villeArrivee, new Periode(dateDepart, dateArrivee));
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (final Iterator i = list.iterator(); i.hasNext();) {
            final Vol vol = (Vol) i.next();
            assertNotNull(vol);
            assertTrue(vol.getPeriode().getDateDebut().getTime() >= dateDepart.getTime());
            assertTrue(vol.getPeriode().getDateFin().getTime() <= dateArrivee.getTime());
            assertEquals(villeDepart,
                vol.getVolGenerique().getAeroportDepart().getVille().getNom());
            assertEquals(villeArrivee,
                vol.getVolGenerique().getAeroportArrivee().getVille().getNom());
        }
    }


    public void testFindByVillePeriodeNull() {
        final List list        = volDao.findByVillePeriode(null, null, null);
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
        volDao              = (VolDao) getBeanOfType(VolDao.class);
        volGeneriqueDao     = (VolGeneriqueDao) getBeanOfType(VolGeneriqueDao.class);
        aeroportDao         = (AeroportDao) getBeanOfType(AeroportDao.class);
    }


    protected void tearDown() throws Exception {
        volDao              = null;
        volGeneriqueDao     = null;
        aeroportDao         = null;
        super.tearDown();
    }
}
