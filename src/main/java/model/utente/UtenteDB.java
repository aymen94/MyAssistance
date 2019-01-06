/*
Project: MyAssistance
Author: Pio
Date: 30/12/2018
*/
package model.utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import control.Database;

import java.sql.Connection;

/**
 * This class manages stored data about Users into the database.
 */
public final class UtenteDB {
    /**
     * This private attribute is a string that contains
     * the name of the table.
     */
    private static final String TABLE_NAME = "utente";

    /**
     * Default constructor.
     */
    private UtenteDB() {

    }

    /**
     * This method inserts a user into the database.
     *
     * @param aUtente is the object of the Utente type that
     * must be inserted into the database.
     * @throws SQLException is the exception that can be thrown
     * during the execution.
     * @return res is 0 if the insert operation is not made,
     *         otherwise an integer greater than 0.
     */
    public static int insert(final Utente aUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res = 0;

        String insertSQL = "INSERT INTO " + UtenteDB.TABLE_NAME
                + " (id,username,pass,email,nome,cognome,"
                + "sesso,data_sospensione,is_gestore) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            int i = 1;
            preparedStatement.setInt(i++, aUtente.getId());
            preparedStatement.setString(i++, aUtente.getUserName());
            preparedStatement.setString(i++, aUtente.getPassword());
            preparedStatement.setString(i++, aUtente.getEmail());
            preparedStatement.setString(i++, aUtente.getNome());
            preparedStatement.setString(i++, aUtente.getCognome());
            preparedStatement.setString(i++, aUtente.getSesso());
            preparedStatement.setDate(i++, aUtente.getDataSospensione());

            if (aUtente instanceof Gestore) {
                preparedStatement.setBoolean(i++, aUtente.getIsGestore());
            } else {
                preparedStatement.setBoolean(i++, false);
            }

            res = preparedStatement.executeUpdate();

            connection.commit();
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
     * This method fetches information about a user
     * given his email address.
     * @param aEmail is the email address of the user.
     * @return user is the user whose the email address is of.
     * @throws SQLException is the exception that can be thrown
     * during the execution.
     */
    public static Utente getByEmail(final String aEmail)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Utente user = null;

        String selectSQL;

        selectSQL = "SELECT * FROM " + UtenteDB.TABLE_NAME + " WHERE email = ?";
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            preparedStatement.setString(1, aEmail);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                boolean b = rs.getBoolean("is_gestore");
                if (b) {
                    user = new Gestore();
                } else {
                    user = new CSU();
                }

                user.setId(rs.getInt("Id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("pass"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setSesso(rs.getString("sesso"));
                user.setDataSospensione(rs.getDate("data_sospensione"));
                user.setIsGestore(b);
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

        return user;
    }

    /**
     * This method fetches information about all the users stored into
     * the database.
     * @return users is a set of all the users.
     * @throws SQLException is the exception that can be thrown
     * during the execution.
     */
    public static Collection<Utente> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Utente> users = new LinkedList<Utente>();

        Utente u = null;

        String selectSQL = "SELECT * FROM " + UtenteDB.TABLE_NAME;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                boolean b = rs.getBoolean("is_gestore");
                if (b) {
                    u = new Gestore();
                } else {
                    u = new CSU();
                }

                u.setId(rs.getInt("Id"));
                u.setUserName(rs.getString("username"));
                u.setPassword(rs.getString("pass"));
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setSesso(rs.getString("sesso"));
                u.setDataSospensione(rs.getDate("data_sospensione"));
                u.setIsGestore(b);

                users.add(u);
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
        if (users.size() > 0) {
            return users;
        } else {
            return (null);
        }
    }

    /**
     * This method deletes a user from the database given
     * his email address.
     * @param aEmail is the email address
     * @throws SQLException is the exception that can be thrown
     * during the execution.
     * @return res is 0 if the delete operation is not made,
     *         otherwise an integer greater than 0.
     */
    public static int delete(final String aEmail) throws SQLException {
        Connection connection = null;
        PreparedStatement s = null;
        int res = 0;

        String deleteSQL = "DELETE FROM " + UtenteDB.TABLE_NAME
                + " WHERE email = ?;";

        try {
            connection = Database.getConnection();
            s = connection.prepareStatement(deleteSQL);
            s.setString(1, aEmail);

            res = s.executeUpdate(deleteSQL);

            connection.commit();
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
     * This method updates information about a given user.
     * @param aUtente is the object containing updated information
     * about the user.
     * @throws SQLException is the exception that can be thrown
     * during the execution.
     * @return res is 0 if the update operation is not made,
     *         otherwise an integer greater than 0.
     */
    public static int update(final Utente aUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement s = null;
        int res = 0;

        String updateSQL = "UPDATE " + UtenteDB.TABLE_NAME
                + " SET data_sospensione = ?"
                + " WHERE id = " + aUtente.getId() + ";";
        try {
            connection = Database.getConnection();
            s = connection.prepareStatement(updateSQL);
            int i = 1;
            s.setDate(i, aUtente.getDataSospensione());
            res = s.executeUpdate(updateSQL);

            connection.commit();
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
}
