package pool;

import java.sql.Connection;

/**
 * The Interface ConnectionManagerInterface.
 */
public interface ConnectionManagerInterface {

    /**
     * Initialize pool.
     *
     * @param configFile the config file
     * @param configName the config name
     * @throws RuntimeException the runtime exception
     */
    void initializePool(String configFile, String configName)
            throws RuntimeException;

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    Connection getConnection();

    /**
     * Destroy pool.
     */
    void destroyPool();

    /**
     * Free connection.
     *
     * @param connection the connection
     */
    void freeConnection(Connection connection);

    /**
     * Checks if is initialized.
     *
     * @return true, if is initialized
     */
    boolean isInitialized();
}
