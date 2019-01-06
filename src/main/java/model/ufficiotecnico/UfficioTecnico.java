package model.ufficiotecnico;

/**
 * The Class UfficioTecnico.
 */
public class UfficioTecnico {

    /**
     * The id.
     */
    private Integer id;

    /**
     * The nome.
     */
    private String nome;

    /**
     * The tel.
     */
    private String tel;

    /**
     * The email.
     */
    private String email;

    /**
     * The ubicazione.
     */
    private String ubicazione;

    /**
     * Costrutto vuoto.
     */
    public UfficioTecnico() {
    }

    /**
     * Gets the id.
     *
     * @return id get the id of UfficioTecnico
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
     * @return nome get the name of UfficioTecnico
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param aNome set nome of UfficioTecnico
     */
    public void setNome(final String aNome) {
        this.nome = aNome;
    }

    /**
     * Gets the tel.
     *
     * @return get telefono of UfficioTecnico
     */
    public String getTel() {
        return tel;
    }

    /**
     * Sets the tel.
     *
     * @param aTel set numero of UfficioTecnico
     */
    public void setTel(final String aTel) {
        this.tel = aTel;
    }

    /**
     * Gets the email.
     *
     * @return get emailof UfficioTecnico
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param aEmail set email of UfficioTecnico
     */
    public void setEmail(final String aEmail) {
        this.email = aEmail;
    }

    /**
     * Gets the ubicazione.
     *
     * @return get ubicazione dell'uffico tecnico
     */
    public String getUbicazione() {
        return ubicazione;
    }

    /**
     * Sets the ubicazione.
     *
     * @param aUbicazione set ubicazione of UfficioTecnico
     */
    public void setUbicazione(final String aUbicazione) {
        this.ubicazione = aUbicazione;
    }

    /**
     * To string.
     *
     * @return return rappresentation of class
     */
    @Override public String toString() {
        return "UfficioTecnico{" + "id=" + id + ", nome='" + nome + '\''
                + ", tel='" + tel + '\'' + ", email='" + email + '\''
                + ", ubicazione='" + ubicazione + '\'' + '}';
    }
}
