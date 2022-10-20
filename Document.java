import java.util.*;

public class Document {
    private Autor autor_;
    private Titol titol_;
    private String contingut_;
    private ArrayList<Frase> frases_ = new ArrayList<>();

    private Date dataCreacio_;
    private Date dataUltimaModificacio_;

    private HashMap<String, Integer> paraules_;


    public Document (Autor autor, Titol titol, String contingut) {
        autor_ = autor;
        titol_ = titol;
        contingut_ = contingut;
        setFrases();
        // dataCreacio_ = getData()?
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

    /*public void actualitzarDataUltimaModificacio() {
        dataUltimaModificacio_ = getData()?;
    }*/

    public HashMap<String, Integer> getParaules() {
        return paraules_;
    }

    private void setParaules() {
        //omplir HashMap
    }
}
