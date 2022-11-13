package JUnit;
import Domini.Autor;
import Domini.Document;
import Domini.Frase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DocumentTest {
    private Document document;

    @Before
    public void setUp() throws Exception {
        Calendar calendari = new GregorianCalendar(2022, Calendar.JANUARY, 1);
        Date creacio = calendari.getTime();
        calendari.set(2022, Calendar.FEBRUARY, 2);
        Date modificacio = calendari.getTime();
        document = new Document("Karl Marx", "Communist Manifesto",
                "Hola, d'aqui: 2 minuts pegaré a la Sra. Laia! Aquesta hauria de ser la 3a frase però " +
                        "probablement serà la 4a, a veure com resol la l·l el programa. Adeu?", creacio, modificacio);
    }
    @Test
    public void testCreadora() {}

    @Test
    public void testGetAutor() {
        assertEquals(document.getAutor(), "Karl Marx");
    }

    @Test
    public void testSetAutor() {
        document.setAutor("Jordi Colomé");
        assertEquals(document.getAutor(), "Jordi Colomé");
    }

    @Test
    public void testGetTitol() {
        assertEquals(document.getTitol(), "Communist Manifesto");
    }

    @Test
    public void testGetContingut() {
        assertEquals(document.getContingut(), "Hola, d'aqui: 2 minuts pegaré a la Sra. Laia! Aquesta hauria de " +
                "ser la 3a frase però probablement serà la 4a, a veure com resol la l·l el programa. Adeu?");
    }

    @Test
    public void testSetContingut() {
        document.setContingut("exemple");
        assertEquals(document.getContingut(), "exemple");
        Calendar c = new GregorianCalendar(2022, Calendar.FEBRUARY, 2);
        assertNotEquals(document.getDataUltimaModificacio(), c.getTime());
    }

    @Test
    public void testGetDataCreacio() {
        Calendar c = new GregorianCalendar(2022, Calendar.JANUARY, 1);
        assertEquals(document.getDataCreacio(), c.getTime());
    }

    @Test
    public void testGetDataUltimaModificacio() {
        Calendar c = new GregorianCalendar(2022, Calendar.FEBRUARY, 2);
        assertEquals(document.getDataUltimaModificacio(), c.getTime());
    }

    @Test
    public void testSetIGetFrases() {
        document.imprimirFrases();
        Frase f1 = new Frase("Hola, d'aqui: 2 minuts pegaré a la Sra", "Karl Marx",
                "Communist Manifesto");
        Frase f2 = new Frase("Laia", "Karl Marx","Communist Manifesto");
        Frase f3 = new Frase("Aquesta hauria de ser la 3a frase però probablement serà la 4a, a veure com resol " +
                "la l·l el programa", "Karl Marx", "Communist Manifesto");
        Frase f4 = new Frase("Adeu", "Karl Marx","Communist Manifesto");
        ArrayList<Frase> frases = new ArrayList<>();
        frases.add(f1);
        frases.add(f2);
        frases.add(f3);
        frases.add(f4);
        assertEquals(document.getFrases(), frases);
    }
}
