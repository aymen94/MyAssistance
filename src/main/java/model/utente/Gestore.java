/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

/**
 * The Class Gestore.
 */
public final class Gestore extends Utente {
    /**
     * Instantiates a new gestore.
     */
    public Gestore() {
    }

    /**
     * @see model.utente.Utente#isGestore()
     * @return always true
     */
    public Boolean isGestore() {
        return true;
    }
}
