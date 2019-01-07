package model.utente;

import model.utente.UtenteDB;
import pool.Database;
import model.utente.Utente;
import model.utente.CSU;
import model.utente.Gestore;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtenteBLTest {

    private UtenteDB udb;
    private static Connection connection;

    public UtenteBLTest() {

    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        Database.initializePool();
        connection=Database.getConnection();
    }

    @AfterClass
    public static void tearDownClass() {
        Database.freeConnection(connection);
        Database.destroyPool();
    }

    @Before
    public void setUp() throws Exception {
        Utente aUtente= new CSU();
        aUtente.setCognome(new String());
        aUtente.setNome(new String());
        aUtente.setUserName("Marissi");
        aUtente.setPassword("Chiave12345");
        UtenteDB.insert(aUtente);
    }

    @After
    public void tearDown() {
        udb.delete("m.rossi12@studenti.unisa.it");
    }

    /**
     * Test di insert della classe UtenteDB.
     * @throws SQLException eccezzioni in casi di mancato inserimento query
     *
     */
    @Test
    public void testAutenticazioneCSU1() {
        CSU utente=UtenteBL.autenticazioneCSU("", "");
        assertTrue(utente==null);
    }

    public void testAutenticazioneCSU2() {
        CSU utente=UtenteBL.autenticazioneCSU("..*]", "");
        assertTrue(utente==null);
    }

    public void testAutenticazioneCSU3() {
        CSU utente=UtenteBL.autenticazioneCSU("Marissi", "");
        assertTrue(utente==null);
    }

    public void testAutenticazioneCSU4() {
        CSU utente=UtenteBL.autenticazioneCSU("Marissi", "Chiave12345");
        assertTrue(utente!=null);
    }

    public void testAutenticazioneCSU3() {
        CSU utente=UtenteBL.autenticazioneCSU("Marissi", "Chiave12345");
        assertTrue(utente!=null);
    }

    public void testRegistrazione1() {
        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "", "", "");
        assertFalse(utente);
    }

    public void testRegistrazione2() {
        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "9####", "", "");
        assertFalse(utente);
    }

    public void testRegistrazione3() {
        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "Mario", "", "");
        assertFalse(utente);
    }

    public void testRegistrazione4() {
        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "Mario", "Rossi].[]", "");
        assertFalse(utente);
    }

    public void testRegistrazione5() {
        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "Mario", "Rossi", "");
        assertFalse(utente);
    }

    public void testRegistrazione6() {
        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "Mario", "Rossi", "");
        assertFalse(utente);
    }

    public void testRegistrazione7() {
        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "", "", "Mario", "Rossi", "1985-30-03");
        assertFalse(utente);
    }

    public void testRegistrazione8() {
        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "", "", "Mario", "Rossi", "1985-30-03");
        assertFalse(utente);
    }

    public void testRegistrazione9() {
        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "Chaive1095", "", "Mario", "Rossi", "1985-30-03");
        assertFalse(utente);
    }

    public void testRegistrazione10() {
        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "Chiave1095", "mariorossi@gmail.com", "Mario", "Rossi", "14-06-1985");
        assertFalse(utente);
    }

    public void testRegistrazione11() {
        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "Chiave1095", "m.rossi12@studenti.unisa.it", "Mario", "Rossi", "14-06-1985");
        assertFalse(utente);
    }

    public void testRegistrazione12() {
        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "Chiave1095", "m.rossi12@studenti.unisa.it", "Mario", "Rossi", "14-06-1985");
        udb.delete("m.rossi12@studenti.unisa.it");
        assertTrue(utente);
    }


    /**
     * Test di getByEmail della classe UtenteDB.
     * @throws SQLException eccezione in mancato ottenimento query
     */
    @Test
    public void testGetByEmail() throws SQLException {
        System.out.println("getByEmail");

        Utente u = udb.getByEmail("a.dauria@test.it");
        assertNotNull(u);
    }

    /**
     * Test di getAll della classe UtenteDB.
     * @throws SQLException eccezione in mancato ottenimento utenti
     */
    @Test
    public void testGetAll() throws SQLException {
        System.out.println("getAll");

        ArrayList<Utente> users = (ArrayList<Utente>) udb.getAll();
        assertNotNull(users);
    }

    /**
     * Test di delete della classe UtenteDB.
     * @throws SQLException eccezione in mancata eliminzaione query
     */
    @Test
    public void testDelete() throws SQLException {
        System.out.println("delete");

        int result = udb.delete("xxx@yy.it");
        assertTrue(result > 0);
    }
}