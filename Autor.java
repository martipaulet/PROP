import java.util.*;

public class Autor {
    private String[] nom_;
    private String nomA;
    private Vector<Document> docsAutor_;

    public Autor(String nomAutor) {
        nom_ = nomAutor.split("\\W+");
        nomA = nomAutor;
    }

    public String getNom() {
        return nomA;
    }

    public void consultaTitols() {
        //retorna tots els títols de docsAutor_ ordenats alfabèticament
        Set<String> t = new HashSet<String>();
        for (int i = 0; i < docsAutor_.size(); ++i) {
            Document d = docsAutor_.elementAt(i);
            String titolAutor = d.getTitol();
            t.add(titolAutor);
        }
        for (String s : t) {
            System.out.println(s);
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
        boolean trobat = false;
        int i = 0;
        Document d = null;
        while (i < docsAutor_.size()) {
            while (!trobat) {
                d = docsAutor_.elementAt(i);
                String titol_autor = d.getTitol();
                if (Objects.equals(titol_autor, titol_doc_autor)) trobat = true;
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
