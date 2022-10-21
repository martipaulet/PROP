import java.util.*;

public class Titol {
    // S'hauria de guardar títol com a string, pot contindre signes de puntuació.
    private String titol_;
    private String[] titolSeparat_;

    private Vector<Document> docs_titol;

    public Titol(String titol) {
        titol_ = titol;
        titolSeparat_ = titol.split("\\W+");
    }

    public void imprimir() {
        System.out.println(titol_);
    }
}

