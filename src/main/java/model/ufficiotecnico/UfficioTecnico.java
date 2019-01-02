/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.ufficiotecnico;

public class UfficioTecnico {

    private Integer id;
    private String nome;
    private String tel;
    private String email;
    private String ubicazione;

    /**
     * Costrutto firmato
     * @param id
     * @param nome
     * @param tel
     * @param email
     * @param ubicazione
     */
    public UfficioTecnico(Integer id, String nome, String tel, String email, String ubicazione) {
        this.id = id;
        this.nome = nome;
        this.tel = tel;
        this.email = email;
        this.ubicazione = ubicazione;
    }

    /**
     * Costrutto vuoto
     */
    public UfficioTecnico(){}

    /**
     * @return ottieni l'id dell'ufficio tecnico
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id setta identificatore dell'ufficio tecnico
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return nome ottieni il nome dell'uffico tecnico
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome setta il nome dell'uffico tecnico
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return ottieni il telefono dell'uffico tecnico
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel setta il numero dell'uffico tecnico
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return ottieni l'email dell'uffico tecnico
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email setta l'email dell'uffico tecnico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return ottieni l'ubicazione dell'uffico tecnico
     */
    public String getUbicazione() {
        return ubicazione;
    }

    /**
     * @param ubicazione setta l'ubicazione dell'uffico tecnico
     */
    public void setUbicazione(String ubicazione) {
        this.ubicazione = ubicazione;
    }

    /**
     * @return ritorna la rappreenzatione della classe
     */
    @Override
    public String toString() {
        return "UfficioTecnico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", ubicazione='" + ubicazione + '\'' +
                '}';
    }
}
