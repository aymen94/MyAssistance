package model.segnalazione;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utility.TestUtility.listEqualsIgnoreOrder;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.utente.CSU;
import model.utente.Gestore;
import model.utente.Utente;

/**
 * The Class SegnalazioneBLTest.
 */
public final class SegnalazioneBLTest {

    /**
     * The segnalazione DB.
     */
    private SegnalazioneDBInterface segnalazioneDB;

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
    private static final int UTENTE_1 = 1;

    /**
     * The Constant UTENTE_ESISTENTE.
     */
    private static final int UTENTE_2 = 2;

    /**
     * The Constant TIPOLOGIA_ESISTENTE.
     */
    private static final int TIPOLOGIA_ESISTENTE = 1;

    /**
     * The Constant TECNICO_ESISTENTE.
     */
    private static final int TECNICO_ESISTENTE = 1;

    /**
     * The lista segnalazioni.
     */
    private List<Segnalazione> listaSegnalazioni;

    private Utente utenteTest;

    private Utente utenteTest2;

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

        utenteTest = new CSU();
        utenteTest.setId(UTENTE_1);
        utenteTest2 = new Gestore();
        utenteTest2.setId(UTENTE_2);

        segnalazioneDB = mock(SegnalazioneDBInterface.class);
        segnalazioneAperta = new Segnalazione();
        segnalazioneAperta.setStato(Segnalazione.STATO_APERTO);
        segnalazioneAperta.setCod(SEGNALAZIONE_APERTA);
        segnalazioneAperta.setAutore(utenteTest);

        segnalazioneRisolta = new Segnalazione();
        segnalazioneRisolta.setStato(Segnalazione.STATO_RISOLTO);
        segnalazioneRisolta.setCod(SEGNALAZIONE_RISOLTA);
        segnalazioneRisolta.setAutore(utenteTest);

        segnalazioneAssegnata = new Segnalazione();
        segnalazioneAssegnata.setStato(Segnalazione.STATO_ASSEGNATO);
        segnalazioneAssegnata.setCod(SEGNALAZIONE_ASSEGNATA);
        segnalazioneAssegnata.setAutore(utenteTest);

        listaSegnalazioni = Arrays.asList(segnalazioneAperta,
                segnalazioneAssegnata,
                segnalazioneRisolta);

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
        when(segnalazioneDB.getByAutore(any(Integer.class)))
                .thenReturn(listaSegnalazioni);
        when(segnalazioneDB.getByAutore(0))
        .thenReturn(null);

        manager = new SegnalazioneBL(segnalazioneDB);

    }

    /**
     * Test insert segnalazione 1.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testInsertSegnalazione1() throws Exception {
        final String aTitolo = null;
        final String aDescrizione = "Ciaooo";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * @throws Exception the SQL exception
     */
    @Test
    public void testInsertSegnalazione2() throws Exception {
        final String aTitolo = "";
        final String aDescrizione = "Ciaooo";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;
        final Segnalazione segnalazione = new Segnalazione();
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
     * @throws Exception the SQL exception
     */
    @Test
    public void testInsertSegnalazione3() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = null;
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);

        final Boolean res = manager.insertSegnalazione(segnalazione);
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 4.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testInsertSegnalazione4() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);

        final Boolean res = manager.insertSegnalazione(segnalazione);
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 5.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testInsertSegnalazione5() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione1() throws Exception {
        final String aTitolo = null;
        final String aDescrizione = "Ciaooo";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione2() throws Exception {
        final String aTitolo = "asdfghjklloiuytrewqwertyuijoklmkngfcdxszasdfgh"
                + "jkoijhuygftrdeswaqawserdftyghuijk";
        final String aDescrizione = "Ciaooo";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione3() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = null;
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * Test update segnalazione 4.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione4() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * Test update segnalazione 5.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione5() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * Test update segnalazione 6.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione6() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * Test update segnalazione 7.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione7() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = null;

        final Segnalazione segnalazione = new Segnalazione();
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
     * Test update segnalazione 8.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione8() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest2;

        final Segnalazione segnalazione = new Segnalazione();
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
     * Test update segnalazione 9.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdateSegnalazione9() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(TIPOLOGIA_ESISTENTE);

        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
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
     * @throws Exception the SQL exception
     */
    @Test
    public void testInoltraSegnalazione1() throws Exception {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final int aIdTecnico = TECNICO_ESISTENTE;

        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 2.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testInoltraSegnalazione2() throws Exception {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 3.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testInoltraSegnalazione3() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
        assertTrue(res);
    }

    /**
     * Test rifiuta segnalazione 1.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione1() throws Exception {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final String aMotivazioneRifiuto = "Ciao";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 2.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione2() throws Exception {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final String aMotivazioneRifiuto = "Ciao";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 3.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione3() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = null;
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 4.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione4() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = "";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 5.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testRifiutaSegnalazione5() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = "Segnalazione duplicata";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        assertTrue(res);
    }

    /**
     * Test segna risolta.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testSegnaRisolta1() throws Exception {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final Boolean res = manager.segnaRisolta(aCod);
        assertFalse(res);
    }

    /**
     * Test segna risolta 2.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testSegnaRisolta2() throws Exception {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final Boolean res = manager.segnaRisolta(aCod);
        assertFalse(res);
    }

    /**
     * Test segna risolta 3.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testSegnaRisolta3() throws Exception {
        final int aCod = SEGNALAZIONE_ASSEGNATA;
        final Boolean res = manager.segnaRisolta(aCod);
        assertTrue(res);
    }

    /**
     * Test elimina segnalazione 1.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testDeleteSegnalazione1() throws Exception {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final Utente aUtente = utenteTest;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        assertFalse(res);
    }

    /**
     * Test elimina segnalazione 2.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testDeleteSegnalazione2() throws Exception {
        final int aCod = SEGNALAZIONE_ASSEGNATA;
        final Utente aUtente = utenteTest;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        assertFalse(res);
    }

    /**
     * Test elimina segnalazione 3.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testDeleteSegnalazione3() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final Utente aUtente = null;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        assertFalse(res);
    }

    /**
     * Test elimina segnalazione 4.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testDeleteSegnalazione4() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final Utente aUtente = utenteTest2;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        assertFalse(res);
    }

    /**
     * Test elimina segnalazione 5.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testDeleteSegnalazione5() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final Utente aUtente = utenteTest;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        assertTrue(res);
    }

    /**
     * Test elimina segnalazione 3.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testGetSegnalazioniEffettuate1() throws Exception {
        final Utente aUtente = null;
        final List<Segnalazione> res = manager
                .getSegnalazioniEffettuate(aUtente);
        assertNull(res);
    }

    /**
     * Test elimina segnalazione 3.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testGetSegnalazioniEffettuate2() throws Exception {
        final Utente aUtente = new CSU();
        final List<Segnalazione> res = manager
                .getSegnalazioniEffettuate(aUtente);
        assertNull(res);
    }

    /**
     * Test elimina segnalazione 3.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testGetSegnalazioniEffettuate3() throws Exception {
        final Utente aUtente = utenteTest;
        final List<Segnalazione> res = manager
                .getSegnalazioniEffettuate(aUtente);
        assertTrue(listEqualsIgnoreOrder(res, listaSegnalazioni));
    }

}
