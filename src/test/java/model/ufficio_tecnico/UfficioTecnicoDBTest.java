package model.ufficio_tecnico;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static utility.TestUtility.listEqualsIgnoreOrder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pool.Database;

/**
 * The Class UfficioTecnicoDBTest.
 */
public class UfficioTecnicoDBTest {

    /**
     * The file handler.
     */
    private static FileHandler fileHandler = null;

    /**
     * The logger.
     */
    private static Logger logger = null;

    /**
     * Sets the up class.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @BeforeClass
    public static void setUpClass() throws IOException {
        Database.initializePool();

        fileHandler = new FileHandler(
                "result_test\\" + UfficioTecnicoDB.class.getName());
        logger = Logger.getLogger(UfficioTecnicoDBTest.class.getName());
        logger.addHandler(fileHandler);
        logger.config("logger loaded");
    }

    /**
     * Tear down class.
     *
     * @throws SQLException the SQL exception
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
        final UfficioTecnicoDB utenteDBTest = new UfficioTecnicoDB();
        utenteDBTest.deleteById(1);
        logger.info("clear db  \n output:" + utenteDBTest.deleteById(1));
        Database.destroyPool();

    }

    /**
     * Clear db.
     *
     * @throws SQLException the SQL exception
     */
    @Before
    public void clearDb() throws SQLException {
        final Connection conn = Database.getConnection();
        conn.prepareStatement("TRUNCATE TABLE my_assistance.ufficio_tecnico")
                .executeUpdate();
        Database.freeConnection(conn);

    }

    /*
     *
     * @Test public void insert() throws SQLException { UfficioTecnicoDB
     * ufficioTecnicoDB = new UfficioTecnicoDB(); UfficioTecnico test1 = new
     * UfficioTecnico(); test1.setNome("nomesettato");
     * test1.setTel("1234567890"); test1.setEmail("abcdef@gmail.com");
     * test1.setUbicazione("Roma"); ufficioTecnicoDB.insert(test1);
     * logger.info("method: insert, \noutput: " + test1); }
     *
     */

    /**
     * Gets the all.
     *
     *
     * @throws SQLException the SQL exception
     */

    @Test
    public void getAll() throws SQLException {
        final UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        final List<UfficioTecnico> list = ufficioTecnicoTest.getAll();
        if (list == null) {
            logger.info("method: getAll()==1 \noutput:\n " + 0);
        } else {
            logger.info("method: getAll()==1 \noutput:\n " + list.size());
        }
    }

    /**
     * Test get by id 1.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testGetById1() throws SQLException {
        final UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        final UfficioTecnico uff = ufficioTecnicoTest.getById(3);
        logger.info("method: getById(1) \noutput:" + uff);
        assertNull(uff);
    }

    /**
     * Test get by id 2.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testGetById2() throws SQLException {

        final Connection conn = Database.getConnection();
        conn.prepareStatement(
                "INSERT INTO my_assistance.ufficio_tecnico "
                        + "(nome,tel,email,ubicazione) VALUES "
                        + "('PIO','1234567890','ABCD@GMAIL.COM','RIMINI')")
                .executeUpdate();
        Database.freeConnection(conn);
        final UfficioTecnico uff2 = new UfficioTecnico();
        uff2.setId(1);
        uff2.setNome("PIO");
        uff2.setTel("1234567890");
        uff2.setEmail("ABCD@GMAIL.COM");
        uff2.setUbicazione("RIMINI");
        final UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        final UfficioTecnico uff = ufficioTecnicoTest.getById(1);

        logger.info(
                "method: getById(1)\noutput :" + uff + " \noracolo:" + uff2);
        assertEquals(uff, uff2);
    }

    /**
     * Delete by id.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void deleteById() throws SQLException {
        final UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();

        logger.info("method: deleteById(1) \noutput:"
                + ufficioTecnicoTest.deleteById(1));
    }

    /**
     * Test insert 1.
     *
     * @throws SQLException the SQL exception
     */
    @Test(expected = SQLException.class)
    public void testInsert1() throws SQLException {
        final UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();

        boolean res = false;

        final String aNome = null;
        final String aEmail = "mail@mgail.com";
        final String aTel = "1234567890";
        final String aUbicazione = "Roma";
        final UfficioTecnico uff = new UfficioTecnico();
        uff.setNome(aNome);
        uff.setEmail(aEmail);
        uff.setTel(aTel);
        uff.setUbicazione(aUbicazione);
        try {
            res = ufficioTecnicoTest.insert(uff) > 0;
        } finally {
            logger.info("methods Insert Test 1 (empty string 'nome') : "
                    + "\nEsito inserimento : " + res);
        }

    }

