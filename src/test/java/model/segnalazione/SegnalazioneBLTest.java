package model.segnalazione;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.utente.CSU;
import pool.Database;

// TODO: Auto-generated Javadoc
/**
 * The Class SegnalazioneBLTest.
 */
public class SegnalazioneBLTest {

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
     * The Constant UTENTE_ESISTENTE.
     */
    private static final int UTENTE_ESISTENTE = 1;

    /**
     * The Constant UTENTE_NON_ESISTENTE.
     */
    private static final int UTENTE_NON_ESISTENTE = 100;

    /**
     * The Constant TIPOLOGIA_ESISTENTE.
     */
    private static final int TIPOLOGIA_ESISTENTE = 1;

    /**
     * The Constant TIPOLOGIA_NON_ESISTENTE.
     */
    private static final int TIPOLOGIA_NON_ESISTENTE = 12;

    /**
     * The Constant TECNICO_ESISTENTE.
     */
    private static final int TECNICO_ESISTENTE = 1;

    /**
     * The Constant TECNICO_NON_ESISTENTE.
     */
    private static final int TECNICO_NON_ESISTENTE = 100;

    /**
     * Instantiates a new segnalazione BL test.
     */
    public SegnalazioneBLTest() {
    }

    /**
     * Sets the up class.
     *
     * @throws SQLException the SQL exception
     */
    @BeforeClass
    public static void setUpClass() throws SQLException {
        Database.initializePool();
    }

    /**
     * Tear down class.
     */
    @AfterClass
    public static void tearDownClass() {
        Database.destroyPool();
    }

    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        Tipologia tipologia = TipologiaDB.getById(TIPOLOGIA_NON_ESISTENTE);
        if (tipologia != null) {
            TipologiaDB.deleteById(TIPOLOGIA_NON_ESISTENTE);
        }

        tipologia = TipologiaDB.getById(TIPOLOGIA_ESISTENTE);
        if (tipologia == null) {
            tipologia = new Tipologia();
            tipologia.setNome("Guasto importante");
            tipologia.setPriorita((short) 1);
            TipologiaDB.insert(tipologia);
        }

        Segnalazione segnalazione = SegnalazioneDB
                .getByCod(SEGNALAZIONE_NON_ESISTENTE);
        if (segnalazione != null) {
            SegnalazioneDB.deleteById(SEGNALAZIONE_NON_ESISTENTE);
        }
        segnalazione = SegnalazioneDB.getByCod(SEGNALAZIONE_APERTA);
        if (segnalazione == null) {
            segnalazione = new Segnalazione();
            CSU utente = new CSU();
            utente.setId(UTENTE_ESISTENTE);
            segnalazione.setAutore(utente);
            segnalazione.setDescrizione("Ciaociao");
            segnalazione.setStato(Segnalazione.STATO_APERTO);
            segnalazione.setTitolo("Segnalazione");
            segnalazione.setDataSegnalazione(new Date(0));
            segnalazione.setTipologia(tipologia);
            SegnalazioneDB.insert(segnalazione);

        } else if (segnalazione.getStato() != Segnalazione.STATO_APERTO) {
            segnalazione.setStato(Segnalazione.STATO_APERTO);
            SegnalazioneDB.update(segnalazione);
        }

        segnalazione = SegnalazioneDB.getByCod(SEGNALAZIONE_RISOLTA);
        if (segnalazione == null) {
            segnalazione = new Segnalazione();
            CSU utente = new CSU();
            utente.setId(UTENTE_ESISTENTE);
            segnalazione.setAutore(utente);
            segnalazione.setDescrizione("Ciaociao");
            segnalazione.setStato(Segnalazione.STATO_RISOLTA);
            segnalazione.setTitolo("Segnalazione");
            segnalazione.setDataSegnalazione(new Date(0));
            segnalazione.setDataRisoluzione(new Date(0));
            segnalazione.setTipologia(tipologia);
            SegnalazioneDB.insert(segnalazione);
        } else if (segnalazione.getStato() != Segnalazione.STATO_RISOLTA) {
            segnalazione.setStato(Segnalazione.STATO_RISOLTA);
            segnalazione.setDataRisoluzione(new Date(0));
            SegnalazioneDB.update(segnalazione);
        }

    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {

    }

