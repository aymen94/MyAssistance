package model.segnalazione;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.ufficio_tecnico.UfficioTecnico;
import model.utente.CSU;
import model.utente.Utente;
import pool.Database;

/**
 * The Class SegnalazioneDBTest.
 */
public class SegnalazioneDBTest {

    /**
     * The segnalazione test.
     */
    private Segnalazione segnalazioneTest;

    /**
     * The autore test.
     */
    private Utente autoreTest;
    /**
     * The db.
     */
    SegnalazioneDB db;

    /**
     * The segnalazione test 2.
     */
    private Segnalazione segnalazioneTest2;

    /**
     * The segnalazione test 3.
     */
    private Segnalazione segnalazioneTest3;

    /**
     * Sets the up class.
     *
     * @throws Exception the SQL exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        Database.initializePool("databases.xml", "Test");
        final Connection conn = Database.getConnection();
        // disable foreign key checks
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
        Database.freeConnection(conn);
    }

    /**
     * Tear down class.
     *
     * @throws Exception the SQL exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        final Connection conn = Database.getConnection();
        // enable foreign key checks
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
        Database.freeConnection(conn);
        Database.destroyPool();
    }

    /**
     * Clear db.
     *
     * @throws Exception the SQL exception
     */
    @Before
    public void clearDB() throws Exception {
        final Connection conn = Database.getConnection();
        conn.prepareStatement("TRUNCATE TABLE segnalazione").executeUpdate();
        Database.freeConnection(conn);
        db = new SegnalazioneDB();
        autoreTest = new CSU();
        autoreTest.setId(1);
        segnalazioneTest = new Segnalazione();
        segnalazioneTest.setTitolo("Lorem ipsum");
        segnalazioneTest.setDescrizione("Lorem ipsum dolor sit amet");

        segnalazioneTest.setAutore(autoreTest);
        segnalazioneTest
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazioneTest.setTipologia(new Tipologia());
        segnalazioneTest2 = new Segnalazione();
        segnalazioneTest2.setTitolo("Lorem ipsum 2");
        segnalazioneTest2.setDescrizione("Lorem ipsum dolor sit amet adcedew");
        segnalazioneTest2.setAutore(autoreTest);
        segnalazioneTest2
                .setDataSegnalazione(Date.valueOf("2018-10-19").toLocalDate());
        segnalazioneTest2.setTipologia(new Tipologia());
        segnalazioneTest2.setStato(Segnalazione.STATO_ASSEGNATO);
        segnalazioneTest2
                .setDataAssegnazione(Date.valueOf("2019-01-19").toLocalDate());
        UfficioTecnico ufficioTecnico=new UfficioTecnico();
        ufficioTecnico.setId(1);
        segnalazioneTest2.setTecnico(ufficioTecnico);

        segnalazioneTest3 = new Segnalazione();
        segnalazioneTest3.setTitolo("Lorem ipsum 3");
        segnalazioneTest3.setDescrizione("Lorem ipsum dolor sit amet ede");
        segnalazioneTest3.setAutore(new CSU());
        segnalazioneTest3
                .setDataSegnalazione(Date.valueOf("2018-10-20").toLocalDate());
        segnalazioneTest3.setTipologia(new Tipologia());
        segnalazioneTest3.setStato(Segnalazione.STATO_RISOLTO);
        segnalazioneTest3
                .setDataAssegnazione(Date.valueOf("2019-01-17").toLocalDate());
        segnalazioneTest3.setTecnico(ufficioTecnico);
        segnalazioneTest3
                .setDataRisoluzione(Date.valueOf("2019-01-20").toLocalDate());
    }

