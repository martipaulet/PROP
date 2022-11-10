package Domini;
import java.util.*;

public class Autor {
    private String nomA;
    private Vector<Document> docsAutor_;

    public Autor(String nomAutor) {
        nomA = nomAutor;
        docsAutor_ = new Vector<>();
    }

    public String getNom() {
        return nomA;
    }

    public void setNom(String n) { nomA = n; }

    public ArrayList<String> consultaTitols() {
        //retorna tots els títols de docsAutor_ ordenats alfabèticament
        ArrayList<String> t = new ArrayList<>();
        for (int i = 0; i < docsAutor_.size(); ++i) {
            Document d = docsAutor_.elementAt(i);
            String titolAutor = d.getTitol();
            t.add(titolAutor);
        }
        Collections.sort(t);
        return t;
    }

    public boolean conteTitol(String titol) {
        boolean trobat = false;
        int i = 0;
        while ( i < docsAutor_.size() && !trobat) {
            Document d = docsAutor_.elementAt(i);
            if (d.getTitol().equals(titol)) trobat = true;
            ++i;
        }
        return trobat;
    }

    public void afegirDocument(Document d) {
        docsAutor_.add(d);
    }

    public void eliminaDocument(Document d) {
        docsAutor_.remove(d);
    }

    public Document obteDocument(String titol_doc_autor) {
        /*busca en vector de documents el Document amb titol t
        si titol = t, mostrem document (igual fa falta funcio) */
        boolean trobat = false;
        Document d = null;
        for (int i = 0; i < docsAutor_.size() && !trobat; ++i) {
            d = docsAutor_.elementAt(i);
            String titol_autor = d.getTitol();
            if (Objects.equals(titol_autor, titol_doc_autor)) trobat = true;
        }
        return d;
    }

    public Boolean tePrefix(String prefix) {
        Boolean trobat = true;
        if (prefix.length() > nomA.length()) trobat = false;
        for (int i = 0; i < prefix.length() && trobat; ++i) {
            if (prefix.charAt(i) != nomA.charAt(i)) trobat = false;
        }
        return trobat;
    }
    public Boolean teDocuments() {
        return (docsAutor_.size() != 0);
    }


    public void imprimir() {
        System.out.println(nomA);
    }
}
