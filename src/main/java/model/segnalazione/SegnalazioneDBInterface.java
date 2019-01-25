package model.segnalazione;

import java.sql.SQLException;
import java.util.List;

/**
 * The Interface SegnalazioneDBInterface.
 */
public interface SegnalazioneDBInterface {

    /**
     * Insert.
     *
     * @param aSegnalazione the segnalazione
     * @return the int
     * @throws SQLException the exception
     */
    int insert(Segnalazione aSegnalazione) throws SQLException;

    /**
     * Update.
     *
     * @param aSegnalazione the segnalazione
     * @return the int
     * @throws SQLException the exception
     */
    int update(Segnalazione aSegnalazione) throws SQLException;

    /**
     * Gets the by autore.
     *
     * @param aAutorId the autor id
     * @return the by autore
     * @throws SQLException the exception
     */
    List<Segnalazione> getByAutore(int aAutorId) throws SQLException;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws SQLException the exception
     */
    List<Segnalazione> getAll() throws SQLException;

    /**
     * Gets the by cod.
     *
     * @param aCod the cod
     * @return the by cod
     * @throws SQLException the exception
     */
    Segnalazione getByCod(int aCod) throws SQLException;

    /**
     * Delete by id.
     *
     * @param aId the id
     * @return the int
     * @throws SQLException the SQL exception
     */
    int deleteById(int aId) throws SQLException;

}
