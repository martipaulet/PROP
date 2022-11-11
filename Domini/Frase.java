//import org.jetbrains.annotations.NotNull;
package Domini;
import java.util.*;

public class Frase {

    private String frase;
    private String titoldoc;
    private String autordoc;

    private final String[] paraules_;

    private ArrayList<String> Paraules = new ArrayList<>();

    public Frase(String paraules, String titol, String autor) {
        paraules_ = paraules.split("\\W+");
        titoldoc = titol;
        autordoc = autor;
        frase = paraules;
        setParaules();
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

    public boolean conteQuery(String query) {
        String [] q2 = query.split("\\W+");
        boolean conte = false;
        boolean finish = false;
        for (int i = 0; i < paraules_.length; ++i) {
            if (paraules_[i].equals(q2[0])) {
                conte = true;
                for (int j = 1; j < q2.length && !finish; ++j) {
                    if ((i+j) == paraules_.length) {
                        finish = true;
                        conte = false;
                    }
                    else if (!paraules_[i+j].equals(q2[j])) conte = false;
                }
            }
        }
        return conte;
    }

}
