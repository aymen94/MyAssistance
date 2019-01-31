package model.segnalazione;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pool.ConnectionManager;

/**
 * The Class TipologiaDBTest.
 */
public class TipologiaDBTest {

    /**
     * The tipologia test.
     */
    private Tipologia tipologiaTest;

    /**
     * The db.
     */
    TipologiaDB db;

    /**
     * The tipologia test 2.
     */
    private Tipologia tipologiaTest2;

    /**
     * The tipologia test 3.
     */
    private Tipologia tipologiaTest3;

    /**
     * Sets the up class.
     *
     * @throws Exception the exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        ConnectionManager.getInstance().initializePool("databases.xml", "Test");
        final Connection conn = ConnectionManager.getInstance().getConnection();
        // disable foreign key checks
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
    }

    /**
     * Tear down class.
     *
     * @throws Exception the exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        final Connection conn = ConnectionManager.getInstance().getConnection();
        // enable foreign key checks
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
        ConnectionManager.getInstance().destroyPool();
    }

    /**
     * Clear DB.
     *
     * @throws Exception the exception
     */
    @Before
    public void clearDB() throws Exception {
        final Connection conn = ConnectionManager.getInstance().getConnection();
        conn.prepareStatement("TRUNCATE TABLE tipologia").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
        db = new TipologiaDB();
        tipologiaTest = new Tipologia();
        tipologiaTest.setId(1);
        tipologiaTest.setNome("Guasto");
        tipologiaTest.setPriorita((short) 1);
        tipologiaTest2 = new Tipologia();
        tipologiaTest2.setId(2);
        tipologiaTest2.setNome("Errore!");
        tipologiaTest2.setPriorita((short) 5);
        tipologiaTest3 = new Tipologia();
        tipologiaTest3.setId(3);
        tipologiaTest3.setNome("Malfunzionamento!");
        tipologiaTest3.setPriorita((short) 10);
    }

    /**
     * Test get by id 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetById1() throws Exception {
        final Tipologia tipologia = db.getById((short) 0);
        assertNull(tipologia);
    }

    /**
     * Test get by id 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetById2() throws Exception {
        final Connection conn = ConnectionManager.getInstance().getConnection();
        conn.prepareStatement("INSERT INTO tipologia (nome, priorita) "
                + "VALUES ('Guasto','1')").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
        final Tipologia tipologiaNew = db.getById((short) 1);
        assertEquals(tipologiaTest, tipologiaNew);
    }

    /**
     * Test insert 1.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsert1() throws Exception {
        tipologiaTest.setNome(null);
        db.insert(tipologiaTest);
    }

    /**
     * Test insert 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert2() throws Exception {
        tipologiaTest.setNome("Lorem ipsum");
        tipologiaTest.setPriorita((short) 0);
        final Boolean res = db.insert(tipologiaTest) > 0;
        assertTrue(res);
        assertEquals(tipologiaTest, db.getById((short) 1));
    }

    /**
     * Test get all 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAll1() throws Exception {
        final List<Tipologia> tipologiaList = db.getAll();
        assertEquals(tipologiaList.size(), 0);
    }

    /**
     * Test get all 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAll2() throws Exception {
        final List<Tipologia> listExpected = new ArrayList<>();
        listExpected.add(tipologiaTest);
        listExpected.add(tipologiaTest2);
        listExpected.add(tipologiaTest3);

        db.insert(tipologiaTest);
        db.insert(tipologiaTest2);
        db.insert(tipologiaTest3);
        final List<Tipologia> tipologiaList = db.getAll();
        assertEquals(new HashSet<>(listExpected), new HashSet<>(tipologiaList));
    }
}
