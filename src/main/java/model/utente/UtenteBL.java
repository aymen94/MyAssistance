/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.utente;

import java.sql.SQLException;
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
     * @throws SQLException the SQL exception
     */
    public boolean effettuaRegistrazione(final String aUserName,
            final String aPassword, final String aEmail, final String aNome,
            final String aCognome, final String aDataDiNascita)
            throws SQLException {

        return false;
    }

    /**
     * Sospendi utente.
     *
     * @param aCSU the CSU
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean sospendiUtente(final CSU aCSU) throws SQLException {
        return false;
    }

    /**
     * Autenticazione CSU.
     *
     * @param aUserName the user name
     * @param aPass     the pass
     * @return the csu
     * @throws SQLException the SQL exception
     */
    public CSU autenticazioneCSU(final String aUserName, final String aPass)
            throws SQLException {
        return null;
    }

    /**
     * Autenticazione gestore.
     *
     * @param aUserName the user name
     * @param aPass     the pass
     * @return the gestore
     * @throws SQLException the SQL exception
     */
    public Gestore autenticazioneGestore(final String aUserName,
            final String aPass) throws SQLException {
        return null;
    }

    /**
     * Gets the utenti registrati.
     *
     * @return the utenti registrati
     * @throws SQLException the SQL exception
     */
    public List<Utente> getUtentiRegistrati() throws SQLException {
        return null;

    }
}
