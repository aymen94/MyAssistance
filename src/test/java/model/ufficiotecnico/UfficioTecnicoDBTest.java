package model.ufficiotecnico;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UfficioTecnicoDBTest {

    public static  void main(String[] args){

    }

    @Before
    public void setUp() {
        UfficioTecnico uff = new UfficioTecnico();
        uff.setId(1);
        uff.setNome("ufficio guasti");
        uff.setTel("1234567890");
        uff.setEmail("xxx@gmail.com");
        uff.setUbicazione("Caserta");
    }

    @After
    public void tearDown()  {
    }

    @Test
    public void insert() throws Exception {
        System.out.println("insert");
        setUp();
    }

    @Test
    public void getAll() {
    }
}