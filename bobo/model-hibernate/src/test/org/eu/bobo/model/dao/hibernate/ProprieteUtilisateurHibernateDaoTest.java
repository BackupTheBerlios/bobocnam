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

import org.eu.bobo.model.bo.ProprieteUtilisateur;
import org.eu.bobo.model.bo.Utilisateur;
import org.eu.bobo.model.dao.ProprieteUtilisateurDao;
import org.eu.bobo.model.dao.UtilisateurDao;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/01/21 10:14:31 $
 */
public class ProprieteUtilisateurHibernateDaoTest
  extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private ProprieteUtilisateurDao proprieteUtilisateurDao;
    private UtilisateurDao          utilisateurDao;

    //~ M�thodes ---------------------------------------------------------------

    public void testFindByNom() {
        final String nom        = "Prop1";
        final List   proprietes = proprieteUtilisateurDao.findByNom(nom);
        assertNotNull(proprietes);
        assertFalse(proprietes.isEmpty());

        final ProprieteUtilisateur propriete = (ProprieteUtilisateur) proprietes.get(0);
        assertNotNull(propriete);
        assertEquals(nom, propriete.getNom());
    }


    public void testFindByUtilisateur() {
        final Utilisateur utilisateur = (Utilisateur) utilisateurDao.findById(new Long(
                    0));
        final List        proprietes = proprieteUtilisateurDao.findByUtilisateur(utilisateur);
        assertNotNull(proprietes);
        assertFalse(proprietes.isEmpty());

        final ProprieteUtilisateur propriete = (ProprieteUtilisateur) proprietes.get(0);
        assertNotNull(propriete);
        assertEquals("Prop1", propriete.getNom());
    }


    protected void setUp() throws Exception {
        super.setUp();
        proprieteUtilisateurDao     = (ProprieteUtilisateurDao) getBean(
                "proprieteUtilisateurDao");
        utilisateurDao = (UtilisateurDao) getBean("utilisateurDao");
    }


    protected void tearDown() throws Exception {
        proprieteUtilisateurDao     = null;
        utilisateurDao              = null;

        super.tearDown();
    }
}
