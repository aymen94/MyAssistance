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
     * Costruttore Utente.
     *
     * @param id        identificatore
     * @param userName  username utente
     * @param password  parolasegreta per identificazione
     * @param email     email utente
     * @param nome      nome utente
     * @param cognome   congome utente
     * @param sesso     sesso utente
     * @param isGestore tipo di utente
     */
    public CSU(Integer id, String userName, String password, String email, String nome, String cognome, String sesso, Boolean isGestore, Date dataSospensione) {
        super(id, userName, password, email, nome, cognome, sesso, isGestore);
        this.dataSospensione = dataSospensione;
    }

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
