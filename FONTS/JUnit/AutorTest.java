//JOEL FERNANDEZ I ALBERT CARDONA

package FONTS.JUnit;

import FONTS.Domini.Autor;
import FONTS.Domini.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AutorTest {

        private Autor a;

    @Before
    public  void before(){
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

    //TEST AFEGIR DOCUMENT EXCEPCIO
    @Test
    public void afegirDocumentTestExc() {
        Document d3 = new Document("b", "Adeu", "aaaaa");
        a.afegirDocument(d3);
    }

    //TEST ELIMINAR DOCUMENT EXCEPCIO
    @Test
    public void eliminaDocumentTestExc() {
        Document d3 = new Document("a", "Adeu", "aaaaa");
        a.afegirDocument(d3);
        Document d4 = new Document ("a", "Final", "aaaa");
        a.eliminaDocument(d4);
    }


    @Test
    public void tePrefixTest(){
        a = new Autor("Marti");
        boolean b = a.tePrefix("Mar");
        assertTrue(b);
        b = a.tePrefix("Al");
        assertFalse(b);
        b = a.tePrefix("");
        assertTrue(b);
        b = a.tePrefix("Martiii");
        assertFalse(b);
    }

}
