/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;
import java.util.Date;

public abstract class Utente {

    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String nome;
    private String cognome;
    private Date dataSospensione;
    private Boolean isGestore;

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
    private Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    private void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    private String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    private void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password
     */
    private String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    private String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    private void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return nome
     */
    private String getNome() {
        return nome;
    }

    /**
     * @param nome
     */
    private void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return congnome
     */
    private String getCognome() {
        return cognome;
    }

    /**
     * @param cognome
     */
    private void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return dataSospensione
     */
    private Date getDataSospensione() {
        return dataSospensione;
    }

    /**
     * @param dataSospensione
     */
    private void setDataSospensione(Date dataSospensione) {
        this.dataSospensione = dataSospensione;
    }

    /**
     * @return isGestore
     */
    private Boolean getIsGestore() {
        return isGestore;
    }

    /**
     * @param isGestore
     */
    private void setIsGestore(Boolean isGestore) {
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