    /**
     * Test get by cod 1.
     *
     * Database void
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testGetByCod1() throws Exception {
        final Segnalazione segnalazione = db.getByCod(0);
        assertNull(segnalazione);
    }

    /**
     * Test get by cod 2.
     *
     * Database void
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testGetByCod2() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setCod(1);
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setStato(Segnalazione.STATO_APERTO);
        final Tipologia tipologia = new Tipologia();
        tipologia.setId(2);
        segnalazione.setTipologia(tipologia);
        final Utente utente = new CSU();
        utente.setId(1);
        segnalazione.setAutore(utente);
        final Connection conn = Database.getConnection();
        conn.prepareStatement(
                "INSERT INTO segnalazione (titolo, descrizione, stato,"
                        + "data_segnalazione, tipologia," + " autore) "
                        + "VALUES ('Lorem ipsum','Lorem ipsum dolor sit amet',"
                        + "0, '2018-10-06', 2, 1)")
                .executeUpdate();
        Database.freeConnection(conn);
        final Segnalazione segnalazioneNew = db.getByCod(1);
        assertEquals(segnalazione, segnalazioneNew);
    }

    /**
     * Test insert 1.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testInsert1() throws Exception {
        segnalazioneTest.setTitolo(null);
        db.insert(segnalazioneTest);
    }

    /**
     * Test insert 2.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testInsert2() throws Exception {
        segnalazioneTest.setTitolo("Lorem ipsum");
        segnalazioneTest.setDescrizione(null);
        db.insert(segnalazioneTest);
    }

    /**
     * Test insert 3.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testInsert3() throws Exception {
        segnalazioneTest.setTitolo("Lorem ipsum");
        segnalazioneTest.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazioneTest.setAutore(null);
        db.insert(segnalazioneTest);
    }

    /**
     * Test insert 4.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testInsert4() throws Exception {
        segnalazioneTest.setTitolo("Lorem ipsum");
        segnalazioneTest.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazioneTest.setAutore(new CSU());
        segnalazioneTest.setDataSegnalazione(null);
        db.insert(segnalazioneTest);
    }

    /**
     * Test insert 5.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testInsert5() throws Exception {
        segnalazioneTest.setTitolo("Lorem ipsum");
        segnalazioneTest.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazioneTest.setAutore(new CSU());
        segnalazioneTest
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazioneTest.setTipologia(null);
        db.insert(segnalazioneTest);
    }

    /**
     * Test insert 6.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testInsert6() throws Exception {
        segnalazioneTest.setTitolo("Lorem ipsum");
        segnalazioneTest.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazioneTest.setAutore(new CSU());
        segnalazioneTest
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazioneTest.setTipologia(new Tipologia());
        final boolean res = db.insert(segnalazioneTest) > 0;
        assertTrue(res);
        assertEquals(segnalazioneTest, db.getByCod(1));
    }

    /**
     * Test delete 1.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testDelete1() throws Exception {
        final boolean res = db.deleteById(0) > 0;
        assertFalse(res);
        assertNull(db.getByCod(0));
    }

    /**
     * Test delete 2.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testDelete2() throws Exception {
        db.insert(segnalazioneTest);
        final boolean res = db.deleteById(1) > 0;
        assertTrue(res);
        assertNull(db.getByCod(1));
    }

    /**
     * Test insert 1.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdate1() throws Exception {
        segnalazioneTest.setCod(0);
        final Boolean res = db.update(segnalazioneTest) > 0;
        assertFalse(res);
    }

    /**
     * Test insert 1.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testUpdate2() throws Exception {
        db.insert(segnalazioneTest);
        final Segnalazione newSegnalazione = new Segnalazione();
        newSegnalazione.setCod(1);
        newSegnalazione.setTitolo("null");
        db.update(newSegnalazione);
    }

    /**
     * Test insert 2.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testUpdate3() throws Exception {
        db.insert(segnalazioneTest);
        final Segnalazione newSegnalazione = new Segnalazione();
        newSegnalazione.setCod(1);
        newSegnalazione.setTitolo("Lorem ipsum");
        newSegnalazione.setDescrizione(null);
        db.update(newSegnalazione);
    }

    /**
     * Test insert 3.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testUpdate4() throws Exception {
        db.insert(segnalazioneTest);
        final Segnalazione newSegnalazione = new Segnalazione();
        newSegnalazione.setCod(1);
        newSegnalazione.setTitolo("Lorem ipsum");
        newSegnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        newSegnalazione.setAutore(null);

        db.update(newSegnalazione);
    }

    /**
     * Test insert 5.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testUpdate5() throws Exception {
        db.insert(segnalazioneTest);
        final Segnalazione newSegnalazione = new Segnalazione();
        newSegnalazione.setCod(1);
        newSegnalazione.setTitolo("Lorem ipsum");
        newSegnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        newSegnalazione.setAutore(new CSU());
        newSegnalazione.setDataSegnalazione(null);
        db.update(newSegnalazione);
    }

    /**
     * Test insert 6.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = Exception.class)
    public void testUpdate6() throws Exception {
        db.insert(segnalazioneTest);
        final Segnalazione newSegnalazione = new Segnalazione();
        newSegnalazione.setCod(1);
        newSegnalazione.setTitolo("Lorem ipsum");
        newSegnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        newSegnalazione.setAutore(new CSU());
        newSegnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        newSegnalazione.setTipologia(null);
        db.update(newSegnalazione);
    }

    /**
     * Test insert 7.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testUpdate7() throws Exception {
        db.insert(segnalazioneTest);
        final Segnalazione newSegnalazione = new Segnalazione();
        newSegnalazione.setCod(1);
        newSegnalazione.setTitolo("Lorem ipsum");
        newSegnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        newSegnalazione.setAutore(new CSU());
        newSegnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        newSegnalazione.setStato(Segnalazione.STATO_RIFIUTATO);
        newSegnalazione.setDataRifiuto(LocalDate.now());
        newSegnalazione.setMotivazioneRifiuto("Prova prova");
        newSegnalazione.setTipologia(new Tipologia());
        final Boolean res = db.update(newSegnalazione) > 0;
        assertTrue(res);
        assertEquals(newSegnalazione, db.getByCod(1));
    }

    /**
     * Test get by autore 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetByAutore1() throws Exception {
        final List<Segnalazione> segnalazioneList = db.getByAutore(0);
        assertEquals(segnalazioneList.size(), 0);
    }

    /**
     * Test get by autore 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetByAutore2() throws Exception {
        final List<Segnalazione> listExpected = new ArrayList<>();
        listExpected.add(segnalazioneTest);
        listExpected.add(segnalazioneTest2);

        db.insert(segnalazioneTest);
        db.insert(segnalazioneTest2);
        db.insert(segnalazioneTest3);
        final List<Segnalazione> segnalazioneList = db
                .getByAutore(autoreTest.getId());

        assertEquals(new HashSet<>(listExpected),
                new HashSet<>(segnalazioneList));
    }

    /**
     * Test get all 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAll1() throws Exception {
        final List<Segnalazione> segnalazioneList = db.getAll();
        assertEquals(segnalazioneList.size(), 0);
    }

    /**
     * Test get all 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAll2() throws Exception {
        final List<Segnalazione> listExpected = new ArrayList<>();
        listExpected.add(segnalazioneTest);
        listExpected.add(segnalazioneTest2);
        listExpected.add(segnalazioneTest3);

        db.insert(segnalazioneTest);
        db.insert(segnalazioneTest2);
        db.insert(segnalazioneTest3);
        final List<Segnalazione> segnalazioneList = db.getAll();
        assertEquals(new HashSet<>(listExpected),
                new HashSet<>(segnalazioneList));
    }
}
