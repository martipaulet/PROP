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
