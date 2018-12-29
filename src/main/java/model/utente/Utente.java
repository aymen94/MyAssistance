/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;
import java.util.Date;

public abstract class Utente {

    protected Integer id;
    protected String userName;
    protected String password;
    protected String email;
    protected String nome;
    protected String cognome;
    protected Date dataSospensione;
    protected Boolean isGestore;

    /**
     * Costruttore Utente vuoto
     */
    public Utente(){ }
    /**
     * Costruttore Utentechang
     * @param id
     * @param userName
     * @param password
     * @param email
     * @param nome
     * @param cognome
     * @param dataSospensione
     * @param isGestore
     */
    public Utente(Integer id, String userName, String password, String email, String nome, String cognome, Date dataSospensione, Boolean isGestore) {
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
     * @return id
     */
    protected Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    protected void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    protected String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    protected void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password
     */
    protected String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    protected void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    protected String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    protected void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return nome
     */
    protected String getNome() {
        return nome;
    }

    /**
     * @param nome
     */
    protected void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return congnome
     */
    protected String getCognome() {
        return cognome;
    }

    /**
     * @param cognome
     */
    protected void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return dataSospensione
     */
    protected Date getDataSospensione() {
        return dataSospensione;
    }

    /**
     * @param dataSospensione
     */
    protected void setDataSospensione(Date dataSospensione) {
        this.dataSospensione = dataSospensione;
    }

    /**
     * @return isGestore
     */
    protected Boolean getIsGestore() {
        return isGestore;
    }

    /**
     * @param isGestore
     */
    protected void setIsGestore(Boolean isGestore) {
        isGestore = isGestore;
    }

    /**
     * @return toString Utente
     */
    @Override
    public String toString() {
        return "Utente {" +
                "id = " + id +
                ", userName = '" + userName + '\'' +
                ", password = " + password +
                ", email = '" + email + '\'' +
                ", nome = '" + nome + '\'' +
                ", cognome = '" + cognome + '\'' +
                ", dataSospensione = " + dataSospensione +
                ", isGestore = " + isGestore +
                '}';
    }
}