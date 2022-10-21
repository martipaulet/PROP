import java.util.*;
import java.util.regex.Pattern;

public class Document {
    private Autor autor_;
    private Titol titol_;
    private String contingut_;
    private ArrayList<Frase> frases_ = new ArrayList<>();

    private Date dataCreacio_;
    private Date dataUltimaModificacio_;

    private HashMap<String, Integer> paraules_ = new HashMap<>();


    public Document (Autor autor, Titol titol, String contingut) {
        autor_ = autor;
        titol_ = titol;
        contingut_ = contingut;
        setFrases();
        dataCreacio_ = new Date();
        dataUltimaModificacio_ = dataCreacio_;
        setParaules();
    }

    public Autor getAutor() {
        return autor_;
    }

    public void setAutor(Autor autor) {
        autor_ = autor;
    }

    public Titol getTitol() {
        return titol_;
    }

    public void setTitol(Titol titol) {
        titol_ = titol;
    }

    public String getContingut() {
        return contingut_;
    }

    public void setContingut(String contingut) {
        contingut_ = contingut;
    }

    public ArrayList<Frase> getFrases() {
        return frases_;
    }

    private void setFrases() {
        String[] frases = contingut_.split("\\.");
        for (String frase : frases) {
            frases_.add(new Frase(frase));
        }
    }

    public Date getDataCreacio() {
        return dataCreacio_;
    }

    public Date getDataUltimaModificacio() {
        return dataUltimaModificacio_;
    }

    public void actualitzarDataUltimaModificacio() {
        dataUltimaModificacio_ = new Date();
    }

    public HashMap<String, Integer> getParaules() {
        return paraules_;
    }

    private void setParaules() {
        String[] paraules = contingut_.split("(?U)\\W+");
        for (String paraula : paraules) {
            paraula = paraula.toLowerCase();
            if (paraules_.containsKey(paraula)) {
                int vegades = paraules_.get(paraula);
                paraules_.put(paraula, ++vegades);
            }
            else {
                paraules_.put(paraula, 1);
            }
        }
    }

    public void imprimir() {
        autor_.imprimir();
        titol_.imprimir();
        System.out.println(contingut_);
        System.out.println(dataCreacio_);
        System.out.println(dataUltimaModificacio_);
        System.out.println(paraules_);
    }
}
