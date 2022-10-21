import java.util.*;

public class Autor {
    private String[] nom_;

    private Vector<Document> docs_autor_;

    public Autor(String nomAutor) {
        nom_ = nomAutor.split("\\W+");
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
