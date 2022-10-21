import java.util.*;

public class Titol {
    private String[] titol_;

    private Vector<Document> docs_titol;

    public Titol(String titol) {
        titol_ = titol.split("\\W+");
    }
}

