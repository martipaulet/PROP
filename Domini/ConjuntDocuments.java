package Domini;
import java.util.*;
import java.lang.*;

public class ConjuntDocuments {
    private Vector<Document> CjtD;

    /*
    public ConjuntDocuments(Set<Document> sd) {
        CjtD = new Vector<Document>();
        CjtD = SetToVec(sd);    //fer algorisme
    }
    */
    public ConjuntDocuments() {
        CjtD = new Vector<Document>();
    }

    public ConjuntDocuments(Vector<Document> sd) {
        CjtD = sd;
    }

    /*
    private Vector<Document> SetToVec(Set<Document> s) {
        Vector<Document> vd = new Vector<>();
        for (Document d : s) {
            vd.add(d);
        }
        OrdreAlfabetic o = new OrdreAlfabetic();
        Vector<Document> vfinal = o.OrdenarDocuments(vd);
        return vfinal;
    }

     */

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
        //elimina un documents seleccionat amb seleccionarDocument
        CjtD.remove(d);
    }

    public void modificarDocuments() {
        //fa trucada a modificaDocument per cada document seleccionat amb selecccionarDocument
    }

    public void afegirDocument(Document d) {
        //afegir document al conjunt
        CjtD.add(d);
    }


    //pre: el document (autor,titol) existeix
    //retorna el document (autor,titol)
    public Document getDocument(String autor, String titol) {
        boolean trobat = false;
        Document d = new Document(null, null, null);
        for (int i = 0; i < CjtD.size() && !trobat; ++i) {
            d = CjtD.elementAt(i);
            if (Objects.equals(d.getAutor(), autor) && Objects.equals(d.getTitol(), titol)) trobat = true;
        }
        return d;
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
                    double a = Math.log(mida/(1.0+rec));
                    if (rec <= 0) a = 0.0;
                    double b = tf.get(paraula);
                    double aux = b*a;
                    sum+=aux;
                }
                ret.replace(d,sum);
            }
        }
        return ret;
    }

    public Set<Frase> obteFrasesContenen(String s) {
        Set<Frase> sf = null;
        for (int i = 0; i < CjtD.size(); ++i) {
            Document d = CjtD.elementAt(i);
            if (d.conteFrase(s)) {
                Vector<Frase> vf = d.getFrasesQuery(s);
                for (int j = 0; j < vf.size(); ++j) {
                    Frase f = vf.elementAt(j);
                    sf.add(f);
                }
            }
        }
        return sf;
    }

    public void imprimir() {
        for(int i=0; i< CjtD.size(); ++i){
            CjtD.elementAt(i).imprimir();
            System.out.println("\r\n");
        }
    }
}