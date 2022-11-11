package JUnit;
import java.util.*;
import Domini.ConjuntDocuments;
import Domini.Document;
import Domini.Frase;
import org.junit.Test;

import javax.print.Doc;

import static org.junit.Assert.*;

public class ConjuntDocumentsTest {

    //TEST CREADORA 1 i GETVECTOR
    @Test
    public void ConjuntDocuments1Test() {
        ConjuntDocuments cd = new ConjuntDocuments();
        Vector<Document> expected = new Vector<>();
        Vector<Document> actual = cd.getVector();
        assertEquals(expected, actual);
    }

    //TEST CREADORA 2 i GETVECTOR
    @Test
    public void ConjuntDocuments2Test() {
        Document d0 = new Document("a", "Final", "aaaaaaa");
        Document d1 = new Document("a", "Hola", "aaaa");
        Document d2 = new Document("a", "La llegenda de Sant Jordi", "aaaaa");
        Document d3 = new Document("a", "Adeu", "aaaaa");
        Document d4 = new Document ("a", "Final", "aaaa");
        Vector<Document> expected = new Vector<>();
        expected.add(d0);
        expected.add(d1);
        expected.add(d2);
        expected.add(d3);
        expected.add(d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(expected);
        Vector<Document> actual = cdactual.getVector();

        assertEquals(expected, actual);
    }

    //TEST VECTOSET
    @Test
    public void VecToSetTest() {
        Document d0 = new Document("a", "Final", "aaaaaaa");
        Document d1 = new Document("a", "Hola", "aaaa");
        Document d2 = new Document("a", "La llegenda de Sant Jordi", "aaaaa");
        Document d3 = new Document("a", "Adeu", "aaaaa");
        Document d4 = new Document ("a", "Final", "aaaa");
        Vector<Document> v = new Vector<>();
        v.add(d0);
        v.add(d1);
        v.add(d2);
        v.add(d3);
        v.add(d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(v);
        Set<Frase> setact = cdactual.VecToSet();

        Frase f0 = new Frase("aaaaaaa", "Final", "a");
        Frase f1 = new Frase("aaaa", "Hola", "a");
        Frase f2 = new Frase("aaaaa", "La llegenda de Sant Jordi", "a");
        Frase f3 = new Frase("aaaaa", "Adeu", "a");
        Frase f4 = new Frase ("aaaa", "Final", "a");

        Set<Frase> setexp = new HashSet<>();
        setexp.add(f0);
        setexp.add(f1);
        setexp.add(f2);
        setexp.add(f3);
        setexp.add(f4);

        assertEquals(setexp, setact);

    }

    //TEST BAIXA DOCUMENT
    @Test
    public void baixaDocumentsTest() {
        Document d0 = new Document("a", "Final", "aaaaaaa");
        Document d1 = new Document("a", "Hola", "aaaa");
        Document d2 = new Document("a", "La llegenda de Sant Jordi", "aaaaa");
        Document d3 = new Document("a", "Adeu", "aaaaa");
        Document d4 = new Document ("a", "Final", "aaaa");
        Vector<Document> v = new Vector<>();
        v.add(d0);
        v.add(d1);
        v.add(d2);
        v.add(d3);
        v.add(d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(v);
        cdactual.baixaDocument(d0);

        Vector<Document> vd = new Vector<>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        ConjuntDocuments cdexpected = new ConjuntDocuments(vd);

        Vector<Document> vexp = cdexpected.getVector();
        Vector<Document> vact = cdactual.getVector();

        assertEquals(vexp, vact);
        //Excepcio
        cdactual.baixaDocument(d0);
    }

    //TEST ALTA DOCUMENT
    @Test
    public void afegirDocumentTest() {
        Document d0 = new Document("a", "Final", "aaaaaaa");
        Document d1 = new Document("a", "Hola", "aaaa");
        Document d2 = new Document("a", "La llegenda de Sant Jordi", "aaaaa");
        Document d3 = new Document("a", "Adeu", "aaaaa");
        Document d4 = new Document ("a", "Final", "aaaa");
        Document d5 = new Document("a", "Afegit", "Soc l'afegit");
        Vector<Document> v = new Vector<>();
        v.add(d0);
        v.add(d1);
        v.add(d2);
        v.add(d3);
        v.add(d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(v);
        cdactual.afegirDocument(d5);

        Vector<Document> vd = new Vector<>();
        vd.add(d0);
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        vd.add(d5);
        ConjuntDocuments cdexpected = new ConjuntDocuments(vd);

        Vector<Document> vexp = cdexpected.getVector();
        Vector<Document> vact = cdactual.getVector();

        assertEquals(vexp, vact);
        //Excepcio
        cdactual.afegirDocument(d5);
    }

    //TEST 1 RETORNA DOCUMENT (document esta al conjunt)
    @Test
    public void getDocumentTest1() {
        Document d0 = new Document("a", "Final", "aaaaaaa");
        Document d1 = new Document("a", "Hola", "aaaa");
        Document d2 = new Document("a", "La llegenda de Sant Jordi", "aaaaa");
        Document d3 = new Document("a", "Adeu", "aaaaa");
        Document d4 = new Document ("a", "Final", "aaaa");
        Vector<Document> v = new Vector<>();
        v.add(d0);
        v.add(d1);
        v.add(d2);
        v.add(d3);
        v.add(d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(v);

        Document actual = cdactual.getDocument("a", "La llegenda de Sant Jordi");

        assertEquals(d2, actual);

    }

    //TEST 2 RETORNA DOCUMENT (document no esta al conjunt, retorna null)
    @Test
    public void getDocumentTest2() {
        Document d0 = new Document("a", "Final", "aaaaaaa");
        Document d1 = new Document("a", "Hola", "aaaa");
        Document d2 = new Document("a", "La llegenda de Sant Jordi", "aaaaa");
        Document d3 = new Document("a", "Adeu", "aaaaa");
        Document d4 = new Document ("a", "Final", "aaaa");
        Vector<Document> v = new Vector<>();
        v.add(d0);
        v.add(d1);
        v.add(d2);
        v.add(d3);
        v.add(d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(v);

        //Excepcio
        Document actual = cdactual.getDocument("a", "La llegenda de Sant Joel");

        assertNull(actual);
    }


}
