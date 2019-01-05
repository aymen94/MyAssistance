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

// TODO: Auto-generated Javadoc
/**
 * The Class SegnalazioneDB.
 */
public final class TipologiaDB {

    /**
     * The Constant TABLE_NAME.
     */
    private static final String TABLE_NAME = "tipologia";

    /**
     * The Constant INSERT_SEGNALAZIONE.
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
     * This is an utility class. So no constructor should be used.
     */
    private TipologiaDB() {

    }

    /**
     * Insert.
     *
     * @param aTipologia the tipologia
     * @return true, if successful
     */
    public static boolean insert(final Tipologia aTipologia) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_TIPOLOGIA);
            int i = 1;
            preparedStatement.setString(i++, aTipologia.getNome());
            preparedStatement.setShort(i++, aTipologia.getPriorita());
            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            freeResources(preparedStatement, connection);
        }
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
    public static List<Tipologia> getAll() {
        return genericGet(SELECT_ALL, -1);
    }

    /**
     * Gets the by id.
     *
     * @param aId the id
     * @return the by id
     */
    public static Tipologia getById(final int aId) {
        Tipologia tipologia = genericGet(SELECT_BY_ID, aId).get(0);
        if (tipologia == null) {
            tipologia = new Tipologia();
        }
        return tipologia;
    }

    /**
     * Generic get.
     *
     * @param aQuery     the query
     * @param aParameter the parameter
     * @return the list
     */
    private static List<Tipologia> genericGet(final String aQuery,
            final int aParameter) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Tipologia> tipologiaList = new ArrayList<Tipologia>();
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(aQuery);
            if (aParameter > 0) {
                preparedStatement.setInt(0, aParameter);
            }

            result = preparedStatement.executeQuery();
            while (result.next()) {
                Tipologia tipologia = new Tipologia();
                tipologia.setId(result.getInt("id"));
                tipologia.setNome(result.getString("nome"));
                tipologia.setPriorita(result.getShort("priorita"));
                tipologiaList.add(tipologia);
            }
            return tipologiaList;

        } catch (final SQLException e) {
            e.printStackTrace();
            return tipologiaList;
        } finally {
            freeResources(preparedStatement, connection);
        }
    }

    /**
     * Free resources.
     *
     * @param aStm  the stm
     * @param aConn the conn
     */
    private static void freeResources(final PreparedStatement aStm,
            final Connection aConn) {
        try {
            if (aStm != null) {
                aStm.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (aConn != null) {
                Database.freeConnection(aConn);
            }
        }
    }
}
