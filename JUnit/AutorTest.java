//JOEL FERNANDEZ

package JUnit;
import Domini.Autor;
import Domini.Document;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.*;


public class AutorTest {

    //TEST CREADORA
    @Test
    public void AutorTest() {
        Autor a = new Autor("a");
        String nomAutor = a.getNom();
        assertEquals(nomAutor, "a");
    }

    //TEST GETNOM I SETNOM
    @Test
    public void getNomTest() {
        Autor a = new Autor ("a");
        a.setNom("b");
        String s = a.getNom();
        assertEquals(s, "b");
    }


}
