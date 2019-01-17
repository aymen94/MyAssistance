/*
Project: MyAssistance
Author: Andrea
Date: 23/12/2018
*/
package model.segnalazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pool.Database;

/**
 * The Class TipologiaDB.
 */
public final class TipologiaDB {

    /**
     * The Constant TABLE_NAME.
     */
    private static final String TABLE_NAME = "tipologia";

    /**
     * The Constant INSERT_TIPOLOGIA.
     */
    private static final String INSERT_TIPOLOGIA = "INSERT INTO " + TABLE_NAME
            + " (nome,priorita) VALUES (?,?)";

    /**
     * The Constant SELECT_BY_ID.
     */
    private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME
            + " WHERE id = ?";

    /**
     * The Constant SELECT_ALL.
     */
    private static final String SELECT_ALL = " SELECT * FROM " + TABLE_NAME;

    /**
     * The Constant DELETE_BY_ID.
     */
    private static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME
            + " WHERE id = ?";

    /**
     * This is an utility class. So no constructor should be used.
     */
    private TipologiaDB() {

    }

    /**
     * Insert.
     *
     * @param aTipologia the tipologia
     * @return the int
     * @throws SQLException the SQL exception
     */
    public static int insert(final Tipologia aTipologia)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_TIPOLOGIA);
            int i = 1;
            preparedStatement.setString(i++, aTipologia.getNome());
            preparedStatement.setShort(i++, aTipologia.getPriorita());
            return preparedStatement.executeUpdate();
        } finally {
            freeResources(preparedStatement, connection);
        }
    }

    /**
     * Gets the all.
     *
     * @return the all
     * @throws SQLException the SQL exception
     */
    public static List<Tipologia> getAll() throws SQLException {
        return genericGet(SELECT_ALL, -1);
    }

    /**
     * Gets the by id.
     *
     * @param aId the id
     * @return the by id
     * @throws SQLException the SQL exception
     */
    public static Tipologia getById(final int aId) throws SQLException {
        List<Tipologia> tipologiaList = genericGet(SELECT_BY_ID, aId);
        if (tipologiaList != null) {
            return tipologiaList.get(0);
        }
        return null;
    }

    /**
     * Generic get.
     *
     * @param aQuery     the query
     * @param aParameter the parameter
     * @return the list
     * @throws SQLException the SQL exception
     */
    private static List<Tipologia> genericGet(final String aQuery,
            final int aParameter) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Tipologia> tipologiaList = new ArrayList<Tipologia>();
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(aQuery);
            if (aParameter > 0) {
                preparedStatement.setInt(1, aParameter);
            }

            result = preparedStatement.executeQuery();
            while (result.next()) {
                Tipologia tipologia = new Tipologia();
                tipologia.setId(result.getInt("id"));
                tipologia.setNome(result.getString("nome"));
                tipologia.setPriorita(result.getShort("priorita"));
                tipologiaList.add(tipologia);
            }
            if (tipologiaList.size() > 0) {
                return tipologiaList;
            } else {
                return null;
            }
        } finally {
            freeResources(preparedStatement, connection);
        }
    }

    /**
     * Free resources.
     *
     * @param aStm  the stm
     * @param aConn the conn
     * @throws SQLException the SQL exception
     */
    private static void freeResources(final PreparedStatement aStm,
            final Connection aConn) throws SQLException {
        try {
            if (aStm != null) {
                aStm.close();
            }
        } finally {
            if (aConn != null) {
                Database.freeConnection(aConn);
            }
        }
    }

    /**
     * Delete by id.
     *
     * @param aId the id
     * @return the int
     * @throws SQLException the SQL exception
     */
    public static  int deleteById(final int aId)
            throws SQLException {
        Connection connection = null;
        PreparedStatement s = null;
        int res = 0;

        try {
            connection = Database.getConnection();
            s = connection.prepareStatement(DELETE_BY_ID);
            s.setInt(1, aId);

            res = s.executeUpdate(DELETE_BY_ID);
        } finally {
            freeResources(s, connection);
        }
        return (res);
    }
}
