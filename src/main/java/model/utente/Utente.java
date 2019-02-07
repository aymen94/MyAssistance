/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The Class Utente.
 */
public abstract class Utente {

    /**
     * The Constant SESSO_ALTRO.
     */
    public static final int SESSO_ALTRO = 0;

    /**
     * The Constant SESSO_MASCHILE.
     */
    public static final int SESSO_MASCHILE = 1;

    /**
     * The Constant SESSO_FEMMINILE.
     */
    public static final int SESSO_FEMMINILE = 2;

    /**
     * l' id.
     */
    private int id;

    /**
     * lo user name.
     */
    private String userName;

    /**
     * la password.
     */
    private String password;

    /**
     * la email.
     */
    private String email;

    /**
     * il nome.
     */
    private String nome;

    /**
     * il cognome.
     */
    private String cognome;

    /**
     * il sesso.
     */
    private int sesso;

    /**
     * Data di nascità.
     */
    private LocalDate dataDiNascita;

    /**
     * Costruttore vuoto.
     */
    public Utente() {
    }

    /**
     * Instantiates a new utente.
     *
     * @param aUserName      the user name
     * @param aPassword      the password
     * @param aEmail         the email
     * @param aNome          the nome
     * @param aCognome       the cognome
     * @param aSesso         the sesso
     * @param aDataDiNascita the data di nascita
     */
    public Utente(final String aUserName, final String aPassword,
            final String aEmail, final String aNome, final String aCognome,
            final int aSesso, final LocalDate aDataDiNascita) {
        userName = aUserName;
        password = aPassword;
        email = aEmail;
        nome = aNome;
        cognome = aCognome;
        sesso = aSesso;
        dataDiNascita = aDataDiNascita;
    }

    /**
     * Make Ovveride.
     *
     * @return Boolean
     */
    public abstract Boolean isGestore();

    /**
     * Gets the id.
     *
     * @return id obtain the user identifier.
     */
    public final Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param aId set the user's id value.
     */
    public final void setId(final int aId) {
        id = aId;
    }

    /**
     * Get username.
     *
     * @return username Get the user's username.
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * Sets l'username.
     *
     * @param aUserName set the value of username.
     */
    public final void setUserName(final String aUserName) {
        userName = aUserName;
    }

    /**
     * Get the password.
     *
     * @return password get the user passoword.
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param aPassword set the user password.
     */
    public final void setPassword(final String aPassword) {
        password = aPassword;
    }

    /**
     * Get le email.
     *
     * @return email get the user's email.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param aEmail sets the user's email.
     */
    public final void setEmail(final String aEmail) {
        email = aEmail;
    }

    /**
     * Gets the nome.
     *
     * @return nome ottieni the user's name.
     */
    public final String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param aNome set the user's name.
     */
    public final void setNome(final String aNome) {
        this.nome = aNome;
    }

    /**
     * Get the cognome.
     *
     * @return cognome get the user's last name.
     */
    public final String getCognome() {
        return cognome;
    }

    /**
     * Sets the cognome.
     *
     * @param aCognogme set the user's last name.
     */
    public final void setCognome(final String aCognogme) {
        cognome = aCognogme;
    }

    /**
     * Get the user's sex.
     *
     * @return sesso
     */
    public final int getSesso() {
        return sesso;
    }

    /**
     * Set up the user's gender.
     *
     * @param aSesso sets the gender value of the user.
     */
    public final void setSesso(final int aSesso) {
        sesso = aSesso;
    }

    /**
     * Get the date of the user's birth.
     *
     * @return Date
     */
    public final LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Set the date of the user's birth.
     *
     * @param aData sets the gender value of the user.
     */
    public final void setDataDiNascita(final LocalDate aData) {
        dataDiNascita = aData;
    }

    /**
     * Compare if two users are identical.
     *
     * @return boolean
     */
    @Override public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Utente utente = (Utente) o;
        return sesso == utente.sesso && Objects.equals(id, utente.id)
                && Objects.equals(userName, utente.userName)
                && Objects.equals(password, utente.password)
                && Objects.equals(email, utente.email)
                && Objects.equals(nome, utente.nome)
                && Objects.equals(cognome, utente.cognome);
    }

    /**
     * It generates an integer for the hash table.
     *
     * @return int Utente
     */
    @Override public final int hashCode() {
        return Objects
                .hash(id, userName, password, email, nome, cognome, sesso);
    }

    /**
     * returns all instantiated values.
     *
     * @return String
     */
    @Override public final String toString() {
        return "Utente {" + "id = " + id + ", userName = '" + userName + '\''
            + ", password = " + password + ", email = '" + email + '\''
            + ", nome = '" + nome + '\'' + ", cognome = '" + cognome + '\''
            + "}";
    }

}
