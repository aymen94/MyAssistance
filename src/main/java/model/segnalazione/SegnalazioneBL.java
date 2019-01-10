/*
Project: MyAssistance
Author: Andrea
Date: 23/12/2018
*/
package model.segnalazione;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.utente.Utente;
import model.utente.UtenteDB;

// TODO: Auto-generated Javadoc
/**
 * The Class SegnalazioneBL.
 */
public final class SegnalazioneBL {

    /**
     * The Constant MAX_TITLE_LENGTH.
     */
    private static final int MAX_TITLE_LENGTH = 50;

    /**
     * This is an utility class. So no constructor should be used.
     */
    private SegnalazioneBL() {

    }

    /**
     * Effettua segnalazione.
     *
     * @param aTitolo      the titolo
     * @param aDescrizione the descrizione
     * @param aIdTipologia the tipologia
     * @param aIdAutore    the autore
     * @return true, if successful
     */
    public static boolean insertSegnalazione(final String aTitolo,
            final String aDescrizione, final int aIdTipologia,
            final int aIdAutore) {
        try {
            final Tipologia aTipologia = TipologiaDB.getById(aIdTipologia);
            final Utente aAutore = UtenteDB.getById(aIdAutore);
            if (aTitolo.length() > 0 && aTitolo.length() <= MAX_TITLE_LENGTH
                    && aDescrizione.length() > 0 && aTipologia != null
                    && aAutore != null) {
                final Segnalazione aSegnalazione = new Segnalazione();
                aSegnalazione.setTitolo(aTitolo);
                aSegnalazione.setDescrizione(aDescrizione);
                aSegnalazione.setTipologia(aTipologia);
                aSegnalazione.setAutore(aAutore);
                // Set the current date
                aSegnalazione.setDataSegnalazione(
                        java.sql.Date.valueOf(LocalDate.now()));
                return SegnalazioneDB.insert(aSegnalazione) > 0;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ottieni segnalazioni effettuate.
     *
     * @param aUtente the utente
     * @return the list
     */
    public static List<Segnalazione> getSegnalazioniEffettuate(
            final Utente aUtente) {
        return null;

    }

    /**
     * Modifica segnalazione.
     *
     * @param aCod         the cod
     * @param aTitolo      the titolo
     * @param aDescrizione the descrizione
     * @param aIdTipologia the tipologia
     * @return true, if successful
     */
    public static boolean updateSegnalazione(final String aTitolo,
            final String aDescrizione, final int aIdTipologia, final int aCod) {
        try {
            final Tipologia aTipologia = TipologiaDB.getById(aIdTipologia);
            final Segnalazione aSegnalazione = SegnalazioneDB.getByCod(aCod);

            if (aTitolo.length() > 0 && aTitolo.length() <= MAX_TITLE_LENGTH
                    && aDescrizione.length() > 0 && aTipologia != null
                    && aSegnalazione != null
                    && aSegnalazione.getStato() == Segnalazione.STATO_APERTO) {
                aSegnalazione.setTitolo(aTitolo);
                aSegnalazione.setDescrizione(aDescrizione);
                aSegnalazione.setTipologia(aTipologia);
                return SegnalazioneDB.update(aSegnalazione) > 0;
            }

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Elimina segnalazione.
     *
     * @param aCod the cod
     * @return true, if successful
     */
    public static boolean deleteSegnalazione(final int aCod) {
        return false;

    }

    /**
     * Inoltra segnalazione.
     *
     * @param aCod       the cod
     * @param aIdTecnico the tecnico
     * @return true, if successful
     */
    public static boolean inoltraSegnalazione(final int aCod,
            final int aIdTecnico) {
        return false;
    }

    /**
     * Ottieni segnalazioni ricevute.
     *
     * @return the list
     */
    public static List<Segnalazione> getSegnalazioniRicevute() {
        return null;

    }

    /**
     * Rifiuta segnalazione.
     *
     * @param aCod                the cod
     * @param aMotivazioneRifiuto the motivazione rifiuto
     * @return true, if successful
     */
    public static boolean rifiutaSegnalazione(final int aCod,
            final String aMotivazioneRifiuto) {

        try {
            final Segnalazione aSegnalazione = SegnalazioneDB.getByCod(aCod);
            if (aSegnalazione != null
                    && aSegnalazione.getStato() == Segnalazione.STATO_APERTO
                    && aMotivazioneRifiuto.length() > 0) {
                aSegnalazione.setStato(Segnalazione.STATO_RIFIUTATO);
                aSegnalazione.setMotivazioneRifiuto(aMotivazioneRifiuto);
                return SegnalazioneDB.update(aSegnalazione) > 0;
            }

        } catch (final SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Segna come risolta.
     *
     * @param aCod the cod
     * @return true, if successful
     */
    public static boolean segnaRisolta(final int aCod) {
        return false;

    }

}
