/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.ufficiotecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pool.Database;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * Questa classe permette la gestione dei dati persistenti relativi agli uffici
 * tecnici.
 */

public final class UfficioTecnicoDB {

    /** The Constant TABLE_NAME. */
    private static final String TABLE_NAME = "ufficio_tecnico";

    /**
     * This is an utility class. So no constructor should be used.
     */
    private UfficioTecnicoDB() {

    }

    /**
     * Questo metodo effettua l'inserimento di un ufficio tecnico nel database.
     *
     * @param uff è l'oggetto di tipo UfficioTecnico che deve essere inserito
     *            all'interno del database.
     * @return res vale 0 se l'inserimento non è stato effettuato, altrimenti un
     *         intero maggiore di 0.
     * @throws SQLException è l'eccezione che può essere lanciata durante
     *                      l'esecuzione del metodo.
     */

    public static synchronized int insert(final UfficioTecnico uff)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res;

        String insertSQL = "INSERT INTO " + UfficioTecnicoDB.TABLE_NAME
                + " (id,nome,tel,email,ubicazione) " + "VALUES (?, ?, ?, ?, ?)";

        try {
            connection = Database.getConnection();
            int i = 1;
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(i++, uff.getId());
            preparedStatement.setString(i++, uff.getNome());
            preparedStatement.setString(i++, uff.getTel());
            preparedStatement.setString(i++, uff.getEmail());
            preparedStatement.setString(i++, uff.getUbicazione());

            res = preparedStatement.executeUpdate();

            connection.commit();
            res++;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                Database.freeConnection(connection);
            }
        }
        return (res);
    }

    /**
     * Questo metodo consente di prelevare dal database le informazioni di tutti
     * gli uffici tecnici registrati.
     *
     * @return users è la lista di tutti gli utenti registrati nel database.
     * @throws SQLException è l'eccezione che può essere lanciata durante
     *                      l'esecuzione del metodo.
     */

    public static synchronized List<UfficioTecnico> getAll()
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<UfficioTecnico> uffici = new ArrayList<UfficioTecnico>();

        String selectSQL = "SELECT * FROM " + UfficioTecnicoDB.TABLE_NAME;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            rs = preparedStatement.executeQuery();

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
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                Database.freeConnection(connection);
            }
        }
        return uffici;
    }

    /**
     * Gets the by id.
     *
     * @param aId the id
     * @return the by id
     * @throws SQLException the SQL exception
     */
    public static synchronized UfficioTecnico getById(final int aId)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        UfficioTecnico uff = null;

        String selectSQL = "SELECT * FROM " + UfficioTecnicoDB.TABLE_NAME
                + " WHERE id=? ";

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(0, aId);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                uff = new UfficioTecnico();
                uff.setId(rs.getInt("Id"));
                uff.setNome(rs.getString("nome"));
                uff.setTel(rs.getString("tel"));
                uff.setEmail(rs.getString("email"));
                uff.setUbicazione(rs.getString("ubicazione"));
            }

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                Database.freeConnection(connection);
            }
        }
        if (uff == null) {
            return new UfficioTecnico();
        }
        return uff;
    }

    /**
     * This method deletes a user from the database given his email address.
     *
     * @param aId is the email address
     * @throws SQLException is the exception that can be thrown during the
     *                      execution.
     * @return res is 0 if the delete operation is not made, otherwise an
     *         integer greater than 0.
     */
    public static synchronized int deleteById(final Integer aId)
            throws SQLException {
        Connection connection = null;
        PreparedStatement s = null;
        int res = 0;

        String deleteSQL = "DELETE FROM " + UfficioTecnicoDB.TABLE_NAME
                + " WHERE id = ?;";

        try {
            connection = Database.getConnection();
            s = connection.prepareStatement(deleteSQL);
            s.setInt(1, aId);

            res = s.executeUpdate(deleteSQL);

            connection.commit();
        } finally {

            if (s != null) {
                s.close();
            }
            if (connection != null) {
                Database.freeConnection(connection);
            }
        }
        return (res);
    }

}
