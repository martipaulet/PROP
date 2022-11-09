//import org.jetbrains.annotations.NotNull;
package Domini;
import java.util.*;

public class Frase {

    private String frase;

    private String[] paraules_;
    private HashMap<String, Integer> RecParaules = new HashMap<>();

    public Frase(String paraules) {
        paraules_ = paraules.split("\\W+");
    }

    private void setParaules() {
        for (String paraula : paraules_) {
            paraula = paraula.toLowerCase();
            if (RecParaules.containsKey(paraula)) {
                int vegades = RecParaules.get(paraula);
                RecParaules.replace(paraula, ++vegades);
            }
            else {
                RecParaules.put(paraula, 1);
            }
        }
    }

    public Boolean conteQuery(String query) {
        Boolean conte = false;
        //ES UNA UNICA PARAULA
        if (!query.contains(" ")) {
            if (RecParaules.containsKey(query)) conte = true;
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
