package model.ufficio_tecnico;

import java.sql.SQLException;
import java.util.List;

/**
 * The Class UfficioTecnicoBL.
 */
public final class UfficioTecnicoBL {

    /**
     * The Constant MAX_TITLE_LENGTH.
     */
    private static final int MAX_NOME_LENGTH = 50;

    /**
     * The Constant MAX_TITLE_LENGTH.
     */
    private static final int MAX_TEL_LENGTH = 15;

    /**
     * The Constant MAX_EMAIL_LENGTH.
     */
    private static final int MAX_EMAIL_LENGTH = 255;

    /**
     * The Constant MAX_EMAIL_LENGTH.
     */
    private static final int MAX_UBICAZIONE_LENGTH = 50;

    /**
     * variable database.
     */
    private UfficioTecnicoDBInterface database;

    /**
     * Instantiates a new ufficio tecnico BL.
     *
     * @param db it's database
     */
    public UfficioTecnicoBL(final UfficioTecnicoDBInterface db) {
        this.database = db;
    }

    /**
     * Memorize and check the technical office.
     *
     * @param aNome       the nome
     * @param aTel        the tel
     * @param aEmail      the email
     * @param aUbicazione the ubicazione
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean insertUfficioTecnico(final String aNome, final String aTel,
            final String aEmail, final String aUbicazione) throws Exception {
        final UfficioTecnico uff = new UfficioTecnico();

        if (aNome.length() > 0 && aNome.length() <= MAX_NOME_LENGTH
                && aTel.length() > 0 && aTel.length() <= MAX_TEL_LENGTH
                && aEmail.length() > 0 && aEmail.length() <= MAX_EMAIL_LENGTH
                && aUbicazione.length() > 0
                && aUbicazione.length() <= MAX_UBICAZIONE_LENGTH) {
            uff.setNome(aNome);
            uff.setTel(aTel);
            uff.setEmail(aEmail);
            uff.setUbicazione(aUbicazione);

            return database.insert(uff) > 0;
        }
        return false;
    }

    /**
     * gets and check all the technical office.
     *
     * @return true, if successful.
     * @throws Exception the exception
     */
    public List<UfficioTecnico> getUfficiTecnici() throws Exception {
        return database.getAll();
    }

    /**
     * get only the technical office with this id.
     *
     * @param aId the id
     * @return the ufficio tecnico
     * @throws Exception the exception
     */
    public UfficioTecnico ottieniUfficio(final int aId) throws Exception {
        return database.getById(aId);
    }
}
