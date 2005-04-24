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

import org.eu.bobo.model.Identite;
import org.eu.bobo.model.bo.Adresse;
import org.eu.bobo.model.bo.AdresseElectronique;
import org.eu.bobo.model.bo.Telephone;
import org.eu.bobo.model.bo.contact.Client;
import org.eu.bobo.model.dao.ClientDao;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.5 $, $Date: 2005/04/24 22:17:00 $
 */
public class ClientHibernateDaoTest extends AbstractHibernateDaoTest {
    //~ Champs d'instance ------------------------------------------------------

    private ClientDao clientDao;

    //~ Méthodes ---------------------------------------------------------------

    public void testCreate() {
        final String   nom    = "Martini";
        final String   prenom = "Jean";

        final Identite identite = new Identite(nom);
        identite.setPrenom(prenom);

        final Client client = new Client(identite);
        clientDao.create(client);

        assertEquals(4, clientDao.findAll().size());
        final List clients = clientDao.findByNom(nom);
        assertNotNull(clients);
        assertFalse(clients.isEmpty());
        assertEquals(1, clients.size());

        final Client clientTest = (Client) clients.get(0);
        assertEquals(nom, clientTest.getIdentite().getNom());
        assertEquals(prenom, client.getIdentite().getPrenom());
    }


    public void testCreateClientAvecTelephoneAdresse() {
        final String   nom      = "Martini";
        final Identite identite = new Identite(nom);
        identite.setPrenom("Jean");

        final Client  client = new Client(identite);

        final Adresse adresse = new Adresse();
        adresse.setRue("10 rue Mistral");
        adresse.setCodePostal("83100");
        adresse.setVille("Toulon");
        adresse.setType("Type1");
        adresse.setPays("France");

        client.getAdresses().add(adresse);

        final Telephone telephone = new Telephone("professionnel", "0610203040");
        client.getTelephones().add(telephone);

        final String        mail   = "j.martin@wanadoo.fr";
        AdresseElectronique adMail = new AdresseElectronique();
        adMail.setAdresse(mail);
        adMail.setType("type1");

        client.getAdressesElectroniques().add(adMail);

        clientDao.create(client);

        assertEquals(4, clientDao.findAll().size());

        final List clients = clientDao.findByNom(nom);
        assertNotNull(clients);
        assertFalse(clients.isEmpty());
        assertEquals(1, clients.size());

        final Client clientTest = (Client) clients.get(0);
        assertEquals(nom, clientTest.getIdentite().getNom());

        final AdresseElectronique adMailTest = (AdresseElectronique) client.getAdressesElectroniques()
                                                                           .iterator()
                                                                           .next();
        assertEquals(adMail.getAdresse(), adMailTest.getAdresse());

        final Adresse adresseTest = (Adresse) client.getAdresses().iterator()
                                                    .next();
        assertEquals(adresse, adresseTest);

        final Telephone telephoneTest = (Telephone) client.getTelephones()
                                                          .iterator().next();
        assertEquals(telephone, telephoneTest);
    }


    public void testDeleteClient() {
        Client client = (Client) clientDao.findById(new Long(10000));
        clientDao.delete(client);

        List listClient = clientDao.findByNom("durand");
        assertTrue(listClient.isEmpty());
    }


    public void testFindByCodePostal() {
        List listClient = clientDao.findByCodePostal("83100");
        assertEquals(2, listClient.size());

        Client client1 = (Client) listClient.get(0);
        Client client2 = (Client) listClient.get(1);
        assertEquals(new Long(10000), client1.getClientId());
        assertEquals(new Long(10001), client2.getClientId());
    }


    public void testFindByCodePostalNull() {
        List listClient = clientDao.findByCodePostal("10000");
        assertEquals(0, listClient.size());
    }


    public void testFindById() {
        Client client = (Client) clientDao.findById(new Long(10000));
        assertNotNull(client);
        assertEquals("durand", client.getIdentite().getNom());
    }


    public void testFindByNomCodePostalVillePays() {
        List listClient = clientDao.findByNomCodePostalVillePays("durand",
                "83100", "toulon", "france");
        assertEquals(2, listClient.size());
        Client client = (Client) listClient.get(0);
        assertEquals(new Long(10000), client.getClientId());
    }


    public void testFindByNomCodePostalVillePaysNull() {
        List listClient = clientDao.findByNomCodePostalVillePays("martini",
                "84000", "la seyne", "USA");
        assertEquals(0, listClient.size());
    }


    public void testFindByPays() {
        List listClient = clientDao.findByPays("france");
        assertEquals(2, listClient.size());

        Client client1 = (Client) listClient.get(0);
        Client client2 = (Client) listClient.get(1);
        assertEquals(new Long(10000), client1.getClientId());
        assertEquals(new Long(10001), client2.getClientId());
    }


    public void testFindByPaysNull() {
        List listClient = clientDao.findByPays("Italie");
        assertEquals(0, listClient.size());
    }


    public void testFindByVille() {
        List listClient = clientDao.findByVille("toulon");
        assertEquals(2, listClient.size());

        Client client1 = (Client) listClient.get(0);
        Client client2 = (Client) listClient.get(1);
        assertEquals(new Long(10000), client1.getClientId());
        assertEquals(new Long(10001), client2.getClientId());
    }


    public void testFindByVilleNull() {
        List listClient = clientDao.findByVille("hyeres");
        assertEquals(0, listClient.size());
    }


    protected void setUp() throws Exception {
        super.setUp();
        clientDao = (ClientDao) getBeanOfType(ClientDao.class);
    }


    protected void tearDown() throws Exception {
        clientDao = null;
        super.tearDown();
    }
}
