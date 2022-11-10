package JUnit;
import Domini.Autor;
import Domini.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DocumentTest {
    private Document document;

    @Before
    public void setUp() throws Exception {
        document = new Document("Karl Marx", "Communist Manifesto", "ª");
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
}
