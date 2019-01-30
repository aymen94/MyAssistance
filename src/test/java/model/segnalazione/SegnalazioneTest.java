package model.segnalazione;

import model.ufficio_tecnico.UfficioTecnico;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SegnalazioneTest {

    @Test public void equals(){
        Segnalazione sgn1 = new Segnalazione();
        Segnalazione sgn2 = new Segnalazione();
        LocalDate data = LocalDate.now();
        UfficioTecnico uff = new UfficioTecnico();
        Tipologia tip = new Tipologia();

        assertEquals(sgn1,sgn1);
        assertNotEquals(sgn1, null);
        assertNotEquals(sgn1," ");
        sgn1.setCod(0);
        sgn2.setCod(1);
        assertEquals(sgn1,sgn2);
        assertEquals(sgn2,sgn1);
        sgn1.setCod(2);
        assertNotEquals(sgn1,sgn2);
        sgn1.setCod(1);
        sgn1.setStato(Segnalazione.STATO_RISOLTO);
        assertNotEquals(sgn1,sgn2);
        sgn2.setStato(Segnalazione.STATO_RISOLTO);
        sgn1.setDataAssegnazione(data);
        assertNotEquals(sgn1,sgn2);
        sgn2.setDataAssegnazione(data);
        sgn1.setDataRifiuto(data);
        assertNotEquals(sgn1,sgn2);
        sgn2.setDataRifiuto(data);
        sgn1.setDataRisoluzione(data);
        assertNotEquals(sgn1,sgn2);
        sgn2.setDataRisoluzione(data);
        sgn1.setDataSegnalazione(data);
        assertNotEquals(sgn1,sgn2);
        sgn2.setDataSegnalazione(data);
        sgn1.setDescrizione("descrizione");
        assertNotEquals(sgn1,sgn2);
        sgn2.setDescrizione("descrizione");
        sgn1.setMotivazioneRifiuto("motivazione");
        assertNotEquals(sgn1,sgn2);
        sgn2.setMotivazioneRifiuto("motivazione");
        sgn1.setTecnico(uff);
        assertNotEquals(sgn1,sgn2);
        sgn2.setTecnico(uff);
        sgn1.setTipologia(tip);
        assertNotEquals(uff,tip);
        sgn2.setTipologia(tip);
        sgn1.setTitolo("titolo");
        assertNotEquals(uff,tip);
        sgn2.setTitolo("titolo");
        assertEquals(sgn1,sgn2);
    }


}
