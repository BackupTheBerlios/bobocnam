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


package org.eu.bobo.web.beans.propertyeditors;

import junit.framework.TestCase;

import org.easymock.MockControl;

import org.eu.bobo.model.bo.Aeroport;
import org.eu.bobo.model.bo.Pays;
import org.eu.bobo.model.bo.Ville;
import org.eu.bobo.model.dao.AeroportDao;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/03/10 22:04:19 $
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
