package JUnit;
import java.util.*;

import Domini.ConjuntDocuments;
import Domini.OrdreCreacio;
import Domini.Document;
import Domini.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrdreCreacioTest {

    //Dates de creació random
    @Test
    public void OrdenarDocumentsRandomTest() throws Exception {
        Document d1 = new Document("a1", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", "a", new Date(112, 6, 4), new Date());
        Document d3 = new Document("a3", "Adeu", "a", new Date(112, 2, 1), new Date());
        Document d4 = new Document ("a4", "Final", "a", new Date(112, 9, 22), new Date());

        HashMap<Pair,Document> vd = new  HashMap<>();
        Pair p1 = new Pair(d1.getAutor(),d1.getTitol());
        vd.put(p1,d1);
        Pair p2 = new Pair(d2.getAutor(),d2.getTitol());
        vd.put(p2,d2);
        Pair p3 = new Pair(d3.getAutor(),d3.getTitol());
        vd.put(p3,d3);
        Pair p4 = new Pair(d4.getAutor(),d4.getTitol());
        vd.put(p4,d4);
        OrdreCreacio o = new OrdreCreacio();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        HashMap<Pair,Document> vfinal = cdfi.getMap();

        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        HashMap<Pair,Document> vfi2 = new  HashMap<>();
        vfi2.put(p4,d4);
        vfi2.put(p2,d2);
        vfi2.put(p1,d1);
        vfi2.put(p3,d3);

        assertEquals(vfinal, vfi2);
    }

    //Vector de document buit
    @Test
    public void OrdenarDocumentsBuitTest() throws Exception {
        HashMap<Pair,Document> vd = new HashMap<>();
        OrdreCreacio o = new OrdreCreacio();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        HashMap<Pair,Document> vfinal = cdfi.getMap();

        //CREO UN VECTOR BUIT PER COMPARAR AMB vfi
        HashMap<Pair,Document> vfi2 = new HashMap<>();

        assertEquals(vfinal, vfi2);
    }

    //Dates iguals, títols diferents
    @Test
    public void OrdenarDocumentsDatesIgualsTest() throws Exception {
        Document d1 = new Document("a1", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", "a", new Date(112, 5, 3), new Date());
        Document d3 = new Document("a3", "Adeu", "a", new Date(112, 5, 3), new Date());
        Document d4 = new Document ("a4", "Final", "a", new Date(112, 5, 3), new Date());

        HashMap<Pair,Document> vd = new  HashMap<>();
        Pair p1 = new Pair(d1.getAutor(),d1.getTitol());
        vd.put(p1,d1);
        Pair p2 = new Pair(d2.getAutor(),d2.getTitol());
        vd.put(p2,d2);
        Pair p3 = new Pair(d3.getAutor(),d3.getTitol());
        vd.put(p3,d3);
        Pair p4 = new Pair(d4.getAutor(),d4.getTitol());
        vd.put(p4,d4);
        OrdreCreacio o = new OrdreCreacio();
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

    //Dates iguals, títols iguals i autors diferents
    @Test
    public void OrdenarDocumentsDatesiTitolsIgualsTest() throws Exception {
        Document d1 = new Document("Josep", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d2 = new Document("Laura", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d3 = new Document("Antoni", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d4 = new Document ("Anna", "Hola", "a", new Date(112, 5, 3), new Date());

        HashMap<Pair,Document> vd = new  HashMap<>();
        Pair p1 = new Pair(d1.getAutor(),d1.getTitol());
        vd.put(p1,d1);
        Pair p2 = new Pair(d2.getAutor(),d2.getTitol());
        vd.put(p2,d2);
        Pair p3 = new Pair(d3.getAutor(),d3.getTitol());
        vd.put(p3,d3);
        Pair p4 = new Pair(d4.getAutor(),d4.getTitol());
        vd.put(p4,d4);
        OrdreCreacio o = new OrdreCreacio();
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
