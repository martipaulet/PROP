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

    public void afegirDocument(Document d) {
        if (d.getAutor() == nomA){
            docsAutor_.add(d);
        }
        else {
            System.out.print("L'autor a qui li vols afegir el document no és el mateix que l'autor escriptor \r\n"); // Excepcio
        }
    }

    public void eliminaDocument(Document d) {
        if (conteDocument(d)){
            docsAutor_.remove(d);
        }
        else {
            System.out.print("L'autor ja no tenia aquest document\r\n"); // Excepcio
        }
    }

    public Document obteDocument(String titol) {
        for (int i = 0; i < docsAutor_.size(); ++i) {
            Document d = docsAutor_.elementAt(i);
            String titol_doc = d.getTitol();
            if (Objects.equals(titol_doc, titol)) return d;
        }
        System.out.print("L'autor no te el document amb aquest titol: "+ titol+ "\r\n"); // Excepcio
        return null;
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

    //FUNCIONS PRIVADES

    private boolean conteDocument(Document d){
        for (int i = 0; i < docsAutor_.size(); ++i) {
            if (docsAutor_.elementAt(i) == d) return true;
        }
        return false;
    }

}
