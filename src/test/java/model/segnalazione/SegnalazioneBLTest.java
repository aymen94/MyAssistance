package model.segnalazione;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import model.utente.CSU;
import model.utente.Utente;

/**
 * The Class SegnalazioneBLTest.
 */
public class SegnalazioneBLTest {

    /**
     * The segnalazione DB.
     */
    private SegnalazioneDB segnalazioneDB;

    /**
     * The manager.
     */
    private SegnalazioneBL manager;

    /**
     * The segnalazione aperta.
     */
    private Segnalazione segnalazioneAperta;

    /**
     * The segnalazione risolta.
     */
    private Segnalazione segnalazioneRisolta;

    /**
     * The segnalazione assegnata.
     */
    private Segnalazione segnalazioneAssegnata;

    /**
     * The Constant SEGNALAZIONE_APERTA.
     */
    private static final int SEGNALAZIONE_APERTA = 2;

    /**
     * The Constant SEGNALAZIONE_RISOLTA.
     */
    private static final int SEGNALAZIONE_RISOLTA = 1;

    /**
     * The Constant SEGNALAZIONE_NON_ESISTENTE.
     */
    private static final int SEGNALAZIONE_NON_ESISTENTE = 1000;

    /**
     * The Constant SEGNALAZIONE_ASSEGNATA.
     */
    private static final int SEGNALAZIONE_ASSEGNATA = 3;

    /**
     * The Constant UTENTE_ESISTENTE.
     */
    private static final int UTENTE_ESISTENTE = 1;

    /**
     * The Constant TIPOLOGIA_ESISTENTE.
     */
    private static final int TIPOLOGIA_ESISTENTE = 1;

    /**
     * The Constant TECNICO_ESISTENTE.
     */
    private static final int TECNICO_ESISTENTE = 1;

    /**
     * Instantiates a new segnalazione BL test.
     */
    public SegnalazioneBLTest() {
    }

    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {

        segnalazioneDB = mock(SegnalazioneDB.class);
        segnalazioneAperta = new Segnalazione();
        segnalazioneAperta.setStato(Segnalazione.STATO_APERTO);
        segnalazioneAperta.setCod(SEGNALAZIONE_APERTA);

        segnalazioneRisolta = new Segnalazione();
        segnalazioneRisolta.setStato(Segnalazione.STATO_RISOLTO);
        segnalazioneRisolta.setCod(SEGNALAZIONE_RISOLTA);

        segnalazioneAssegnata = new Segnalazione();
        segnalazioneAssegnata.setStato(Segnalazione.STATO_ASSEGNATO);
        segnalazioneAssegnata.setCod(SEGNALAZIONE_ASSEGNATA);

        when(segnalazioneDB.getByCod(SEGNALAZIONE_NON_ESISTENTE))
                .thenReturn(null);

        when(segnalazioneDB.getByCod(SEGNALAZIONE_APERTA))
                .thenReturn(segnalazioneAperta);
        when(segnalazioneDB.getByCod(SEGNALAZIONE_RISOLTA))
                .thenReturn(segnalazioneRisolta);
        when(segnalazioneDB.getByCod(SEGNALAZIONE_ASSEGNATA))
                .thenReturn(segnalazioneAssegnata);
        when(segnalazioneDB.insert(any(Segnalazione.class))).thenReturn(1);
        when(segnalazioneDB.update(any(Segnalazione.class))).thenReturn(1);
        when(segnalazioneDB.deleteById(any(Integer.class))).thenReturn(1);

        manager = new SegnalazioneBL(segnalazioneDB);

    }

