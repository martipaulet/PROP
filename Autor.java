import java.util.*;

public class Autor {
    private Vector<String> nom;

    private Vector<Document> docs_autor;

    public Autor(Vector<String> n) {
        nom = n;
    }

    public void consultaTitols() {
        //retorna tots els títols de docs_autor ordenats alfabèticament
    }

    /*public void obteContingut(Titol t) {
        //busca en el vector de documents el Document amb títol t
        //getTítol de cada document
        //si titol = t, document.mostraContingut()
    }*/

    public void obteDocument(Titol t) {
        //busca en vector de documents el Document amb titol t
        //si titol = t, mostrem document (igual fa falta funcio)
    }
}
