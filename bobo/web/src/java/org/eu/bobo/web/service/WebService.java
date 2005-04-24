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


package org.eu.bobo.web.service;

import org.eu.bobo.model.bo.reservation.avion.Aeroport;
import org.eu.bobo.model.bo.reservation.avion.Vol;

import java.util.Date;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.3 $, $Date: 2005/04/24 22:18:59 $
 */
public interface WebService {
    //~ Méthodes ---------------------------------------------------------------

    Aeroport getAeroport(String id);


    Vol getVol(Number id);


    String[] findAeroportsByVille(String ville);


    Number[] findVolsByAeroportDate(String codeAeroportDepart,
        String codeAeroportArrivee, Date dateDepart, Date dateArrivee);


    String version();
}
