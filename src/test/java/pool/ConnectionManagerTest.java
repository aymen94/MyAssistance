package pool;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

/**
 * The Class ConnectionManagerTest.
 */
public class ConnectionManagerTest {

    /**
     * The connection manager.
     */
    private ConnectionManager connectionManager = new ConnectionManager();

    /**
     * Sets the up.
     */
    @After
    public void setUp() {
        connectionManager.destroyPool();
    }

    /**
     * Initialize pool 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void initializePool1() throws Exception {
        connectionManager.initializePool("databases.xml", "Test");
        assertTrue(connectionManager.isInitialized());
        System.out.println("Test1: Pool initialized");
    }

    /**
     * Initialize pool 2.
     *
     * @throws Exception the exception
     */
    @Test(expected = RuntimeException.class)
    public void initializePool2() throws Exception {

        connectionManager.initializePool(null, "Test");
        assertTrue(connectionManager.isInitialized());
        System.out.println("Test 2: failed  Pool initialization");
    }

    /**
     * Initialize pool 3.
     *
     * @throws Exception the exception
     */
    @Test(expected = RuntimeException.class)
    public void initializePool3() throws Exception {
        connectionManager.initializePool("db.xml", "Test");
        assertTrue(connectionManager.isInitialized());
        System.out.println("Test 3: failed  Pool initialization");
    }

    /**
     * Initialize pool 4.
     *
     * @throws Exception the exception
     */
    @Test(expected = RuntimeException.class)
    public void initializePool4() throws Exception {
        connectionManager.initializePool("databases.xml", null);
        assertTrue(connectionManager.isInitialized());
        System.out.println("Test 4: failed  Pool initialization");
    }

    /**
     * Initialize pool 5.
     *
     * @throws Exception the exception
     */
    @Test(expected = RuntimeException.class)
    public void initializePool5() throws Exception {
        connectionManager.initializePool("databases.xml", "test1");
        assertTrue(connectionManager.isInitialized());
        System.out.println("Test 5: failed  Pool initialization");
    }

    /**
     * Gets the connection.
     *
     * @throws Exception the exception
     */
    @Test
    public void getConnection() throws Exception {
        connectionManager.initializePool("databases.xml", "Test");
        Connection conn = connectionManager.getConnection();
        assertNotNull(conn);
        System.out.println(" --- ConnectionManager connected --- ");
    }

    /**
     * Free connection.
     */
    @Test
    public void freeConnection1() {
        connectionManager.initializePool("databases.xml", "Test");
        Connection conn = connectionManager.getConnection();
        connectionManager.freeConnection(conn);
        Connection conn2 = connectionManager.getConnection();
        assertEquals(conn, conn2);
        System.out.println(" --- Connection freed --- ");
    }

    /**
     * Free connection.
     */
    @Test(expected = NullPointerException.class)
    public void freeConnection2() {
        connectionManager.initializePool("databases.xml", "Test");
        Connection conn = null;
        connectionManager.freeConnection(conn);
        Connection conn2 = connectionManager.getConnection();
        assertEquals(conn, conn2);
        System.out.println(" --- Connection freed --- ");
    }

//    /**
//     * Free connection.
//     */
//    @Test
//    public void freeConnection3() {
//        connectionManager.initializePool("databases.xml", "Test");
//        Connection conn = null;
//        connectionManager.freeConnection(conn);
//        Connection conn2 = connectionManager.getConnection();
//        assertEquals(conn, conn2);
//        System.out.println(" --- Connection freed --- ");
//    }

    /**
     * Destroy pool.
     */
    @Test
    public void destroyPool() {
        connectionManager.initializePool("databases.xml", "Test");
        connectionManager.destroyPool();
        assertFalse(connectionManager.isInitialized());
        System.out.println(" --- ConnectionManager destroyed --- ");
    }
}
