/*
Project: MyAssistance
Author: Andrea
Date: 23/12/2018
*/
package model.segnalazione;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.ufficio_tecnico.UfficioTecnico;
import model.utente.Utente;

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
    private final SegnalazioneDBInterface segnalazioneDB;

    /**
     * Instantiates a new segnalazione BL.
     *
     * @param aSegnalazioneDB the segnalazione DB
     */
    public SegnalazioneBL(final SegnalazioneDBInterface aSegnalazioneDB) {
        segnalazioneDB = aSegnalazioneDB;
    }

    /**
     * Effettua segnalazione.
     *
     * @param segnalazione the segnalazione
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean insertSegnalazione(final Segnalazione segnalazione)
            throws SQLException {

        if (validateSegnalazione(segnalazione)) {
            // Set the current date
            segnalazione.setDataSegnalazione(LocalDate.now());
            return segnalazioneDB.insert(segnalazione) > 0;
        }
        return false;
    }

    /**
     * Ottieni segnalazioni effettuate.
     *
     * @param aUtente the utente
     * @return the list
     * @throws SQLException the SQL exception
     */
    public List<Segnalazione> getSegnalazioniEffettuate(final Utente aUtente)
            throws SQLException {
        if (aUtente != null && aUtente.getId() != null) {
            return segnalazioneDB.getByAutore(aUtente.getId());
        }
        return null;
    }

    /**
     * Modifica segnalazione.
     *
     * @param segnalazione the segnalazione
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean updateSegnalazione(final Segnalazione segnalazione)
            throws SQLException {

        if (validateSegnalazione(segnalazione)) {
            final Segnalazione aSegnalazione = segnalazioneDB
                    .getByCod(segnalazione.getCod());
            if (aSegnalazione != null
                    && aSegnalazione.getStato() == Segnalazione.STATO_APERTO
                    && segnalazione.getAutore().getId() == aSegnalazione
                            .getAutore().getId()) {
                aSegnalazione.setTitolo(segnalazione.getTitolo());
                aSegnalazione.setDescrizione(segnalazione.getDescrizione());
                aSegnalazione.setTipologia(segnalazione.getTipologia());
                return segnalazioneDB.update(aSegnalazione) > 0;
            }
        }
        return false;

    }

    /**
     * Elimina segnalazione.
     *
     * @param aCod    the cod
     * @param aUtente the utente
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean deleteSegnalazione(final int aCod, final Utente aUtente)
            throws SQLException {
        final Segnalazione aSegnalazione = segnalazioneDB.getByCod(aCod);
        if (aSegnalazione != null
                && aSegnalazione.getStato() == Segnalazione.STATO_APERTO
                && aUtente != null
                && aSegnalazione.getAutore().getId() == aUtente.getId()) {
            return segnalazioneDB.deleteById(aCod) > 0;
        }
        return false;
    }

    /**
     * Inoltra segnalazione.
     *
     * @param aCod       the cod
     * @param aIdTecnico the tecnico
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean inoltraSegnalazione(final int aCod, final int aIdTecnico)
            throws SQLException {
        Segnalazione aSegnalazione;
        aSegnalazione = segnalazioneDB.getByCod(aCod);
        if (aSegnalazione != null
                && aSegnalazione.getStato() == Segnalazione.STATO_APERTO) {
            final UfficioTecnico tecnico = new UfficioTecnico();
            tecnico.setId(aIdTecnico);
            aSegnalazione.setTecnico(tecnico);
            aSegnalazione.setDataAssegnazione(LocalDate.now());
            return segnalazioneDB.update(aSegnalazione) > 0;
        }
        return false;
    }

    /**
     * Ottieni segnalazioni ricevute.
     *
     * @return the list
     * @throws SQLException the SQL exception
     */
    public List<Segnalazione> getSegnalazioniRicevute() throws SQLException {
        return segnalazioneDB.getAll();

    }

    /**
     * Rifiuta segnalazione.
     *
     * @param aCod                the cod
     * @param aMotivazioneRifiuto the motivazione rifiuto
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean rifiutaSegnalazione(final int aCod,
            final String aMotivazioneRifiuto) throws SQLException {

        final Segnalazione aSegnalazione = segnalazioneDB.getByCod(aCod);
        if (aSegnalazione != null
                && aSegnalazione.getStato() == Segnalazione.STATO_APERTO
                && aMotivazioneRifiuto != null
                && aMotivazioneRifiuto.length() > 0) {
            aSegnalazione.setStato(Segnalazione.STATO_RIFIUTATO);
            aSegnalazione.setDataRifiuto(LocalDate.now());
            aSegnalazione.setMotivazioneRifiuto(aMotivazioneRifiuto);
            return segnalazioneDB.update(aSegnalazione) > 0;
        }
        return false;
    }

    /**
     * Segna come risolta.
     *
     * @param aCod the cod
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public boolean segnaRisolta(final int aCod) throws SQLException {
        final Segnalazione aSegnalazione = segnalazioneDB.getByCod(aCod);
        if (aSegnalazione != null
                && aSegnalazione.getStato() == Segnalazione.STATO_ASSEGNATO) {
            aSegnalazione.setStato(Segnalazione.STATO_RISOLTO);
            return segnalazioneDB.update(aSegnalazione) > 0;
        }
        return false;
    }

    /**
     * Validate segnalazione.
     *
     * @param segnalazione the segnalazione
     * @return true, if successful
     */
    private boolean validateSegnalazione(final Segnalazione segnalazione) {
        if (segnalazione != null && segnalazione.getTitolo() != null
                && segnalazione.getTitolo().length() > 0
                && segnalazione.getTitolo().length() <= MAX_TITLE_LENGTH
                && segnalazione.getDescrizione() != null
                && segnalazione.getDescrizione().length() > 0
                && segnalazione.getAutore() != null
                && segnalazione.getTipologia() != null) {
            return true;
        }
        return false;
    }
}
