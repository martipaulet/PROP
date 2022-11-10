package JUnit;
import java.util.*;
import Domini.Frase;
import org.junit.Test;
import static org.junit.Assert.*;

public class FraseTest {

    private Frase f;


    //TEST DE LA CREADORA I ELS DIFERENTS GETTERS (getFrase, getTitolDoc i getAutorDoc)
    @Test
    public void FraseTest() {
        Frase f = new Frase("un munt de paraules", "Un munt", "Joan");
        Vector<String> expected = new Vector<>();
        expected.add("un munt de paraules");
        expected.add("Un munt");
        expected.add("Joan");

        Vector<String> actuals = new Vector<>();
        actuals.add(f.getFrase());
        actuals.add(f.getTitolDoc());
        actuals.add(f.getAutorDoc());

        assertEquals(expected, actuals);
    }

    //TEST getParaules
    @Test
    public void getParaulesTest() {
        Frase f = new Frase("un munt de paraules", "Un munt", "Joan");
        String[] s = f.getParaules();
        String prova = "un munt de paraules";
        String[] p = prova.split("\\W+");

        assertEquals(s, p);
    }

    //TEST setParaules i getrecParaules
    @Test
    public void setParaulesTest() {
        Frase f = new Frase("un munt de paraules", "Un munt", "Joan");
        HashMap<String, Integer> prova = new HashMap<>();
        prova.put("un", 1);
        prova.put("munt", 1);
        prova.put("de", 1);
        prova.put("paraules", 1);

        assertEquals(prova, f.getrecParaules());

    }

    //TEST de conteQuery --> CAS 1: No conté la query
    @Test
    public void conteQueryTest1() {
        Frase f = new Frase("un munt de paraules", "Un munt", "Joan");
        Boolean b = f.conteQuery("un munt de coses");
        assertFalse(b);
    }

    //TEST de conteQuery --> CAS 2: Sí conté la query (1 paraula)
    @Test
    public void conteQueryTest2() {
        Frase f = new Frase("un munt de paraules", "Un munt", "Joan");
        Boolean b = f.conteQuery("munt");
        assertTrue(b);
    }

    //TEST de conteQuery --> CAS 3: Sí conté la query (+ 1 paraula)
    @Test
    public void conteQueryTest3() {
        Frase f = new Frase("un munt de paraules", "Un munt", "Joan");
        Boolean b = f.conteQuery("un munt de");
        assertTrue(b);
    }

    //TEST de conteQuery --> CAS 4: Conté la query però no les paraules (FALSE)
    @Test
    public void conteQueryTest4() {
        Frase f = new Frase("un munt de paraules", "Un munt", "Joan");
        Boolean b = f.conteQuery("un munt d");
        assertFalse(b);
    }



}
