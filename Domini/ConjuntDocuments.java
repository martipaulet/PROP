package Domini;
import java.util.*;

public class ConjuntDocuments {
    private Vector<Document> DocumentsBD;

    public void importarDocuments () {
        //fa crides a la creadora de document
    }

    public void exportarDocuments() {
        //exporta els documents seleccionats amb seleccionarDocument
    }

    public void baixaDocuments(ConjuntDocuments cd) {
        //elimina els documents seleccionats amb seleccionarDocument
    }

    public void baixaDocument(Document d) {
        //elimina un documents seleccionat amb seleccionarDocument
        DocumentsBD.remove(d);
    }

    public void modificarDocuments() {
        //fa trucada a modificaDocument per cada document seleccionat amb selecccionarDocument
    }

    public void afegirDocument(Document d) {
        //afegir document al conjunt
        DocumentsBD.add(d);
    }


    //pre: el document (autor,titol) existeix
    //retorna el document (autor,titol)
    public Document getDocument(String autor, String titol) {
        Boolean trobat = false;
        Document d = new Document(null, null, null);
        for (int i = 0; i < DocumentsBD.size() && !trobat; ++i) {
            d = DocumentsBD.elementAt(i);
            if (d.getAutor() == autor && d.getTitol() == titol) trobat = true;
        }
        return d;
    }
}
