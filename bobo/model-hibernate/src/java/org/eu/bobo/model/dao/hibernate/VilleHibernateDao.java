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

import org.eu.bobo.model.bo.Pays;
import org.eu.bobo.model.bo.Ville;
import org.eu.bobo.model.dao.VilleDao;

import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.2 $, $Date: 2005/04/24 22:17:00 $
 */
public class VilleHibernateDao extends AbstractHibernateDao implements VilleDao {
    //~ Constructeurs ----------------------------------------------------------

    public VilleHibernateDao() {
        super(Ville.class);
    }

    //~ Méthodes ---------------------------------------------------------------

    public List findByCodePostal(String codePostal) {
        return findByProperty("codePostal", codePostal);
    }


    public List findByNom(String nom) {
        return findByProperty("nom", nom);
    }


    public List findByPays(Pays pays) {
        return findByProperty("pays", pays);
    }
}
