package JUnit;
import java.util.*;
import Domini.Model.ConjuntDocuments;
import Domini.Model.Document;
import Domini.Model.Frase;
import Domini.Model.Pair;
import org.junit.Test;

import javax.print.Doc;

import static org.junit.Assert.*;

public class ConjuntDocumentsTest {

    //TEST CREADORA 1 i GETVECTOR
    @Test
    public void ConjuntDocuments1Test() {
        ConjuntDocuments cd = new ConjuntDocuments();
        Vector<Document> expected = new Vector<>();
        HashMap<Pair,Document> actual = cd.getMap();
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
        HashMap<Pair,Document> expected = new HashMap<>();
        expected.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
        expected.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
        expected.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
        expected.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
        expected.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(expected);
        HashMap<Pair,Document> actual = cdactual.getMap();

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
        HashMap<Pair,Document> v = new HashMap<>();
        v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
        v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
        v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
        v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
        v.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
        ConjuntDocuments cdactual = new ConjuntDocuments(v);
        Set<Frase> setact = cdactual.MapToSet();
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
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
            v.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            cdactual.baixaDocument(d0);

            HashMap<Pair,Document> vd = new HashMap<>();
            vd.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            vd.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            vd.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            vd.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
            vd.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
            ConjuntDocuments cdexpected = new ConjuntDocuments(vd);

            HashMap<Pair,Document> vexp = cdexpected.getMap();
            HashMap<Pair,Document> vact= cdactual.getMap();

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
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
            v.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            cdactual.afegirDocument(d5);

            HashMap<Pair,Document> vd = new HashMap<>();
            vd.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            vd.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            vd.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            vd.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
            vd.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
            ConjuntDocuments cdexpected = new ConjuntDocuments(vd);

            HashMap<Pair,Document> vexp = cdexpected.getMap();
            HashMap<Pair,Document> vact= cdactual.getMap();

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
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
            v.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
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
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
            v.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
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
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
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
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
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
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
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
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
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
