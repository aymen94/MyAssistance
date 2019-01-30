package pool;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseTest {



    @Test public void initializePool() throws Exception {
        Database.initializePool("databases.xml", "Test");
        assertTrue(Database.isInitialized());
        System.out.println(" --- Database initialized --- ");
    }

    @Test(expected = RuntimeException.class) public void initializePool2() throws Exception {

        Database.initializePool(null, "Test");
        assertTrue(Database.isInitialized());
        System.out.println(" ---Test 2: failed  Database initialized --- ");
    }

    @Test(expected = RuntimeException.class) public void initializePool3() throws Exception {
        Database.initializePool("db.xml", "Test");
        assertTrue(Database.isInitialized());
        System.out.println("Test 3: failed --- Database initialized --- ");
    }

    @Test(expected = RuntimeException.class) public void initializePool4() throws Exception {
        Database.initializePool("databases.xml", null);
        assertTrue(Database.isInitialized());
        System.out.println("Test 4: failed --- Database initialized --- ");
    }

    @Test(expected = RuntimeException.class) public void initializePool5() throws Exception {
        Database.initializePool("databases.xml", "test1");
        assertTrue(Database.isInitialized());
        System.out.println("Test 5: failed --- Database initialized --- ");
    }


    @Test public void getConnection() throws Exception {
        Database.initializePool("databases.xml", "Test");
        Connection conn = Database.getConnection();
        assertNotNull(conn);
        System.out.println(" --- Database connected --- ");
    }

    @Test public void freeConnection() {
        Database.initializePool("databases.xml", "Test");
        Connection conn = Database.getConnection();
        Database.freeConnection(conn);
        System.out.println(" --- Database FreeConnectioned --- ");
    }

    @Test public void destroyPool(){
        Database.destroyPool();
        System.out.println(" --- Database destroyed --- ");
    }
}