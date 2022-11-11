//import org.jetbrains.annotations.NotNull;
package Domini;
import java.util.*;

public class Frase {

    private String frase_;
    private String titolDoc_;
    private String autorDoc_;

    private final String[] paraules_;

    private ArrayList<String> Paraules = new ArrayList<>();

    public Frase(String frase, String titolDoc, String autorDoc) {
        paraules_ = frase.split("\\W+");
        titolDoc_ = titolDoc;
        autorDoc_ = autorDoc;
        frase_ = frase;
        setParaules();
    }

    public String[] getParaules() {
        return paraules_;
    }

    public String getTitolDoc() {
        return titolDoc_;
    }

    public String getAutorDoc() {
        return autorDoc_;
    }

    public String getFrase() {
        return frase_;
    }

    public ArrayList<String> getrecParaules() {
        return Paraules;
    }

    private void setParaules() {
        for (String paraula : paraules_) {
            paraula = paraula.toLowerCase();
            if (!Paraules.contains(paraula)) {
                Paraules.add(paraula);
            }
        }
    }

    public Boolean conteQuery(String query) {
        boolean conte = false;
        //ES UNA UNICA PARAULA
        if (!query.contains(" ")) {
            if (frase_.contains(query)) conte = true;
        }
        else {
            if (frase_.contains(" " + query + " ") || frase_.contains(" " + query + ",") || frase_.contains(" " + query + ".")
                    || frase_.contains(" " + query + "!") || frase_.contains(" " + query + "?") || frase_.contains(" " + query + ":")
                    || frase_.contains(" " + query + ";")) conte = true;

            /*
            else if (frase.contains(query) && (frase.length() >= query.length())) {
                Boolean finish1 = false;
                for (int i = 0; i < query.length() && !finish1; ++i) {
                    if (query.charAt(i) != frase.charAt(i)) finish1 = true;
                }
                if (!finish1) {
                    Integer a = query.length();
                    if (frase.charAt(a) != ' ') finish1 = true;
                }
                Boolean finish2 = false;
                if (!finish1) {
                    for (int j = query.length() - 1; j >= 0 && !finish2; --j) {
                        if (query.charAt(j) != frase.charAt(j)) finish2 = true;
                    }
                    if (!finish2) {
                        Integer b = query.length();
                        Integer c = frase.length();
                        Integer pos = b + c - 1;
                        if (frase.charAt(pos) != ' ') finish2 = true;
                    }
                }
                if (!finish1 || !finish2) conte = true;
            } */
        }
        return conte;
    }

    public Boolean conteQuerySeq(String[] query) {
        boolean conte = true;
        for (int i = 0; i < query.length; ++i) {
            String aux = query[i];
            //mirar en String[] paraules si esta String[] query seguit
        }
        return conte;
    }

    public void imprimir() {
        System.out.println(frase_);
        System.out.println(Arrays.toString(paraules_));
        System.out.println();
    }
    //pre: la frase conte totes les paraules de String[] query
    //post: rtetorna true si les conte seguides false altrament
    /*public Boolean seguides(String[] query) {
        boolean seguides = true;
        boolean trobat = false;
        for (int i = 0; i < query.length; ++i) {
            if (r)
        }
        return conte;
    }*/

    /*
    private Boolean conteQuery3(String query) {
        Boolean ret = false;
        Frase f = new Frase (query, "", "");
        if (frase.contains(query)) {

            ret = true;
        }
        return ret;
    }

    private Boolean conteQuery2(String query) {
        String[] disjoint = query.split("\\W+");
        int currentPos = 0;
        Boolean matches = false;
        Boolean finish = false;

        for (String p : disjoint) {
            if (p.equals(paraules_[currentPos]) && matches) {
                ++currentPos;
                if (currentPos == (disjoint.length -1)) finish = true;
            }
            else if (p.equals(paraules_[currentPos]) && !matches) {
                matches = true;
                ++currentPos;
            }
            else if (!p.equals(paraules_[currentPos])){
                ++currentPos;
                matches = false;
                finish = false;
            }
        }
        return finish;
    }
    */
}
