/*                                                   *\
 * Copyright © 2004 Centre Informatique Multimédia   *
 *                                                   *
 * $Id: ProprieteUtilisateurHibernateDaoTest.java,v 1.1 2005/01/13 13:38:06 romale Exp $
\*                                                   */


package org.eu.bobo.model.dao.hibernate;

import java.util.List;

import org.eu.bobo.model.bo.ProprieteUtilisateur;
import org.eu.bobo.model.bo.Utilisateur;
import org.eu.bobo.model.dao.ProprieteUtilisateurDao;
import org.eu.bobo.model.dao.UtilisateurDao;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/01/13 13:38:06 $
 */
public class ProprieteUtilisateurHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private ProprieteUtilisateurDao proprieteUtilisateurDao;
    private UtilisateurDao          utilisateurDao;

    //~ Méthodes ---------------------------------------------------------------

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
