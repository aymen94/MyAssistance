package model.utente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import pool.Database;

public final class UtenteDBTest {

    @BeforeClass public static void setUpClass()
        throws IOException, SQLException {
        Database.initializePool();
        Database.getConnection().prepareStatement(
            "ALTER TABLE my_assistance.utente AUTO_INCREMENT = 1")
            .executeUpdate();
    }

    /**
     * Tear down class.
     */
    @AfterClass public static void tearDownClass() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        utenteDBTest.delete("test@test.com");
        Database.destroyPool();

    }

    @Test public void insert() throws SQLException {
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

    @Test public void getById() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        Utente utente = utenteDBTest.getById(1);
        final int result = utente.getId();
        assertEquals(1, result);
    }

    @Test public void getById1() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        Utente utente = utenteDBTest.getById(10);
        assertNull(utente);
    }

    @Test public void getAll() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        List<Utente> list = utenteDBTest.getAll();

    }

    @Test public void getByEmail() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        Utente utente = utenteDBTest.getByEmail("test@test.com");
        final String result = utente.getEmail();
        assertEquals("test@test.com", result);

    }

    @Test public void update() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        CSU test1 = (CSU) utenteDBTest.getById(1);
        test1.setDataSospensione(LocalDate.of(2019, 8, 2));
        utenteDBTest.update(test1);
    }

}
