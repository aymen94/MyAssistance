/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class CSU.
 */
public class CSU extends Utente {


    private Date dataSospensione;

    /**
     * Instantiates a new csu.
     */
    public CSU( ) {

    }

    /**
     * Ottieni la data sospensione.
     *
     * @return dataSospensione ottieni la data di sospensione dell'utente.
     */
    public Date getDataSospensione() {

        return dataSospensione;
    }

    /**
     * Imposta la data sospensione.
     *
     * @param dataSospensione imposta la data di sospensione dell'utente.
     */
    public void setDataSospensione(Date dataSospensione) {
        this.dataSospensione = dataSospensione;
    }

}
