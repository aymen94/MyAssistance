package model.utente;

import model.utente.UtenteDB;
import model.utente.Utente;
import model.utente.CSU;
import model.utente.Gestore;
import java.io.IOException;
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

public class UtenteDBTest {

    private UtenteDB udb;

    public UtenteDBTest() {
        udb = new UtenteDB();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test di insert della classe UtenteDB.
     * @throws SQLException
     */
    @Test
    public void testInsert() throws SQLException {
        System.out.println("insert");

        Utente u = null;

        int result = udb.insert(u);
        assertTrue(result > 0);
    }

    /**
     * Test di getByEmail della classe UtenteDB.
     * @throws SQLException
     */
    @Test
    public void testGetByEmail() throws SQLException {
        System.out.println("getByEmail");

        Utente u = udb.getByEmail("a.dauria@test.it");
        assertNotNull(u);
    }

    /**
     * Test di getAll della classe UtenteDB.
     * @throws SQLException
     */
    @Test
    public void testGetAll() throws SQLException {
        System.out.println("getAll");

        ArrayList<Utente> users = (ArrayList<Utente>) udb.getAll();
        assertNotNull(users);
    }

    /**
     * Test di delete della classe UtenteDB.
     * @throws SQLException
     */
    @Test
    public void testDelete() throws SQLException {
        System.out.println("delete");

        int result = udb.delete("xxx@yy.it");
        assertTrue(result > 0);
    }
}
