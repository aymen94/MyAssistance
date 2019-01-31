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
     * @param aUserName      the user name
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
     * Ottieni la data sospensione.
     *
     * @return dataSospensione ottieni la data di sospensione dell'utente.
     */
    public final LocalDate getDataSospensione() {

        return dataSospensione;
    }

    /**
     * Imposta la data sospensione.
     *
     * @param aDataSospensione imposta la data di sospensione dell'utente.
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
