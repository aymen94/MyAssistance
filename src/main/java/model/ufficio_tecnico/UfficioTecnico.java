package model.ufficio_tecnico;

import java.util.Objects;

/**
 * The Class UfficioTecnico.
 */
public class UfficioTecnico {

    /**
     * The id.
     */
    private int id;

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
    public final int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param aId set technical office identifier
     */
    public final void setId(final int aId) {
        this.id = aId;
    }

    /**
     * Gets the nome.
     *
     * @return nome get the name of UfficioTecnico
     */
    public final String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param aNome set nome of UfficioTecnico
     */
    public final void setNome(final String aNome) {
        this.nome = aNome;
    }

    /**
     * Gets the tel.
     *
     * @return get telefono of UfficioTecnico
     */
    public final String getTel() {
        return tel;
    }

    /**
     * Sets the tel.
     *
     * @param aTel set numero of UfficioTecnico
     */
    public final void setTel(final String aTel) {
        this.tel = aTel;
    }

    /**
     * Gets the email.
     *
     * @return get emailof UfficioTecnico
     */
    public final String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param aEmail set email of UfficioTecnico
     */
    public final void setEmail(final String aEmail) {
        this.email = aEmail;
    }

    /**
     * Gets the ubicazione.
     *
     * @return get ubicazione dell'uffico tecnico
     */
    public final String getUbicazione() {
        return ubicazione;
    }

    /**
     * Sets the ubicazione.
     *
     * @param aUbicazione set ubicazione of UfficioTecnico
     */
    public final void setUbicazione(final String aUbicazione) {
        this.ubicazione = aUbicazione;
    }

    /**
     * To string.
     *
     * @return return rappresentation of class
     */
    @Override
    public final String toString() {
        return "UfficioTecnico{" + "id=" + id + ", nome='" + nome + '\''
                + ", tel='" + tel + '\'' + ", email='" + email + '\''
                + ", ubicazione='" + ubicazione + '\'' + '}';
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return result of equals
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof UfficioTecnico)) {
            return false;
        }
        final UfficioTecnico other = (UfficioTecnico) obj;

        // if id or other.id is 0 do not compare them
        return (id == 0 || other.id == 0 || id == other.id)
                && Objects.equals(nome, other.nome)
                && Objects.equals(ubicazione, other.ubicazione)
                && Objects.equals(email, other.email)
                && Objects.equals(tel, other.tel);
    }

    /**
     * Hash code.
     *
     * @return result
     */
    @Override
    public final int hashCode() {
        return Objects.hash(nome, ubicazione, email, tel);
    }

}
