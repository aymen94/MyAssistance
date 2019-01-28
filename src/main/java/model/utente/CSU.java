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
public final class CSU extends Utente {

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
     * Ottieni la data sospensione.
     *
     * @return dataSospensione ottieni la data di sospensione dell'utente.
     */
    public LocalDate getDataSospensione() {

        return dataSospensione;
    }

    /**
     * Imposta la data sospensione.
     *
     * @param aDataSospensione imposta la data di sospensione dell'utente.
     */
    public void setDataSospensione(final LocalDate aDataSospensione) {
        dataSospensione = aDataSospensione;
    }

    /**
     * @return false because is CSU.
     */
    public Boolean isGestore() {
        return false;
    }

}
