/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.security.BasicPermission;
import java.sql.Date;


/**
 * The Class Gestore.
 */
public class Gestore extends Utente {
    /**
     * Instantiates a new gestore.
     */
    public Gestore() {
    }

    public Boolean isGestore() {
        return true;
    }
}
