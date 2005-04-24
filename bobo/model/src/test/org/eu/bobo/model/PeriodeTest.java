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


package org.eu.bobo.model;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:22:37 $
 */
public class PeriodeTest extends TestCase {
    //~ Méthodes ---------------------------------------------------------------

    public void testContains() {
        final Date    debut   = createDate(2005, Calendar.MARCH, 10);
        final Date    fin     = createDate(2005, Calendar.MARCH, 12);
        final Periode periode = new Periode(debut, fin);

        assertTrue(periode.contains(createDate(2005, Calendar.MARCH, 10)));
        assertTrue(periode.contains(createDate(2005, Calendar.MARCH, 10)));
        assertTrue(periode.contains(createDate(2005, Calendar.MARCH, 12)));
        assertFalse(periode.contains(createDate(2005, Calendar.FEBRUARY, 10)));
        assertFalse(periode.contains(createDate(2005, Calendar.APRIL, 12)));
    }


    public void testGetDuree() {
        final Date    debut   = createDate(2005, Calendar.MARCH, 1);
        final Date    fin     = createDate(2005, Calendar.MARCH, 10);
        final Periode periode = new Periode(debut, fin);

        // un jour en millisecondes
        final long jour = 86400 * 1000;
        assertEquals(new Long(9 * jour), periode.getDuree());
    }


    private Date createDate(int year, int month, int day) {
        final Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }
}
