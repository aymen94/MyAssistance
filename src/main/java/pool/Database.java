package pool;

import java.sql.Connection;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
     * @param sce the sce
     * @see javax.servlet.ServletContextListener#contextInitialized(
     *      javax.servlet.ServletContextEvent)
     */
    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
        System.out.println("### run ###");
        initializePool("databases.xml", "Production");
    }

    /**
     * Initialize pool.
     *
     * @param configFile the config file
     * @param configName the config name
     * @throws RuntimeException the runtime exception
     */
    public static synchronized void initializePool(final String configFile,
            final String configName) throws RuntimeException {

        try {
            XMLDatabaseParser parser = new XMLDatabaseParser(configFile,
                    configName);

            pool = new JDBCConnectionPool(parser.getDriver(), parser.getUrl(),
                    parser.getUser(), parser.getPassword());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        // try connection
        final Connection connection = getConnection();
        if (connection == null) {
            final String message = "Could not find Database";
            System.err.println("### " + message + " ###");
            throw new RuntimeException(message);
        } else {
            final String message = "Estabilished connection with database";
            System.out.println("### " + message + " ###");
            freeConnection(connection);
        }
    }

    /**
     * Context destroyed.
     *
     * @param sce the sce
     */
    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {
        destroyPool();
    }

    /**
     * Destroy pool.
     */
    public static synchronized void destroyPool() {
        if (pool != null) {
            pool.destroyUnlocked();
            pool = null;
        }
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public static synchronized Connection getConnection() {
        return pool.takeOut();
    }

    /**
     * Free connection.
     *
     * @param connection the connection
     */
    public static synchronized void freeConnection(
            final Connection connection) {
        pool.takeIn(connection);
    }

}
