package model.ufficio_tecnico;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UfficioTecnicoTest {

    @Test public void testEquals(){
            UfficioTecnico x = new UfficioTecnico();
            UfficioTecnico y = new UfficioTecnico();
            assertEquals(x,x);
            assertNotEquals(x, null);
            assertNotEquals(x," ");
            x.setId(0);
            y.setId(1);
            assertEquals(x,y);
            assertEquals(y,x);
            x.setId(2);
            assertNotEquals(x,y);
            x.setNome("nome");
            x.setId(1);
            assertNotEquals(x,y);
            y.setNome("nome");
            x.setUbicazione("ubicazione");
            assertNotEquals(x,y);
            y.setUbicazione("ubicazione");
            x.setEmail("email");
            assertNotEquals(x,y);
            y.setEmail("email");
            x.setTel("1234567890");
            assertNotEquals(x,y);
            y.setTel("1234567890");
            assertEquals(x,y);

    }
}
