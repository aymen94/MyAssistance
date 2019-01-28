package model.segnalazione;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.utente.CSU;
import model.utente.Utente;
import pool.Database;

/**
 * The Class SegnalazioneDBTest.
 */
public class SegnalazioneDBTest {

    /**
     * The db.
     */
    SegnalazioneDB db;

    /**
     * Sets the up class.
     *
     * @throws Exception the SQL exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        Database.initializePool("databases.xml", "Test");
    }

    /**
     * Tear down class.
     *
     * @throws Exception the SQL exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
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
    @Test(expected = SQLException.class)
    public void testInsert1() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("null");
        db.insert(segnalazione);
    }

    /**
     * Test insert 2.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = SQLException.class)
    public void testInsert2() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione(null);
        db.insert(segnalazione);
    }

    /**
     * Test insert 3.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = SQLException.class)
    public void testInsert3() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(null);
        db.insert(segnalazione);
    }

    /**
     * Test insert 4.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = SQLException.class)
    public void testInsert4() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione.setDataSegnalazione(null);
        db.insert(segnalazione);
    }

    /**
     * Test insert 5.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = SQLException.class)
    public void testInsert5() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(null);
        db.insert(segnalazione);
    }

    /**
     * Test insert 6.
     *
     * @throws Exception the SQL exception
     */
    @Test
    public void testInsert6() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(new Tipologia());
        final boolean res = db.insert(segnalazione) > 0;
        assertTrue(res);
        assertEquals(segnalazione, db.getByCod(1));
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
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(new Tipologia());
        db.insert(segnalazione);
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
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setCod(0);
        final Boolean res = db.update(segnalazione) > 0;
        assertFalse(res);
    }

    /**
     * Test insert 1.
     *
     * @throws Exception the SQL exception
     */
    @Test(expected = SQLException.class)
    public void testUpdate2() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(new Tipologia());
        db.insert(segnalazione);
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
    @Test(expected = SQLException.class)
    public void testUpdate3() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(new Tipologia());
        db.insert(segnalazione);
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
    @Test(expected = SQLException.class)
    public void testUpdate4() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(new Tipologia());
        db.insert(segnalazione);
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
    @Test(expected = SQLException.class)
    public void testUpdate5() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(new Tipologia());
        db.insert(segnalazione);
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
    @Test(expected = SQLException.class)
    public void testUpdate6() throws Exception {
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(new Tipologia());
        db.insert(segnalazione);
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
        final Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo("Lorem ipsum");
        segnalazione.setDescrizione("Lorem ipsum dolor sit amet");
        segnalazione.setAutore(new CSU());
        segnalazione
                .setDataSegnalazione(Date.valueOf("2018-10-06").toLocalDate());
        segnalazione.setTipologia(new Tipologia());
        db.insert(segnalazione);
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

}
