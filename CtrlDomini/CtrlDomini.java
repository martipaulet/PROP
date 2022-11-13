package CtrlDomini;
import Domini.*;

import java.util.*;

import static java.util.stream.Collectors.toMap;


public class CtrlDomini {

    private Map<String, Autor> autors; //Conjunt autors
    private ConjuntDocuments documents; //Conjunt de documents
    private static CtrlDomini instance;
    private static CtrlExpressioBooleana CtrlExpressioBooleana; //Sub-controlador de les expressions booleanes


    //Post: es crea una instancia de CtrlDomini
    public CtrlDomini() {
        inicialitzarCtrlDomini();
    }

    //Post: S'inicialitzen les variables del CtrlDomini
    private void inicialitzarCtrlDomini() {
        autors = new HashMap<> ();
        documents = new ConjuntDocuments();
        CtrlExpressioBooleana = new CtrlExpressioBooleana();
    }

    //Post: Retorna la instancia de CtrlDomini. Si no existeix cap instancia de CtrlDomini, es crea.
    public static CtrlDomini getInstance() {
        if (instance == null) instance = new CtrlDomini();
        return instance;
    }


    //---GESTIO DOCUMENTS---


    //Post: es crea un nou document en el sistema si aquest no existia previament.
    //      Si l'autor del document no existia es crea l'autor.
    //      Es crea la relacio entre aquest nou document i l'autor.
    public void altaDocument(String autor, String titol, String contingut) {
        if (autor == null || titol == null) System.out.println("Error en la creació de Document");
        Autor a = new Autor();
        if (!existeixAutor(autor)) {
            a.setNom(autor);
            autors.put(autor, a);
        }
        else {
            a = autors.get(autor);
            if (a.conteTitol(titol)) {
                System.out.print("Document ja existent");
                return;
            }
        }
        Document d = new Document(autor, titol, contingut);
        a.afegirDocument(d);
        documents.afegirDocument(d);
    }

    //Post: S'elimina el document d i la seva relacio amb l'autor.
    //      Si l'autor del document eliminat nomes tenia aquest document s'elimina l'autor.
    public void baixaDocument(String autor, String titol) {
        if (autor == null || titol == null) System.out.println("Introdueix un autor i títol vàlid"); //Excepcio
        else if (documents.getDocument(autor,titol) != null) {
            Document d = documents.getDocument(autor,titol);
            desassociaAutor(d);
            documents.baixaDocument(d);
        }
        else {
            System.out.println("Document no existeix en el sistema"); //Excepcio
        }

    }

    //Post: S'actualitza el contingut i la data d'ultima modificació del document si es que s'ha modificat el contingut.
    public void modificarDocument(String nouContingut, String autor, String titol) {
        if (autor == null || titol == null) System.out.println("Introdueix un autor i títol vàlid"); //Excepcio
        else if (documents.getDocument(autor,titol) != null) {
            Document d = documents.getDocument(autor,titol);
            if (!d.getContingut().equals(nouContingut)) d.actualitzaDocument(nouContingut);
        }
        else {
            System.out.println("Document no existeix en el sistema"); //Excepcio
        }
    }


    //---GESTIO EXPRESSIONS BOOLEANES---


    //Post: Es crea una nova expressio booleana si aquesta no existia.
    public void altaExpressioBooleana(String query) {
        CtrlExpressioBooleana.altaExpressioBooleana(query);
    }

    //Post: S'elimina l'expressió booleana si aquesta existia.
    public void baixaExpressioBooleana(String query) {
        CtrlExpressioBooleana.baixaExpressioBooleana(query);
    }

    //Post:Es modifica l'expressio booleana indicada en queryantiga si aquesta existia per querymodificada.
    public void modificaExpressioBooleana(String queryantiga, String querymodificada) {
        CtrlExpressioBooleana.modificaExpressioBooleana(queryantiga,querymodificada);
    }


    //---CONSULTES---

    //Post: es retorna una llista amb el conjunt de titols de l'autor indicat.
    public List<String> titolsAutor(String autor) {
        List<String> ls = new ArrayList<String>();
        if (existeixAutor(autor)) {
            Autor a = autors.get(autor);
            ls = a.consultaTitols();
        }
        else {
            System.out.println("Autor no existent"); //Excepcio
        }
        return ls;
    }

    //Post: es retorna una llista amb el conjunt d'autors que començen pel prefix indicat.
    public ArrayList<String> prefixAutor(String prefix) {
        ArrayList<String> ls = new ArrayList<>();
        boolean algun = false;
        for (String nomAutor : autors.keySet()) {
            Autor a = autors.get(nomAutor);
            if (a.tePrefix(prefix)) {
                if (!algun) algun = true;
                ls.add(a.getNom());
            }
        }
        if (!algun) {
            System.out.println("Cap autor amb el prefix " + prefix); //Excepcio
        }
        Collections.sort(ls);
        return ls;
    }

   //Post: Es retorna el contingut del document referenciat per autor i titol si aquest existeix.
    public String obteContingut(String autor, String titol) {
        if (existeixDocument(autor,titol)) {
            Document d = documents.getDocument(autor,titol);
            return d.getContingut();
        }
        else {
            System.out.println("El document no existeix");
            return null;
        }
    }

