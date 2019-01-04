/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.sql.Date;


/**
 * The Class Gestore.
 */
public class Gestore extends Utente {
    /**
     * Costruttore Utente.
     *
     * @param id              identificatore
     * @param userName        username utente
     * @param password        parolasegreta per identificazione
     * @param email           email utente
     * @param nome            nome utente
     * @param cognome         congome utente
     * @param dataSospensione data di sospensione
     */
    public Gestore(final Integer id, final String userName,
            final String password, final String email, final String nome,
            final String cognome, final Date dataSospensione) {
        super(id, userName, password, email, nome, cognome, dataSospensione,
                true);
    }

    /**
     * Instantiates a new gestore.
     */
    public Gestore() {
    }

}
