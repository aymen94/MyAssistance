/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;
import java.util.Date;

public abstract class Utente {

    Integer id;
    private String userName;
    private String password;
    private String email;
    private String nome;
    private String cognome;
    private Date dataSospensione;
    private Boolean isGestore;


    /**
     * Costruttore Utente
     * @param id identificatore
     * @param userName username utente
     * @param password parolasegreta per identificazione
     * @param email email utente
     * @param nome nome utente
     * @param cognome congome utente
     * @param dataSospensione data di sospensione
     * @param isGestore parametro per determinare il tipo di utente
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
     * @return id ottienti l'identificatore dell'utente.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id imposta il valore id dell'utente.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username Ottieni l'username dell'utente.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName imposta il valore di username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password ottieni la passoword dell'utente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password imposta la password dell'utente.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email  ottieni l'email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email imposta l'emaio dell'utente.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return nome ottieni il nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome imposta il nome dell'utente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return cognome ottieni il cognome dell'utente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome imposta il cognome dell'utente.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return dataSospensione ottieni la data di sospensione dell'utente.
     */
    public Date getDataSospensione() {
        return dataSospensione;
    }

    /**
     * @param dataSospensione imposta la data di sospensione dell'utente.
     */
    public void setDataSospensione(Date dataSospensione) {
        this.dataSospensione = dataSospensione;
    }

    /**
     * @return isGestore ritorna la tipologia dell'utente, se è 'true' allore è gestore, altrimenti e CSU.
     */
    public Boolean getIsGestore() {
        return isGestore;
    }

    /**
     * @param isGestore imposta il ruolo dell'utente.
     */
    public void setIsGestore(Boolean isGestore) {
        this.isGestore = isGestore;
    }

    /**
     * ritorna tutti i valori istanziati.
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