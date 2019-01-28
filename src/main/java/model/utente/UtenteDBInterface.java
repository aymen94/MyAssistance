package model.utente;

import java.util.List;

/**
 * The Interface UtenteDBInterface.
 */
public interface UtenteDBInterface {

    /**
     * Insert.
     *
     * @param aUtente the utente
     * @return the int
     * @throws Exception the exception
     */
    int insert(Utente aUtente) throws Exception;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    List<Utente> getAll() throws Exception;

    /**
     * Gets the by id.
     *
     * @param aId the id
     * @return the by id
     * @throws Exception the exception
     */
    Utente getById(int aId) throws Exception;

    /**
     * Gets the by email.
     *
     * @param aEmail the email
     * @return the by email
     * @throws Exception the exception
     */
    Utente getByEmail(String aEmail) throws Exception;

    /**
     * Update.
     *
     * @param aUtente the utente
     * @return the int
     * @throws Exception the exception
     */
    int update(Utente aUtente) throws Exception;

    /**
     * Delete.
     *
     * @param aEmail the email
     * @return the int
     * @throws Exception the exception
     */
    int delete(String aEmail) throws Exception;

}
