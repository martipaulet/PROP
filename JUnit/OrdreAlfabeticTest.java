//JOEL FERNANDEZ

package JUnit;
import java.util.*;
import Domini.OrdreAlfabetic;
import Domini.Document;
import Domini.OrdreCreacio;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.*;

import javax.print.Doc;


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

    //Vector de documents buit
    @Test
    public void OrdenarDocumentsBuitTest() {
        Vector<Document> vd = new Vector<Document>();
        Vector<Document> vfi = new Vector<Document>();
        OrdreAlfabetic o = new OrdreAlfabetic();
        vfi = o.OrdenarDocuments(vd);

        //CREO UN VECTOR BUIT PER COMPARAR AMB vfi
        Vector<Document> vfi2 = new Vector<Document>();

        assertEquals(vfi, vfi2);
    }

    //Tots els documents tenen el mateix titol


}
