package model.ufficio_tecnico;

/**
 * The Class UfficioTecnico.
 */
public final class UfficioTecnico {

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
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param aId setta identificatore dell'ufficio tecnico
     */
    public void setId(final int aId) {
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

    /**
     * @param obj
     * @return result of eguals
     */
    @Override public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UfficioTecnico)) {
            return false;
        }
        final UfficioTecnico other = (UfficioTecnico) obj;

        // if id or other.id is null do not compare
        if (id != 0 && other.id != 0) {
            if (!(id == other.id)) {
                return false;
            }
        }

        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        if (tel == null) {
            if (other.tel != null) {
                return false;
            }
        } else if (!tel.equals(other.tel)) {
            return false;
        }
        if (ubicazione == null) {
            if (other.ubicazione != null) {
                return false;
            }
        } else if (!ubicazione.equals(other.ubicazione)) {
            return false;
        }
        return true;
    }


    /**
     *
     * @return result
     */

    @Override public int hashCode() {

         final int tr = 31;
        /*int result = (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (ubicazione != null ? ubicazione.hashCode() : 0);
        return result;*/
        int result = 0;

        if (nome != null) {
              result = tr * result + nome.hashCode();
        }

        if (tel != null) {
              result = tr * result + tel.hashCode();
            return result;
        }

        if (email != null) {
              result = tr * result + email.hashCode();
            return result;
        }

        if (ubicazione != null) {
              result = tr * result + ubicazione.hashCode();
            return result;
        }
        return 0;
    }

}
