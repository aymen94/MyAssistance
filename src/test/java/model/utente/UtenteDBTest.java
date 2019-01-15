package model.utente;

import org.junit.*;
import pool.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class UtenteDBTest {
    private FileHandler fileHandler;
    private Logger logger;

    /**
     * Sets the up class.
     *
     * @throws SQLException the SQL exception
     */
    @BeforeClass public static void setUpClass() throws SQLException {
        Database.initializePool();
    }

    /**
     * Tear down class.
     */
    @AfterClass public static void tearDownClass() {
        Database.destroyPool();
    }

    @Before public void loadLogger() throws IOException {
        fileHandler = new FileHandler("UtenteDB");
        logger = Logger.getLogger(UtenteDBTest.class.getName());
        logger.addHandler(fileHandler);
        logger.config("logger loaded");
    }

    @Test public void insert() throws SQLException {
        Utente test1 = new CSU();
        test1.setNome("test1");
        test1.setCognome("test1");
        test1.setUserName("test1");
        test1.setPassword("test1");
        test1.setEmail("test@test.com");
        test1.setSesso(1);
        UtenteDB utenteDBTest = new UtenteDB();
        utenteDBTest.insert(test1);
        logger.info("method: insert, \noutput: " + test1);

    }

    @Test public void getById() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        logger.info("method: getById(1) \noutput:" + utenteDBTest.getById(1));
    }

    @Test public void getByEmail() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        logger.info("method: getByEmail(test@test.com)\n output:" + utenteDBTest
            .getByEmail("test@test.com"));
    }

    @Test public void getAll() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        List<Utente> list = utenteDBTest.getAll();
        if (list == null)
            logger.info("method: getAll()==1 \noutput:\n " + 0);
        else
            logger.info("method: getAll()==1 \noutput:\n " + list.size());

    }

    @Test public void update() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        Utente test1 = utenteDBTest.getById(1);
        test1.setEmail("abc@abc.com");
        utenteDBTest.update(test1);
        logger.info(
            "method: update() email: test@test.com to abc@abc.com\n output:"
                + utenteDBTest.getByEmail("test@test.com"));

    }

    @After public void clearDB() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        utenteDBTest.delete("abc@abc.com");
        logger
            .info("clear db  \n output:" + utenteDBTest.delete("abc@abc.com"));
    }
}
