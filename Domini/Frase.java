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
        //MÉS D'UNA PARAULA
        else {
            if (frase.contains(" " + query + " ") || frase.contains(" " + query + ",") || frase.contains(" " + query + ".")
                    || frase.contains(" " + query + "!") || frase.contains(" " + query + "?") || frase.contains(" " + query + ":")
                    || frase.contains(" " + query + ";")) conte = true;
        }
        return conte;
    }
}
