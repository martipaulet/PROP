import java.util.*;

public class Autor {
    private String[] nom_;

    private Vector<Document> docs_autor_;

    public Autor(String nomAutor) {
        nom_ = nomAutor.split("\\W+");
    }

    public void consultaTitols() {
        //retorna tots els títols de docs_autor ordenats alfabèticament
        Set<String> t = null;
        for (int i = 0; i < docs_autor.size(); ++i) {
            Document d = docs_autor.elementAt(i);
            String titol_autor = d.getTitol();
            t.add(titol_autor);
        }
        Iterator it = t.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /*public void obteContingut(Titol t) {
        //busca en el vector de documents el Document amb títol t
        //getTítol de cada document
        //si titol = t, document.mostraContingut()
    }*/

    /*REALMENT NO CREC QUE CALGUI JA QUE ANANT AL CONTROLADOR I FENT getDoc(Autor, Titol)
    JA OBTENIM EL DOCUMENT QUE VOLEM*/
    public void obteDocument(String titol_doc_autor) {
        //busca en vector de documents el Document amb titol t
        //si titol = t, mostrem document (igual fa falta funcio)
        Boolean trobat = false;
        Integer i = 0;
        Document d = null;
        while (i < docs_autor.size()) {
            while (trobat == false) {
                d = docs_autor.elementAt(i);
                String titol_autor = d.getTitol();
                if (titol_autor == titol_doc_autor) trobat = true;
            }
            ++i;
        }
        d.mostraDocument();
    }

    public void imprimir() {
        for (String nom : nom_) {
            if (!Objects.equals(nom, nom_[0])) {
                System.out.print(" ");
            }
            System.out.print(nom);
        }
        System.out.println();
    }
}
