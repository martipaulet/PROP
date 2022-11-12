package JUnit;
import java.util.*;

import Domini.ConjuntDocuments;
import Domini.OrdreCreacio;
import Domini.Document;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrdreCreacioTest {

    //Dates de creació random
    @Test
    public void OrdenarDocumentsRandomTest() {
        Document d1 = new Document("a1", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", "a", new Date(112, 6, 4), new Date());
        Document d3 = new Document("a3", "Adeu", "a", new Date(112, 2, 1), new Date());
        Document d4 = new Document ("a4", "Final", "a", new Date(112, 9, 22), new Date());

        Vector<Document> vd = new Vector<Document>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        OrdreCreacio o = new OrdreCreacio();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        Vector<Document> vfinal = cdfi.getVector();

        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();
        vfi2.add(d4);
        vfi2.add(d2);
        vfi2.add(d1);
        vfi2.add(d3);

        assertEquals(vfinal, vfi2);
    }

    //Vector de document buit
    @Test
    public void OrdenarDocumentsBuitTest() {
        Vector<Document> vd = new Vector<Document>();
        OrdreCreacio o = new OrdreCreacio();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        Vector<Document> vfinal = cdfi.getVector();

        //CREO UN VECTOR BUIT PER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();

        assertEquals(vfinal, vfi2);
    }

    //Dates iguals, títols diferents
    @Test
    public void OrdenarDocumentsDatesIgualsTest() {
        Document d1 = new Document("a1", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", "a", new Date(112, 5, 3), new Date());
        Document d3 = new Document("a3", "Adeu", "a", new Date(112, 5, 3), new Date());
        Document d4 = new Document ("a4", "Final", "a", new Date(112, 5, 3), new Date());

        Vector<Document> vd = new Vector<Document>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        OrdreCreacio o = new OrdreCreacio();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        Vector<Document> vfinal = cdfi.getVector();

        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();
        vfi2.add(d3);
        vfi2.add(d4);
        vfi2.add(d1);
        vfi2.add(d2);

        assertEquals(vfinal, vfi2);
    }

    //Dates iguals, títols iguals i autors diferents
    @Test
    public void OrdenarDocumentsDatesiTitolsIgualsTest() {
        Document d1 = new Document("Josep", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d2 = new Document("Laura", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d3 = new Document("Antoni", "Hola", "a", new Date(112, 5, 3), new Date());
        Document d4 = new Document ("Anna", "Hola", "a", new Date(112, 5, 3), new Date());

        Vector<Document> vd = new Vector<Document>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        OrdreCreacio o = new OrdreCreacio();
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        Vector<Document> vfinal = cdfi.getVector();

        //CREO UN VECTOR I AFEGEIXO ELS DOCUMENTS EN L'ORDRE ALFABÈTIC PER PODER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();
        vfi2.add(d4);
        vfi2.add(d3);
        vfi2.add(d1);
        vfi2.add(d2);

        assertEquals(vfinal, vfi2);
    }
}
