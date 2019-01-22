package model.ufficio_tecnico;

import model.ufficiotecnico.UfficioTecnico;
import model.ufficiotecnico.UfficioTecnicoBL;
import model.ufficiotecnico.UfficioTecnicoDB;
import model.utente.Utente;
import org.junit.*;
import pool.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UfficioTecnicoDBTest {

    private static FileHandler fileHandler = null;
    private static Logger logger = null;


    /**
     * Sets the up class.
     *
     * @throws SQLException the SQL exception
     */
    @BeforeClass public static void setUpClass()
            throws IOException {
        Database.initializePool();

        fileHandler = new FileHandler("result_test\\"+UfficioTecnicoDB.class.getName());
        logger = Logger.getLogger(UfficioTecnicoDBTest.class.getName());
        logger.addHandler(fileHandler);
        logger.config("logger loaded");
    }

    /**
     * Tear down class.
     */
    @AfterClass public static void tearDownClass() throws SQLException {
        UfficioTecnicoDB utenteDBTest = new UfficioTecnicoDB();
        utenteDBTest.deleteById(1);
        logger.info("clear db  \n output:" + utenteDBTest.deleteById(1));
        Database.destroyPool();

    }

    @Before public void clearDb() throws SQLException {
        Connection conn = Database.getConnection();
        conn.prepareStatement("TRUNCATE TABLE my_assistance.ufficio_tecnico")
                .executeUpdate();
        Database.freeConnection(conn);

    }


    /**
     *
     * @throws SQLException the SQL exception
     */
    @Test public void insert()  throws SQLException {
        UfficioTecnicoDB ufficioTecnicoDB = new UfficioTecnicoDB();
        UfficioTecnico test1 = new UfficioTecnico();
        test1.setNome("nomesettato");
        test1.setTel("1234567890");
        test1.setEmail("abcdef@gmail.com");
        test1.setUbicazione("Roma");
        ufficioTecnicoDB.insert(test1);
        logger.info("method: insert, \noutput: " + test1);
    }

    /**
     *
     * @throws SQLException the SQL exception
     */

    @Test public void getAll() throws SQLException {
        UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        List<UfficioTecnico> list = ufficioTecnicoTest.getAll();
        if (list == null)
            logger.info("method: getAll()==1 \noutput:\n " + 0);
        else
            logger.info("method: getAll()==1 \noutput:\n " + list.size());
    }
    /**
     *
     * @throws SQLException the SQL exception
     */

    @Test public void getById() throws SQLException {
        UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        logger.info("method: getById(1) \noutput:" + ufficioTecnicoTest
                .getById(1));
    }


    @Test public void deleteById() throws SQLException {
        UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();

        logger.info("method: deleteById(1) \noutput:" + ufficioTecnicoTest
                .deleteById(1));
    }

    @Test(expected = SQLException.class) public void InserimentoUfficioTecnicoTest1()
            throws SQLException {
        UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();

        boolean res = false;

        String aNome = null;
        String aEmail = "mail@mgail.com";
        String aTel = "1234567890";
        String aUbicazione = "Roma";
        UfficioTecnico uff = new UfficioTecnico();
        uff.setNome(aNome);
        uff.setEmail(aEmail);
        uff.setTel(aTel);
        uff.setUbicazione(aUbicazione);
        try {
            res = ufficioTecnicoTest.insert(uff) > 0;
        } finally {
            logger.info(
                    "methods Insert Test 1 (empty string 'nome') : \nEsito inserimento : "
                            + res);
        }

    }

    @Test(expected = SQLException.class) public void InserimentoUfficioTecnicoTest2()
            throws SQLException {
        UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        boolean res = false;

        String aNome = "ufficio amministrazione";
        String aEmail = null;
        String aTel = "1234567890";
        String aUbicazione = "Roma";
        UfficioTecnico uff = new UfficioTecnico();
        uff.setNome(aNome);
        uff.setEmail(aEmail);
        uff.setTel(aTel);
        uff.setUbicazione(aUbicazione);
        try {
            res = ufficioTecnicoTest.insert(uff) > 0;
        } finally {
            logger.info(
                    "methods Insert Test 2 (empty string 'email') : \nEsito inserimento : "
                            + res);
        }

    }

    /**
     * @throws SQLException sql error
     */

    @Test public void InserimentoUfficioTecnicoTest3() throws SQLException {
        UfficioTecnicoDB ufficioTecnicoTest = new UfficioTecnicoDB();
        boolean res = false;

        String aNome = "ufficio amministrazione";
        String aEmail = "abcde@gmail.com";
        String aTel = null;
        String aUbicazione = "Roma";
        UfficioTecnico uff = new UfficioTecnico();
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
            logger.info(
                    "methods Insert Test 3 (empty string 'tel') : \nEsito inserimento : "
                            + res);

        }
    }

    @Test public void getAllTest() throws SQLException {
        boolean res = false;
        String aNome1 = "nome1", aNome2 = "nome2";
        String aEmail1 = "abcde@gmail.com", aEmail2 = "abc@gmail.com";
        String aTel1 = "0123456789", aTel2 = "1234567890";
        String aUbicazione1 = "Roma", aUbicazione2 = "Berlino";
        UfficioTecnicoDB ufficioTecnicoDBTest = new UfficioTecnicoDB();

        UfficioTecnico uff1 = new UfficioTecnico();

        uff1.setNome(aNome1);
        uff1.setEmail(aEmail1);
        uff1.setTel(aTel1);
        uff1.setUbicazione(aUbicazione1);

        UfficioTecnico uff2 = new UfficioTecnico();

        uff2.setNome(aNome2);
        uff2.setEmail(aEmail2);
        uff2.setTel(aTel2);
        uff2.setUbicazione(aUbicazione2);
        List<UfficioTecnico> lista2 = new ArrayList<>();

        lista2.add(uff1);
        lista2.add(uff2);

        for (UfficioTecnico x : lista2) {
            ufficioTecnicoDBTest.insert(x);

        }

        List<UfficioTecnico> lista1 = ufficioTecnicoDBTest.getAll();
        this.listEqualsIgnoreOrder(lista1, lista2);
        logger.info("return : " + this.listEqualsIgnoreOrder(lista1, lista2));
        System.out.println("lista 1 " + lista1 + "\n");
        System.out.println(lista2);
    }

    private <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
