//import org.jetbrains.annotations.NotNull;
package Domini;
import java.util.*;

public class Frase {
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

}
