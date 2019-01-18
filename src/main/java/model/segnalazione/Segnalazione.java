/*
Project: MyAssistance
Author: Andrea
Date: 23/12/2018
*/
package model.segnalazione;

import java.sql.Date;

import model.ufficiotecnico.UfficioTecnico;
import model.utente.Utente;

// TODO: Auto-generated Javadoc
/**
 * The Class Segnalazione.
 */
public class Segnalazione {

    /**
     * The cod.
     */
    private int cod;

    /**
     * The titolo.
     */
    private String titolo;

    /**
     * The descrizione.
     */
    private String descrizione;

    /**
     * The stato.
     */
    private short stato;

    /**
     * The data segnalazione.
     */
    private Date dataSegnalazione;

    /**
     * The data rifiuto.
     */
    private Date dataRifiuto;

    /**
     * The data assegnazione.
     */
    private Date dataAssegnazione;

    /**
     * The data risoluzione.
     */
    private Date dataRisoluzione;

    /**
     * The motivazione rifiuto.
     */
    private String motivazioneRifiuto;

    /**
     * The tipologia.
     */
    private Tipologia tipologia;

    /**
     * The autore.
     */
    private Utente autore;

    /**
     * The tecnico.
     */
    private UfficioTecnico tecnico;

    /**
     * The Constant STATO_APERTO.
     */
    public static final short STATO_APERTO = 0;

    /**
     * The Constant STATO_RIFIUTATO.
     */
    public static final short STATO_RIFIUTATO = -1;

    /**
     * The Constant STATO_ASSEGNATO.
     */
    public static final short STATO_ASSEGNATO = 1;

    /**
     * The Constant STATO_RISOLTA.
     */
    public static final short STATO_RISOLTA = 2;

    /**
     * Instantiates a new segnalazione.
     */
    public Segnalazione() {
    }

    /**
     * Gets the cod.
     *
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * Sets the cod.
     *
     * @param aCod the cod to set
     */
    public void setCod(final int aCod) {
        cod = aCod;
    }

    /**
     * Gets the titolo.
     *
     * @return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Sets the titolo.
     *
     * @param aTitolo the titolo to set
     */
    public void setTitolo(final String aTitolo) {
        titolo = aTitolo;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param aDescrizione the descrizione to set
     */
    public void setDescrizione(final String aDescrizione) {
        descrizione = aDescrizione;
    }

    /**
     * Gets the stato.
     *
     * @return the stato
     */
    public short getStato() {
        return stato;
    }

    /**
     * Sets the stato.
     *
     * @param aStato the stato to set
     */
    public void setStato(final short aStato) {
        stato = aStato;
    }

    /**
     * Gets the data segnalazione.
     *
     * @return the dataSegnalazione
     */
    public Date getDataSegnalazione() {
        return dataSegnalazione;
    }

    /**
     * Sets the data segnalazione.
     *
     * @param aDataSegnalazione the dataSegnalazione to set
     */
    public void setDataSegnalazione(final Date aDataSegnalazione) {
        dataSegnalazione = aDataSegnalazione;
    }

    /**
     * Gets the data rifiuto.
     *
     * @return the dataRifiuto
     */
    public Date getDataRifiuto() {
        return dataRifiuto;
    }

    /**
     * Sets the data rifiuto.
     *
     * @param aDataRifiuto the dataRifiuto to set
     */
    public void setDataRifiuto(final Date aDataRifiuto) {
        dataRifiuto = aDataRifiuto;
    }

    /**
     * @return the dataAssegnazione
     */
    public Date getDataAssegnazione() {
        return dataAssegnazione;
    }

    /**
     * @param aDataAssegnazione the dataAssegnazione to set
     */
    public void setDataAssegnazione(final Date aDataAssegnazione) {
        dataAssegnazione = aDataAssegnazione;
    }

    /**
     * Gets the data risoluzione.
     *
     * @return the dataRisoluzione
     */
    public Date getDataRisoluzione() {
        return dataRisoluzione;
    }

    /**
     * Sets the data risoluzione.
     *
     * @param aDataRisoluzione the dataRisoluzione to set
     */
    public void setDataRisoluzione(final Date aDataRisoluzione) {
        dataRisoluzione = aDataRisoluzione;
    }

    /**
     * Gets the motivazione rifiuto.
     *
     * @return the motivazioneRifiuto
     */
    public String getMotivazioneRifiuto() {
        return motivazioneRifiuto;
    }

    /**
     * Sets the motivazione rifiuto.
     *
     * @param aMotivazioneRifiuto the motivazioneRifiuto to set
     */
    public void setMotivazioneRifiuto(final String aMotivazioneRifiuto) {
        motivazioneRifiuto = aMotivazioneRifiuto;
    }

    /**
     * Gets the tipologia.
     *
     * @return the tipologia
     */
    public Tipologia getTipologia() {
        return tipologia;
    }

    /**
     * Sets the tipologia.
     *
     * @param aTipologia the tipologia to set
     */
    public void setTipologia(final Tipologia aTipologia) {
        tipologia = aTipologia;
    }

    /**
     * Gets the autore.
     *
     * @return the autore
     */
    public Utente getAutore() {
        return autore;
    }

    /**
     * Sets the autore.
     *
     * @param aAutore the autore to set
     */
    public void setAutore(final Utente aAutore) {
        autore = aAutore;
    }

    /**
     * Gets the tecnico.
     *
     * @return the tecnico
     */
    public UfficioTecnico getTecnico() {
        return tecnico;
    }

    /**
     * Sets the tecnico.
     *
     * @param aTecnico the tecnico to set
     */
    public void setTecnico(final UfficioTecnico aTecnico) {
        tecnico = aTecnico;
    }

}
