package model.utente;

import org.junit.Test;

import java.util.Date;

public class UtenteTest {
    @Test
    public static void main(String[] args){

        Date data = new Date();
        Date data1 = new Date();

        //Test CSU costruttore firmato
        Utente csu1 = new CSU(1,"Giupp","1234","giusepperossi@gmail.com","Giuseppe","Rossi",data,true);

        // Test costruttore vuoto e metodi set
        Utente csu = new CSU();
        csu.setId(1);
        csu.setNome("Pippo");
        csu.setCognome("Rossi");
        csu.setDataSospensione(data1);
        csu.setEmail("wao@gmail.com");
        csu.setIsGestore(true);
        csu.setUserName("Prossi");
        csu.setPassword("4321");

        //Test metodi get
        csu.getId();
        csu.getNome();
        csu.getCognome();
        csu.getDataSospensione();
        csu.getUserName();
        csu.getPassword();
        csu.getEmail();
        csu.getIsGestore();
        csu.toString();

        Date data2 = new Date();
        Date data3 = new Date();

        //Test Gestore costrutto firmato
        Utente ges = new Gestore(1, "alb90", "5678","albino@gmail.com","Albino","Bassi", data2, false );

        //test Gestore costrutto vuoto e metodi set
        Utente ges1 = new Gestore();
        ges1.setId(2);
        ges1.setUserName("SuperMark100");
        ges1.setPassword("1234567890");
        ges1.setEmail("@super100@gmail.com");
        ges1.setNome("Mario");
        ges1.setCognome("Bianchi");
        ges1.setDataSospensione(data3);
        ges1.setIsGestore(false);

        UtenteDB x = new UtenteDB();
    }
}