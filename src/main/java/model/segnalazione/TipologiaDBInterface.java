package model.segnalazione;

import java.util.List;

/**
 * The Interface TipologiaDBInterface.
 */
public interface TipologiaDBInterface {

    /**
     * Insert.
     *
     * @param aTipologia the tipologia
     * @return the int
     * @throws Exception the exception
     */
    int insert(Tipologia aTipologia) throws Exception;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    List<Tipologia> getAll() throws Exception;

    /**
     * Gets the by id.
     *
     * @param aExternal the id
     * @return the by id
     * @throws Exception the exception
     */
    Tipologia getById(int aExternal) throws Exception;
}
