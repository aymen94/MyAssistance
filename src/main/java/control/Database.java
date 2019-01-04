package control;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pool.JDBCConnectionPool;

// TODO: Auto-generated Javadoc
/**
 * La Classe Database inizializza un pool di tipo {@link JDBCConnectionPool}
 * all'avvio del container, mette a disposizione i metodi per ottenere una
 * connessione e per liberarla, infine distrugge tale pool quando il container
 * viene distrutto.
 *
 * @author Andrea Mennillo
 */
@WebListener
public class Database implements ServletContextListener {

    /** Il pool. */
    private static JDBCConnectionPool pool;

    /**
     * Inizializza un JDBCConnectionPool all'avvio del container.
     *
     * @see
     * // javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
        System.out.println("### run ###");
        try {
            String database = "jdbc:mysql://localhost:3306/my_assistance?"
                    + "autoReconnect=true&amp;allowMultiQueries=true&amp;"
                    + "useSSL=false&amp;serverTimezone=Europe/Rome\"";
            pool = new JDBCConnectionPool("com.mysql.jdbc.Driver", database,
                    "root", "root");

            if (pool == null) {
                String message = "Could not find our Database";
                System.err.println("### " + message);
                throw new Exception(message);
            }
        } catch (NamingException e) {
            String message = "Si è verificato un errore";
            System.err.println("### " + message);
        } catch (Exception e) {
            System.err.println("### " + e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {
        pool.destroyUnlocked();
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     * @throws SQLException the SQL exception
     */
    public static synchronized Connection getConnection() throws SQLException {
        return pool.takeOut();
    }

    /**
     * Free connection.
     *
     * @param connection the connection
     */
    public static synchronized void freeConnection(
            final Connection connection) {
        try {
            pool.takeIn(connection);
        } catch (Exception e) {
            System.err.println(
                    "Threw an exception unlocking a database connection");
            e.printStackTrace();
        }
    }

}