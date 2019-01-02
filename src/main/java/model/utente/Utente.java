/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Utente.
 */
public abstract class Utente {

    /** l' id. */
    private Integer id;

    /** lo user name. */
    private String userName;

    /** la password. */
    private String password;

    /** la email. */
    private String email;

    /** il nome. */
    private String nome;

    /** il cognome. */
    private String cognome;

    /** la data sospensione. */
    private Date dataSospensione;

    /** is gestore. */
    private Boolean isGestore;

    /**
     * Costruttore Utente.
     *
     * @param id               identificatore
     * @param userName        username utente
     * @param password       parolasegreta per identificazione
     * @param email           email utente
     * @param nome            nome utente
     * @param cognome         congome utente
     * @param dataSospensione data di sospensione
     * @param isGestore       parametro per determinare il tipo di utente
     */

    public Utente(final Integer id, final String userName,
            final String password, String email, final String nome,
            final String cognome, final Date dataSospensione,
            final Boolean isGestore) {

        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.dataSospensione = dataSospensione;
        this.isGestore = isGestore;
    }

    /**
     * Costruttore vuoto.
     */
    public Utente() {
    }

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
     * @param id imposta il valore id dell'utente.
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @param userName imposta il valore di username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @param password imposta la password dell'utente.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @param email imposta l'emaio dell'utente.
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param nome imposta il nome dell'utente.
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * @param cognome imposta il cognome dell'utente.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Ottieni la data sospensione.
     *
     * @return dataSospensione ottieni la data di sospensione dell'utente.
     */
    public Date getDataSospensione() {
        return dataSospensione;
    }

    /**
     * Imposta la data sospensione.
     *
     * @param dataSospensione imposta la data di sospensione dell'utente.
     */
    public void setDataSospensione(Date dataSospensione) {
        this.dataSospensione = dataSospensione;
    }

    /**
     * controlli è gestore o no.
     *
     * @return isGestore ritorna la tipologia dell'utente, se è 'true' allore è
     *         gestore, altrimenti e CSU.
     */
    public Boolean getIsGestore() {
        return isGestore;
    }

    /**
     * Imposti iSgestore.
     *
     * @param isGestore imposta il ruolo dell'utente.
     */
    public void setIsGestore(Boolean isGestore) {
        this.isGestore = isGestore;
    }

    /**
     * ritorna tutti i valori istanziati.
     *
     * @return toString Utente
     */
    @Override
    public String toString() {
        return "Utente {" + "id = " + id + ", userName = '" + userName + '\''
                + ", password = " + password + ", email = '" + email + '\''
                + ", nome = '" + nome + '\'' + ", cognome = '" + cognome + '\''
                + ", dataSospensione = " + dataSospensione + ", isGestore = "
                + isGestore + '}';
    }

    /**
     * Ottieni il sesso dell'utente.
     *
     * @return  sesso
     */
    public String getSesso() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Impsti  sesso dell'utente.
     *
     * @param string  sesso
     */
    public void setSesso(final String string) {
        // TODO Auto-generated method stub

    }
}
