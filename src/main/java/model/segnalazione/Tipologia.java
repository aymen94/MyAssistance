package model.segnalazione;

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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Tipologia [nome=" + nome + ", id=" + id + ", priorita="
                + priorita + "]";
    }

}
