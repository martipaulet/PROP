package JUnit;
import java.util.*;
import Domini.Model.ExpressionTree;
import Domini.Model.Document;
import Domini.Model.ConjuntDocuments;
import Domini.Model.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExpressionTreeTest {

    //TEST CALCULATE 1
    @Test
    public void calculateTest() {
        try {
            String query = "{p1 p2 p3} & (\"hola adeu\" | pep) & !joan";
            ExpressionTree et = new ExpressionTree(query);
            Document d0 = new Document("a", "Final", "p1 p3 p2 hola adeu pep");
            Document d1 = new Document("a", "Hola", "p1 p3 p2 hola adeu pep joan");
            Document d2 = new Document("a", "La llegenda de Sant Jordi", "p1 p3. p2 hola adeu pep");
            Document d3 = new Document("a", "Adeu", "pep p1 p2 p3");
            Document d4 = new Document ("a", "Final", "joan");
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
            v.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            ConjuntDocuments cd = et.calculate(cdactual);
            Integer size = cd.getMap().size();
            Integer exp = 2;
            assertEquals(size, exp);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //TEST CALCULATE 2 (El document 1 ara té hola adeuh, per tant no hauria de sortir al calculate,
    //                  i el document 4 te p12 i per tant no hauria de sortir tampoc al calculate)
    @Test
    public void calculateTest2() {
        try {
            String query = "{p1 p2 p3} & (\"hola adeu\" | pep) & !joan";
            ExpressionTree et = new ExpressionTree(query);
            Document d0 = new Document("a", "Final", "p1 p3 p2 hola adeuh");
            Document d1 = new Document("a", "Hola", "p1 p3 p2 hola adeu pep joan");
            Document d2 = new Document("a", "La llegenda de Sant Jordi", "p1 p3. p2 hola adeu pep");
            Document d3 = new Document("a", "Adeu", "pep p12 p2 p3");
            Document d4 = new Document ("a", "Final", "joan");
            HashMap<Pair,Document> v = new HashMap<>();
            v.put(new Pair(d0.getAutor(), d0.getTitol()), d0);
            v.put(new Pair(d1.getAutor(), d1.getTitol()), d1);
            v.put(new Pair(d2.getAutor(), d2.getTitol()), d2);
            v.put(new Pair(d3.getAutor(), d3.getTitol()), d3);
            v.put(new Pair(d4.getAutor(), d4.getTitol()), d4);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            ConjuntDocuments cd = et.calculate(cdactual);
            Integer size = cd.getMap().size();
            Integer exp = 0;
            assertEquals(size, exp);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
