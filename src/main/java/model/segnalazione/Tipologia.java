package model.segnalazione;

import java.util.Objects;

/**
 * The Class Tipologia.
 */
public final class Tipologia {

    /**
     * The nome.
     */
    private String nome;

    /**
     * The id.
     */
    private int id;

    /**
     * The priorita.
     */
    private short priorita;

    /**
     * Instantiates a new tipologia.
     */
    public Tipologia() {
    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param aNome the nome to set
     */
    public void setNome(final String aNome) {
        nome = aNome;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param aId the id to set
     */
    public void setId(final int aId) {
        id = aId;
    }

    /**
     * Gets the priorita.
     *
     * @return the priorita
     */
    public short getPriorita() {
        return priorita;
    }

    /**
     * Sets the priorita.
     *
     * @param aPriorita the priorita to set
     */
    public void setPriorita(final short aPriorita) {
        priorita = aPriorita;
    }

    /**
     * To string.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Tipologia [nome=" + nome + ", id=" + id + ", priorita="
                + priorita + "]";
    }

    /**
     * Hash code.
     *
     * @return the int
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, id, priorita);
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Tipologia)) {
            return false;
        }
        final Tipologia other = (Tipologia) obj;
        if (id != other.id) {
            return false;
        }
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        if (priorita != other.priorita) {
            return false;
        }
        return true;
    }

}
