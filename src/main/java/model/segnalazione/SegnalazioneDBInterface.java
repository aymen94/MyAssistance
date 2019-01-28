package model.segnalazione;

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
     * @throws Exception the exception
     */
    int insert(Segnalazione aSegnalazione) throws Exception;

    /**
     * Update.
     *
     * @param aSegnalazione the segnalazione
     * @return the int
     * @throws Exception the exception
     */
    int update(Segnalazione aSegnalazione) throws Exception;

    /**
     * Gets the by autore.
     *
     * @param aAutorId the autor id
     * @return the by autore
     * @throws Exception the exception
     */
    List<Segnalazione> getByAutore(int aAutorId) throws Exception;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    List<Segnalazione> getAll() throws Exception;

    /**
     * Gets the by cod.
     *
     * @param aCod the cod
     * @return the by cod
     * @throws Exception the exception
     */
    Segnalazione getByCod(int aCod) throws Exception;

    /**
     * Delete by id.
     *
     * @param aId the id
     * @return the int
     * @throws Exception the exception
     */
    int deleteById(int aId) throws Exception;

}
