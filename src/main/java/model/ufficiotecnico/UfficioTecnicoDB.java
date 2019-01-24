package model.ufficiotecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pool.Database;

import java.sql.Connection;

/**
 * This class allows the management of persistent data relating to offices
 *  technicians
 */

public final class UfficioTecnicoDB {

    /**
     * Empty construct.
     */
    public UfficioTecnicoDB() {

    }

    /**
     * The Constant TABLE_NAME.
     */

    private static final String TABLE_NAME = "ufficio_tecnico";

    /**
     * The Constant INSERT_UFFICIO_TECNICO.
     */

    private static final String INSERT_UFFICIO_TECNICO = "INSERT INTO "
            + TABLE_NAME + " (nome,tel,email,ubicazione) "
            + "VALUES ( ?, ?, ?, ?)";

    /**
     * The Constant SELECT_ALL.
     */
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    /**
     * The Constant DELETE_BY_ID.
     */
    private static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME
            + " WHERE id = ?";

    /**
     * The Constant GET_BY_ID.
     */
    private static final String GET_BY_ID = "SELECT * FROM " + TABLE_NAME
            + " WHERE id = ?";

    /**
     * This method inserts a technical office in the database.
     *
     * @param uff it is the Technical Office object that must be inserted in the
     *            database.
     * @return res boolean variable, if it's true the insert is done, Otherwise
     *         it doesn't .
     * @throws SQLException this exception that can be launched during the
     *                      execution of the method.
     */

    public synchronized int insert(final UfficioTecnico uff)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res = 0;

        try {
            connection = Database.getConnection();
            preparedStatement = connection
                    .prepareStatement(INSERT_UFFICIO_TECNICO);
            int i = 1;
            preparedStatement.setString(i++, uff.getNome());
            preparedStatement.setString(i++, uff.getTel());
            preparedStatement.setString(i++, uff.getEmail());
            preparedStatement.setString(i, uff.getUbicazione());
            res = preparedStatement.executeUpdate();

            res++;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                Database.freeConnection(connection);
            }
        }
        return (res);
    }



    /**
     * This method allows everyone to get information from the database
     * registered technical offices.
     *
     * @return uffici List of UfficioTecnico.
     * @throws SQLException it is the exception that can be launched during the
     *                      execution of the method.
     */

    public List<UfficioTecnico> getAll()
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<UfficioTecnico> uffici;
        uffici = null;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);

            ResultSet rs = preparedStatement.executeQuery();
            uffici = new ArrayList<UfficioTecnico>();
            while (rs.next()) {
                UfficioTecnico uff = new UfficioTecnico();

                uff.setId(rs.getInt("Id"));
                uff.setNome(rs.getString("nome"));
                uff.setTel(rs.getString("tel"));
                uff.setEmail(rs.getString("email"));
                uff.setUbicazione(rs.getString("ubicazione"));

                uffici.add(uff);
            }

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                Database.freeConnection(connection);
            }
        }
        return uffici;
    }

    /**
     * This method deletes a user from the database given his email address.
     *
     * @param aId id the id of UfficioTecnico.
     * @return returns true if a technical office has been canceled, otherwise
     *         false.
     * @throws SQLException is the exception that can be thrown during the
     *                      execution.
     */
    public int deleteById(final Integer aId)
            throws SQLException {
        Connection connection = null;
        PreparedStatement s = null;
        int res;

        try {
            connection = Database.getConnection();
            s = connection.prepareStatement(DELETE_BY_ID);
            s.setInt(1, aId);

            res = s.executeUpdate();

        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } finally {
                Database.freeConnection(connection);
            }
        }
        return (res);
    }




    /**
     * This method select the UfficioTecnico by a id from the database given all dates of UfficioTecnico.
     *
     * @param aId id the id of UfficioTecnico.
     * @return uffici a  type list of  UfficioTecnico.
     * @throws SQLException is the exception that can be thrown during the
     *                      execution.
     */
    public UfficioTecnico getById(final Integer aId) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        UfficioTecnico uff = null;

        try {
            connection = Database.getConnection();

            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1,aId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                uff = new UfficioTecnico();
                uff.setId(rs.getInt("Id"));
                uff.setNome(rs.getString("nome"));
                uff.setTel(rs.getString("tel"));
                uff.setEmail(rs.getString("email"));
                uff.setUbicazione(rs.getString("ubicazione"));

            }

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                Database.freeConnection(connection);
            }
        }
        return uff;
    }
}
