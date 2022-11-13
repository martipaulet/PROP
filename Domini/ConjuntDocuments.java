package Domini;
import java.util.*;
import java.lang.*;

public class ConjuntDocuments {
    private Vector<Document> CjtD;

    public ConjuntDocuments() {
        CjtD = new Vector<Document>();
    }

    public ConjuntDocuments(Vector<Document> sd) {
        CjtD = sd;
    }

    public Set<Frase> VecToSet() {
        Set<Frase> s = new HashSet<>();
        for (int i = 0; i < CjtD.size(); ++i) {
            Document d = CjtD.elementAt(i);
            Set<Frase> sdoc = d.getFrasesToSet();
            s.addAll(sdoc);
        }
        return s;
    }

    public Vector<Document> getVector() {
        return CjtD;
    }

    public void importarDocuments () {
        //fa crides a la creadora de document
    }

    public void exportarDocuments() {
        //exporta els documents seleccionats amb seleccionarDocument
    }

    public void baixaDocuments(ConjuntDocuments cd) {
        //elimina els documents seleccionats amb seleccionarDocument
    }

    public void baixaDocument(Document d) {
        //elimina un document seleccionat amb seleccionarDocument
        if (pertany(d)){
            CjtD.remove(d);
        }
        else {
            System.out.print("EL document no pertany al conjunt\r\n"); //Excepcio
        }
    }

    public void modificarDocuments() {
        //fa trucada a modificaDocument per cada document seleccionat amb selecccionarDocument
    }

    public void afegirDocument(Document d) {
        //afegir document al conjunt
        if (!pertany(d)){
            CjtD.add(d);
        }
        else {
            System.out.print("EL document ja pertanyia al conjunt\r\n"); //Excepcio
        }
    }

    //retorna el document (autor,titol)
    public Document getDocument(String autor, String titol) {
        for (int i = 0; i < CjtD.size(); ++i) {
            Document d = CjtD.elementAt(i);
            if (Objects.equals(d.getAutor(), autor) && Objects.equals(d.getTitol(), titol)) return d;
        }
        return null;
    }

    //Implementa idf
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


    public Set<Frase> obteFrasesContenen(String s) {
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
        if (sf.size() == 0)System.out.print("La paraula "+s+" no esta en cap frase del conjunt de documents\r\n"); //Excepcio
        return sf;
    }

    public void imprimir() {
        for(int i = 0; i < CjtD.size(); ++i){
            if (i != 0) System.out.println("\n");
            CjtD.elementAt(i).imprimir();
        }
    }

    private boolean pertany(Document d){
        for (int i = 0; i < CjtD.size(); ++i) {
            if (CjtD.elementAt(i) == d) return true;
        }
        return false;
    }
}