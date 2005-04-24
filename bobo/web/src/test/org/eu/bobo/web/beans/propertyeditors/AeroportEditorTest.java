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


package org.eu.bobo.web.beans.propertyeditors;

import junit.framework.TestCase;

import org.easymock.MockControl;

import org.eu.bobo.model.bo.Pays;
import org.eu.bobo.model.bo.Ville;
import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.dao.AeroportDao;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:18:59 $
 */
public class AeroportEditorTest extends TestCase {
    //~ Champs d'instance ------------------------------------------------------

    private AeroportDao    aeroportDao;
    private AeroportEditor aeroportEditor;
    private MockControl    aeroportDaoControl;

    //~ Méthodes ---------------------------------------------------------------

    public void testConstructeur() {
        boolean error = false;
        try {
            new AeroportEditor(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testGetAsText() {
        final String aeroportId = "TL";

        aeroportEditor.setValue(createAeroport(aeroportId));
        assertNotNull(aeroportEditor.getValue());
        assertEquals(aeroportId, aeroportEditor.getAsText());
    }


    public void testGetAsTextNull() {
        assertNull(aeroportEditor.getValue());
        assertNull(aeroportEditor.getAsText());
    }


    public void testSetAsText() {
        final String   aeroportId = "TL";
        final Aeroport aeroport = createAeroport(aeroportId);

        aeroportDaoControl.expectAndReturn(aeroportDao.findById(aeroportId),
            aeroport);
        aeroportDaoControl.replay();

        assertNull(aeroportEditor.getValue());
        aeroportEditor.setAsText(aeroportId);
        assertEquals(aeroport, aeroportEditor.getValue());

        aeroportDaoControl.verify();
    }


    public void testSetAsTextNull() {
        final Aeroport aeroport = createAeroport("TL");
        aeroportEditor.setValue(aeroport);
        assertNotNull(aeroportEditor.getValue());
        aeroportEditor.setAsText(null);
        assertNull(aeroportEditor.getValue());
    }


    protected void setUp() throws Exception {
        super.setUp();
        aeroportDaoControl     = MockControl.createControl(AeroportDao.class);
        aeroportDao            = (AeroportDao) aeroportDaoControl.getMock();
        aeroportEditor         = new AeroportEditor(aeroportDao);
    }


    protected void tearDown() throws Exception {
        aeroportEditor         = null;
        aeroportDao            = null;
        aeroportDaoControl     = null;
        super.tearDown();
    }


    private Aeroport createAeroport(String aeroportId) {
        return new Aeroport(aeroportId,
            new Ville(new Pays("FR", "France"), "83000", "Toulon"));
    }
}
