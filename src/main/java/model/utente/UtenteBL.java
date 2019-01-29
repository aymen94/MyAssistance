/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.utente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The Class UtenteBL.
 */
public final class UtenteBL {

    /**
     * The Constant NAME_REGEX.
     */
    private static final String NAME_REGEX = "^[\\w'\\-,.][^0-9_!¡?÷?¿/\\\\+"
            + "=@#$%ˆ&*(){}|~<>;:[\\]]+$";

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
    private static final String EMAIL_REGEX = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+"
            + "(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\"
            + ".[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)"
            + "*unisa\\.it))$";

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
     * Instantiates a new utente BL.
     *
     * @param aUtenteDB the utente DB
     */
    public UtenteBL(final UtenteDBInterface aUtenteDB) {
        utenteDB = aUtenteDB;
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
    public boolean effettuaRegistrazione(final String aUserName,
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
                        "^[\\w]+$")
                && aDataDiNascita.matches(DATE_REGEX)
                && aPassword.length() >= PASSWORD_MAXLENGTH
                && validateLengthRegex(aEmail,
                        EMAIL_MINLENGTH,
                        EMAIL_MAXLENGTH,
                        EMAIL_REGEX)) {
            final String password = PasswordHash.createHash(aPassword);
            final LocalDate date = LocalDate.parse(aDataDiNascita,
                    DateTimeFormatter.ofPattern("dd[-][.][/]mm[-][.][/]yyyy"));
            final Utente utente = new CSU(aUserName, password, aEmail, aNome,
                    aCognome, aSesso, date);

            return utenteDB.insert(utente) > 0;
        }
        return false;
    }

    /**
     * Sospendi utente.
     *
     * @param aCSU the CSU
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean sospendiUtente(final CSU aCSU) throws SQLException {
        return false;
    }

    /**
     * Autenticazione CSU.
     *
     * @param aUserName the user name
     * @param aPass     the pass
     * @return the csu
     * @throws Exception the exception
     */
    public CSU autenticazioneCSU(final String aUserName, final String aPass)
            throws Exception {
        try {
            return (CSU) autenticazione(aUserName, aPass);
        } catch (ClassCastException ex) {
            return null;
        }
    }

    /**
     * Autenticazione gestore.
     *
     * @param aUserName the user name
     * @param aPass     the pass
     * @return the gestore
     * @throws Exception the exception
     */
    public Gestore autenticazioneGestore(final String aUserName,
            final String aPass) throws Exception {
        try {
            return (Gestore) autenticazione(aUserName, aPass);
        } catch (ClassCastException ex) {
            return null;
        }
    }

    /**
     * Gets the utenti registrati.
     *
     * @return the utenti registrati
     * @throws Exception the exception
     */
    public List<Utente> getUtentiRegistrati() throws Exception {
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
        final Utente utente = utenteDB.getByUserName(username);
        if (PasswordHash.validatePassword(password, utente.getPassword())) {
            return utente;
        } else {
            return null;
        }
    }
}