    /**
     * Test insert segnalazione 1.
     */
    @Test
    public void testInsertSegnalazione1() {
        final String aTitolo = "";
        final String aDescrizione = "Ciaooo";
        final int aIdTipologia = TIPOLOGIA_ESISTENTE;
        final int aIdAutore = UTENTE_ESISTENTE;

        final Boolean res = SegnalazioneBL.insertSegnalazione(aTitolo,
                aDescrizione,
                aIdTipologia,
                aIdAutore);
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 2.
     */
    @Test
    public void testInsertSegnalazione2() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "";
        final int aIdTipologia = TIPOLOGIA_ESISTENTE;
        final int aIdAutore = UTENTE_ESISTENTE;

        final Boolean res = SegnalazioneBL.insertSegnalazione(aTitolo,
                aDescrizione,
                aIdTipologia,
                aIdAutore);
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 3.
     */
    @Test
    public void testInsertSegnalazione3() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int aIdTipologia = TIPOLOGIA_NON_ESISTENTE;
        final int aIdAutore = UTENTE_ESISTENTE;

        final Boolean res = SegnalazioneBL.insertSegnalazione(aTitolo,
                aDescrizione,
                aIdTipologia,
                aIdAutore);
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 4.
     */
    @Test
    public void testInsertSegnalazione4() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int aIdTipologia = TIPOLOGIA_NON_ESISTENTE;
        final int aIdAutore = UTENTE_NON_ESISTENTE;

        final Boolean res = SegnalazioneBL.insertSegnalazione(aTitolo,
                aDescrizione,
                aIdTipologia,
                aIdAutore);
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 5.
     */
    @Test
    public void testInsertSegnalazione5() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int aIdTipologia = TIPOLOGIA_ESISTENTE;
        final int aIdAutore = UTENTE_ESISTENTE;

        final Boolean res = SegnalazioneBL.insertSegnalazione(aTitolo,
                aDescrizione,
                aIdTipologia,
                aIdAutore);
        assertTrue(res);
    }

    /**
     * Test update segnalazione 1.
     */
    @Test
    public void testUpdateSegnalazione1() {
        final String aTitolo = "";
        final String aDescrizione = "Ciaooo";
        final int aIdTipologia = TIPOLOGIA_ESISTENTE;
        final int aCod = SEGNALAZIONE_APERTA;

        final Boolean res = SegnalazioneBL
                .updateSegnalazione(aTitolo, aDescrizione, aIdTipologia, aCod);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 2.
     */
    @Test
    public void testUpdateSegnalazione2() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "";
        final int aIdTipologia = TIPOLOGIA_ESISTENTE;
        final int aCod = SEGNALAZIONE_APERTA;

        final Boolean res = SegnalazioneBL
                .updateSegnalazione(aTitolo, aDescrizione, aIdTipologia, aCod);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 3.
     */
    @Test
    public void testUpdateSegnalazione3() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int aIdTipologia = TIPOLOGIA_NON_ESISTENTE;
        final int aCod = SEGNALAZIONE_APERTA;

        final Boolean res = SegnalazioneBL
                .updateSegnalazione(aTitolo, aDescrizione, aIdTipologia, aCod);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 4.
     */
    @Test
    public void testUpdateSegnalazione4() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int aIdTipologia = TIPOLOGIA_ESISTENTE;
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;

        final Boolean res = SegnalazioneBL
                .updateSegnalazione(aTitolo, aDescrizione, aIdTipologia, aCod);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 5.
     */
    @Test
    public void testUpdateSegnalazione5() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int aIdTipologia = TIPOLOGIA_ESISTENTE;
        final int aCod = SEGNALAZIONE_RISOLTA;

        final Boolean res = SegnalazioneBL
                .updateSegnalazione(aTitolo, aDescrizione, aIdTipologia, aCod);
        assertFalse(res);
    }

    /**
     * Test update segnalazione 6.
     */
    @Test
    public void testUpdateSegnalazione6() {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int aIdTipologia = TIPOLOGIA_ESISTENTE;
        final int aCod = SEGNALAZIONE_APERTA;

        final Boolean res = SegnalazioneBL
                .updateSegnalazione(aTitolo, aDescrizione, aIdTipologia, aCod);
        assertTrue(res);
    }

    /**
     * Test inoltra segnalazione 1.
     */
    @Test
    public void testInoltraSegnalazione1() {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = SegnalazioneBL.inoltraSegnalazione(aCod,
                aIdTecnico);
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 2.
     */
    @Test
    public void testInoltraSegnalazione2() {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = SegnalazioneBL.inoltraSegnalazione(aCod,
                aIdTecnico);
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 3.
     */
    @Test
    public void testInoltraSegnalazione3() {
        final int aCod = SEGNALAZIONE_APERTA;
        final int aIdTecnico = TECNICO_NON_ESISTENTE;
        final Boolean res = SegnalazioneBL.inoltraSegnalazione(aCod,
                aIdTecnico);
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 4.
     */
    @Test
    public void testInoltraSegnalazione4() {
        final int aCod = SEGNALAZIONE_APERTA;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = SegnalazioneBL.inoltraSegnalazione(aCod,
                aIdTecnico);
        assertTrue(res);
    }

    /**
     * Test rifiuta segnalazione 1.
     */
    @Test
    public void testRifiutaSegnalazione1() {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final String aMotivazioneRifiuto = "Ciao";
        final Boolean res = SegnalazioneBL.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 2.
     */
    @Test
    public void testRifiutaSegnalazione2() {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final String aMotivazioneRifiuto = "Ciao";
        final Boolean res = SegnalazioneBL.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 3.
     */
    @Test
    public void testRifiutaSegnalazione3() {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = "";
        final Boolean res = SegnalazioneBL.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 4.
     */
    @Test
    public void testRifiutaSegnalazione4() {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = "Segnalazione duplicata";
        final Boolean res = SegnalazioneBL.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertTrue(res);
    }

}
