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
     * Insert.
     *
     * @param aTipologia the tipologia
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int insert(final Tipologia aTipologia) throws SQLException {
        final Connection connection = Database.getConnection();
        try {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement(INSERT_TIPOLOGIA);
            preparedStatement.setString(1, aTipologia.getNome());
            preparedStatement.setShort(2, aTipologia.getPriorita());
            return preparedStatement.executeUpdate();
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * Gets the all.
     *
     * @return the all
     * @throws SQLException the SQL exception
     */
    public List<Tipologia> getAll() throws SQLException {
        return genericGet(SELECT_ALL, -1);
    }

    /**
     * Gets the by id.
     *
     * @param aId the id
     * @return the by id
     * @throws SQLException the SQL exception
     */
    public Tipologia getById(final int aId) throws SQLException {
        final List<Tipologia> tipologiaList = genericGet(SELECT_BY_ID, aId);
        if (tipologiaList.size() > 0) {
            return tipologiaList.get(0);
        } else {
            return null;
        }
    }

    /**
     * Generic get.
     *
     * @param aQuery     the query
     * @param aParameter the parameter
     * @return the list
     * @throws SQLException the SQL exception
     */
    private List<Tipologia> genericGet(final String aQuery,
            final int aParameter) throws SQLException {
        final Connection connection = Database.getConnection();
        final List<Tipologia> tipologiaList = new ArrayList<Tipologia>();
        try {

            final PreparedStatement preparedStatement = connection
                    .prepareStatement(aQuery);
            if (aQuery.equals(SELECT_BY_ID)) {
                preparedStatement.setInt(1, aParameter);
            }

            final ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                final Tipologia tipologia = new Tipologia();
                tipologia.setId(result.getInt("id"));
                tipologia.setNome(result.getString("nome"));
                tipologia.setPriorita(result.getShort("priorita"));
                tipologiaList.add(tipologia);
            }
            return tipologiaList;
        } finally {
            Database.freeConnection(connection);
        }
    }
}
