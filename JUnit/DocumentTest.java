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
        document = new Document("Karl Marx", "Communist manifesto", "ª");
    }

    @Test
    public void testGetAutor() {
        assertEquals(document.getAutor(), "Karl Marx");
    }
}
