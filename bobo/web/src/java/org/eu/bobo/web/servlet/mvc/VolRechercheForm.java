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


package org.eu.bobo.web.servlet.mvc;

import org.eu.bobo.model.bo.reservation.avion.Aeroport;

import java.util.Date;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.5 $, $Date: 2005/04/24 22:18:59 $
 */
public class VolRechercheForm {
    //~ Champs d'instance ------------------------------------------------------

    private Aeroport aeroportArrivee;
    private Aeroport aeroportDepart;
    private Boolean  allerSimple = Boolean.FALSE;
    private Boolean  volDirect   = Boolean.FALSE;
    private Date     dateArrivee;
    private Date     dateDepart;

    //~ Méthodes ---------------------------------------------------------------

    public void setAeroportArrivee(Aeroport aeroportArrivee) {
        this.aeroportArrivee = aeroportArrivee;
    }


    public Aeroport getAeroportArrivee() {
        return aeroportArrivee;
    }


    public void setAeroportDepart(Aeroport aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }


    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }


    public void setAllerSimple(Boolean allerSimple) {
        this.allerSimple = allerSimple;
    }


    public Boolean getAllerSimple() {
        return allerSimple;
    }


    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }


    public Date getDateArrivee() {
        return dateArrivee;
    }


    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }


    public Date getDateDepart() {
        return dateDepart;
    }


    public void setVolDirect(Boolean volsDirects) {
        this.volDirect = volsDirects;
    }


    public Boolean getVolDirect() {
        return volDirect;
    }
}
