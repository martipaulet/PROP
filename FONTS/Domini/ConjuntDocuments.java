package FONTS.Domini;
import java.util.*;

public class ConjuntDocuments {


    //---ATRIBUTS---


    private Vector<Document> CjtD;  //Conjunt format per Documents


    //---CONSTRUCTORES---


    //Post: es crea una instancia de ConjuntDocuments buida.
    public ConjuntDocuments() {
        CjtD = new Vector<>();
    }

    //Post: es crea una instancia de ConjuntDocuments amb els Documents indicats.
    public ConjuntDocuments(Vector<Document> sd) {
        CjtD = sd;
    }


    //---GESTIO DOCUMENTS---


    //Post: S'afegeix el document d al conjunt de documents si aquest no estava.
    public void afegirDocument(Document d) throws Exception{
        if (!pertany(d)){
            CjtD.add(d);
        }
        else {
            throw new Exception("El document ja pertanyia al conjunt\r\n");
        }
    }

    //Post: S'elimina el document d del conjunt de documents si aquest estava.
    public void baixaDocument(Document d) throws Exception{
        if (pertany(d)){
            CjtD.remove(d);
        }
        else {
            throw new Exception("El document no pertany al conjunt\r\n");
        }
    }


    //---CONSULTORES---


    //Post: retorna true si el document d pertany al conjunt de documents. False altrament.
    private boolean pertany(Document d){
        for (int i = 0; i < CjtD.size(); ++i) {
            if (CjtD.elementAt(i) == d) return true;
        }
        return false;
    }


    //---GETTER/SETTER---


    //Post: retorna en un vector de documents el conjunt de documents.
    public Vector<Document> getVector() {
        return CjtD;
    }

    //Post: retorna el document referenciat per autor i titol indicats. Si aquest no estava en el conjunt retorna null.
    public Document getDocument(String autor, String titol) {
        for (int i = 0; i < CjtD.size(); ++i) {
            Document d = CjtD.elementAt(i);
            if (Objects.equals(d.getAutor(), autor) && Objects.equals(d.getTitol(), titol)) return d;
        }
        return null;
    }


    //---CALCULS---


    //Post: retorna un set amb les frases dels documents del conjunt de documents.
    public Set<Frase> VecToSet() {
        Set<Frase> s = new HashSet<>();
        for (int i = 0; i < CjtD.size(); ++i) {
            Document d = CjtD.elementAt(i);
            Set<Frase> sdoc = d.getFrasesToSet();
            s.addAll(sdoc);
        }
        return s;
    }

    //Post: retorna un maps compost per paraula -> nom de vegades que apareix la paraula en el conjunt de documents.
    public HashMap<String, Integer> CalculCopsParaules(Document D) {
        HashMap<String, Integer> paraules_D = D.getParaules();
        HashMap<String, Integer> CopsParaules = new HashMap<>();
        for(String paraula : paraules_D.keySet()){
            CopsParaules.put(paraula,0);
            for(int i=0; i < CjtD.size(); ++i){
                Document d = CjtD.elementAt(i);
                HashMap<String, Integer> paraules_d = d.getParaules();
                if(paraules_d.containsKey(paraula)){
                    CopsParaules.replace(paraula,CopsParaules.get(paraula)+1);
                }
            }
        }
        return CopsParaules;
    }

    //Post: retorna un mapa compost per document -> valor tfidf agafant de referencia el document D indicat.
    public HashMap<Document, Double> CalculTfIdf(Document D) {
        HashMap<Document,Double> ret = new HashMap<>();
        HashMap<String, Integer> idf = CalculCopsParaules(D);
        for (int i=0; i< CjtD.size(); ++i){
            double sum = 0.0;
            Document d = CjtD.elementAt(i);
            ret.put(d, 0.0);
            HashMap<String, Integer> tf = d.getParaules();
            if (d != D){
                for(String paraula : idf.keySet()){
                    double mida = CjtD.size();
                    double rec = idf.get(paraula);
                    double a;
                    if(rec == 0) a = 0.0;
                    else a = Math.log(mida/(rec));
                    if (a < 0) a = 0.0;
                    double b = 0.0;
                    if (tf.containsKey(paraula)){
                        b = tf.get(paraula);
                    }
                    double aux = b*a;
                    sum+=aux;
                }
                ret.replace(d,sum);
            }
        }
        return ret;
    }

    //Post: retorna un mapa compost per document -> valor tf agafant de referencia el document D indicat.
    public HashMap<Document, Double> CalculTf(Document D) {
        HashMap<Document,Double> ret = new HashMap<>();
        HashMap<String, Integer> par = D.getParaules();
        for (int i=0; i< CjtD.size(); ++i){
            double sum = 0.0;
            Document d = CjtD.elementAt(i);
            ret.put(d, 0.0);
            HashMap<String, Integer> tf = d.getParaules();
            if (d != D){
                for(String paraula : par.keySet()){
                    if (tf.containsKey(paraula)){
                        double aux = tf.get(paraula);
                        sum+=aux;
                    }

                }
                ret.replace(d,sum);
            }
        }
        return ret;
    }

    //Post: retorna un set de frases dels documents del conjunt que contenen l'string s en la frase.
    public Set<Frase> obteFrasesContenen(String s) throws Exception{
        Set<Frase> sf = new HashSet<>();
        for (int i = 0; i < CjtD.size(); ++i) {
            Document d = CjtD.elementAt(i);
            if (d.fraseConteString(s)) {
                Vector<Frase> vf = d.getFrasesParaula(s);
                for (int j = 0; j < vf.size(); ++j) {
                    Frase f = vf.elementAt(j);
                    sf.add(f);
                }
            }
        }
        if (sf.size() == 0) throw new Exception("La paraula "+s+" no esta en cap frase del conjunt de documents\r\n");
        return sf;
    }


    //---ESCRIPTURA---


    //Post: imprimeix els atributs de tots els documents del conjunt de documents.
    public void imprimir() {
        for(int i = 0; i < CjtD.size(); ++i){
            if (i != 0) System.out.println("\n");
            CjtD.elementAt(i).imprimir();
        }
    }

}