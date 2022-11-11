package JUnit;
import Domini.Autor;
import Domini.Document;
import org.junit.Before;
import org.junit.Test;

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
        document = new Document("Karl Marx", "Communist Manifesto", "ª", creacio, modificacio);
    }

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
        assertEquals(document.getContingut(), "ª");
    }

    @Test
    public void testSetContingut() {
        document.actualitzaDocument("exemple");
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
}
