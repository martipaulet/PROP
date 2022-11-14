package FONTS.Domini;
import java.util.*;

public class Autor {


    //---ATRIBUTS---


    private String nom_; //nom de l'autor.
    private Vector<Document> docs_; //Conjunt de documents de l'autor.


    //---CONSTRUCTORES---


    //Post: es crea una instancia d'Autor buida.
    public Autor() {
        nom_ = null;
        docs_ = new Vector<>();
    }

    //Post: es crea una instancia d'Autor amb nomA = nomAutor.
    public Autor(String nomAutor) {
        nom_ = nomAutor;
        docs_ = new Vector<>();
    }


    //---GETTER/SETTER---


    //Post: retorna el nom de l'autor.
    public String getNom() {
        return nom_;
    }

    //Post: s'assigna al autor el nomA = nomAutor.
    public void setNom(String nomAutor) {
        nom_ = nomAutor;
    }


    //---CONSULTORES---


    //Post: es retorna una llista dels titols de l'autor ordenats alfabèticament.
    public ArrayList<String> consultaTitols() {
        ArrayList<String> t = new ArrayList<>();
        for (int i = 0; i < docs_.size(); ++i) {
            Document d = docs_.elementAt(i);
            String titolAutor = d.getTitol();
            t.add(titolAutor);
        }
        Collections.sort(t);
        return t;
    }

    //Post: retorna true si l'autor te un document amb nomTitol = titol. False altrament.
    public boolean conteTitol(String titol) {
        boolean trobat = false;
        int i = 0;
        while ( i < docs_.size() && !trobat) {
            Document d = docs_.elementAt(i);
            if (d.getTitol().equals(titol)) trobat = true;
            ++i;
        }
        return trobat;
    }

    //Pre: L'autor del document es this.nomA.
    //Post: S'afegeix al conjunt de documents de l'autor el document d.
    public void afegirDocument(Document d) {
        docs_.add(d);
    }

    //Pre: L'autor del document es this.nomA.
    //Post: S'elimina del conjunt de documents de l'autor el document d.
    public void eliminaDocument(Document d) {
        docs_.remove(d);
    }

    //Post: retorna true si el nom de l'autor comença pel prefix indicat. False altrament.
    public boolean tePrefix(String prefix) {
        boolean trobat = prefix.length() <= nom_.length();
        for (int i = 0; i < prefix.length() && trobat; ++i) {
            if (prefix.charAt(i) != nom_.charAt(i)) trobat = false;
        }
        return trobat;
    }

    //Post: retorna true si l'autor te documents. False altrament.
    public boolean teDocuments() {
        return (docs_.size() != 0);
    }

}
