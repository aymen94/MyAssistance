package pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Class JDBCConnectionPool.
 */
public final class JDBCConnectionPool extends ObjectPool<Connection> {

    /** The db. */
    private final String db;

    /** The usr. */
    private final String usr;

    /** The pwd. */
    private final String pwd;

    /**
     * Instantiates a new JDBC connection pool.
     *
     * @param driver   the driver
     * @param database the database
     * @param user     the user
     * @param password the password
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws ClassNotFoundException the class not found exception
     */
    public JDBCConnectionPool(final String driver, final String database,
            final String user, final String password)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        super();
        Class.forName(driver).newInstance();
        this.db = database;
        this.usr = user;
        this.pwd = password;
    }

    /**
     * Instantiates a new JDBC connection pool.
     *
     * @param driver   the driver
     * @param database the database
     * @param user     the user
     * @param password the password
     * @param deadTime the deadTime
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws ClassNotFoundException the class not found exception
     */
    public JDBCConnectionPool(final String driver, final String database,
            final String user, final String password, final Long deadTime)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        this(driver, database, user, password);
        this.setDeadtime(deadTime);
    }

    /*
     * (non-Javadoc)
     *
     * @see pool.ObjectPool#create()
     */
    @Override
    Connection create() {
        try {
            return (DriverManager.getConnection(db, usr, pwd));
        } catch (final SQLException e) {
            e.printStackTrace();
            return (null);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see pool.ObjectPool#destroy(java.lang.Object)
     */
    @Override
    void destroy(final Connection o) {
        try {
            o.close();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see pool.ObjectPool#validate(java.lang.Object)
     */
    @Override
    boolean validate(final Connection o) {
        try {
            return (!o.isClosed());
        } catch (final SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
