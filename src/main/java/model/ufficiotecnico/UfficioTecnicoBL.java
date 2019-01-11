/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.ufficiotecnico;

/**
 * The Class UfficioTecnicoBL.
 */
public final class UfficioTecnicoBL {

    /**
     * varialbe db.
     */
    private UfficioTecnicoDB datab;
    /**
     * This is an utility class.
     * @param database this instance make database method to query.
     */
    private UfficioTecnicoBL(final UfficioTecnicoDB database) {
       this.datab = database;
    }
    /**
     * This is an utility class.
     * without a parameter.
     */
    private UfficioTecnicoBL() {
       new UfficioTecnicoDB();
    }


    /**
     * Memorizza ufficio tecnico.
     *
     * @param nome       the nome
     * @param tel        the tel
     * @param email      the email
     * @param ubicazione the ubicazione
     * @return true, if successful
     */
    public static boolean insertUfficioTecnico(final String nome,
            final String tel, final String email, final String ubicazione) {
        return false;
    }
}
