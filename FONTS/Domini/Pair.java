package Domini;

import java.util.Objects;

public class Pair {


    //---ATRIBUTS---


    public String titol_;
    public String autor_;


    //---CONSTRUCTORA---


    public Pair(String autor, String titol) {
        autor_ = autor;
        titol_ = titol;
    }


    //---GETTERS/SETTERS---


    public String getAutor() {
        return autor_;
    }


    public String getTitol() {
        return titol_;
    }


    //---METODES AUXILIARS---

    @Override public boolean equals(Object p) {
        if (p == this) return true;
        if (p == null || this.getClass() != p.getClass()) return false;
        final Pair pair = (Pair) p;
        return pair.getAutor() != null && pair.getTitol() != null && pair.getAutor().equals(autor_) && pair.getTitol().equals(titol_);
    }

    @Override public int hashCode() {
        int result = autor_.hashCode();
        result = 31 * result + titol_.hashCode();
        return result;
    }

}
