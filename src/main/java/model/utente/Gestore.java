/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.security.BasicPermission;
import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Gestore.
 */
public class Gestore extends Utente {


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
    public Gestore(Integer id, String userName, String password, String email, String nome, String cognome, String sesso, Boolean isGestore) {
        super(id, userName, password, email, nome, cognome, sesso, isGestore);
    }



    public Gestore(){ }



}
