/*
Project: MyAssistance
Author: Pio
Date: 30/12/2018
*/
package model.utente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import pool.Database;

/**
 * This class manages stored data about Users into the database.
 */
public final class UtenteDB implements UtenteDBInterface {
    /**
     * This private attribute is a string that contains
     * the name of the table.
     */
    private static final String TABLE_NAME = "utente";

    /**
     * This private attribute is a string that contains
     * the query insert user by id.
     */
    private static final String INSERT = "INSERT INTO " + UtenteDB.TABLE_NAME
        + " (username,pass,email,nome,cognome,"
        + "sesso,data_di_nascita,data_sospensione,is_gestore) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?,? ,?);";

    /**
     * This private attribute is a string that contains
     * the query get user by id.
     */
    private static final String GET_BY_ID =
        "SELECT * FROM " + UtenteDB.TABLE_NAME + " WHERE id = ?";

    /**
     * This private attribute is a string that contains
     * the query get user by username.
     */
    private static final String GET_BY_USERNAME =
        "SELECT * FROM " + UtenteDB.TABLE_NAME + " WHERE username = ?";

    /**
     * This private attribute is a string that contains
     * the query get all user.
     */
    private static final String GET_ALL =
        "SELECT * FROM " + UtenteDB.TABLE_NAME;

    /**
     * This private attribute is a string that contains
     * the query to delete user by username.
     */
    private static final String DELETE =
        "DELETE FROM " + UtenteDB.TABLE_NAME + " WHERE email =?;";

    /**
     * This private attribute is a string that contains
     * the query to update data user by username.
     */
    private static final String UPDATE =
        "UPDATE " + UtenteDB.TABLE_NAME + " SET data_sospensione = ?"
            + " WHERE id = ?";

    /**
     * Default constructor.
     */
    public UtenteDB() {

    }

    /**
     * This method inserts a user into the database.
     *
     * @param aUtente is the object of the Utente type that
     *                must be inserted into the database.
     * @return res is 0 if the insert operation is not made,
     * otherwise an integer greater than 0.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public int insert(final Utente aUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        Utente utente = aUtente;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            int i = 1;
            preparedStatement.setString(i++, aUtente.getUserName());
            preparedStatement.setString(i++, aUtente.getPassword());
            preparedStatement.setString(i++, aUtente.getEmail());
            preparedStatement.setString(i++, aUtente.getNome());
            preparedStatement.setString(i++, aUtente.getCognome());
            preparedStatement.setInt(i++, aUtente.getSesso());
            preparedStatement.setDate(i++,
                    Date.valueOf(aUtente.getDataDiNascita()));

            if (aUtente instanceof Gestore) {
                preparedStatement.setDate(i++, null);
            } else {
                if (((CSU) utente).getDataSospensione() != null) {
                    preparedStatement.setDate(i++,
                            Date.valueOf(((CSU) utente).getDataSospensione()));
                } else {
                    preparedStatement.setDate(i++, null);
                }
            }
            preparedStatement.setBoolean(i, utente.isGestore());
            return preparedStatement.executeUpdate();
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * This method fetches information about a user
     * given his id.
     *
     * @param aId is the id of the user.
     * @return user is the user whose id is of.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public Utente getById(final int aId) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_BY_ID);

            preparedStatement.setInt(1, aId);

            List<Utente> listUtente = parseResultSet(
                    preparedStatement.executeQuery());
            if (listUtente.size() > 0) {
                return listUtente.get(0);
            } else {
                return null;
            }
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * This method fetches information about a user
     * given his username address.
     *
     * @param aUserName is the username of the user.
     * @return user is the user whose the username is of.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public Utente getByUserName(final String aUserName) throws SQLException {
        Connection connection = Database.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_BY_USERNAME);

            preparedStatement.setString(1, aUserName);

            List<Utente> listUtente = parseResultSet(
                    preparedStatement.executeQuery());
            if (listUtente.size() > 0) {
                return listUtente.get(0);
            } else {
                return null;
            }
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * This method fetches information about all the users stored into
     * the database.
     *
     * @return users is a set of all the users.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public List<Utente> getAll() throws SQLException {
        Connection connection = Database.getConnection();
        try {
            return parseResultSet(
                    connection.prepareStatement(GET_ALL).executeQuery());
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * This method deletes a user from the database given
     * his email address.
     *
     * @param aEmail is the email address
     * @return res is 0 if the delete operation is not made,
     * otherwise an integer greater than 0.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public int delete(final String aEmail) throws SQLException {
        Connection connection = null;
        PreparedStatement s;
        int res;

        try {
            connection = Database.getConnection();
            s = connection.prepareStatement(DELETE);
            s.setString(1, aEmail);

            res = s.executeUpdate();

        } finally {
            Database.freeConnection(connection);
        }
        return (res);
    }

    /**
     * This method updates information about a given user.
     *
     * @param aUtente is the object containing updated information
     *                about the user.
     * @return res is 0 if the update operation is not made,
     * otherwise an integer greater than 0.
     * @throws SQLException is the exception that can be thrown
     *                      during the execution.
     */
    public int update(final Utente aUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CSU csu = (CSU) aUtente;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDate(1,
                    Date.valueOf(csu.getDataSospensione()));
            preparedStatement.setInt(2, csu.getId());
            return preparedStatement.executeUpdate();

        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * Parses the result set.
     *
     * @param rs the rs
     * @return the list
     * @throws SQLException the SQL exception
     */
    private List<Utente> parseResultSet(final ResultSet rs)
            throws SQLException {
        List<Utente> users = new ArrayList<>();
        Utente u;
        while (rs.next()) {

            if (rs.getBoolean("is_gestore")) {
                u = new Gestore();
            } else {
                u = new CSU();
                ((CSU) u).setDataSospensione(
                        toLocalDate(rs.getDate("data_sospensione")));
            }
            u.setId(rs.getInt("Id"));
            u.setUserName(rs.getString("username"));
            u.setPassword(rs.getString("pass"));
            u.setEmail(rs.getString("email"));
            u.setNome(rs.getString("nome"));
            u.setCognome(rs.getString("cognome"));
            u.setDataDiNascita(rs.getDate("data_di_nascita").toLocalDate());
            u.setSesso(rs.getInt("sesso"));
            users.add(u);
        }
        return users;
    }

    /**
     * To date.
     *
     * @param date the date
     * @return the date
     */
    private LocalDate toLocalDate(final Date date) {
        if (date != null) {
            return date.toLocalDate();
        } else {
            return null;
        }
    }
}
