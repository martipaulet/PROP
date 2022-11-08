package JUnit;
import java.util.*;

import Domini.OrdreCreacio;
import Domini.OrdreModificacio;
import Domini.Document;
import org.junit.Test;
import static org.junit.Assert.*;


public class OrdreModificacioTest {

    //Dates de modificació random
    @Test
    public void OrdenarDocumentsRandomTest() {
        Document d1 = new Document("a1", "Hola", new Date(122, 4, 3), "a");
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", new Date(122, 6, 12), "a");
        Document d3 = new Document("a3", "Adeu", new Date(122, 7, 13), "a");
        Document d4 = new Document ("a4", "Final", new Date(122, 9, 22), "a");

        Vector<Document> vd = new Vector<Document>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        OrdreModificacio o = new OrdreModificacio();
        Vector<Document> vfi = new Vector<Document>();
        vfi = o.OrdenarDocuments(vd);

        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();
        vfi2.add(d4);
        vfi2.add(d3);
        vfi2.add(d2);
        vfi2.add(d1);

        assertEquals(vfi, vfi2);
    }

    //Vector de document buit
    @Test
    public void OrdenarDocumentsBuitTest() {
        Vector<Document> vd = new Vector<Document>();
        Vector<Document> vfi = new Vector<Document>();
        OrdreModificacio o = new OrdreModificacio();
        vfi = o.OrdenarDocuments(vd);

        //CREO UN VECTOR BUIT PER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();

        assertEquals(vfi, vfi2);
    }

    //Dates i titols iguals
    @Test
    public void OrdenarDocumentsDatesiTitolsIgualsTest() {
        Document d1 = new Document("a1", "Hola", new Date(122, 4, 3), "a");
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", new Date(122, 4, 3), "a");
        Document d3 = new Document("a3", "Adeu", new Date(122, 4, 3), "a");
        Document d4 = new Document ("a4", "Final", new Date(122, 4, 3), "a");

        Vector<Document> vd = new Vector<Document>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        OrdreModificacio o = new OrdreModificacio();
        Vector<Document> vfi = new Vector<Document>();
        vfi = o.OrdenarDocuments(vd);

        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();
        vfi2.add(d3);
        vfi2.add(d4);
        vfi2.add(d1);
        vfi2.add(d2);

        assertEquals(vfi, vfi2);
    }

    //Dates i titols iguals, autors diferents
    @Test
    public void OrdenarDocumentsDatesIgualsTest() {
        Document d1 = new Document("Josep", "Hola", new Date(122, 4, 3), "a");
        Document d2 = new Document("Laura", "Hola", new Date(122, 4, 3), "a");
        Document d3 = new Document("Antoni", "Hola", new Date(122, 4, 3), "a");
        Document d4 = new Document ("Anna", "Hola", new Date(122, 4, 3), "a");

        Vector<Document> vd = new Vector<Document>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        OrdreModificacio o = new OrdreModificacio();
        Vector<Document> vfi = new Vector<Document>();
        vfi = o.OrdenarDocuments(vd);

        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();
        vfi2.add(d4);
        vfi2.add(d3);
        vfi2.add(d1);
        vfi2.add(d2);

        assertEquals(vfi, vfi2);
    }
}
