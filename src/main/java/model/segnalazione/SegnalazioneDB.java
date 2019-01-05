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

import control.Database;
import model.ufficiotecnico.UfficioTecnico;
import model.utente.CSU;

// TODO: Auto-generated Javadoc
/**
 * The Class SegnalazioneDB.
 */
public final class SegnalazioneDB {

    /**
     * The Constant TABLE_NAME.
     */
    private static final String TABLE_NAME = "segnalazione";

    /**
     * The Constant INSERT_SEGNALAZIONE.
     */
    private static final String INSERT_SEGNALAZIONE = "INSERT INTO "
            + TABLE_NAME
            + " (titolo,descrizione,stato,data_segnalazione,data_rifiuto,"
            + "data_assegnazione,data_risoluzione,motivazione_rifiuto,"
            + "tipologia, autore, tecnico) VALUES (?,?,?,?,?,?,?,?,?,?)";

    /**
     * The Constant UPDATE_SEGNALAZIONE.
     */
    private static final String UPDATE_SEGNALAZIONE = "UPDATE " + TABLE_NAME
            + " SET titolo = ?, descrizione = ?, stato = ?,"
            + " data_segnalazione = ?, data_rifiuto = ?, data_assegnazione = ?,"
            + " data_risoluzione = ?, motivazione_rifiuto = ?, "
            + "tipologia = ?,  autore = ?,  tecnico= ? " + " WHERE cod = ?";

    /**
     * The Constant SELECT_BY_AUTHOR.
     */
    private static final String SELECT_BY_AUTHOR = " SELECT * FROM "
            + TABLE_NAME + " WHERE autore = ?";

    /**
     * The Constant SELECT_BY_ID.
     */
    private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME
            + " WHERE cod = ?";

    /**
     * The Constant SELECT_ALL.
     */
    private static final String SELECT_ALL = " SELECT * FROM " + TABLE_NAME;

    /**
     * This is an utility class. So no constructor should be used.
     */
    private SegnalazioneDB() {

    }

    /**
     * Insert.
     *
     * @param aSegnalazione the segnalazione
     * @return true, if successful
     */
    public static boolean insert(final Segnalazione aSegnalazione) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Database.getConnection();
            preparedStatement = connection
                    .prepareStatement(INSERT_SEGNALAZIONE);
            int i = 1;
            preparedStatement.setString(i++, aSegnalazione.getTitolo());
            preparedStatement.setString(i++, aSegnalazione.getDescrizione());
            preparedStatement.setShort(i++, aSegnalazione.getStato());
            preparedStatement.setDate(i++, aSegnalazione.getDataSegnalazione());
            preparedStatement.setDate(i++, aSegnalazione.getDataRifiuto());
            preparedStatement.setDate(i++, aSegnalazione.getDataAssegnazione());
            preparedStatement.setDate(i++, aSegnalazione.getDataRisoluzione());
            preparedStatement.setInt(i++, aSegnalazione.getTipologia().getId());
            preparedStatement.setInt(i++, aSegnalazione.getAutore().getId());
            preparedStatement.setInt(i++, aSegnalazione.getTecnico().getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.freeConnection(connection);
        }

    }

    /**
     * Update.
     *
     * @param aSegnalazione the segnalazione
     * @return true, if successful
     */
    public static boolean update(final Segnalazione aSegnalazione) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Database.getConnection();
            preparedStatement = connection
                    .prepareStatement(UPDATE_SEGNALAZIONE);
            int i = 1;
            preparedStatement.setString(i++, aSegnalazione.getTitolo());
            preparedStatement.setString(i++, aSegnalazione.getDescrizione());
            preparedStatement.setShort(i++, aSegnalazione.getStato());
            preparedStatement.setDate(i++, aSegnalazione.getDataSegnalazione());
            preparedStatement.setDate(i++, aSegnalazione.getDataRifiuto());
            preparedStatement.setDate(i++, aSegnalazione.getDataAssegnazione());
            preparedStatement.setDate(i++, aSegnalazione.getDataRisoluzione());
            preparedStatement.setInt(i++, aSegnalazione.getTipologia().getId());
            preparedStatement.setInt(i++, aSegnalazione.getAutore().getId());
            preparedStatement.setInt(i++, aSegnalazione.getTecnico().getId());
            preparedStatement.setInt(i++, aSegnalazione.getCod());
            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.freeConnection(connection);
        }
    }

    /**
     * Gets the by autore.
     *
     * @return the by autore
     */
    public static List<Segnalazione> getByAutore() {
        return genericGet(SELECT_BY_AUTHOR);
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
    public static List<Segnalazione> getAll() {
        return genericGet(SELECT_ALL);
    }

    /**
     * Gets the by id.
     *
     * @return the by id
     */
    public static Segnalazione getById() {
        Segnalazione segnalazione = genericGet(SELECT_BY_ID).get(0);
        if (segnalazione == null) {
            segnalazione = new Segnalazione();
        }
        return segnalazione;
    }

    /**
     * Generic get.
     *
     * @param query the query
     * @return the list
     */
    private static List<Segnalazione> genericGet(final String query) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Segnalazione> segnalazioneList = new ArrayList<Segnalazione>();

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Segnalazione segnalazione = new Segnalazione();
                segnalazione.setCod(result.getInt("cod"));

                segnalazione.setDataAssegnazione(
                        result.getDate("data_assegnazione"));
                segnalazione.setDataRifiuto(result.getDate("data_rifiuto"));
                segnalazione
                        .setDataRisoluzione(result.getDate("data_risoluzione"));
                segnalazione.setDataSegnalazione(
                        result.getDate("data_segnalazione"));
                segnalazione.setDescrizione(result.getString("descrizione"));
                segnalazione.setMotivazioneRifiuto(
                        result.getString("motivazione_rifiuto"));
                segnalazione.setStato(result.getShort("stato"));

                // HACK: Use a fake author until UtenteDB.getById() is
                // implemented
                segnalazione.setAutore(new CSU());

                // HACK: Use a fake Tecnico until UfficioTecnicoDB.getById() is
                // implemented
                segnalazione.setTecnico(new UfficioTecnico());

                // HACK: Use a fake Tipologia until TipologiaDB.getById() is
                // implemented
                segnalazione.setTipologia(new Tipologia());

                segnalazione.setTitolo(result.getString("titolo"));
                segnalazioneList.add(segnalazione);
            }
            return segnalazioneList;

        } catch (final SQLException e) {
            e.printStackTrace();
            return segnalazioneList;
        } finally {
            Database.freeConnection(connection);
        }
    }

}
