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
 * @version $Revision: 1.9 $, $Date: 2005/04/24 22:17:00 $
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

        final Aeroport aeroportDepart  = (Aeroport) aeroportDao.findById("CDG");
        final Aeroport aeroportArrivee = (Aeroport) aeroportDao.findById("JFK");

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
        final Date   dateDepart  = createDate(2005, Calendar.MARCH, 11);
        final Date   dateArrivee = createDate(2005, Calendar.MARCH, 12);

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
