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

import org.eu.bobo.model.bo.Autorite;
import org.eu.bobo.model.bo.Utilisateur;
import org.eu.bobo.model.dao.UtilisateurDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.5 $, $Date: 2005/04/24 22:17:00 $
 */
public class UtilisateurHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private UtilisateurDao utilisateurDao;

    //~ Méthodes ---------------------------------------------------------------

    public void testAjouterPropriete() {
        final String      login = "alex";
        final Utilisateur alex = utilisateurDao.findByLogin(login);
        assertNotNull(alex);
        assertTrue(alex.getProprietes().isEmpty());
        alex.getProprietes().put("email.work", "alex@bobo.eu.org");
        utilisateurDao.update(alex);
        assertEquals(1, alex.getProprietes().size());
    }


    public void testCreate() {
        final String      login = "test";
        final Utilisateur test = new Utilisateur(login, "cim");
        utilisateurDao.create(test);
        final Utilisateur result = utilisateurDao.findByLogin(login);
        assertNotNull(result);
        assertEquals(login, result.getLogin());
    }


    public void testCreateUserAvecAutorites() {
        final String login = "marc";
        assertNull(utilisateurDao.findByLogin(login));

        final Utilisateur marc        = new Utilisateur(login, "azerty");
        final String      nomAutorite = "DEVELOPPEUR";
        marc.getAutorites().add(new Autorite(nomAutorite));
        utilisateurDao.create(marc);

        final Utilisateur marcTest = utilisateurDao.findByLogin(login);
        assertNotNull(marcTest);
        assertEquals(login, marcTest.getLogin());
        assertNotNull(marcTest.getAutorites());
        assertEquals(1, marcTest.getAutorites().size());

        final Autorite autoriteTest = (Autorite) marcTest.getAutorites()
                                                         .iterator().next();
        assertEquals(nomAutorite, autoriteTest.getNom());
    }


    public void testCreateUserDejaPresent() {
        boolean error = false;
        try {
            utilisateurDao.create(new Utilisateur("alex", "abc"));
        } catch (Exception e) {
            error = true;
        }
    }


    public void testDeleteUserAbsent() {
        boolean error = false;
        try {
            utilisateurDao.delete(new Utilisateur("test", "123"));
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testDeleteUserAvecAutorites() {
        final String      login = "root";
        final Utilisateur root = utilisateurDao.findByLogin(login);
        assertNotNull(root);
        assertFalse(root.getAutorites().isEmpty());
        utilisateurDao.delete(root);
    }


    public void testDeleteUserAvecProprietes() {
        final String      login  = "julien";
        final Utilisateur julien = utilisateurDao.findByLogin(login);
        assertNotNull(julien);
        assertFalse(julien.getProprietes().isEmpty());
        utilisateurDao.delete(julien);
        assertNull(utilisateurDao.findByLogin(login));
    }


    public void testDeleteUserPresent() {
        final String      login = "alex";
        final Utilisateur alex = utilisateurDao.findByLogin(login);
        assertNotNull(alex);
        utilisateurDao.delete(alex);
        assertNull(utilisateurDao.findByLogin(login));
    }


    public void testExists() {
        assertTrue(utilisateurDao.exists(new Long(100)));
    }


    public void testExistsUtilisateurAbsent() {
        assertFalse(utilisateurDao.exists(new Long(-1)));
    }


    public void testFindAll() {
        assertEquals(3, utilisateurDao.findAll().size());
    }


    public void testFindById() {
        final Long        id = new Long(100);

        final Utilisateur user = (Utilisateur) utilisateurDao.findById(id);
        assertEquals(id, user.getId());
        assertEquals("alex", user.getLogin());
    }


    public void testFindByIdList() {
        final Long id1 = new Long(0);
        final Long id2 = new Long(100);

        final List ids = new ArrayList();
        ids.add(id1);
        ids.add(id2);

        final List utilisateurs = utilisateurDao.findById(ids);
        assertEquals(2, utilisateurs.size());

        final Utilisateur user1 = (Utilisateur) utilisateurs.get(0);
        final Utilisateur user2 = (Utilisateur) utilisateurs.get(1);
        assertEquals("root", user1.getLogin());
        assertEquals("alex", user2.getLogin());
    }


    public void testFindByLoginConnu() {
        final String      login = "alex";
        final Utilisateur util = utilisateurDao.findByLogin(login);
        assertNotNull(util);
        assertEquals(login, util.getLogin());
    }


    public void testFindByLoginInconnu() {
        final String      login = "test";
        final Utilisateur util = utilisateurDao.findByLogin(login);
        assertNull(util);
    }


    public void testFindByLoginNull() {
        boolean error = false;
        try {
            utilisateurDao.findByLogin(null);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }


    public void testFindByNom() {
        final String nom          = "Alexandre";
        final List   utilisateurs = utilisateurDao.findByNom(nom);
        assertNotNull(utilisateurs);
        assertEquals(1, utilisateurs.size());

        final Utilisateur utilisateur = (Utilisateur) utilisateurs.get(0);
        assertNotNull(utilisateur);
        assertEquals(nom, utilisateur.getNom());
    }


    public void testGetAutorites() {
        final Utilisateur util = utilisateurDao.findByLogin("root");
        assertNotNull(util);

        final Collection autorites = util.getAutorites();
        assertNotNull(autorites);
        assertEquals(1, autorites.size());

        final Autorite autorite = (Autorite) autorites.iterator().next();
        assertNotNull(autorite);
        assertEquals("ADMIN", autorite.getNom());
    }


    public void testSupprimerPropriete() {
        final String      login  = "julien";
        final Utilisateur julien = utilisateurDao.findByLogin(login);
        assertNotNull(julien);
        assertFalse(julien.getProprietes().isEmpty());
        julien.getProprietes().clear();
        utilisateurDao.update(julien);
        assertTrue(julien.getProprietes().isEmpty());
    }


    public void testUpdateUserPresent() {
        final String      login      = "alex";
        final String      motDePasse = "abc";
        final Utilisateur alex       = utilisateurDao.findByLogin(login);
        assertNotNull(alex);
        assertEquals(login, alex.getLogin());

        alex.setMotDePasse(motDePasse);
        utilisateurDao.update(alex);

        final Utilisateur alexTest = utilisateurDao.findByLogin(login);
        assertNotNull(alexTest);
        assertEquals(login, alexTest.getLogin());
        assertEquals(motDePasse, alexTest.getMotDePasse());
    }


    protected void setUp() throws Exception {
        super.setUp();
        utilisateurDao = (UtilisateurDao) getBeanOfType(UtilisateurDao.class);
    }


    protected void tearDown() throws Exception {
        utilisateurDao = null;
        super.tearDown();
    }
}
