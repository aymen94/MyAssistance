package pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO Auto-generated Javadoc
/**
 * The Class JDBCConnectionPool.
 */
public class JDBCConnectionPool extends ObjectPool<Connection> {

    /** The db. */
    private String db;

    /** The usr. */
    private String usr;

    /** The pwd. */
    private String pwd;

    /**
     * Instantiates a new JDBC connection pool.
     *
     * @param driver   the driver
     * @param database the database
     * @param user     the user
     * @param password the password
     */
    public JDBCConnectionPool(final String driver, final String database,
            final String user, final String password) {
        super();
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     */
    public JDBCConnectionPool(final String driver, final String database,
            final String user, final String password, final Long deadTime) {
        super();
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.db = database;
        this.usr = user;
        this.pwd = password;
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
        } catch (SQLException e) {
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
            ((Connection) o).close();
        } catch (SQLException e) {
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
            return (!((Connection) o).isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
