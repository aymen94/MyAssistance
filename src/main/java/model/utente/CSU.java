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
     * @param id identificatore
     * @param userName username utente
     * @param password parolasegreta per identificazione
     * @param email email utente
     * @param nome nome utente
     * @param cognome congome utente
     * @param dataSospensione data di sospensione
     * @param isGestore parametro per determinare il tipo di utente
     */
    public CSU(Integer id,String userName,String password, String email, String nome, String cognome, Date dataSospensione, Boolean isGestore) {
        super(id, userName, password, email, nome, cognome, dataSospensione, isGestore);
    }
}
