/*
Project: MyAssistance
Author: Andrea
Date: 23/12/2018
*/
package model.segnalazione;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.ufficiotecnico.UfficioTecnico;
import model.utente.Utente;

// TODO Auto-generated Javadoc
/**
 * The Class SegnalazioneBL.
 */
public final class SegnalazioneBL {

    /**
     * The Constant MAX_TITLE_LENGTH.
     */
    private static final int MAX_TITLE_LENGTH = 50;

    /**
     * The segnalazione DB.
     */
    private SegnalazioneDB segnalazioneDB;

    /**
     * Instantiates a new segnalazione BL.
     *
     * @param aSegnalazioneDB the segnalazione DB
     */
    public SegnalazioneBL(SegnalazioneDB aSegnalazioneDB) {
        segnalazioneDB = aSegnalazioneDB;
    }

    /**
     * Effettua segnalazione.
     *
     * @param segnalazione the segnalazione
     * @return true, if successful
     */
    public boolean insertSegnalazione(final Segnalazione segnalazione) {
        try {

            if (segnalazione.getTitolo().length() > 0
                    && segnalazione.getTitolo().length() <= MAX_TITLE_LENGTH
                    && segnalazione.getDescrizione().length() > 0
                    && segnalazione.getAutore() != null
                    && segnalazione.getTipologia() != null) {

                segnalazione.setTitolo(segnalazione.getTitolo());
                segnalazione.setDescrizione(segnalazione.getDescrizione());
                segnalazione.setTipologia(segnalazione.getTipologia());
                segnalazione.setAutore(segnalazione.getAutore());
                // Set the current date
                segnalazione.setDataSegnalazione(
                        java.sql.Date.valueOf(LocalDate.now()));
                return segnalazioneDB.insert(segnalazione) > 0;
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
     * @param segnalazione the segnalazione
     * @return true, if successful
     */
    public boolean updateSegnalazione(final Segnalazione segnalazione) {
        try {

            if (segnalazione.getTitolo().length() > 0
                    && segnalazione.getTitolo().length() <= MAX_TITLE_LENGTH
                    && segnalazione.getDescrizione().length() > 0
                    && segnalazione.getTipologia() != null
                    && segnalazione.getStato() == Segnalazione.STATO_APERTO) {

                final Segnalazione aSegnalazione = segnalazioneDB
                        .getByCod(segnalazione.getCod());
                if (aSegnalazione == null) {
                    return false;
                }
                aSegnalazione.setTitolo(segnalazione.getTitolo());
                aSegnalazione.setDescrizione(segnalazione.getDescrizione());
                aSegnalazione.setTipologia(segnalazione.getTipologia());
                return segnalazioneDB.update(aSegnalazione) > 0;
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
    public boolean deleteSegnalazione(final int aCod) {
        return false;

    }

    /**
     * Inoltra segnalazione.
     *
     * @param aCod       the cod
     * @param aIdTecnico the tecnico
     * @return true, if successful
     */
    public boolean inoltraSegnalazione(final int aCod, final int aIdTecnico) {
        Segnalazione aSegnalazione;
        try {
            aSegnalazione = segnalazioneDB.getByCod(aCod);
            if (aSegnalazione != null
                    && aSegnalazione.getStato() == Segnalazione.STATO_APERTO) {
                UfficioTecnico tecnico = new UfficioTecnico();
                tecnico.setId(aIdTecnico);
                aSegnalazione.setTecnico(tecnico);
                aSegnalazione.setDataAssegnazione(
                        java.sql.Date.valueOf(LocalDate.now()));
                return segnalazioneDB.update(aSegnalazione) > 0;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ottieni segnalazioni ricevute.
     *
     * @return the list
     */
    public List<Segnalazione> getSegnalazioniRicevute() {
        return null;

    }

    /**
     * Rifiuta segnalazione.
     *
     * @param aCod                the cod
     * @param aMotivazioneRifiuto the motivazione rifiuto
     * @return true, if successful
     */
    public boolean rifiutaSegnalazione(final int aCod,
            final String aMotivazioneRifiuto) {

        try {
            final Segnalazione aSegnalazione = segnalazioneDB.getByCod(aCod);
            if (aSegnalazione != null
                    && aSegnalazione.getStato() == Segnalazione.STATO_APERTO
                    && aMotivazioneRifiuto.length() > 0) {
                aSegnalazione.setStato(Segnalazione.STATO_RIFIUTATO);
                aSegnalazione.setMotivazioneRifiuto(aMotivazioneRifiuto);
                return segnalazioneDB.update(aSegnalazione) > 0;
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
    public boolean segnaRisolta(final int aCod) {
        return false;

    }

}
