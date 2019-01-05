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
     * @param aId setta identificatore dell'ufficio tecnico
     */
    public void setId(final Integer aId) {
        this.id = aId;
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
     * @param aNome setta il nome dell'uffico tecnico
     */
    public void setNome(final String aNome) {
        this.nome = aNome;
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
     * @param aTel setta il numero dell'uffico tecnico
     */
    public void setTel(final String aTel) {
        this.tel = aTel;
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
     * @param aEmail setta l'email dell'uffico tecnico
     */
    public void setEmail(final String aEmail) {
        this.email = aEmail;
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
     * @param aUbicazione setta l'ubicazione dell'uffico tecnico
     */
    public void setUbicazione(final String aUbicazione) {
        this.ubicazione = aUbicazione;
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
