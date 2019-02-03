package model.utente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.runners.MethodSorters;
import pool.ConnectionManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public final class UtenteDBTest {
    @BeforeClass
    public static void setUpClass() throws SQLException {
        ConnectionManager.getInstance().initializePool("databases.xml", "Test");
        final Connection conn = ConnectionManager.getInstance().getConnection();
        conn.prepareStatement(
                "ALTER TABLE my_assistance.utente AUTO_INCREMENT = 1")
                .executeUpdate();

        // disable foreign key checks
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
    }

    /**
     * Tear down class.
     *
     * @throws SQLException
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
        final Connection conn = ConnectionManager.getInstance().getConnection();
        // enable foreign key checks
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
        ConnectionManager.getInstance().destroyPool();
    }

    /**
     * Clear db.
     *
     * @throws SQLException the SQL exception
     */
    @BeforeClass
    public static void clearDb() throws SQLException {
        final Connection conn = ConnectionManager.getInstance().getConnection();
        conn.prepareStatement("TRUNCATE TABLE utente").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
    }

    @Test
    public void testAInsert1() throws SQLException {
        Utente test1 = new CSU();
        test1.setNome("test1");
        test1.setCognome("test1");
        test1.setUserName("test11");
        test1.setPassword("test1");
        test1.setEmail("test@test.com");
        test1.setSesso(1);
        test1.setDataDiNascita(LocalDate.of(1990, 10, 2));
        UtenteDB utenteDBTest = new UtenteDB();
        final int result = utenteDBTest.insert(test1);
        assertEquals(1, result);
    }

    @Test
    public void testAInsert2() throws SQLException {
        Utente test2 = new CSU();
        test2.setNome("test2");
        test2.setCognome("test2");
        test2.setUserName("test2");
        test2.setPassword("test2");
        test2.setEmail("test2@test.com");
        test2.setSesso(0);
        test2.setDataDiNascita(LocalDate.of(2000, 1, 20));
        ((CSU) test2).setDataSospensione(LocalDate.of(2018, 9, 2));
        UtenteDB utenteDBTest = new UtenteDB();
        final boolean result = utenteDBTest.insert(test2) == 1;
        assertTrue(result);
    }

    @Test
    public void testAInsert3() throws SQLException {
        Utente test3 = new Gestore();
        test3.setNome("test3");
        test3.setCognome("test3");
        test3.setUserName("test3");
        test3.setPassword("test3");
        test3.setEmail("test3@test.com");
        test3.setSesso(0);
        test3.setDataDiNascita(LocalDate.of(1989, 5, 22));
        UtenteDB utenteDBTest = new UtenteDB();
        final boolean result = utenteDBTest.insert(test3) == 1;
        assertTrue(result);
    }

    @Test
    public void testBGetById1() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        Utente utente = utenteDBTest.getById(1);
        final int result = utente.getId();
        assertEquals(1, result);
    }

    @Test
    public void testBGetById2() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        Utente utente = utenteDBTest.getById(10);
        assertNull(utente);
    }

    @Test
    public void testCGetAll1() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        List<Utente> list = utenteDBTest.getAll();
        final int result = list.size();
        assertEquals(3, result);
    }

    @Test
    public void testCGetAll2() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        List<Utente> list = utenteDBTest.getAll();
        assertNotNull(list);
    }

    @Test
    public void testDGetByEmail1() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        Utente utente = utenteDBTest.getByUserName("test11");
        final String result = utente.getUserName();
        assertEquals("test11", result);
    }

    @Test
    public void testDGetByEmail2() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        Utente utente = utenteDBTest.getByUserName("abc@test.com");
        assertNull(utente);
    }

    @Test
    public void testCUpdate() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        CSU test1 = (CSU) utenteDBTest.getById(1);
        test1.setDataSospensione(LocalDate.of(2019, 8, 2));
        utenteDBTest.update(test1);
        assertNotNull(test1);
    }

    @Test
    public void testEDelete1() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        final int result = utenteDBTest.delete("test@test.com");
        assertEquals(1, result);
    }

    @Test
    public void testEDelete2() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        final int result = utenteDBTest.delete("test2@test.com");
        assertEquals(1, result);
    }

    @Test
    public void testEDelete3() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        final int result = utenteDBTest.delete("test3@test.com");
        assertEquals(1, result);
    }

}