    //Post: Es retorna el conjunt de K documents mes semblants al document referenciat per titol autor si existeix.
    //      L'algorisme per detectar semblançes sera Tf_idf si mode == 0 o Tf si mode == 1.
    public ConjuntDocuments DocumentsSemblants(String autor, String titol, Integer K, Integer mode) {
        if (mode != 0 && mode != 1) {
            System.out.println("Mode ha de ser 0 o 1");
            return null;
        }
        if (existeixDocument(autor,titol)) {
            ConjuntDocuments cd;
            if (mode == 0) cd = DocumentsSemblants_TfIdf(autor,titol,K);
            else cd = DocumentsSemblants_Tf(autor,titol,K);
            return cd;
        }
        else {
            System.out.println("El document no existeix");
            return null;
        }
    }

    //Post: es retorna el conjunt de documents format per documents que contenen almenys una frase que compleix la query booleana.
    public ConjuntDocuments ConsultaBooleana(String query) {
        CtrlExpressioBooleana = CtrlExpressioBooleana.getInstance();
        ConjuntDocuments cd = CtrlExpressioBooleana.evalua(query,documents);
        return cd;
    }

    public ConjuntDocuments ordenaDocuments(ConjuntDocuments cd, int ordre) {
        if (ordre != 0 && ordre != 1 && ordre != 2) {
            System.out.println("El nombre a introduir per escollir ordenació ha de ser 0, 1 o 2");
            return null;
        }
        else if (ordre == 0) {
            OrdreAlfabetic o = new OrdreAlfabetic();
            ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
            return cdfi;
        }
        else if (ordre == 1) {
            OrdreCreacio o = new OrdreCreacio();
            ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
            return cdfi;
        }
        else {
            OrdreModificacio o = new OrdreModificacio();
            ConjuntDocuments cdfi = o.OrdenarDocuments(cd);
            return cdfi;
        }
    }


    //---METODES PRIVATS---


    //Post: s'elimina l'associacio entre el document d i el seu autor.
    //      si l'autor nomes tenia aquell document s'elimina l'autor.
    private void desassociaAutor(Document d) {
        Autor tmp = autors.get(d.getAutor());
        tmp.eliminaDocument(d);
        if(!tmp.teDocuments()) autors.remove(d.getAutor());
    }

    //Post: retorna true si l'autor existeix. False altrament.
    private boolean existeixAutor(String autor) {
        return autors.containsKey(autor);
    }

    //Post: retorna true si el document existeix. False altrament.
    private boolean existeixDocument(String autor, String titol) {
        if (autors.containsKey(autor)) {
            Autor a = autors.get(autor);
            return a.conteTitol(titol);
        }
        else return false;
    }

    //Post: Es retorna el conjunt de K documents mes semblants al document referenciat per titol autor si existeix.
    //      Utilitza algorisme Tf_idf.
    private ConjuntDocuments DocumentsSemblants_TfIdf(String autor, String titol, Integer K) {
        Vector<Document> vd = documents.getVector();
        if (K >= vd.size()) {
            System.out.println("El natural K és major als documents del sistema"); //Excepcio
            return null;
        }
        Document D = documents.getDocument(autor, titol);
        HashMap<Document, Double> TfIdf = documents.CalculTfIdf(D);
        HashMap<Document, Double> aux = sortMapByValue(TfIdf);
        Vector<Document> ret = new Vector<>(K);

        Iterator<Map.Entry<Document, Double>> it = aux.entrySet().iterator();
        while (it.hasNext() && ret.size()<K) {
            Map.Entry<Document, Double> entry = it.next();
            ret.add(entry.getKey());
        }
        ConjuntDocuments cd = new ConjuntDocuments(ret);
        return cd;
    }

    //Post: Es retorna el conjunt de K documents mes semblants al document referenciat per titol autor si existeix.
    //      Utilitza algorisme Tf.
    private ConjuntDocuments DocumentsSemblants_Tf(String autor, String titol, Integer K) {
        Vector<Document> vd = documents.getVector();
        if (K >= vd.size()) {
            System.out.println("El natural K és major als documents del sistema"); //Excepcio
            return null;
        }
        Document D = documents.getDocument(autor, titol);
        HashMap<Document, Double> Tf = documents.CalculTf(D);
        HashMap<Document, Double> aux = sortMapByValue(Tf);
        Vector<Document> ret = new Vector<>(K);

        Iterator<Map.Entry<Document, Double>> it = aux.entrySet().iterator();
        while (it.hasNext() && ret.size()<K) {
            Map.Entry<Document, Double> entry = it.next();
            ret.add(entry.getKey());
        }
        ConjuntDocuments cd = new ConjuntDocuments(ret);
        return cd;
    }

    //Post: Retorna el HashMap de Document i valor idf ordenat descendentment.
    private HashMap<Document, Double> sortMapByValue(HashMap<Document, Double> map) {
        HashMap<Document, Double> sortedMap =  map.entrySet().stream()
                .sorted(Comparator.comparingDouble(e -> -1 * e.getValue() ))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError();},
                        LinkedHashMap::new
                ));
        return sortedMap;
    }
}
