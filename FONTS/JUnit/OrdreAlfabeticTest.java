//JOEL FERNANDEZ

package FONTS.JUnit;
import java.util.*;
import FONTS.Domini.OrdreAlfabetic;
import FONTS.Domini.Document;
import FONTS.Domini.ConjuntDocuments;
import org.junit.Test;
import static org.junit.Assert.*;


public class OrdreAlfabeticTest {

    //Titols de documents random
    @Test
    public void OrdenarDocumentsRandomTest() {
        Document d1 = new Document("a1", "Hola", "a");
        Document d2 = new Document("a2", "La llegenda de Sant Jordi", "a");
        Document d3 = new Document("a3", "Adeu", "a");
        Document d4 = new Document ("a4", "Final", "a");

        Vector<Document> vd = new Vector<Document>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        OrdreAlfabetic o = new OrdreAlfabetic();
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

    //Vector de documents buit
    @Test
    public void OrdenarDocumentsBuitTest() {
        Vector<Document> vfi = new Vector<Document>();
        OrdreAlfabetic o = new OrdreAlfabetic();
        ConjuntDocuments cd = new ConjuntDocuments(vfi);
        ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
        Vector<Document> vfinal = cdfi.getVector();

        //CREO UN VECTOR BUIT PER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();

        assertEquals(vfinal, vfi2);
    }

    //Tots els documents tenen el mateix titol
    //AQUESTA LA FAIG JO DESPRÉS
    @Test
    public void OrdenarDocumentsTitolsIgualsTest() {
        Document d1 = new Document("Josep", "Hola", "a");
        Document d2 = new Document("Laura", "Hola", "a");
        Document d3 = new Document("Antoni", "Hola", "a");
        Document d4 = new Document ("Anna", "Hola", "a");

        Vector<Document> vd = new Vector<Document>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);
        OrdreAlfabetic o = new OrdreAlfabetic();
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