    /**
     * Test insert 2.
     *
     * @throws SQLException the SQL exception
     */
    @Test(expected = SQLException.class)
    public void testInsert2() throws SQLException {
        final UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        boolean res = false;

        final String aNome = "ufficio amministrazione";
        final String aEmail = null;
        final String aTel = "1234567890";
        final String aUbicazione = "Roma";
        final UfficioTecnico uff = new UfficioTecnico();
        uff.setNome(aNome);
        uff.setEmail(aEmail);
        uff.setTel(aTel);
        uff.setUbicazione(aUbicazione);
        try {
            res = ufficioTecnicoTest.insert(uff) > 0;
        } finally {
            logger.info("methods Insert Test 2 (empty string 'email') : "
                    + "\nEsito inserimento : " + res);
        }

    }

    /**
     * Test insert 3.
     *
     * @throws SQLException sql error
     */

    @Test
    public void testInsert3() throws SQLException {
        final UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        boolean res = false;

        final String aNome = "ufficio amministrazione";
        final String aEmail = "abcde@gmail.com";
        final String aTel = null;
        final String aUbicazione = "Roma";
        final UfficioTecnico uff = new UfficioTecnico();
        uff.setNome(aNome);
        uff.setEmail(aEmail);
        uff.setTel(aTel);
        uff.setUbicazione(aUbicazione);
        try {
            res = ufficioTecnicoTest.insert(uff) > 0;
            UfficioTecnico nuovoUfficioTecnico;
            nuovoUfficioTecnico = ufficioTecnicoTest.getById(1);
            assertTrue(res);
            assertEquals(uff, nuovoUfficioTecnico);
        } finally {
            logger.info("methods Insert Test 3 (empty string 'tel') :"
                    + "\nEsito inserimento : " + res);

        }
    }

    /**
     * Gets the all test.
     *
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void getAllTest() throws SQLException {
        final String aNome1 = "nome1", aNome2 = "nome2";
        final String aEmail1 = "abcde@gmail.com", aEmail2 = "abc@gmail.com";
        final String aTel1 = "0123456789", aTel2 = "1234567890";
        final String aUbicazione1 = "Roma", aUbicazione2 = "Berlino";
        final UfficioTecnicoDB ufficioTecnicoDBTest = new UfficioTecnicoDB();

        final UfficioTecnico uff1 = new UfficioTecnico();

        uff1.setNome(aNome1);
        uff1.setEmail(aEmail1);
        uff1.setTel(aTel1);
        uff1.setUbicazione(aUbicazione1);

        final UfficioTecnico uff2 = new UfficioTecnico();

        uff2.setNome(aNome2);
        uff2.setEmail(aEmail2);
        uff2.setTel(aTel2);
        uff2.setUbicazione(aUbicazione2);
        final List<UfficioTecnico> lista2 = new ArrayList<>();

        lista2.add(uff1);
        lista2.add(uff2);

        for (final UfficioTecnico x : lista2) {
            ufficioTecnicoDBTest.insert(x);

        }

        final List<UfficioTecnico> lista1 = ufficioTecnicoDBTest.getAll();
        logger.info("return : " + listEqualsIgnoreOrder(lista1, lista2));
        System.out.println("lista 1 " + lista1 + "\n");
        System.out.println(lista2);
        assertTrue(listEqualsIgnoreOrder(lista1, lista2));
    }

}