    /**
     * Test insert segnalazione 1.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testInsertSegnalazione1() throws SQLException {

        final String aTitolo = "";
        final String aDescrizione = "Ciaooo";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = new CSU();
        autore.setId(UTENTE_ESISTENTE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);

        final Boolean res = manager.insertSegnalazione(segnalazione);
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 2.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testInsertSegnalazione2() throws SQLException {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = new CSU();
        autore.setId(UTENTE_ESISTENTE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);

        final Boolean res = manager.insertSegnalazione(segnalazione);
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 3.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testInsertSegnalazione3() throws SQLException {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = new CSU();
        autore.setId(UTENTE_ESISTENTE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);

        final Boolean res = manager.insertSegnalazione(segnalazione);
        assertTrue(res);
    }

    /**
     * Test update segnalazione 1.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testUpdateSegnalazione1() throws SQLException {
        final String aTitolo = "";
        final String aDescrizione = "Ciaooo";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = new CSU();
        autore.setId(UTENTE_ESISTENTE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setCod(SEGNALAZIONE_APERTA);
        segnalazione.setStato(Segnalazione.STATO_APERTO);

        final Boolean res = manager.updateSegnalazione(segnalazione);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 2.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testUpdateSegnalazione2() throws SQLException {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = new CSU();
        autore.setId(UTENTE_ESISTENTE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setCod(SEGNALAZIONE_APERTA);
        segnalazione.setStato(Segnalazione.STATO_APERTO);

        final Boolean res = manager.updateSegnalazione(segnalazione);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 3.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testUpdateSegnalazione3() throws SQLException {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = new CSU();
        autore.setId(UTENTE_ESISTENTE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setCod(SEGNALAZIONE_NON_ESISTENTE);
        segnalazione.setStato(Segnalazione.STATO_APERTO);

        final Boolean res = manager.updateSegnalazione(segnalazione);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 4.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testUpdateSegnalazione4() throws SQLException {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = new CSU();
        autore.setId(UTENTE_ESISTENTE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setCod(SEGNALAZIONE_RISOLTA);
        segnalazione.setStato(Segnalazione.STATO_RISOLTO);

        final Boolean res = manager.updateSegnalazione(segnalazione);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 5.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testUpdateSegnalazione5() throws SQLException {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = new CSU();
        autore.setId(UTENTE_ESISTENTE);

        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setCod(SEGNALAZIONE_APERTA);
        segnalazione.setStato(Segnalazione.STATO_APERTO);

        final Boolean res = manager.updateSegnalazione(segnalazione);
        assertTrue(res);
    }

    /**
     * Test inoltra segnalazione 1.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testInoltraSegnalazione1() throws SQLException {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final int aIdTecnico = TECNICO_ESISTENTE;

        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 2.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testInoltraSegnalazione2() throws SQLException {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 3.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testInoltraSegnalazione3() throws SQLException {
        final int aCod = SEGNALAZIONE_APERTA;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
        assertTrue(res);
    }

    /**
     * Test rifiuta segnalazione 1.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione1() throws SQLException {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final String aMotivazioneRifiuto = "Ciao";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 2.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione2() throws SQLException {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final String aMotivazioneRifiuto = "Ciao";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 3.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione3() throws SQLException {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = "";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 4.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione4() throws SQLException {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = "Segnalazione duplicata";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertTrue(res);
    }

    /**
     * Test segna risolta.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testSegnaRisolta1() throws SQLException {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final Boolean res = manager.segnaRisolta(aCod);
        assertFalse(res);
    }

    /**
     * Test segna risolta 2.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testSegnaRisolta2() throws SQLException {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final Boolean res = manager.segnaRisolta(aCod);
        assertFalse(res);
    }

    /**
     * Test segna risolta 3.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testSegnaRisolta3() throws SQLException {
        final int aCod = SEGNALAZIONE_ASSEGNATA;
        final Boolean res = manager.segnaRisolta(aCod);
        assertTrue(res);
    }

    /**
     * Test elimina segnalazione 1.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testDeleteSegnalazione1() throws SQLException {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final Boolean res = manager.deleteSegnalazione(aCod);
        assertFalse(res);
    }

    /**
     * Test elimina segnalazione 2.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testDeleteSegnalazione2() throws SQLException {
        final int aCod = SEGNALAZIONE_ASSEGNATA;
        final Boolean res = manager.deleteSegnalazione(aCod);
        assertFalse(res);
    }

    /**
     * Test elimina segnalazione 3.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testDeleteSegnalazione3() throws SQLException {
        final int aCod = SEGNALAZIONE_APERTA;
        final Boolean res = manager.deleteSegnalazione(aCod);
        assertTrue(res);
    }

}
