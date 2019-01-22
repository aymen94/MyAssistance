package model.ufficiotecnico;


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
     *  variable database.
     */
    private UfficioTecnicoDB database;

    /**
     * This is an utility class. So no constructor should be used.
     */
    private UfficioTecnicoBL() {
        UfficioTecnicoDB data = new UfficioTecnicoDB();
    }

    /**
     * This is an utility class. So no constructor should be used.
     * @param db it's database
     */
    private UfficioTecnicoBL(final UfficioTecnicoDB db) {
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
     */
    public boolean insertUfficioTecnico(final String aNome, final String aTel,
            final String aEmail, final String aUbicazione) {
        try {
            final UfficioTecnico uff = new UfficioTecnico();

            if (aNome.length() > 0 && aNome.length() <= MAX_NOME_LENGTH
                    && aTel.length() > 0 && aTel.length() <= MAX_TEL_LENGTH
                    && aEmail.length() > 0
                    && aEmail.length() <= MAX_EMAIL_LENGTH
                    && aUbicazione.length() > 0
                    && aUbicazione.length() <= MAX_UBICAZIONE_LENGTH) {
                uff.setNome(aNome);
                uff.setTel(aTel);
                uff.setEmail(aEmail);
                uff.setUbicazione(aUbicazione);

              return database.insert(uff) > 0;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * gets and check all the technical office.
     * @return true, if successful.
     * @throws SQLException error sql.
     */
    public boolean ottieniUfficiTecnici() throws SQLException {

       List<UfficioTecnico> all = database.getAll();


        return all != null;
    }


    /**
     * get only the technical office with this id.
     * @param aId the id
     * @return true, if successful
     * @throws SQLException error sql.
     */
    public boolean ottieniUfficio(final Integer aId) throws SQLException {
        UfficioTecnico byId = null;

            if (aId != null && aId > 0) {
                byId = database.getById(aId);
            }

        return byId != null;
    }



    /**
     * check if id is validate and delete technical office .
     *
     * @param aId the ubicazione
     * @return true, if successful
     */
    public boolean deleteUfficioTecnico(final Integer aId) {
        try {
            final UfficioTecnico uff = new UfficioTecnico();
            if (aId != null && aId != 0) {
                uff.setId(aId);
                return database.deleteById(uff.getId()) > 0;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
