
package model.utente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class UtenteBLTest.
 */
public class UtenteBLTest {

    /**
     * The utente DB.
     */
    private UtenteDBInterface utenteDB;

    /**
     * The manager.
     */
    private UtenteBL manager;

    /**
     * The utente test.
     */
    private CSU utenteTest;

    /**
     * The utente test 2.
     */
    private Gestore utenteTest2;

    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        utenteDB = mock(UtenteDBInterface.class);
        utenteTest = new CSU("marissi", PasswordHash.createHash("Chiave12345"),
                "m.rossi12@studenti.unisa.it", "Mario", "Rossi",
                Utente.SESSO_MASCHILE, LocalDate.parse("1997-05-02"));
        utenteTest2 = new Gestore("owner", PasswordHash.createHash("12345678"),
                "m.rossi12@unisa.it", "Mario", "Rossi", Utente.SESSO_MASCHILE,
                LocalDate.parse("1975-05-02"));
        when(utenteDB.getByUserName(utenteTest.getUserName()))
                .thenReturn(utenteTest);
        when(utenteDB.getByUserName(utenteTest2.getUserName()))
                .thenReturn(utenteTest2);
        manager = new UtenteBL(utenteDB);
    }

    /**
     * Test autenticazione CSU 1.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testAutenticazioneCSU1() throws Exception {
        final String username = null;
        final String password = "Chiave12345";

        manager.autenticazioneCSU(username, password);
    }

    /**
     * Test autenticazione CSU 2.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testAutenticazioneCSU2() throws Exception {
        final String username = "mar";
        final String password = "Chiave12345";

        manager.autenticazioneCSU(username, password);
    }

    /**
     * Test autenticazione CSU 3.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testAutenticazioneCSU3() throws Exception {
        final String username = "marissi";
        final String password = null;

        manager.autenticazioneCSU(username, password);
    }

    /**
     * Test autenticazione CSU 4.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAutenticazioneCSU4() throws Exception {
        final String username = "marissi";
        final String password = "chiave";

        final CSU utente = manager.autenticazioneCSU(username, password);
        assertNull(utente);
    }

    /**
     * Test autenticazione CSU 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAutenticazioneCSU5() throws Exception {
        final String username = "marissi";
        final String password = "Chiave12345";

        final CSU utente = manager.autenticazioneCSU(username, password);
        assertEquals(utenteTest, utente);
    }

    /**
     * Test autenticazione gestore 1.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testAutenticazioneGestore1() throws Exception {
        final String username = null;
        final String password = "Chiave12345";

        manager.autenticazioneGestore(username, password);
    }

    /**
     * Test autenticazione gestore 2.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testAutenticazioneGestore2() throws Exception {
        final String username = "mar";
        final String password = "Chiave12345";

        manager.autenticazioneGestore(username, password);
    }

    /**
     * Test autenticazione gestore 3.
     *
     * @throws Exception the exception
     */
    @Test(expected = Exception.class)
    public void testAutenticazioneGestore3() throws Exception {
        final String username = "owner";
        final String password = null;

        manager.autenticazioneGestore(username, password);
    }

    /**
     * Test autenticazione gestore 4.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAutenticazioneGestore4() throws Exception {
        final String username = "owner";
        final String password = "chiave";

        final Gestore utente = manager.autenticazioneGestore(username,
                password);
        assertNull(utente);
    }

    /**
     * Test autenticazione gestore 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAutenticazioneGestore5() throws Exception {
        final String username = "owner";
        final String password = "12345678";

        final Gestore utente = manager.autenticazioneGestore(username,
                password);
        assertEquals(utenteTest2, utente);
    }
//    public void testRegistrazione1() {
//        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "", "", "");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione2() {
//        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "9####", "", "");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione3() {
//        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "Mario", "", "");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione4() {
//        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "Mario", "Rossi].[]", "");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione5() {
//        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "Mario", "Rossi", "");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione6() {
//        boolean utente=UtenteBL.effettuaRegistrazione("", "", "", "Mario", "Rossi", "");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione7() {
//        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "", "", "Mario", "Rossi", "1985-30-03");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione8() {
//        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "", "", "Mario", "Rossi", "1985-30-03");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione9() {
//        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "Chaive1095", "", "Mario", "Rossi", "1985-30-03");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione10() {
//        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "Chiave1095", "mariorossi@gmail.com", "Mario", "Rossi", "14-06-1985");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione11() {
//        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "Chiave1095", "m.rossi12@studenti.unisa.it", "Mario", "Rossi", "14-06-1985");
//        assertFalse(utente);
//    }
//
//    public void testRegistrazione12() {
//        boolean utente=UtenteBL.effettuaRegistrazione("Marissi", "Chiave1095", "m.rossi12@studenti.unisa.it", "Mario", "Rossi", "14-06-1985");
//        udb.delete("m.rossi12@studenti.unisa.it");
//        assertTrue(utente);
//    }
//
//
//
///**
//     * Test di getByEmail della classe UtenteDB.
//     * @throws SQLException eccezione in mancato ottenimento query
//     *//*
//
//    @Test
//    public void testGetByEmail() throws SQLException {
//        System.out.println("getByEmail");
//
//        Utente u = udb.getByEmail("a.dauria@test.it");
//        assertNotNull(u);
//    }
//
//
///**
//     * Test di getAll della classe UtenteDB.
//     * @throws SQLException eccezione in mancato ottenimento utenti
//     *//*
//
//    @Test
//    public void testGetAll() throws SQLException {
//        System.out.println("getAll");
//
//        ArrayList<Utente> users = (ArrayList<Utente>) udb.getAll();
//        assertNotNull(users);
//    }
//
///**
//     * Test di delete della classe UtenteDB.
//     * @throws SQLException eccezione in mancata eliminzaione query
//     *//*
//
//    @Test
//    public void testDelete() throws SQLException {
//        System.out.println("delete");
//
//        int result = udb.delete("xxx@yy.it");
//        assertTrue(result > 0);
//    }
//}
}
