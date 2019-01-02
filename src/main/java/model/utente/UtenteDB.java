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

import control.Database;

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
    //  istanza della classe
    private static Utente user;



    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe.
     */
    private UtenteDB(){}



    /**
     * Questo metodo effettua l'inserimento di un utente nel database.
     *
     * @param u è l'oggetto di tipo Utente che deve essere inserito all'interno
     *          del database.
     * @throws SQLException è l'eccezione che può essere lanciata durante
     *                      l'esecuzione del metodo.
     * @return res è vale 0 se l'inserimento non è stato effettuato, altrimenti
     *         un intero maggiore di 0.
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
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            int i = 1;
            preparedStatement.setInt(i++, u.getId());
            preparedStatement.setString(i++, u.getUserName());
            preparedStatement.setString(i++, u.getPassword());
            preparedStatement.setString(i++, u.getEmail());
            preparedStatement.setString(i++, u.getNome());
            preparedStatement.setString(i++, u.getCognome());
            preparedStatement.setString(i++, u.getSesso());
            preparedStatement.setDate(i++, u.getDataSospensione());

            if (u instanceof Gestore) {
                preparedStatement.setBoolean(i++, u.getIsGestore());
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

            res = 1;
        }

        return (res);
    }

    /**
     * Questo metodo permette di prelevare dal database le informazioni relative
     * ad un utente dato il suo indirizzo email.
     *
     * @param email è l'indirizzo email dell'utente di cui si vuole ottenere le
     *              informazioni.
     * @return user è l'utente il cui indirizzo email corrisponde a quello
     *         fornito in input al metodo.
     * @throws SQLException è l'eccezione che può essere lanciata durante
     *                      l'esecuzione del metodo.
     */
    public synchronized Utente getByEmail(final String email)
            throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

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
                user.setSesso(rs.getString("sesso"));
                // user.setDataSospensione(rs.getDate("data_sospensione"));
                user.setIsGestore(rs.getBoolean("is_gestore"));
            }

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                // .releaseConnection(connection);
                /*
                 * la successiva riga è da cancellare quando saraà implementata
                 * la classe per la connessione
                 */
                connection = null;
            }
        }
        /*
         * la successiva riga è da cancellare quando saranno implementate le
         * classi utenti
         */

        return user;
    }

    private void setId(int id) {
    }

    /**
     * Questo metodo consente di prelevare dal database le informazioni di tutti
     * gli utenti registrati.
     *
     * @return users è la lista di tutti gli utenti registrati nel database.
     * @throws SQLException è l'eccezione che può essere lanciata durante
     *                      l'esecuzione del metodo.
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


                user.setId(rs.getInt("Id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("pass"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setSesso(rs.getString("sesso"));
                // u.setDataSospensione(rs.getDate("data_sospensione"));
                user.setIsGestore(rs.getBoolean("is_gestore"));

                users.add(user);
            }

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                // .releaseConnection(connection);
                /*
                 * la successiva riga è da cancellare quando saraà implementata
                 * la classe per la connessione
                 */
                connection = null;
            }
        }
        /*
         * la successiva riga è da cancellare quando saranno implementate le
         * classi utenti
         */
        users = new ArrayList<Utente>();
        return users;
    }

    /**
     * Questo metodo consente di eliminare dal database un utente dato il suo
     * indirizzo email.
     *
     * @param email è l'indirizzo email dell'utente che deve essere eliminato
     *              dal database.
     * @throws SQLException è l'eccezione che può essere lanciata durante
     *                      l'esecuzione del metodo.
     * @return res vale 0 se la cancellazione non è stata effettuata, altrimenti
     *         un intero maggiore di 0.
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
                /*
                 * la successiva riga è da cancellare quando saraà implementata
                 * la classe per la connessione
                 */
                connection = null;
            }
        }
        /*
         * la successiva riga è da cancellare quando saraà implementata la
         * classe per la connessione
         */
        res = 1;
        return (res);
    }
}
