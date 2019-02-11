
package model.ufficio_tecnico;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pool.ConnectionManager;

// TODO: Auto-generated Javadoc
/**
 * The Class UfficioTecnicoBLTest.
 */
public class UfficioTecnicoIT {

    /**
     * The UfficioTecnico DB.
     */
    private UfficioTecnicoDBInterface db;

    /**
     * The manager.
     */
    private UfficioTecnicoBL manager;

    /**
     * The UfficioTecnico test.
     */
    private UfficioTecnico ufficioTecnicoTest;

    /**
     * The UfficioTecnico test 2.
     */
    private UfficioTecnico ufficioTecnicoTest2;

    /**
     * Sets the up class.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws SQLException
     */
    @BeforeClass
    public static void setUpClass() throws IOException, SQLException {
        ConnectionManager.getInstance().initializePool("databases.xml", "Test");
        final Connection conn = ConnectionManager.getInstance().getConnection();
        ConnectionManager.getInstance().freeConnection(conn);
    }

    /**
     * Tear down class.
     *
     * @throws SQLException the SQL exception
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
        ConnectionManager.getInstance().destroyPool();
    }


    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        final Connection conn = ConnectionManager.getInstance().getConnection();
        db= new UfficioTecnicoDB();
        ufficioTecnicoTest = new UfficioTecnico();
        ufficioTecnicoTest.setId(1);
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("ufficio1@libero.it");
        ufficioTecnicoTest.setTel("092000000");
        //db.insert(ufficioTecnicoTest);

        ufficioTecnicoTest2 = new UfficioTecnico();
        ufficioTecnicoTest2.setId(2);
        ufficioTecnicoTest2.setNome("Uffico di riparazione proiettori");
        ufficioTecnicoTest2.setEmail("p.ufficio1@unisa.net");
        ufficioTecnicoTest2.setTel("092000001");
        //db.insert(ufficioTecnicoTest2);

        manager = new UfficioTecnicoBL(db);
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
        conn.prepareStatement("TRUNCATE TABLE my_assistance.ufficio_tecnico")
        .executeUpdate();
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
        ConnectionManager.getInstance().freeConnection(conn);
    }

    /**
     * Test insert 1.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsert1() throws Exception {
        ufficioTecnicoTest.setNome(null);
        manager.insertUfficioTecnico(ufficioTecnicoTest);
    }

    /**
     * Test insert 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert2() throws Exception {
        ufficioTecnicoTest.setNome("");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert3() throws Exception {
        ufficioTecnicoTest.setNome(".*0-Uffico di riparazione caloriferi");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 4.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsert4() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail(null);
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert5() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("bla");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 6.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert6() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel("000000000000000000000000000000000000000");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 7.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert7() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel("*********");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 8.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert8() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel("+34566798923");
        ufficioTecnicoTest.setUbicazione("Ci siamo Trasferiti a via XYZCi siamo"
                + " Trasferiti a via XYZCi siamo Trasferiti a via XYZCi"
                + " siamo Trasferiti a via XYZCi siamo Trasferiti a via XYZ");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 9.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert9() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel("+34566798923");
        ufficioTecnicoTest.setUbicazione("Ci siamo Trasferiti a via XYZ");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertTrue(res);
    }

    /**
     * Test insert 10.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert10() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel(null);
        ufficioTecnicoTest.setUbicazione(null);
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertTrue(res);
    }

    /**
     * Test insert 11.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert11() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel(null);
        String ubicazione = "Ci siamo Trasferiti a via XYZ";
        ubicazione += ubicazione += ubicazione += ubicazione;
        ufficioTecnicoTest.setUbicazione(ubicazione);
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

}
