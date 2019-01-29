package model.ufficio_tecnico;

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
     * This will be removed in final release, please use
     * {@link #UfficioTecnicoBL(UfficioTecnicoDBInterface)} constructor.
     */
    @Deprecated
    public UfficioTecnicoBL() {
        this(new UfficioTecnicoDB());
    }

    /**
     * Instantiates a new ufficio tecnico BL.
     *
     * @param db it's database manager
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

        if (validateLengthRegex(aNome, 1, MAX_NOME_LENGTH, "^[A-Za-z0-9]+$")
                && validateLengthRegex(aTel,
                        MIN_TEL_LENGTH,
                        MAX_TEL_LENGTH,
                        "^(+){0,1}[0-9]*$")
                && validateLengthRegex(aEmail,
                        MIN_EMAIL_LENGTH,
                        MAX_EMAIL_LENGTH,
                        REGEX_EMAIL)
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

    /**
     * Validate length and regex.
     *
     * @param text      the text
     * @param minLength the min length
     * @param maxLength the max length
     * @param regex     the regex
     * @return true, if successful
     */
    private boolean validateLengthRegex(final String text, final int minLength,
            final int maxLength, final String regex) {
        return text.length() >= minLength && text.length() <= maxLength
                && text.matches(regex);
    }
}
