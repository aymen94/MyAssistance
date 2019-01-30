package model.ufficio_tecnico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pool.Database;

/**
 * This class allows the management of persistent data relating to offices.
 * technicians
 */

public final class UfficioTecnicoDB implements UfficioTecnicoDBInterface {

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

    @Override
    public synchronized int insert(final UfficioTecnico uff)
            throws SQLException {
        final Connection connection = Database.getConnection();
        try {

            final PreparedStatement preparedStatement = connection
                    .prepareStatement(INSERT_UFFICIO_TECNICO);
            int i = 1;
            preparedStatement.setString(i++, uff.getNome());
            preparedStatement.setString(i++, uff.getTel());
            preparedStatement.setString(i++, uff.getEmail());
            preparedStatement.setString(i, uff.getUbicazione());
            return preparedStatement.executeUpdate();
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * This method allows everyone to get information from the database
     * registered technical offices.
     *
     * @return uffici List of UfficioTecnico.
     * @throws SQLException it is the exception that can be launched during the
     *                      execution of the method.
     */

    @Override
    public List<UfficioTecnico> getAll() throws SQLException {
        final Connection connection = Database.getConnection();
        try {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement(SELECT_ALL);
            final ResultSet rs = preparedStatement.executeQuery();
            final List<UfficioTecnico> uffici = new ArrayList<UfficioTecnico>();
            while (rs.next()) {
                final UfficioTecnico uff = new UfficioTecnico();
                uff.setId(rs.getInt("Id"));
                uff.setNome(rs.getString("nome"));
                uff.setTel(rs.getString("tel"));
                uff.setEmail(rs.getString("email"));
                uff.setUbicazione(rs.getString("ubicazione"));
                uffici.add(uff);
            }
            return uffici;
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * This method select the UfficioTecnico by a id from the database given all
     * dates of UfficioTecnico.
     *
     * @param aId id the id of UfficioTecnico.
     * @return uffici a type list of UfficioTecnico.
     * @throws SQLException is the exception that can be thrown during the
     *                      execution.
     */
    @Override
    public UfficioTecnico getById(final int aId) throws SQLException {
        final Connection connection = Database.getConnection();
        UfficioTecnico uff = null;

        try {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, aId);
            final ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                uff = new UfficioTecnico();
                uff.setId(rs.getInt("Id"));
                uff.setNome(rs.getString("nome"));
                uff.setTel(rs.getString("tel"));
                uff.setEmail(rs.getString("email"));
                uff.setUbicazione(rs.getString("ubicazione"));
            }
            return uff;
        } finally {
            Database.freeConnection(connection);
        }
    }
}
