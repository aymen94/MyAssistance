package model.ufficio_tecnico;

import model.ufficiotecnico.UfficioTecnico;
import model.ufficiotecnico.UfficioTecnicoDB;
import model.utente.Utente;
import org.junit.*;
import pool.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class UfficioTecnicoDBTest {
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
                "ALTER TABLE my_assistance.ufficio_tecnico AUTO_INCREMENT = 1")
                .executeUpdate();
        fileHandler = new FileHandler(UfficioTecnicoDB.class.getName());
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
        logger
                .info("clear db  \n output:" + utenteDBTest.deleteById(1));
        Database.destroyPool();

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


}
