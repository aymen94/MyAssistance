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
     * is gestore.
     */
    private Boolean isGestore;

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
     * Effetuare Ovveride.
     *
     * @return Boolean
     */
    public abstract Boolean isGestore();

    /**
     * Gets the id.
     *
     * @return id ottienti l'identificatore dell'utente.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param aId imposta il valore id dell'utente.
     */
    public void setId(final int aId) {
        id = aId;
    }

    /**
     * Ottieni l' username.
     *
     * @return username Ottieni l'username dell'utente.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * imposta l'username.
     *
     * @param aUserName imposta il valore di username.
     */
    public void setUserName(final String aUserName) {
        userName = aUserName;
    }

    /**
     * Ottieni the password.
     *
     * @return password ottieni la passoword dell'utente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password.
     *
     * @param aPassword imposta la password dell'utente.
     */
    public void setPassword(final String aPassword) {
        password = aPassword;
    }

    /**
     * Ottieni le email.
     *
     * @return email ottieni l'email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta la email.
     *
     * @param aEmail imposta l'emaio dell'utente.
     */
    public void setEmail(final String aEmail) {
        email = aEmail;
    }

    /**
     * Ottieni il nome.
     *
     * @return nome ottieni il nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome.
     *
     * @param aNome imposta il nome dell'utente.
     */
    public void setNome(final String aNome) {
        this.nome = aNome;
    }

    /**
     * Ottieni il cognome.
     *
     * @return cognome ottieni il cognome dell'utente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome.
     *
     * @param aCognogme imposta il cognome dell'utente.
     */
    public void setCognome(final String aCognogme) {
        cognome = aCognogme;
    }

    /**
     * Ottieni il sesso dell'utente.
     *
     * @return sesso
     */
    public int getSesso() {
        return sesso;
    }

    /**
     * Impsti  sesso dell'utente.
     *
     * @param aSesso imposta il valore sesso dell'utente.
     */
    public void setSesso(final int aSesso) {
        sesso = aSesso;
    }

    /**
     * Ottieni la data di nascità dell'utente.
     *
     * @return Date
     */
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Impsti  la data di nascità dell'utente.
     *
     * @param aData imposta il valore sesso dell'utente.
     */
    public void setDataDiNascita(final LocalDate aData) {
        dataDiNascita = aData;
    }

    /**
     * Confronta se due utenti sono identici.
     *
     * @return boolean
     */
    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Utente utente = (Utente) o;
        return sesso == utente.sesso && Objects.equals(id, utente.id) && Objects
            .equals(userName, utente.userName) && Objects
            .equals(password, utente.password) && Objects
            .equals(email, utente.email) && Objects.equals(nome, utente.nome)
            && Objects.equals(cognome, utente.cognome) && Objects
            .equals(isGestore, utente.isGestore);
    }

    /**
     * Genera un intero per la tabella hash.
     *
     * @return int Utente
     */
    @Override public int hashCode() {
        return Objects.hash(id,
            userName,
            password,
            email,
            nome,
            cognome,
            isGestore,
            sesso);
    }

    /**
     * ritorna tutti i valori istanziati.
     *
     * @return String
     */
    @Override public String toString() {
        return "Utente {" + "id = " + id + ", userName = '" + userName + '\''
            + ", password = " + password + ", email = '" + email + '\''
            + ", nome = '" + nome + '\'' + ", cognome = '" + cognome + '\''
            + "}";
    }

}

