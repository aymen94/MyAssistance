package model.segnalazione;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.ufficio_tecnico.UfficioTecnico;
import model.ufficio_tecnico.UfficioTecnicoDB;
import model.ufficio_tecnico.UfficioTecnicoDBInterface;
import model.utente.CSU;
import model.utente.Gestore;
import model.utente.Utente;
import model.utente.UtenteDB;
import pool.ConnectionManager;

/**
 * The Class SegnalazioneBLTest.
 */
public final class SegnalazioneIT {

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
     * The Constant UTENTE_1.
     */
    private static final int UTENTE_1 = 1;

    /**
     * The Constant UTENTE_2.
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

    /**
     * The utente test.
     */
    private Utente utenteTest;

    /**
     * The utente test 2.
     */
    private Utente utenteTest2;

    /**
     * The tipologia test.
     */
    private Tipologia tipologiaTest;

    private UfficioTecnicoDBInterface tecnicoDB;

    /**
     * Instantiates a new segnalazione BL test.
     */
    public SegnalazioneIT() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException, SQLException {
        ConnectionManager.getInstance().initializePool("databases.xml", "Test");
        final Connection conn = ConnectionManager.getInstance().getConnection();

        ConnectionManager.getInstance().freeConnection(conn);
        
    }

    /**
     * Tear down class.
     *
     * @throws SQLException the SQL exception
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
        ConnectionManager.getInstance().destroyPool();
    }


    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        final Connection conn = ConnectionManager.getInstance().getConnection();

        utenteTest = new CSU();
        utenteTest.setId(UTENTE_1);
        utenteTest.setNome("aymen");
        utenteTest.setCognome("aymen");
        utenteTest.setDataDiNascita(LocalDate.now());
        utenteTest.setEmail("a@gmail.com");
        utenteTest.setPassword("bla");
        utenteTest.setSesso(Utente.SESSO_ALTRO);
        utenteTest.setUserName("bluuu");
        
        utenteTest2 = new Gestore();
        utenteTest2.setId(UTENTE_2);
        utenteTest2.setNome("aymen");
        utenteTest2.setCognome("aymen");
        utenteTest2.setDataDiNascita(LocalDate.now());
        utenteTest2.setEmail("a22@gmail.com");
        utenteTest2.setPassword("bla");
        utenteTest2.setSesso(Utente.SESSO_ALTRO);
        utenteTest2.setUserName("bluuu2211");
        tipologiaTest = new Tipologia();
        tipologiaTest.setId(TIPOLOGIA_ESISTENTE);
        tipologiaTest.setNome("guasto");
        tipologiaTest.setPriorita((short)10);

        segnalazioneDB = new SegnalazioneDB();
        segnalazioneAperta = new Segnalazione();
        segnalazioneAperta.setStato(Segnalazione.STATO_APERTO);
        segnalazioneAperta.setCod(SEGNALAZIONE_APERTA);
        segnalazioneAperta.setAutore(utenteTest);
        segnalazioneAperta.setDataSegnalazione(LocalDate.now());
        segnalazioneAperta.setTipologia(tipologiaTest);
        segnalazioneAperta.setTitolo("titolo");
        segnalazioneAperta.setDescrizione("descrizione");

        segnalazioneRisolta = new Segnalazione();
        segnalazioneRisolta.setStato(Segnalazione.STATO_RISOLTO);
        segnalazioneRisolta.setCod(SEGNALAZIONE_RISOLTA);
        segnalazioneRisolta.setAutore(utenteTest);
        segnalazioneRisolta.setDataSegnalazione(LocalDate.now());
        segnalazioneRisolta.setDataAssegnazione(LocalDate.now());
        segnalazioneRisolta.setDataRisoluzione(LocalDate.now());
        segnalazioneRisolta.setTitolo("titolo");
        segnalazioneRisolta.setDescrizione("descrizione");
        segnalazioneRisolta.setTipologia(tipologiaTest);

        
        segnalazioneAssegnata = new Segnalazione();
        segnalazioneAssegnata.setStato(Segnalazione.STATO_ASSEGNATO);
        segnalazioneAssegnata.setCod(SEGNALAZIONE_ASSEGNATA);
        segnalazioneAssegnata.setAutore(utenteTest);
        segnalazioneAssegnata.setDataSegnalazione(LocalDate.now());
        segnalazioneAssegnata.setDataAssegnazione(LocalDate.now());
        segnalazioneAssegnata.setDataRisoluzione(LocalDate.now());
        segnalazioneAssegnata.setTitolo("titolo");
        segnalazioneAssegnata.setDescrizione("descrizione");
        segnalazioneAssegnata.setTipologia(tipologiaTest);

        
        listaSegnalazioni = Arrays.asList(segnalazioneAperta,
                segnalazioneAssegnata,
                segnalazioneRisolta);

       
        UfficioTecnico tecnico=new UfficioTecnico();
        tecnico.setEmail("myassistance.teamC@gmail.com");
        tecnico.setId(TECNICO_ESISTENTE);
        tecnico.setNome("Tizio");
        tecnicoDB = new UfficioTecnicoDB();
        manager = new SegnalazioneBL(segnalazioneDB,tecnicoDB);
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
        conn.prepareStatement("TRUNCATE TABLE ufficio_tecnico")
        .executeUpdate();
        tecnicoDB.insert(tecnico);
        conn.prepareStatement("TRUNCATE TABLE utente")
        .executeUpdate();
        new UtenteDB().insert(utenteTest);
        new UtenteDB().insert(utenteTest2);
        conn.prepareStatement("TRUNCATE TABLE tipologia")
        .executeUpdate();
        new TipologiaDB().insert(tipologiaTest);
        conn.prepareStatement("TRUNCATE TABLE segnalazione")
        .executeUpdate();
        segnalazioneDB.insert(segnalazioneRisolta);
        segnalazioneDB.insert(segnalazioneAperta);
        segnalazioneDB.insert(segnalazioneAssegnata);
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
    }

    /**
     * Test insert segnalazione 10.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testInsertSegnalazione10() throws Exception {
        segnalazioneAperta = null;
        final Boolean res = manager.insertSegnalazione(segnalazioneAperta);
        System.out.println(" ### Test - testInsertSegnalazione1 \n\tinserimento:"
                + " " + segnalazioneAperta + "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsertSegnalazione0() throws Exception {
        final String aTitolo = null;

        segnalazioneAperta.setTitolo(aTitolo);
        final Boolean res = manager.insertSegnalazione(segnalazioneAperta);
        System.out.println(" ### Test - testInsertSegnalazione2 \n\tinserimento: " + segnalazioneAperta + "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsertSegnalazione1() throws Exception {
        final String aTitolo = "";

        segnalazioneAperta.setTitolo(aTitolo);
        final Boolean res = manager.insertSegnalazione(segnalazioneAperta);
        System.out.println(" ### Test - testInsertSegnalazione3 \n\tinserimento: " + segnalazioneAperta + "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 4.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsertSegnalazione4() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = null;

        segnalazioneAperta.setTitolo(aTitolo);
        segnalazioneAperta.setDescrizione(aDescrizione);
        final Boolean res = manager.insertSegnalazione(segnalazioneAperta);
        System.out.println(" ### Test - testInsertSegnalazione4 \n\tinserimento:" + segnalazioneAperta + "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsertSegnalazione5() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "";

        segnalazioneAperta.setTitolo(aTitolo);
        segnalazioneAperta.setDescrizione(aDescrizione);
        final Boolean res = manager.insertSegnalazione(segnalazioneAperta);
                System.out.println(" ### Test - testInsertSegnalazione5 \n\tinserimento:" + segnalazioneAperta + "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 6.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsertSegnalazione6() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = null;

        segnalazioneAperta.setTitolo(aTitolo);
        segnalazioneAperta.setDescrizione(aDescrizione);
        segnalazioneAperta.setTipologia(tipologia);
        final Boolean res = manager.insertSegnalazione(segnalazioneAperta);
                System.out.println(" ### Test - testInsertSegnalazione6 \n\tinserimento:" + segnalazioneAperta + "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 7.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsertSegnalazione7() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = null;

        segnalazioneAperta.setTitolo(aTitolo);
        segnalazioneAperta.setDescrizione(aDescrizione);
        segnalazioneAperta.setTipologia(tipologia);
        segnalazioneAperta.setAutore(autore);
        final Boolean res = manager.insertSegnalazione(segnalazioneAperta);
                System.out.println(" ### Test - testInsertSegnalazione7 \n\tinserimento:" + segnalazioneAperta + "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test insert segnalazione 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsertSegnalazione3() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest;

        segnalazioneAperta.setTitolo(aTitolo);
        segnalazioneAperta.setDescrizione(aDescrizione);
        segnalazioneAperta.setTipologia(tipologia);
        segnalazioneAperta.setAutore(autore);
        final Boolean res = manager.insertSegnalazione(segnalazioneAperta);
        System.out.println(" ### Test - testInsertSegnalazione8 \n\tinserimento:"
                + "" + segnalazioneAperta + "\n\tresult: " + res );
        assertTrue(res);
    }

    /**
     * Test update segnalazione 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione1() throws Exception {
        final Segnalazione segnalazione = null;
        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione1 \n\tinserimento:" + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione2() throws Exception {
        final String aTitolo = null;
        final String aDescrizione = "Ciao ciao ciao ciao";
        final int cod = SEGNALAZIONE_APERTA;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione2 \n\tinserimento:" + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione3() throws Exception {
        final String aTitolo = "asdfghjklloiuytrewqwertyuijoklmkngfcdxszasdfgh"
                + "jkoijhuygftrdeswaqawserdftyghuijk";
        final String aDescrizione = "Ciao ciao ciao ciao";
        final int cod = SEGNALAZIONE_APERTA;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione3 \n\tinserimento:" + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 4.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione4() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = null;
        final int cod = SEGNALAZIONE_APERTA;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione4 \n\tinserimento: " + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione5() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "";
        final int cod = SEGNALAZIONE_APERTA;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione5 \n\tinserimento: " + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 6.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione6() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int cod = SEGNALAZIONE_NON_ESISTENTE;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione6 \n\tinserimento: " + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 7.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione7() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int cod = SEGNALAZIONE_RISOLTA;
        final short stato = Segnalazione.STATO_RISOLTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione7 \n\tinserimento: " + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 8.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione8() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int cod = SEGNALAZIONE_APERTA;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = null;
        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione8 \n\tinserimento: " + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 9.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione9() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int cod = SEGNALAZIONE_APERTA;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = null;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione9 \n\tinserimento: " + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 10.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione10() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int cod = SEGNALAZIONE_APERTA;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest2;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);

        final Boolean res = manager.updateSegnalazione(segnalazione);
                System.out.println(" ### Test - testUpdateSegnalazione10 \n\tinserimento:" + segnalazione+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test update segnalazione 11.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateSegnalazione11() throws Exception {
        final String aTitolo = "Si è guastato il calorifero";
        final String aDescrizione = "Il calorifero vicino all’entrata dell’aula"
                + " F8 emana aria fredda.";
        final int cod = SEGNALAZIONE_APERTA;
        final short stato = Segnalazione.STATO_APERTO;
        final Tipologia tipologia = tipologiaTest;
        final Utente autore = utenteTest;

        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setAutore(autore);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setTipologia(tipologia);
        segnalazione.setTitolo(aTitolo);
        segnalazione.setDescrizione(aDescrizione);
        segnalazione.setStato(stato);
        segnalazione.setCod(cod);
        final Boolean res = manager.updateSegnalazione(segnalazione);
        System.out.println(" ### Test - testUpdateSegnalazione11 \n\tinserimento:" + segnalazione+ "\n\tresult: " + res );
        assertTrue(res);
    }

    /**
     * Test inoltra segnalazione 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInoltraSegnalazione1() throws Exception {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final int aIdTecnico = TECNICO_ESISTENTE;

        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
                System.out.println(" ### Test - testInoltra \n\tInoltro: tecnico=" + aIdTecnico+ "Segnalazione "+ aCod+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInoltraSegnalazione2() throws Exception {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
                System.out.println(" ### Test - testInoltra \n\tInoltro: tecnico=" + aIdTecnico+ "Segnalazione "+ aCod+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test inoltra segnalazione 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInoltraSegnalazione3() throws Exception {
        System.out.println(" ### Test - testInoltraSegnalazione3 ");
        segnalazioneAperta.setTitolo("URGENTE : si è guastato il termosifone ");
        segnalazioneAperta.setDescrizione("ciao ciao ciao muovetevi");
        final int aCod = SEGNALAZIONE_APERTA;
        final int aIdTecnico = TECNICO_ESISTENTE;
        final Boolean res = manager.inoltraSegnalazione(aCod, aIdTecnico);
        System.out.println("\tInoltro : tecnico esistente =" + aIdTecnico+ "\tSegnalazione n. "+ aCod+ "\n\tresult: " + res );

        assertTrue(res);
    }

    /**
     * Test rifiuta segnalazione 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRifiutaSegnalazione1() throws Exception {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final String aMotivazioneRifiuto = "Ciao";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
                System.out.println(" ### Test - testRifiuta \n\tRifiuto: MotivazioneRifiuto=" + aMotivazioneRifiuto+ "Segnalazione "+ aCod+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRifiutaSegnalazione2() throws Exception {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final String aMotivazioneRifiuto = "Ciao";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
                System.out.println(" ### Test - testRifiuta \n\tRifiuto: MotivazioneRifiuto=" + aMotivazioneRifiuto+ "Segnalazione "+ aCod+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRifiutaSegnalazione3() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = null;
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
                System.out.println(" ### Test - testRifiuta \n\tRifiuto: MotivazioneRifiuto=" + aMotivazioneRifiuto+ "Segnalazione "+ aCod+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 4.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRifiutaSegnalazione4() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = "";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
                System.out.println(" ### Test - testRifiuta \n\tRifiuto: MotivazioneRifiuto=" + aMotivazioneRifiuto+ "Segnalazione "+ aCod+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test rifiuta segnalazione 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRifiutaSegnalazione5() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final String aMotivazioneRifiuto = "Segnalazione duplicata";
        final Boolean res = manager.rifiutaSegnalazione(aCod,
                aMotivazioneRifiuto);
        System.out.println(" ### Test - testRifiutaSegnalazione \nRifiuta: " + aCod + "aMotivazioneRifiuto : " + aMotivazioneRifiuto + "\n\tresult: " + res );
        assertTrue(res);
    }

    /**
     * Test segna risolta 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSegnaRisolta1() throws Exception {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final Boolean res = manager.segnaRisolta(aCod);
        System.out.println(" ### Test - testSegnaRisolta1 \nNon esistente: " + aCod + "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test segna risolta 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSegnaRisolta2() throws Exception {
        final int aCod = SEGNALAZIONE_RISOLTA;
        final Boolean res = manager.segnaRisolta(aCod);
        System.out.println(" ### Test - testSegnaRisolta \nRisolta: " + aCod + "\n\tresult: " + res );

        assertFalse(res);
    }

    /**
     * Test segna risolta 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testSegnaRisolta3() throws Exception {
        final int aCod = SEGNALAZIONE_ASSEGNATA;
        final Boolean res = manager.segnaRisolta(aCod);
        System.out.println(" ### Test - testSegnaRisolta \nAssegnata: " + aCod + "\n\tresult: " + res );

        assertTrue(res);
    }

    /**
     * Test delete segnalazione 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteSegnalazione1() throws Exception {
        final int aCod = SEGNALAZIONE_NON_ESISTENTE;
        final Utente aUtente = utenteTest;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        System.out.println(" ### Test - testDeleteSegnalazione \nNon esistente\n\taCod: " + aCod +"\n\taUtente: "+ aUtente+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test delete segnalazione 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteSegnalazione2() throws Exception {
        final int aCod = SEGNALAZIONE_ASSEGNATA;
        final Utente aUtente = utenteTest;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        System.out.println(" ### Test - testDeleteSegnalazione \nNon assegnato aCod: " + aCod +"\naUtente: "+ aUtente+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test delete segnalazione 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteSegnalazione3() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final Utente aUtente = null;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        System.out.println(" ### Test - testDeleteSegnalazione \nsegnalazione aperta aCod: " + aCod +"\naUtente: "+ aUtente+ "\n\tresult: " + res );
        assertFalse(res);
    }

    /**
     * Test delete segnalazione 4.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteSegnalazione4() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final Utente aUtente = utenteTest2;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        System.out.println(" ### Test - testDeleteSegnalazione \nsegnalazione aperta aCod: " + aCod +"\naUtente: "+ aUtente+ "\n\tresult: " + res );

        assertFalse(res);
    }

    /**
     * Test delete segnalazione 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteSegnalazione5() throws Exception {
        final int aCod = SEGNALAZIONE_APERTA;
        final Utente aUtente = utenteTest;
        final Boolean res = manager.deleteSegnalazione(aCod, aUtente);
        System.out.println(" ### Test - testDeleteSegnalazione \nsegnalazione aperta aCod: " + aCod +"\n aUtente: "+ aUtente+ "\n\tresult: " + res );

        assertTrue(res);
    }

    /**
     * Test get segnalazioni effettuate 1.
     *
     * @throws Exception the exception
     */
    @Test(expected = NullPointerException.class)
    public void testGetSegnalazioniEffettuate1() throws Exception {
        final Utente aUtente = null;
        manager.getSegnalazioniEffettuate(aUtente);
        System.out.println(" ### Test - testGetSegnalazioniEffettuate \naUtente: "+ aUtente);

    }

    /**
     * Test get segnalazioni effettuate 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetSegnalazioniEffettuate2() throws Exception {
        final Utente aUtente = new CSU();
        final List<Segnalazione> res = manager
                .getSegnalazioniEffettuate(aUtente);
        System.out.println(" ### Test - testGetSegnalazioniEffettuate \n res: "+ res);
        assertEquals(res.size(),0);
    }

    /**
     * Test get segnalazioni effettuate 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetSegnalazioniEffettuate3() throws Exception {
        final Utente aUtente = utenteTest;
        final List<Segnalazione> res = manager
                .getSegnalazioniEffettuate(aUtente);
        System.out.println(" ### Test - testGetSegnalazioniEffettuate \naUtente: "+ res + "lista segnalazioni" + listaSegnalazioni);
        assertEquals(new HashSet<>(listaSegnalazioni), new HashSet<>(res));
    }

}
