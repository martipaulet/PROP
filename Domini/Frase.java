//import org.jetbrains.annotations.NotNull;
package Domini;
import java.util.*;

public class Frase {

    private String frase;
    private String titoldoc;
    private String autordoc;

    private final String[] paraules_;
    private HashMap<String, Integer> recParaules = new HashMap<>();

    public Frase(String paraules, String titol, String autor) {
        paraules_ = paraules.split("\\W+");
        titoldoc = titol;
        autordoc = autor;
        frase = paraules;
        this.setParaules();
    }

    public String[] getParaules() {
        return paraules_;
    }

    public String getTitolDoc() {
        return titoldoc;
    }

    public String getAutorDoc() {
        return autordoc;
    }

    public String getFrase() {
        return frase;
    }

    public HashMap<String, Integer> getrecParaules() {
        return recParaules;
    }

    private void setParaules() {
        for (String paraula : paraules_) {
            paraula = paraula.toLowerCase();
            if (recParaules.containsKey(paraula)) {
                int vegades = recParaules.get(paraula);
                recParaules.replace(paraula, ++vegades);
            }
            else {
                recParaules.put(paraula, 1);
            }
        }
    }

    public Boolean conteQuery(String query) {
        boolean conte = false;
        //ES UNA UNICA PARAULA
        if (!query.contains(" ")) {
            if (recParaules.containsKey(query)) conte = true;
        }
        else {
            if (frase.contains(" " + query + " ") || frase.contains(" " + query + ",") || frase.contains(" " + query + ".")
                    || frase.contains(" " + query + "!") || frase.contains(" " + query + "?") || frase.contains(" " + query + ":")
                    || frase.contains(" " + query + ";")) conte = true;

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
}
