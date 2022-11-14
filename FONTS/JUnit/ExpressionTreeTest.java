package JUnit;
import java.util.*;
import Domini.ExpressionTree;
import Domini.Document;
import Domini.ConjuntDocuments;
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
            Vector<Document> v = new Vector<>();
            v.add(d0);
            v.add(d1);
            v.add(d2);
            v.add(d3);
            v.add(d4);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            ConjuntDocuments cd = et.calculate(cdactual);
            Integer size = cd.getVector().size();
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
            Vector<Document> v = new Vector<>();
            v.add(d0);
            v.add(d1);
            v.add(d2);
            v.add(d3);
            v.add(d4);
            ConjuntDocuments cdactual = new ConjuntDocuments(v);
            ConjuntDocuments cd = et.calculate(cdactual);
            Integer size = cd.getVector().size();
            Integer exp = 0;
            assertEquals(size, exp);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
