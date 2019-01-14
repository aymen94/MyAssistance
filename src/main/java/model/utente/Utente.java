/*
Project: MyAssistance
Author: Gaetano
Date: 23/12/2018
*/
package model.utente;

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


    /** is gestore. */
    private Boolean isGestore;

    /** il sesso. */
    private int sesso;

    public void setSesso(int aSesso) {
        sesso = aSesso;
    }

    /**
     * Costruttore vuoto.
     */
    public Utente() {
    }

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
     * ritorna tutti i valori istanziati.
     *
     * @return toString Utente
     */
    @Override
    public String toString() {
        return "Utente {" + "id = " + id + ", userName = '" + userName + '\''
                + ", password = " + password + ", email = '" + email + '\''
                + ", nome = '" + nome + '\'' + ", cognome = '" + cognome + '\''
                + "}";
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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Utente utente = (Utente) o;

        if (sesso != utente.sesso)
            return false;
        if (id != null ? !id.equals(utente.id) : utente.id != null)
            return false;
        if (userName != null ?
            !userName.equals(utente.userName) :
            utente.userName != null)
            return false;
        if (password != null ?
            !password.equals(utente.password) :
            utente.password != null)
            return false;
        if (email != null ? !email.equals(utente.email) : utente.email != null)
            return false;
        if (nome != null ? !nome.equals(utente.nome) : utente.nome != null)
            return false;
        if (cognome != null ?
            !cognome.equals(utente.cognome) :
            utente.cognome != null)
            return false;
        return isGestore != null ?
            isGestore.equals(utente.isGestore) :
            utente.isGestore == null;
    }

    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (isGestore != null ? isGestore.hashCode() : 0);
        result = 31 * result + sesso;
        return result;
    }
}
