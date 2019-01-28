package model.ufficio_tecnico;

import java.util.List;

/**
 * The Interface UfficioTecnicoDBInterface.
 */
public interface UfficioTecnicoDBInterface {

    /**
     * Insert.
     *
     * @param aUfficioTecnico the ufficio tecnico
     * @return the int
     * @throws Exception the exception
     */
    int insert(UfficioTecnico aUfficioTecnico) throws Exception;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    List<UfficioTecnico> getAll() throws Exception;

    /**
     * Gets the by id.
     *
     * @param aId the id
     * @return the by id
     * @throws Exception the exception
     */
    UfficioTecnico getById(int aId) throws Exception;

}
