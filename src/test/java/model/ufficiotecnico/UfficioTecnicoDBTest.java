package model.ufficiotecnico;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pool.Database;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UfficioTecnicoDBTest {

    @Before public void setUp() throws Exception {
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void insert() throws SQLException {
        UfficioTecnico uff = new UfficioTecnico();
        uff.setId(1);
        uff.setNome("testNome");
        uff.setTel("1234567890");
        uff.setEmail("test123@gmail.com");
        uff.setUbicazione("Roma");

        Database.getConnection();
    }

    @Test public void getAll() {
    }

    @Test public void deleteById() {
    }

    @Test public void getById() {
    }
}