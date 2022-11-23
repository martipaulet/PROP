package JUnit;
import java.util.*;

import Domini.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class OrdreModificacioTest {

    //Dates de modificació random
    @Test
    public void OrdenarDocumentsRandomTest() throws Exception {
        Document d1 = new Document("a1", "Hola", "a", new Date(), new Date(122, 4, 3));
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", "a", new Date(), new Date(122, 6, 12));
        Document d3 = new Document("a3", "Adeu", "a", new Date(), new Date(122, 7, 13));
        Document d4 = new Document ("a4", "Final", "a", new Date(), new Date(122, 9, 22));

        HashMap<Pair,Document> vd = new  HashMap<>();
        Pair p1 = new Pair(d1.getAutor(),d1.getTitol());
        vd.put(p1,d1);
        Pair p2 = new Pair(d2.getAutor(),d2.getTitol());
        vd.put(p2,d2);
        Pair p3 = new Pair(d3.getAutor(),d3.getTitol());
        vd.put(p3,d3);
        Pair p4 = new Pair(d4.getAutor(),d4.getTitol());
        vd.put(p4,d4);
        OrdreModificacio o = new OrdreModificacio();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        HashMap<Pair,Document> vfinal = cdfi.getMap();

        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        HashMap<Pair,Document> vfi2 = new  HashMap<>();
        vfi2.put(p4,d4);
        vfi2.put(p3,d3);
        vfi2.put(p2,d2);
        vfi2.put(p1,d1);

        assertEquals(vfinal, vfi2);
    }

    //Vector de document buit
    @Test
    public void OrdenarDocumentsBuitTest() throws Exception {
        HashMap<Pair,Document> vd = new HashMap<>();
        OrdreModificacio o = new OrdreModificacio();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        HashMap<Pair,Document> vfinal = cdfi.getMap();

        //CREO UN VECTOR BUIT PER COMPARAR AMB vfi
        HashMap<Pair,Document> vfi2 = new HashMap<>();

        assertEquals(vfinal, vfi2);
    }

    //Dates i titols iguals
    @Test
    public void OrdenarDocumentsDatesiTitolsIgualsTest() throws Exception {
        Document d1 = new Document("a1", "Hola", "a", new Date(), new Date(122, 4, 3));
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", "a", new Date(), new Date(122, 4, 3));
        Document d3 = new Document("a3", "Adeu", "a", new Date(), new Date(122, 4, 3));
        Document d4 = new Document ("a4", "Final", "a", new Date(), new Date(122, 4, 3));

        HashMap<Pair,Document> vd = new  HashMap<>();
        Pair p1 = new Pair(d1.getAutor(),d1.getTitol());
        vd.put(p1,d1);
        Pair p2 = new Pair(d2.getAutor(),d2.getTitol());
        vd.put(p2,d2);
        Pair p3 = new Pair(d3.getAutor(),d3.getTitol());
        vd.put(p3,d3);
        Pair p4 = new Pair(d4.getAutor(),d4.getTitol());
        vd.put(p4,d4);
        OrdreModificacio o = new OrdreModificacio();
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

    //Dates i titols iguals, autors diferents
    @Test
    public void OrdenarDocumentsDatesIgualsTest() throws Exception {
        Document d1 = new Document("Josep", "Hola", "a", new Date(),new Date(122, 4, 3));
        Document d2 = new Document("Laura", "Hola", "a", new Date(),new Date(122, 4, 3));
        Document d3 = new Document("Antoni", "Hola", "a", new Date(),new Date(122, 4, 3));
        Document d4 = new Document ("Anna", "Hola", "a", new Date(),new Date(122, 4, 3));

        HashMap<Pair,Document> vd = new  HashMap<>();
        Pair p1 = new Pair(d1.getAutor(),d1.getTitol());
        vd.put(p1,d1);
        Pair p2 = new Pair(d2.getAutor(),d2.getTitol());
        vd.put(p2,d2);
        Pair p3 = new Pair(d3.getAutor(),d3.getTitol());
        vd.put(p3,d3);
        Pair p4 = new Pair(d4.getAutor(),d4.getTitol());
        vd.put(p4,d4);
        OrdreModificacio o = new OrdreModificacio();
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
