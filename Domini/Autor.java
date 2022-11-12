package Domini;
import java.util.*;

public class Autor {
    private String nomA;
    private Vector<Document> docsAutor_;

    public Autor() {
        nomA = null;
        docsAutor_ = new Vector<>();
    }

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

    //Pre: L'autor del document es this.nomA
    public void afegirDocument(Document d) {
        docsAutor_.add(d);
    }

    //Pre: L'autor del document es this.nomA
    public void eliminaDocument(Document d) {
        docsAutor_.remove(d);
    }

    public boolean tePrefix(String prefix) {
        boolean trobat = prefix.length() <= nomA.length();
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
