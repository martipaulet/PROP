//JOEL FERNANDEZ

package JUnit;
import java.util.*;
import Domini.Autor;
import Domini.Document;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AutorTest {

        private  Autor a;

    @BeforeClass
    public void beforeClass(){
        a = new Autor("a");
    }

    //TEST CREADORA
    @Test
    public void AutorTest() {
        String nomAutor = a.getNom();
        assertEquals(nomAutor, "a");
    }

    //TEST GETNOM I SETNOM
    @Test
    public void getNom_setNomTest() {
        a.setNom("b");
        String s = a.getNom();
        assertEquals(s, "b");
    }

    //TEST CONSULTA TITOLS I AFEGIR DOCUMENT I ELIMINAR DOCUMENT
    @Test
    public void consultaTitolsBuitTest() {
        ArrayList<String> aTitols = a.consultaTitols();
        ArrayList<String> solucio = new ArrayList<>();
        assertEquals(aTitols,solucio);
    }
    //FALTA EXEC I LA EXCEPCIO DE QUE EL DOCUMENT NO SIGUI DE L'AUTOR
    @Test
    public void consultaTitolsRandomTest() {
        Document d0 = new Document("a", "Final", "aaaaaaa");
        Document d1 = new Document("a", "Hola", "aaaa");
        Document d2 = new Document("a", "La llegenda de Sant Jordi", "aaaaa");
        Document d3 = new Document("a", "Adeu", "aaaaa");
        Document d4 = new Document ("a", "Final", "aaaa");

        a.afegirDocument(d0);
        a.afegirDocument(d1);
        a.afegirDocument(d2);
        a.afegirDocument(d3);
        a.afegirDocument(d4);

        ArrayList<String> aTitols = a.consultaTitols();

        ArrayList<String> solucio = new ArrayList<>();
        solucio.add("Adeu");
        solucio.add("Final");
        solucio.add("Final");
        solucio.add("Hola");
        solucio.add("La llegenda de Sant Jordi");
        assertEquals(aTitols,solucio);

        a.eliminaDocument(d0);
        solucio.remove("Final");
        aTitols = a.consultaTitols();
        assertEquals(aTitols,solucio);
    }

    //TEST CONTE TITOL
    @Test
    public void conteTitolTest() {
        Document d3 = new Document("a", "Adeu", "aaaaa");
        a.afegirDocument(d3);
        boolean b = a.conteTitol("Adeu");
        assertTrue(b);
        b = a.conteTitol("AAAA");
        assertFalse(b);
    }


}
