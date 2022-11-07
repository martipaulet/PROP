package Domini;
import java.util.*;

public class ConjuntDocuments {
    private Vector<Document> CjtD;
    private HashMap<String, Integer> CopsParaules = new HashMap<>();

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
        CjtD.remove(d);
    }

    public void modificarDocuments() {
        //fa trucada a modificaDocument per cada document seleccionat amb selecccionarDocument
    }

    public void afegirDocument(Document d) {
        //afegir document al conjunt
        CjtD.add(d);
    }


    //pre: el document (autor,titol) existeix
    //retorna el document (autor,titol)
    public Document getDocument(String autor, String titol) {
        Boolean trobat = false;
        Document d = new Document(null, null, null);
        for (int i = 0; i < CjtD.size() && !trobat; ++i) {
            d = CjtD.elementAt(i);
            if (d.getAutor() == autor && d.getTitol() == titol) trobat = true;
        }
        return d;
    }

    //Implementa idf
    public void CalculCopsParaules() {
        for (int i=0; i < CjtD.size(); ++i){
            Document d = CjtD.elementAt(i);
            HashMap<String, Integer> paraules = d.getParaules();
            for (String paraula : paraules.keySet()) {
                if(CopsParaules.containsKey(paraula)){
                    CopsParaules.replace(paraula,CopsParaules.get(paraula)+1);
                }
                else {
                    CopsParaules.put(paraula,1);
                }
            }
        }
    }
}