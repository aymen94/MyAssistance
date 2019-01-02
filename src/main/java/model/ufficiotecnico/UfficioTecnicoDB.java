/*
Project: MyAssistance
Author: Aymen
Date: 23/12/2018
*/
package model.ufficiotecnico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.sql.Connection;

/**
 * Questa classe permette la gestione dei dati persistenti relativi agli uffici tecnici.
 */

public class UfficioTecnicoDB {

    private static final String TABLE_NAME = "ufficio_tecnico";

    /**
     * Questo metodo effettua l'inserimento di un ufficio tecnico nel database.
     * @param uff è l'oggetto di tipo UfficioTecnico che deve essere inserito
     * all'interno del database.
     * @throws SQLException è l'eccezione che può essere lanciata
     * durante l'esecuzione del metodo.
     * @return res  vale 0 se l'inserimento non è stato effettuato,
     * altrimenti un intero maggiore di 0.
     */

    public synchronized int insert(final UfficioTecnico uff) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res;

        String insertSQL = "INSERT INTO " + UfficioTecnicoDB.TABLE_NAME + " (id,nome,tel,email,ubicazione) " + "VALUES (?, ?, ?, ?, ?)";

        try {
            // connection = OTTIENI CONNESSIONE
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, uff.getId());
            preparedStatement.setString(2, uff.getNome());
            preparedStatement.setString(3, uff.getTel());
            preparedStatement.setString(4, uff.getEmail());
            preparedStatement.setString(5, uff.getUbicazione());

            res = preparedStatement.executeUpdate();

            connection.commit();
            res++;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                // .releaseConnection(connection);
                connection = null;
            }
        }
        return (res);
    }

    /**
     * Questo metodo consente di prelevare dal database le informazioni
     * di tutti gli
     * uffici tecnici registrati.
     * @return users è la lista di tutti gli utenti registrati
     * nel database.
     * @throws SQLException è l'eccezione che può essere lanciata
     * durante l'esecuzione del metodo.
     */

    public synchronized Collection<UfficioTecnico> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<UfficioTecnico> uffici;
        uffici = null;

        String selectSQL = "SELECT * FROM " + UfficioTecnicoDB.TABLE_NAME;

        try {
            // connection = OTTIENI CONNESSIONE
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

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
                // .releaseConnection(connection);
                connection = null;
            }
        }
        return uffici;
    }
}