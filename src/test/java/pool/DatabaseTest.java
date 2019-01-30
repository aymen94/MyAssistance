package pool;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseTest {
    
    private Connection conn;

    @Test public void initializePool() throws Exception {
        Database.initializePool("databases.xml", "Test");
        System.out.println(" --- Database initialized --- ");
    }

    @Test public void getConnection() throws Exception {
        Database.initializePool("databases.xml", "Test");
        conn = Database.getConnection();
        assertNotNull(conn);
        System.out.println(" --- Database connected --- ");
    }

    @Test public void freeConnection() {
        Database.initializePool("databases.xml", "Test");
        conn = Database.getConnection();
        Database.freeConnection(conn);
        System.out.println(" --- Database FreeConnectioned --- ");
    }
}