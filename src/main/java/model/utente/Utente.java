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
     * @return id ottienti l'identificatore dell'utente.
     */
    Integer getId() {
        return id;
    }

    /**
     * @param id imposta il valore id dell'utente.
     */
    void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username Ottieni l'username dell'utente.
     */
    String getUserName() {
        return userName;
    }

    /**
     * @param userName imposta il valore di username.
     */
    void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password ottieni la passoword dell'utente.
     */
    String getPassword() {
        return password;
    }

    /**
     * @param password imposta la password dell'utente.
     */
    void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email  ottieni l'email dell'utente.
     */
    String getEmail() {
        return email;
    }

    /**
     * @param email imposta l'emaio dell'utente.
     */
    void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return nome ottieni il nome dell'utente.
     */
    String getNome() {
        return nome;
    }

    /**
     * @param nome imposta il nome dell'utente.
     */
    void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return cognome ottieni il cognome dell'utente.
     */
    String getCognome() {
        return cognome;
    }

    /**
     * @param cognome imposta il cognome dell'utente.
     */
    void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return dataSospensione ottieni la data di sospensione dell'utente.
     */
    Date getDataSospensione() {
        return dataSospensione;
    }

    /**
     * @param dataSospensione imposta la data di sospensione dell'utente.
     */
    void setDataSospensione(Date dataSospensione) {
        this.dataSospensione = dataSospensione;
    }

    /**
     * @return isGestore ritorna la tipologia dell'utente, se è 'true' allore è gestore, altrimenti e CSU.
     */
    Boolean getIsGestore() {
        return isGestore;
    }

    /**
     * @param isGestore imposta il ruolo dell'utente.
     */
    void setIsGestore(Boolean isGestore) {
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