package model.utente;

import org.junit.*;
import pool.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class UtenteDBTest {
    private static FileHandler fileHandler = null;
    private static Logger logger = null;

    /**
     * Sets the up class.
     *
     * @throws SQLException the SQL exception
     */
    @BeforeClass public static void setUpClass()
        throws IOException, SQLException {
        Database.initializePool();
        Database.getConnection().prepareStatement(
            "ALTER TABLE my_assistance.utente AUTO_INCREMENT = 1")
            .executeUpdate();
        fileHandler = new FileHandler(UtenteDB.class.getName());
        logger = Logger.getLogger(UtenteDBTest.class.getName());
        logger.addHandler(fileHandler);
        logger.config("logger loaded");
    }

    /**
     * Tear down class.
     */
    @AfterClass public static void tearDownClass() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        utenteDBTest.delete("test@test.com");
        logger
            .info("clear db  \n output:" + utenteDBTest.delete("abc@abc.com"));
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
        utenteDBTest.insert(test1);
        logger.info("create user \noutput: " + test1);

    }

    @Test public void getById() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        logger.info("param: 1 \noutput:" + utenteDBTest.getById(1));
    }

    @Test public void getByEmail() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        logger.info("param: test@test.com \n output:" + utenteDBTest
            .getByEmail("test@test.com"));
    }

    @Test public void getAll() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        List<Utente> list = utenteDBTest.getAll();
        if (list == null)
            logger.info("1 \noutput:\n " + 0);
        else
            logger.info("1 \noutput:\n " + list.size());

    }

    @Test public void update() throws SQLException {
        UtenteDB utenteDBTest = new UtenteDB();
        CSU test1 = (CSU) utenteDBTest.getById(1);
        System.out.println(test1);
        test1.setDataSospensione(LocalDate.of(2019, 8, 2));
        utenteDBTest.update(test1);
    }

}
