
package model.ufficio_tecnico;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class UfficioTecnicoBLTest.
 */
public class UfficioTecnicoBLTest {

    /**
     * The UfficioTecnico DB.
     */
    private UfficioTecnicoDBInterface db;

    /**
     * The manager.
     */
    private UfficioTecnicoBL manager;

    /**
     * The UfficioTecnico test.
     */
    private UfficioTecnico ufficioTecnicoTest;

    /**
     * The UfficioTecnico test 2.
     */
    private UfficioTecnico ufficioTecnicoTest2;

    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        db = mock(UfficioTecnicoDBInterface.class);
        ufficioTecnicoTest = new UfficioTecnico();
        ufficioTecnicoTest.setId(1);
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("ufficio1@libero.it");
        ufficioTecnicoTest.setTel("092000000");

        ufficioTecnicoTest2 = new UfficioTecnico();
        ufficioTecnicoTest2.setId(2);
        ufficioTecnicoTest2.setNome("Uffico di riparazione proiettori");
        ufficioTecnicoTest2.setEmail("p.ufficio1@unisa.net");
        ufficioTecnicoTest2.setTel("092000001");
        when(db.insert(any(UfficioTecnico.class))).thenReturn(1);
        when(db.getById(1)).thenReturn(ufficioTecnicoTest);
        when(db.getById(2)).thenReturn(ufficioTecnicoTest2);

        when(db.getAll()).thenReturn(
                Arrays.asList(ufficioTecnicoTest, ufficioTecnicoTest2));
        manager = new UfficioTecnicoBL(db);
    }

    /**
     * Test insert 1.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsert1() throws Exception {
        ufficioTecnicoTest.setNome(null);
        manager.insertUfficioTecnico(ufficioTecnicoTest);
    }

    /**
     * Test insert 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert2() throws Exception {
        ufficioTecnicoTest.setNome("");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert3() throws Exception {
        ufficioTecnicoTest.setNome(".*0-Uffico di riparazione caloriferi");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 4.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testInsert4() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail(null);
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert5() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("bla");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 6.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert6() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel("000000000000000000000000000000000000000");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 7.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert7() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel("*********");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 8.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert8() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel("+34566798923");
        ufficioTecnicoTest.setUbicazione("");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

    /**
     * Test insert 9.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert9() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel("+34566798923");
        ufficioTecnicoTest.setUbicazione("Ci siamo Trasferiti a via XYZ");
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertTrue(res);
    }

    /**
     * Test insert 10.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert10() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel(null);
        ufficioTecnicoTest.setUbicazione(null);
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertTrue(res);
    }

    /**
     * Test insert 11.
     *
     * @throws Exception the exception
     */
    @Test
    public void testInsert11() throws Exception {
        ufficioTecnicoTest.setNome("Uffico di riparazione caloriferi");
        ufficioTecnicoTest.setEmail("hm@ufficio.it");
        ufficioTecnicoTest.setTel(null);
        String ubicazione = "Ci siamo Trasferiti a via XYZ";
        ubicazione += ubicazione += ubicazione += ubicazione;
        ufficioTecnicoTest.setUbicazione(ubicazione);
        boolean res = manager.insertUfficioTecnico(ufficioTecnicoTest);
        assertFalse(res);
    }

}
