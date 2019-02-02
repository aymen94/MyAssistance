package model.ufficio_tecnico;

import java.util.List;

/**
 * The Class UfficioTecnicoBL.
 */
public class UfficioTecnicoBL {

    /**
     * The Constant MAX_TITLE_LENGTH.
     */
    private static final int MAX_NOME_LENGTH = 50;

    /**
     * The Constant MIN_TITLE_LENGTH.
     */
    private static final int MIN_TEL_LENGTH = 3;

    /**
     * The Constant MAX_TITLE_LENGTH.
     */
    private static final int MAX_TEL_LENGTH = 21;

    /**
     * The Constant MAX_EMAIL_LENGTH.
     */
    private static final int MIN_EMAIL_LENGTH = 7;

    /**
     * The Constant MAX_EMAIL_LENGTH.
     */
    private static final int MAX_EMAIL_LENGTH = 254;

    /**
     * The Constant REGEX_EMAIL.
     */
    private static final String REGEX_EMAIL = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+"
            + "(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}"
            + "\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+"
            + "\\.)+[a-zA-Z]{2,}))$";

    /**
     * The Constant MAX_EMAIL_LENGTH.
     */
    private static final int MAX_UBICAZIONE_LENGTH = 50;

    /**
     * variable database.
     */
    private UfficioTecnicoDBInterface database;

    /**
     * Instantiates a new ufficio tecnico BL. Using the default db manager
     *
     */
    public UfficioTecnicoBL() {
        this(new UfficioTecnicoDB());
    }

    /**
     * Instantiates a new ufficio tecnico BL.<br>
     * This should be used only for testing,
     * for others purpose use {@link #UfficioTecnicoBL()} instead.
     *
     * @param db it's database manager
     */
    public UfficioTecnicoBL(final UfficioTecnicoDBInterface db) {
        this.database = db;
    }

    /**
     * Memorize and check the technical office.
     *
     * @param uff the ufficio tecnico to insert
     * @return true, if successful
     * @throws Exception the exception
     */
    public final boolean insertUfficioTecnico(final UfficioTecnico uff)
            throws Exception {
        if (validateLengthRegex(uff.getNome(),
                1,
                MAX_NOME_LENGTH,
                "^[\\w\\h]+$")
                && (uff.getTel() == null || validateLengthRegex(uff.getTel(),
                        MIN_TEL_LENGTH,
                        MAX_TEL_LENGTH,
                        "^(\\+){0,1}[0-9]*$"))
                && validateLengthRegex(uff.getEmail(),
                        MIN_EMAIL_LENGTH,
                        MAX_EMAIL_LENGTH,
                        REGEX_EMAIL)
                && (uff.getUbicazione() == null
                        || (uff.getUbicazione().length() > 0
                                && uff.getUbicazione()
                                        .length() <= MAX_UBICAZIONE_LENGTH))) {

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
    public final List<UfficioTecnico> getUfficiTecnici() throws Exception {
        return database.getAll();
    }

    /**
     * get only the technical office with this id.
     *
     * @param aId the id
     * @return the ufficio tecnico
     * @throws Exception the exception
     */
    public final UfficioTecnico ottieniUfficio(final int aId) throws Exception {
        return database.getById(aId);
    }

    /**
     * Validate length and regex.
     *
     * @param text      the text
     * @param minLength the min length
     * @param maxLength the max length
     * @param regex     the regex
     * @return true, if successful
     */
    private boolean validateLengthRegex(final String text,
            final int minLength, final int maxLength, final String regex) {
        return text.length() >= minLength && text.length() <= maxLength
                && text.matches(regex);
    }
}
