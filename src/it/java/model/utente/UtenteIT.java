
package model.utente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pool.ConnectionManager;

/**
 * The Class UtenteBLTest.
 */
public class UtenteIT {

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

    @BeforeClass
    public static void setUpClass() throws IOException, SQLException {
        ConnectionManager.getInstance().initializePool("databases.xml", "Test");
        final Connection conn = ConnectionManager.getInstance().getConnection();

        ConnectionManager.getInstance().freeConnection(conn);
        
    }
    
    /**
     * Tear down class.
     *
     * @throws SQLException the SQL exception
     */
    @AfterClass
    public static void tearDownClass() throws SQLException {
        ConnectionManager.getInstance().destroyPool();
    }
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        final Connection conn = ConnectionManager.getInstance().getConnection();
        utenteDB = new UtenteDB();
        utenteTest = new CSU("marissi", PasswordHash.createHash("Chiave12345"),
                "m.rossi12@studenti.unisa.it", "Mario", "Rossi",
                Utente.SESSO_MASCHILE, LocalDate.parse("1997-05-02"));
        utenteTest.setId(1);
        utenteTest2 = new Gestore("owner", PasswordHash.createHash("12345678"),
                "m.rossi12@unisa.it", "Mario", "Rossi", Utente.SESSO_MASCHILE,
                LocalDate.parse("1975-05-02"));
        utenteTest2.setId(2);

        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
        conn.prepareStatement("TRUNCATE TABLE utente")
        .executeUpdate();
        utenteDB.insert(utenteTest);
        utenteDB.insert(utenteTest2);

        manager = new UtenteBL(utenteDB);
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();

        ConnectionManager.getInstance().freeConnection(conn);

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

        final Utente utente = manager.autenticazioneCSU(username, password);
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

        final Utente utente = manager.autenticazioneCSU(username, password);
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

    /**
     * Test registrazione 1.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione1() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager
                .effettuaRegistrazione("", "", "", "", "", "", sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione2() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager
                .effettuaRegistrazione("", "", "", "9####", "", "", sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione3() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager
                .effettuaRegistrazione("", "", "", "Mario", "", "", sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 4.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione4() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager.effettuaRegistrazione("",
                "",
                "",
                "Mario",
                "Rossi].[]",
                "",
                sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione5() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager
                .effettuaRegistrazione("", "", "", "Mario", "Rossi", "", sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 6.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione6() throws Exception {
        final int sesso = Utente.SESSO_FEMMINILE;
        final boolean utente = manager.effettuaRegistrazione("Blablablablabla",
                "",
                "",
                "Mario",
                "Rossi",
                "",
                sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 7.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione7() throws Exception {
        final int sesso = Utente.SESSO_MASCHILE;
        final boolean utente = manager.effettuaRegistrazione("Marissi",
                "",
                "",
                "Mario",
                "Rossi",
                "14-06-1985",
                sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 8.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione8() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager.effettuaRegistrazione("Marissi",
                "",
                "",
                "Mario",
                "Rossi",
                "14-06-1985",
                sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 9.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione9() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager.effettuaRegistrazione("Marissi",
                "Chiave1095",
                "",
                "Mario",
                "Rossi",
                "14-06-1985",
                sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 10.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione10() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager.effettuaRegistrazione("Marissi",
                "Chiave1095",
                "mariorossi@gmail.com",
                "Mario",
                "Rossi",
                "14-06-1985",
                sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 11.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione11() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        final boolean utente = manager.effettuaRegistrazione("Marissi",
                "Chiave1095",
                "m.rossi12@studenti.unisa.it",
                "Mario",
                "Rossi",
                "1985-30-03",
                sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 12.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione12() throws Exception {
        final int sesso = -1;
        final boolean utente = manager.effettuaRegistrazione("Marissi",
                "Chiave1095",
                "m.rossi12@studenti.unisa.it",
                "Mario",
                "Rossi",
                "14-06-1985",
                sesso);
        assertFalse(utente);
    }

    /**
     * Test registrazione 13.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRegistrazione13() throws Exception {
        final int sesso = Utente.SESSO_ALTRO;
        utenteDB.delete("m.rossi12@studenti.unisa.it");
        final boolean utente = manager.effettuaRegistrazione("Marissi",
                "Chiave1095",
                "m.rossi12@studenti.unisa.it",
                "Mario",
                "Rossi",
                "14-06-1985",
                sesso);
        assertTrue(utente);
    }

    @Test
    public void testSospendiUtente1() throws Exception {
        utenteTest.setId(0);
        utenteTest.setDataSospensione(null);
        final Boolean res = manager.sospendiUtente(utenteTest);
        assertFalse(res);
    }

    @Test
    public void testSospendiUtente2() throws Exception {
        utenteTest.setId(1);
        utenteTest.setDataSospensione(LocalDate.parse("2019-01-21"));
        final Boolean res = manager.sospendiUtente(utenteTest);
        assertTrue(res);
    }
}
