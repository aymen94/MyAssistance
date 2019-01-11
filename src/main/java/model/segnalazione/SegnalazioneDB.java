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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.ufficiotecnico.UfficioTecnico;
import model.utente.CSU;
import model.utente.Utente;
import pool.Database;

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
    private static final String INSERT_SEGNALAZIONE =
        "INSERT INTO " + TABLE_NAME
            + " (titolo,descrizione,stato,data_segnalazione,data_rifiuto,"
            + "data_assegnazione,data_risoluzione,motivazione_rifiuto,"
            + "tipologia, autore, tecnico) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * The Constant UPDATE_SEGNALAZIONE.
     */
    private static final String UPDATE_SEGNALAZIONE =
        "UPDATE " + TABLE_NAME + " SET titolo = ?, descrizione = ?, stato = ?,"
            + " data_segnalazione = ?, data_rifiuto = ?, data_assegnazione = ?,"
            + " data_risoluzione = ?, motivazione_rifiuto = ?, "
            + "tipologia = ?,  autore = ?,  tecnico= ? " + " WHERE cod = ?";

    /**
     * The Constant SELECT_BY_AUTHOR.
     */
    private static final String SELECT_BY_AUTHOR =
        " SELECT * FROM " + TABLE_NAME + " WHERE autore = ?";

    /**
     * The Constant SELECT_BY_ID.
     */
    private static final String SELECT_BY_ID =
        "SELECT * FROM " + TABLE_NAME + " WHERE cod = ?";

    /**
     * The Constant SELECT_ALL.
     */
    private static final String SELECT_ALL = " SELECT * FROM " + TABLE_NAME;

    /**
     * The Constant DELETE_BY_ID.
     */
    private static final String DELETE_BY_ID =
        "DELETE FROM " + TABLE_NAME + " WHERE id=?";

    /**
     * Insert.
     *
     * @param aSegnalazione the segnalazione
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int insert(final Segnalazione aSegnalazione) throws SQLException {
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
            preparedStatement
                .setString(i++, aSegnalazione.getMotivazioneRifiuto());
            preparedStatement.setInt(i++, aSegnalazione.getTipologia().getId());
            preparedStatement.setInt(i++, aSegnalazione.getAutore().getId());
            if (aSegnalazione.getTecnico() != null) {
                preparedStatement.setInt(i, aSegnalazione.getTecnico().getId());
            } else {
                preparedStatement.setNull(i, Types.INTEGER);
            }
            return preparedStatement.executeUpdate();
        } finally {
            freeResources(preparedStatement, connection);
        }

    }

    /**
     * Update.
     *
     * @param aSegnalazione the segnalazione
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int update(final Segnalazione aSegnalazione) throws SQLException {
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
            preparedStatement
                .setString(i++, aSegnalazione.getMotivazioneRifiuto());
            preparedStatement.setInt(i++, aSegnalazione.getTipologia().getId());
            preparedStatement.setInt(i++, aSegnalazione.getAutore().getId());
            if (aSegnalazione.getTecnico() != null) {
                preparedStatement
                    .setInt(i++, aSegnalazione.getTecnico().getId());
            } else {
                preparedStatement.setNull(i++, Types.INTEGER);
            }
            preparedStatement.setInt(i, aSegnalazione.getCod());
            return preparedStatement.executeUpdate();
        } finally {
            freeResources(preparedStatement, connection);
        }
    }

    /**
     * Gets the by autore.
     *
     * @param aAutorId the autor id
     * @return the by autore
     * @throws SQLException the SQL exception
     */
    public List<Segnalazione> getByAutore(final int aAutorId)
        throws SQLException {
        return genericGet(SELECT_BY_AUTHOR, aAutorId);
    }

    /**
     * Gets the all.
     *
     * @return the all
     * @throws SQLException the SQL exception
     */
    public List<Segnalazione> getAll() throws SQLException {
        return genericGet(SELECT_ALL, -1);
    }

    /**
     * Gets the by cod.
     *
     * @param aCod the cod
     * @return the by cod
     * @throws SQLException the SQL exception
     */
    public Segnalazione getByCod(final int aCod) throws SQLException {
        List<Segnalazione> segnalazioneList = genericGet(SELECT_BY_ID, aCod);
        if (segnalazioneList != null) {
            return segnalazioneList.get(0);
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
    private List<Segnalazione> genericGet(final String aQuery,
        final int aParameter) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final List<Segnalazione> segnalazioneList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(aQuery);
            if (aParameter > 0) {
                preparedStatement.setInt(1, aParameter);
            }
            final ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                final Segnalazione segnalazione = new Segnalazione();
                segnalazione.setCod(result.getInt("cod"));

                segnalazione
                    .setDataAssegnazione(result.getDate("data_assegnazione"));
                segnalazione.setDataRifiuto(result.getDate("data_rifiuto"));
                segnalazione
                    .setDataRisoluzione(result.getDate("data_risoluzione"));
                segnalazione
                    .setDataSegnalazione(result.getDate("data_segnalazione"));
                segnalazione.setDescrizione(result.getString("descrizione"));
                segnalazione.setMotivazioneRifiuto(result
                    .getString("motivazione_rifiuto"));
                segnalazione.setStato(result.getShort("stato"));

                // HACK: Use a fake author until UtenteDB.getById() is
                // implemented
                Utente autore = new CSU();
                autore.setId(1);
                segnalazione.setAutore(autore);

                // HACK: Use a fake Tecnico until UfficioTecnicoDB.getById() is
                // implemented
                if (result.getInt("tecnico") != 0) {
                    UfficioTecnico tecnico = new UfficioTecnico();
                    tecnico.setId(1);
                    segnalazione.setTecnico(tecnico);
                }

                segnalazione.setTipologia(TipologiaDB
                    .getById(result.getInt("tipologia")));

                segnalazione.setTitolo(result.getString("titolo"));
                segnalazioneList.add(segnalazione);
            }
            if (segnalazioneList.size() > 0) {
                return segnalazioneList;
            } else {
                return null;
            }
        } finally {
            freeResources(preparedStatement, connection);
        }
    }

    /**
     * Delete by id.
     *
     * @param aId the id
     * @return the int
     * @throws SQLException the SQL exception
     */
    public int deleteById(final int aId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res = 0;
        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, aId);

            res = preparedStatement.executeUpdate(DELETE_BY_ID);
        } finally {
            freeResources(preparedStatement, connection);
        }
        return (res);
    }

    /**
     * Free resources.
     * @param con The Connection
     * @param aStm the stm
     * @throws SQLException the SQL exception
     */
    private void freeResources(final PreparedStatement aStm,
        final Connection con) throws SQLException {
        if (aStm != null) {
            aStm.close();
        }
        con.close();
    }

}
