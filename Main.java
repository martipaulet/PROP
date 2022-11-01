import java.util.*;

public class Main {
    public static void main(String[] args) {
        Autor a = new Autor("George R. Martin");
        String c = "Aixo es: un exemple, de contingut. Es un exemple amb paraules repetides";
        Document d = new Document(a.getNom(), "Juego de tronos", c);
        d.imprimir();

        String c1 = "a";
        String c2 = "b";
        String c3 = "c";
        String c4 = "d";

        Document d1 = new Document(a.getNom(), "Hola", c1);
        Document d2 = new Document(a.getNom(), "Blau", c2);
        //Document d3 = new Document(a.getNom(), "Hola", c3);
        Document d4 = new Document(a.getNom(), "Adeu", c4);

        a.afegirDocument(d1);
        a.afegirDocument(d2);
        a.afegirDocument(d4);
        a.afegirDocument(d);

        /*Document res = a.obteDocument("Blau");
        if (res == d2) System.out.println("Funciona");
        Document res2 = a.obteDocument("Adeu");
        if (res2 != d2) System.out.println("funciona"); */

        //DOC 1
        Autor a1 = new Autor ("Juan");
        Autor a2 = new Autor ("Pedro");
        Autor a3 = new Autor ("Mireia");
        Autor a4 = new Autor ("Angel");

        Boolean b1 = a1.tePrefix("Ju");
        Boolean b2 = a2.tePrefix("");
        Boolean b3 = a3.tePrefix("Mireiax");
        Boolean b4 = a4.tePrefix("Aj");

        if (b1) System.out.println("b1 true");
        if (b2) System.out.println("b2 true");
        if (b3) System.out.println("b3 true");
        if (b4) System.out.println("b4 true");

       /* Vector<Document> vd = new Vector<>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);

        Vector<Document> vdReal = new Vector<>();
        OrdreCreacio o = new OrdreCreacio();
        vdReal = o.OrdenarDocuments(vd);
        for (int i = 0; i < vdReal.size(); ++i) {
            Document doc = vdReal.elementAt(i);
            String s =doc.getAutor();
            System.out.println(s);
        }
        System.out.println('\n');*/

    }
}
