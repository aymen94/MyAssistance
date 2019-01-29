/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.time.LocalDate;

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
     * Instantiates a new gestore.
     *
     * @param aUserName      the user name
     * @param aPassword      the password
     * @param aEmail         the email
     * @param aNome          the nome
     * @param aCognome       the cognome
     * @param aSesso         the sesso
     * @param aDataDiNascita the data di nascita
     */
    public Gestore(final String aUserName, final String aPassword,
            final String aEmail, final String aNome, final String aCognome,
            final int aSesso, final LocalDate aDataDiNascita) {
        super(aUserName, aPassword, aEmail, aNome, aCognome, aSesso,
                aDataDiNascita);
    }

    /**
     * Checks if is gestore.
     *
     * @return always true
     * @see model.utente.Utente#isGestore()
     */
    @Override
    public Boolean isGestore() {
        return true;
    }
}
