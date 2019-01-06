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
     * Construct signed.
     *
     * @param aid         the id
     * @param anome       the nome
     * @param atel        the tel
     * @param aemail      the email
     * @param aubicazione the ubicazione
     */

    public UfficioTecnico(final Integer aid, final String anome,
            final String atel, final String aemail, final String aubicazione) {
        this.id = aid;
        this.nome = anome;
        this.tel = atel;
        this.email = aemail;
        this.ubicazione = aubicazione;
    }

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
     * @param aid set identificatore  of UfficioTecnico
     */
    public void setId(final Integer aid) {
        this.id = aid;
    }

    /**
     * Gets the nome.
     *
     * @return nome getthe the name of UfficioTecnico
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param anome set nome of UfficioTecnico
     */
    public void setNome(final String anome) {
        this.nome = anome;
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
     * @param atel set numero of UfficioTecnico
     */
    public void setTel(final String atel) {
        this.tel = atel;
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
     * @param aemail zet email of UfficioTecnico
     */
    public void setEmail(final String aemail) {
        this.email = aemail;
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
     * @param aubicazione set ubicazione of UfficioTecnico
     */
    public void setUbicazione(final String aubicazione) {
        this.ubicazione = aubicazione;
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
