/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.utente;

import java.util.List;

/**
 * The Class UtenteBL.
 */
public final class UtenteBL {

    /**
     * Effettua registrazione.
     *
     * @param aUserName      the user name
     * @param aPassword      the password
     * @param aEmail         the email
     * @param aNome          the nome
     * @param aCognome       the cognome
     * @param aDataDiNascita the data di nascita
     * @return true, if successful
     */
    public boolean effettuaRegistrazione(final String aUserName,
        final String aPassword, final String aEmail, final String aNome,
        final String aCognome, final String aDataDiNascita) {

        return false;
    }

    /**
     * Sospendi utente.
     *
     * @param aCSU the CSU
     * @return true, if successful
     */
    public boolean sospendiUtente(final CSU aCSU) {
        return false;
    }

    /**
     * Autenticazione CSU.
     *
     * @param aUserName the user name
     * @param aPass     the pass
     * @return the csu
     */
    public CSU autenticazioneCSU(final String aUserName, final String aPass) {
        return null;
    }

    /**
     * Autenticazione gestore.
     *
     * @param aUserName the user name
     * @param aPass     the pass
     * @return the gestore
     */
    public Gestore autenticazioneGestore(final String aUserName,
        final String aPass) {
        return null;
    }

    /**
     * Gets the utenti registrati.
     *
     * @return the utenti registrati
     */
    public List<Utente> getUtentiRegistrati() {
        return null;

    }
}
