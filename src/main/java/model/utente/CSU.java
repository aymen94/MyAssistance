/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.time.LocalDate;

/**
 * The Class CSU.
 */
public class CSU extends Utente {

    /**
     * LocalDate dataSospensione.
     */
    private LocalDate dataSospensione;

    /**
     * Instantiates a new csu.
     */
    public CSU() {

    }

    /**
     * Instantiates a new csu.
     *
     * @param aUserName      the username
     * @param aPassword      the password
     * @param aEmail         the email
     * @param aNome          the nome
     * @param aCognome       the cognome
     * @param aSesso         the sesso
     * @param aDataDiNascita the data di nascita
     */
    public CSU(final String aUserName, final String aPassword,
            final String aEmail, final String aNome, final String aCognome,
            final int aSesso, final LocalDate aDataDiNascita) {
        super(aUserName, aPassword, aEmail, aNome, aCognome, aSesso,
                aDataDiNascita);
    }

    /**
     * Get the suspension date.
     *
     * @return dataSospensione get the user's suspension date.
     */
    public final LocalDate getDataSospensione() {

        return dataSospensione;
    }

    /**
     * Set the suspension date.
     *
     * @param aDataSospensione set the user's suspension date.
     */
    public final void setDataSospensione(final LocalDate aDataSospensione) {
        dataSospensione = aDataSospensione;
    }

    /**
     * @return false because is CSU.
     */
    @Override
    public final Boolean isGestore() {
        return false;
    }

}
