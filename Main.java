import java.util.*;

public class Main {
    public static void main(String[] args) {
        Autor a = new Autor("George R. Martin");
        String c = "Aixo es: un exemple, de contingut. Es un exemple amb paraules repetides";
        Document d = new Document(a.getNom(), "Juego de tronos", c);
        d.imprimir();

        //DOC 1
        Autor a1 = new Autor ("Juan");
        Autor a2 = new Autor ("Pedro");
        Autor a3 = new Autor ("Mireia");
        Autor a4 = new Autor ("Angel");

        String c1 = "a";
        String c2 = "b";
        String c3 = "c";
        String c4 = "d";

        Document d1 = new Document(a1.getNom(), "Hola", c1);
        Document d2 = new Document(a2.getNom(), "Blau", c2);
        Document d3 = new Document(a3.getNom(), "Hola", c3);
        Document d4 = new Document(a4.getNom(), "Adeu", c4);

        Vector<Document> vd = new Vector<>();
        vd.add(d1);
        vd.add(d2);
        vd.add(d3);
        vd.add(d4);

        Vector<Document> vdReal = new Vector<>();
        OrdreAlfabetic o = new OrdreAlfabetic();
        vdReal = o.OrdenarDocuments(vd);
        for (int i = 0; i < vdReal.size(); ++i) {
            Document doc = vdReal.elementAt(i);
            String s =doc.getAutor();
            System.out.println(s);
        }
        System.out.println('\n');

    }
}
