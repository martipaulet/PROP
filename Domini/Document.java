package Domini;
import java.util.*;
import java.util.regex.Pattern;

public class Document {
    private String autor_;
    private String titol_;
    private String contingut_;
    private ArrayList<Frase> frases_ = new ArrayList<>();

    private Date dataCreacio_;
    private Date dataUltimaModificacio_;

    private HashMap<String, Integer> paraules_ = new HashMap<>();


    public Document (String autor, String titol, String contingut) {
        autor_ = autor;
        titol_ = titol;
        contingut_ = contingut;
        setFrases();
        dataCreacio_ = new Date();
        dataUltimaModificacio_ = dataCreacio_;
        setParaules();
    }

    public String getAutor() {
        return autor_;
    }

    public void setAutor(String autor) {
        autor_ = autor;
    }

    public String getTitol() {
        return titol_;
    }

    public void setTitol(String titol) {
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

    public void actualitzaDocument(String nouContingut) {
        contingut_ = nouContingut;
        dataUltimaModificacio_ = new Date();
    }

    public Boolean conteFrase(String s) {
        Boolean conte = false;
        for (int i = 0; i < frases_.size() && !conte; ++i) {
            Frase f = frases_.get(i);
            if (f.conteQuery(s)) conte = true;
        }
        return conte;
    }

    public Vector<Frase> getFrasesQuery(String s) {
        Vector<Frase> vf = new Vector<>();
        for (int i = 0; i < frases_.size(); ++i) {
            Frase f = frases_.get(i);
            if (f.conteQuery(s)) vf.add(f);
        }
        return vf;
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

    //Implementacio tf
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
        System.out.println(autor_);
        System.out.println(titol_);
        System.out.println(contingut_);
        System.out.println(dataCreacio_);
        System.out.println(dataUltimaModificacio_);
        System.out.println(paraules_);
    }

    public void mostraDocument() {

    }
}
