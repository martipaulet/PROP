//JOEL FERNANDEZ

package JUnit;
import java.util.*;
import Domini.Model.OrdreAlfabetic;
import Domini.Model.Document;
import Domini.Model.ConjuntDocuments;
import Domini.Model.Pair;
import org.junit.Test;
import static org.junit.Assert.*;


public class OrdreAlfabeticTest {

    //Titols de documents random
    @Test
    public void OrdenarDocumentsRandomTest() throws Exception {
        Document d1 = new Document("a1", "Hola", "a");
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", "a");
        Document d3 = new Document("a3", "Adeu", "a");
        Document d4 = new Document ("a4", "Final", "a");

        HashMap<Pair,Document> vd = new  HashMap<>();
        Pair p1 = new Pair(d1.getAutor(),d1.getTitol());
        vd.put(p1,d1);
        Pair p2 = new Pair(d2.getAutor(),d2.getTitol());
        vd.put(p2,d2);
        Pair p3 = new Pair(d3.getAutor(),d3.getTitol());
        vd.put(p3,d3);
        Pair p4 = new Pair(d4.getAutor(),d4.getTitol());
        vd.put(p4,d4);
        OrdreAlfabetic o = new OrdreAlfabetic();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        HashMap<Pair,Document> vfinal = cdfi.getMap();


        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        HashMap<Pair,Document> vfi2 = new  HashMap<>();
        vfi2.put(p3,d3);
        vfi2.put(p4,d4);
        vfi2.put(p1,d1);
        vfi2.put(p2,d2);
        assertEquals(vfinal, vfi2);
    }

    //Vector de documents buit
    @Test
    public void OrdenarDocumentsBuitTest() throws Exception {
        HashMap<Pair,Document> vfi = new HashMap<>();
        OrdreAlfabetic o = new OrdreAlfabetic();
        ConjuntDocuments cd = new ConjuntDocuments(vfi);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        HashMap<Pair,Document> vfinal = cdfi.getMap();

        //CREO UN VECTOR BUIT PER COMPARAR AMB vfi
        HashMap<Pair,Document> vfi2 = new HashMap<>();

        assertEquals(vfinal, vfi2);
    }

    //Tots els documents tenen el mateix titol
    //AQUESTA LA FAIG JO DESPRÉS
    @Test
    public void OrdenarDocumentsTitolsIgualsTest() throws Exception {
        Document d1 = new Document("Josep", "Hola", "a");
        Document d2 = new Document("Laura", "Hola", "a");
        Document d3 = new Document("Antoni", "Hola", "a");
        Document d4 = new Document ("Anna", "Hola", "a");

        HashMap<Pair,Document> vd = new  HashMap<>();
        Pair p1 = new Pair(d1.getAutor(),d1.getTitol());
        vd.put(p1,d1);
        Pair p2 = new Pair(d2.getAutor(),d2.getTitol());
        vd.put(p2,d2);
        Pair p3 = new Pair(d3.getAutor(),d3.getTitol());
        vd.put(p3,d3);
        Pair p4 = new Pair(d4.getAutor(),d4.getTitol());
        vd.put(p4,d4);
        OrdreAlfabetic o = new OrdreAlfabetic();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        HashMap<Pair,Document> vfinal = cdfi.getMap();


        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        HashMap<Pair,Document> vfi2 = new  HashMap<>();
        vfi2.put(p4,d4);
        vfi2.put(p3,d3);
        vfi2.put(p1,d1);
        vfi2.put(p2,d2);
        assertEquals(vfinal, vfi2);
    }
}
