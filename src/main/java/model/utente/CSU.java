/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.util.Date;

public class CSU extends Utente {

    /**
     * Costruttore Utente
     * @param id
     * @param userName
     * @param password
     * @param email
     * @param nome
     * @param cognome
     * @param dataSospensione
     * @param isGestore
     */
    public CSU(Integer id,String userName,String password, String email, String nome, String cognome, Date dataSospensione, Boolean isGestore) {
        super(id, userName, password, email, nome, cognome, dataSospensione, isGestore);
    }


    /**
     * Costruttore CSU Vuoto;
     */
    public CSU(){
        super();

    }
}
