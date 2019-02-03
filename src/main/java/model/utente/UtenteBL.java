/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.utente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * The Class UtenteBL.
 */
public class UtenteBL {

    /**
     * The Constant NAME_REGEX.
     */
    private static final String NAME_REGEX = "^[\\w'\\-,.][^0-9_!¡?÷?¿/\\\\+"
            + "=@#$%ˆ&*(){}|~<>;:\\[\\]]+$";

    /** The Constant USERNAME_REGEX. */
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9]+$";

    /**
     * The Constant DATE_REGEX.
     */
    private static final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|"
            + "1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))"
            + "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3"
            + "(?:(?:(?:1[6-9]|[29]\\d)?(?:0[48]|[2468][048]|[13579][26])|"
            + "(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])"
            + "(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)"
            + "?\\d{2})$";

    /**
     * The Constant EMAIL_REGEX.
     */
    private static final String EMAIL_REGEX = "[a-zA-Z0-9.!#$%&’*+/=?^_`{}~-]+"
            + "@(?:[a-zA-Z0-9-]+\\.)*unisa\\.it";

    /**
     * The Constant NAME_MAXLENGTH.
     */
    private static final int NAME_MAXLENGTH = 50;

    /**
     * The Constant NAME_MINLENGTH.
     */
    private static final int NAME_MINLENGTH = 2;

    /**
     * The Constant USERNAME_MAXLENGTH.
     */
    private static final int USERNAME_MAXLENGTH = 10;

    /**
     * The Constant USERNAME_MINLENGTH.
     */
    private static final int USERNAME_MINLENGTH = 3;

    /**
     * The Constant EMAIL_MAXLENGTH.
     */
    private static final int EMAIL_MAXLENGTH = 254;

    /**
     * The Constant EMAIL_MINLENGTH.
     */
    private static final int EMAIL_MINLENGTH = 7;

    /**
     * The Constant EMAIL_MINLENGTH.
     */
    private static final int PASSWORD_MAXLENGTH = 8;

    /**
     * The db.
     */
    private final UtenteDBInterface utenteDB;

    /**
     * Instantiates a new utente BL.<br>
     * This should be used only for testing, for others purpose use
     * {@link #UtenteBL()} instead.
     *
     * @param aUtenteDB the db manager
     */
    public UtenteBL(final UtenteDBInterface aUtenteDB) {
        utenteDB = aUtenteDB;
    }

    /**
     * Instantiates a new utente BL. Using the default db manager
     *
     */
    public UtenteBL() {
        this(new UtenteDB());
    }

    /**
     * Effettua registrazione.
     *
     * @param aUserName      the user name
     * @param aPassword      the password
     * @param aEmail         the email
     * @param aNome          the nome
     * @param aCognome       the cognome
     * @param aDataDiNascita the data di nascita
     * @param aSesso         the sesso
     * @return true, if successful
     * @throws Exception the exception
     */
    public final boolean effettuaRegistrazione(final String aUserName,
            final String aPassword, final String aEmail, final String aNome,
            final String aCognome, final String aDataDiNascita,
            final int aSesso) throws Exception {

        if ((aSesso == Utente.SESSO_ALTRO || aSesso == Utente.SESSO_MASCHILE
                || aSesso == Utente.SESSO_FEMMINILE)
                && validateLengthRegex(aNome,
                        NAME_MINLENGTH,
                        NAME_MAXLENGTH,
                        NAME_REGEX)
                && validateLengthRegex(aCognome,
                        NAME_MINLENGTH,
                        NAME_MAXLENGTH,
                        NAME_REGEX)
                && validateLengthRegex(aUserName,
                        USERNAME_MINLENGTH,
                        USERNAME_MAXLENGTH,
                        USERNAME_REGEX)
                && aDataDiNascita.matches(DATE_REGEX)
                && aPassword.length() >= PASSWORD_MAXLENGTH
                && validateLengthRegex(aEmail,
                        EMAIL_MINLENGTH,
                        EMAIL_MAXLENGTH,
                        EMAIL_REGEX)) {
            final String password = PasswordHash.createHash(aPassword);
            final LocalDate date = LocalDate.parse(aDataDiNascita,
                    DateTimeFormatter.ofPattern("dd[-][.][/]MM[-][.][/]yyyy"));
            final Utente utente = new CSU(aUserName.toLowerCase(Locale.ROOT),
                    password, aEmail, aNome, aCognome, aSesso, date);

            return utenteDB.insert(utente) > 0;
        }
        return false;
    }

    /**
     * Sospendi utente.
     *
     * @param aCSU the CSU
     * @return true, if successful
     * @throws Exception the exception
     */
    public final boolean sospendiUtente(final CSU aCSU) throws Exception {
        return utenteDB.update(aCSU) > 0;
    }

    /**
     * Autenticazione CSU.
     *
     * @param aUserName the user name
     * @param aPass     the pass
     * @return the csu
     * @throws Exception the exception
     */
    public final CSU autenticazioneCSU(final String aUserName,
            final String aPass) throws Exception {
        return (CSU) autenticazione(aUserName, aPass);
    }

    /**
     * Autenticazione gestore.
     *
     * @param aUserName the user name
     * @param aPass     the pass
     * @return the gestore
     * @throws Exception the exception
     */
    public final Gestore autenticazioneGestore(final String aUserName,
            final String aPass) throws Exception {
        return (Gestore) autenticazione(aUserName, aPass);
    }

    /**
     * Gets the utenti registrati.
     *
     * @return the utenti registrati
     * @throws Exception the exception
     */
    public final List<Utente> getUtentiRegistrati() throws Exception {
        return utenteDB.getAll();

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

    /**
     * Autenticazione.
     *
     * @param username the username
     * @param password the password
     * @return the utente
     * @throws Exception the exception
     */
    private Utente autenticazione(final String username, final String password)
            throws Exception {
        final Utente utente = utenteDB.getByUserName(username.toLowerCase());
        if (PasswordHash.validatePassword(password, utente.getPassword())) {
            return utente;
        } else {
            return null;
        }
    }
}
