package model.utente;

import org.junit.Before;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import java.sql.Date;

import static org.junit.Assert.*;

public class UtenteTest {


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception{
        Utente ut = new CSU();
        ut.setId(1);
        ut.setUserName("aaa");
        ut.setPassword("1234");
        ut.setEmail("abcdef@gmail.com");
        ut.setNome("mario");
        ut.setCognome("rossi");
        Date data = new Date(0);
        ut.setDataSospensione(data);
        ut.setIsGestore(false);
    }

    public static void main(String[] args){

    }

}