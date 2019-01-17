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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UfficioTecnico that = (UfficioTecnico) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null)
            return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null)
            return false;
        return ubicazione != null ?
                ubicazione.equals(that.ubicazione) :
                that.ubicazione == null;
    }

    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (ubicazione != null ? ubicazione.hashCode() : 0);
        return result;
    }
}
