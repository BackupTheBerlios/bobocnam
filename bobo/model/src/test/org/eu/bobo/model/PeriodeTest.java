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


package org.eu.bobo.model;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/03/13 00:53:02 $
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
