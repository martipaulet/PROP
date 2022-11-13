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
        Document d0 = new Document("a", "Final", "Frase1. Frase2");
        Document d1 = new Document("a", "Hola", "Frase3");
        Document d2 = new Document("a", "La llegenda de Sant Jordi", "Frase4. Frase5");
        Document d3 = new Document("a", "Adeu", "Frase6. Frase7. Frase8");
        Document d4 = new Document ("a", "Final", "Frase9. Frase10");
        Vector<Document> v = new Vector<>();
        v.add(d0);
        v.add(d1);
        v.add(d2);
        v.add(d3);
        v.add(d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(v);
        Set<Frase> setact = cdactual.VecToSet();
        //HI HA 10 FRASES
        Integer act = setact.size();
        Integer exp = 10;

        assertEquals(exp, act);
    }

    //TEST BAIXA DOCUMENT
    @Test
    public void baixaDocumentsTest() {
        try {
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
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //TEST ALTA DOCUMENT
    @Test
    public void afegirDocumentTest() {
        try {
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
        catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    //TEST 1 RETORNA DOCUMENT (document esta al conjunt)
    @Test
    public void getDocumentTest1() {
        try {
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
        catch (Exception e) {
            System.out.println(e.toString());
        }


    }

    //TEST 2 RETORNA DOCUMENT (document no esta al conjunt, retorna null)
    @Test
    public void getDocumentTest2() {
        try {
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
        catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    //TEST CÀLCUL COPS PARAULES
    @Test
    public void CalculCopsParaulesTest() {
        try {
            Document d0 = new Document("a", "Final", "1 vez hola");
            Document d1 = new Document("a", "Hola", "hola. hola hola 2 veces");
            Document d2 = new Document("a", "La llegenda de Sant Jordi", "no tinc la paraula");
            Document d3 = new Document("a", "Joel", "hola, la tinc, el resultat ha de ser 3");
            Vector<Document> v = new Vector<>();
            v.add(d0);
            v.add(d1);
            v.add(d2);
            v.add(d3);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            HashMap<String, Integer> actual = cdactual.CalculCopsParaules(d0);
            Integer act = actual.get("hola");
            Integer exp = 3;

            assertEquals(exp, act);
            //test2

            actual = cdactual.CalculCopsParaules(d0);
            act = actual.get("albert");

            assertNull(act);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //TEST CÀLCUL TFIDF
    @Test
    public void CalculTfIdfTest() {
        try {
            Document d0 = new Document("a", "Final", "1 vez hola");
            Document d1 = new Document("a", "Hola", "hola. hola hola 2 veces");
            Document d2 = new Document("a", "La llegenda de Sant Jordi", "no tinc la paraula");
            Document d3 = new Document("a", "Joel", "hola, la tinc, 1 vez");
            Vector<Document> v = new Vector<>();
            v.add(d0);
            v.add(d1);
            v.add(d2);
            v.add(d3);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            HashMap<Document, Double> actual = cdactual.CalculTfIdf(d0);

            //Vez no la conta ja que és una stop word
            double v1 = 3.0 * Math.log(4.0/3.0);
            double v0 = actual.get(d1);
            assertEquals(v0, v1,0.0001);
            v1 = Math.log(8.0/3.0);
            v0 = actual.get(d3);
            assertEquals(v0,v1,0.0001);
            v0 = actual.get(d2);
            assertEquals(v0,0.0,0.0001);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    //TEST CÀLCUL TF
    @Test
    public void CalculTfTest() {
        try {
            Document d0 = new Document("a", "Final", "1 vez hola");
            Document d1 = new Document("a", "Hola", "hola. hola hola 2 veces");
            Document d2 = new Document("a", "La llegenda de Sant Jordi", "no tinc la paraula");
            Document d3 = new Document("a", "Joel", "hola, la tinc, 1 vez");
            Vector<Document> v = new Vector<>();
            v.add(d0);
            v.add(d1);
            v.add(d2);
            v.add(d3);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            HashMap<Document, Double> actual = cdactual.CalculTf(d0);

            //Vez no la conta ja que és una stop word
            double v0 = actual.get(d1);
            assertEquals(v0, 3.0 ,0.0001);
            v0 = actual.get(d3);
            assertEquals(v0,2.0 ,0.0001);
            v0 = actual.get(d2);
            assertEquals(v0,0.0,0.0001);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    //TEST OBTEFRASESCONTENEN
    @Test
    public void obteFrasesContenenTest() {
        try {
            Document d0 = new Document("a", "Final", "Frase que contiene hola");
            Document d1 = new Document("a", "Hola", "No la contiene. Si contiene hola");
            Document d2 = new Document("a", "La llegenda de Sant Jordi", "no tinc la paraula");
            Document d3 = new Document("a", "Joel", "hola. la tinc. el resultat ha de ser hola");
            Vector<Document> v = new Vector<>();
            v.add(d0);
            v.add(d1);
            v.add(d2);
            v.add(d3);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            //HAY 4 frases que contienen hola
            Set<Frase> sact = cdactual.obteFrasesContenen("hola");
            Integer size = sact.size();
            Integer sexp = 4;

            assertEquals(sexp, size);

            //test2
            sact = cdactual.obteFrasesContenen("albert");
            size = sact.size();
            sexp = 0;

            assertEquals(sexp, size);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}
