/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.ufficiotecnico;

// TODO: Auto-generated Javadoc
/**
 * The Class UfficioTecnico.
 */
public class UfficioTecnico {

    /** The id. */
    private Integer id;

    /** The nome. */
    private String nome;

    /** The tel. */
    private String tel;

    /** The email. */
    private String email;

    /** The ubicazione. */
    private String ubicazione;

    /**
     * Costrutto firmato.
     *
     * @param id         the id
     * @param nome       the nome
     * @param tel        the tel
     * @param email      the email
     * @param ubicazione the ubicazione
     */
    public UfficioTecnico(final Integer id, final String nome, final String tel,
            final String email, final String ubicazione) {
        this.id = id;
        this.nome = nome;
        this.tel = tel;
        this.email = email;
        this.ubicazione = ubicazione;
    }

    /**
     * Costrutto vuoto.
     */
    public UfficioTecnico() {
    }

    /**
     * Gets the id.
     *
     * @return ottieni l'id dell'ufficio tecnico
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id setta identificatore dell'ufficio tecnico
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Gets the nome.
     *
     * @return nome ottieni il nome dell'uffico tecnico
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param nome setta il nome dell'uffico tecnico
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * Gets the tel.
     *
     * @return ottieni il telefono dell'uffico tecnico
     */
    public String getTel() {
        return tel;
    }

    /**
     * Sets the tel.
     *
     * @param tel setta il numero dell'uffico tecnico
     */
    public void setTel(final String tel) {
        this.tel = tel;
    }

    /**
     * Gets the email.
     *
     * @return ottieni l'email dell'uffico tecnico
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email setta l'email dell'uffico tecnico
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets the ubicazione.
     *
     * @return ottieni l'ubicazione dell'uffico tecnico
     */
    public String getUbicazione() {
        return ubicazione;
    }

    /**
     * Sets the ubicazione.
     *
     * @param ubicazione setta l'ubicazione dell'uffico tecnico
     */
    public void setUbicazione(final String ubicazione) {
        this.ubicazione = ubicazione;
    }

    /**
     * To string.
     *
     * @return ritorna la rappreenzatione della classe
     */
    @Override
    public String toString() {
        return "UfficioTecnico{" + "id=" + id + ", nome='" + nome + '\''
                + ", tel='" + tel + '\'' + ", email='" + email + '\''
                + ", ubicazione='" + ubicazione + '\'' + '}';
    }
}
