/*
Project: MyAssistance
Author: Pio
Date: 30/12/2018
*/
package model.utente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.sql.Connection;

/**
 * Questa classe permette la gestione dei dati persistenti relativi agli utenti.
 */
public class UtenteDB {
    /**
     * Questo attributo privato è una stringa contenete il nome della tabella
     * contenente i dati da gestire.
     */
    private static final String TABLE_NAME = "utente";

    /**
     * Questo metodo effettua l'inserimento di un utente nel database.
     * @param u è l'oggetto di tipo Utente che deve essere inserito
     * all'interno del database.
     * @throws SQLException è l'eccezione che può essere lanciata
     * durante l'esecuzione del metodo.
     * @return res è vale 0 se l'inserimento non è stato effettuato,
     * altrimenti un intero maggiore di 0.
     */
    public synchronized int insert(final Utente u) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res = 0;

        String insertSQL = "INSERT INTO " + UtenteDB.TABLE_NAME
                + " (id,username,pass,email,nome,cognome,"
                + "sesso,data_sospensione,is_gestore) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            // connection = OTTIENI CONNESSIONE
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, u.getId());
            preparedStatement.setString(2, u.getUserName());
            preparedStatement.setString(3, u.getPassword());
            preparedStatement.setString(4, u.getEmail());
            preparedStatement.setString(5, u.getNome());
            preparedStatement.setString(6, u.getCognome());
            preparedStatement.setString(7, u.getSesso());
            //preparedStatement.setDate(8, u.getDataSospensione());

            if (u instanceof Gestore) {
                preparedStatement.setBoolean(9, u.getIsGestore());
            } else {
                preparedStatement.setBoolean(9, false);
            }

            res = preparedStatement.executeUpdate();

            connection.commit();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                // .releaseConnection(connection);
                /*la successiva riga è da cancellare quando saraà
                implementata la classe per la connessione*/
                connection = null;
            }
            /*la successiva riga è da cancellare quando saraà
            implementata la classe per la connessione*/
            res = 1;
        }

        return (res);
    }

    /**
     * Questo metodo permette di prelevare
     * dal database le informazioni
     * relative ad un utente dato il suo indirizzo email.
     * @param email è l'indirizzo email dell'utente di cui
     * si vuole ottenere le informazioni.
     * @return user è l'utente il cui indirizzo email
     * corrisponde a quello fornito in input al metodo.
     * @throws SQLException è l'eccezione che può essere
     * lanciata durante l'esecuzione del metodo.
     */
    public synchronized Utente getByEmail(final String email)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Utente user = new Utente();
        user = null;

        String selectSQL;

        selectSQL = "SELECT * FROM " + UtenteDB.TABLE_NAME + " WHERE email = ?";
        try {
            // connection = OTTIENI CONNESSIONE
            preparedStatement = connection.prepareStatement(selectSQL);

            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                user.setId(rs.getInt("Id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("pass"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setSesso(
                        rs.getString("sesso"));
                //user.setDataSospensione(rs.getDate("data_sospensione"));
                user.setIsGestore(rs.getBoolean("is_gestore"));
            }

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                //.releaseConnection(connection);
                /*la successiva riga è da cancellare quando saraà
                implementata la classe per la connessione*/
                connection = null;
            }
        }
        /*la successiva riga è da cancellare
        quando saranno implementate le classi utenti*/
        user = new Utente();
        return user;
    }

    /**
     * Questo metodo consente di prelevare dal database le informazioni
     * di tutti gli
     * utenti registrati.
     * @return users è la lista di tutti gli utenti registrati
     * nel database.
     * @throws SQLException è l'eccezione che può essere lanciata
     * durante l'esecuzione del metodo.
     */
    public synchronized Collection<Utente> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Utente> users = new LinkedList<Utente>();
        users = null;

        String selectSQL = "SELECT * FROM " + UtenteDB.TABLE_NAME;

        try {
            // connection = OTTIENI CONNESSIONE
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Utente u = new Utente();

                u.setId(rs.getInt("Id"));
                u.setUserName(rs.getString("username"));
                u.setPassword(rs.getString("pass"));
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setSesso(rs.getString("sesso"));
                //u.setDataSospensione(rs.getDate("data_sospensione"));
                u.setIsGestore(rs.getBoolean("is_gestore"));

                users.add(u);
            }

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                // .releaseConnection(connection);
                /*la successiva riga è da cancellare quando saraà
                implementata la classe per la connessione*/
                connection = null;
            }
        }
        /*la successiva riga è da cancellare
        quando saranno implementate le classi utenti*/
        users = new ArrayList<Utente>();
        return users;
    }

    /**
     * Questo metodo consente di eliminare dal database un utente dato il suo
     * indirizzo email.
     * @param email è l'indirizzo email dell'utente che deve essere
     * eliminato dal database.
     * @throws SQLException è l'eccezione che può essere lanciata
     * durante l'esecuzione del metodo.
     * @return res vale 0 se la cancellazione non è stata effettuata,
     * altrimenti un intero maggiore di 0.
     */
    public synchronized int delete(final String email) throws SQLException {
        Connection connection = null;
        PreparedStatement s = null;
        int res = 0;

        String deleteSQL = "DELETE FROM " + UtenteDB.TABLE_NAME
                + " WHERE email = ?;";

        try {
            // connection = OTTIENI CONNESSIONE
            s = connection.prepareStatement(deleteSQL);
            s.setString(1, email);

            res = s.executeUpdate(deleteSQL);

            connection.commit();
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } finally {
                // .releaseConnection(connection);
                /*la successiva riga è da cancellare quando saraà
                implementata la classe per la connessione*/
                connection = null;
            }
        }
        /*la successiva riga è da cancellare quando saraà
        implementata la classe per la connessione*/
        res = 1;
        return (res);
    }
}
