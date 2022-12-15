package Domini.Controladors;
import Dades.CtrlDocumentsFile;
import Dades.CtrlExpressionsFile;
import Domini.Model.*;

import java.util.*;
import java.text.Normalizer;

import static java.util.stream.Collectors.toMap;


public class CtrlDomini {


    //---ATRIBUTS---


    private Map<String, Autor> autors; //Conjunt autors.
    private ConjuntDocuments documents; //Conjunt de documents.
    private static CtrlDomini instance;
    private CtrlExpressioBooleana ctrlExpressioBooleana; //Sub-controlador de les expressions booleanes.

    //interficies dels controladors de dades
    private CtrlDocumentsFile ctrlDocuments;
    private CtrlExpressionsFile ctrlExpressions;


    //---CONSTRUCTORA---


    //Post: es crea una instancia de CtrlDomini.
    public CtrlDomini() throws Exception {
        inicialitzarCtrlDomini();
    }

    //Post: S'inicialitzen les variables del CtrlDomini.
    private void inicialitzarCtrlDomini() throws Exception {
        //ctrlExpressions = CtrlExpressions.getInstance();
        ctrlDocuments = CtrlDocumentsFile.getInstance();
        autors = new HashMap<> ();
        documents = new ConjuntDocuments();
        ctrlExpressioBooleana = CtrlExpressioBooleana.getInstance();
        carregaDades(); //carregar documents (autors inclosos) + carregar expressions booleanes guardades.
    }

    //Post: Retorna la instancia de CtrlDomini. Si no existeix cap instancia de CtrlDomini, es crea.
    public static CtrlDomini getInstance() throws Exception {
        if (instance == null) instance = new CtrlDomini();
        return instance;
    }

    //Post: Retorna el conjunt de documents del sistema.
    public ConjuntDocuments DocSistema(){
        return documents;
    }

    public void carregaDades() throws Exception {
        //carregar documents
        Vector <Vector<String>> v = ctrlDocuments.carregaDocuments();   //cada fila es un document amb un vector contenidor de autor|titol|contingut|dataCreacio|dataUMod
        for (Vector<String> strings : v) {
            //System.out.println(strings.get(0)+" "+strings.get(1)+" "+strings.get(2)+" "+strings.get(3)+" "+strings.get(4)+" ");
            carregaDocument(strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4));
        }
    }

    public void guardaDades() {
        //guardar documents
        Vector <Vector<String>> v = documents.toVec();
        /*for (int i = 0; i < v.size(); ++i) {
            System.out.println(v.get(i).get(0)+" " +v.get(i).get(1)+ " " +v.get(i).get(2)+" "+ v.get(i).get(3)+" "+v.get(i).get(4));
        }*/
        ctrlDocuments.guardaDocuments(v);
        //guardar expressions booleanes
    }


    //---GESTIO DOCUMENTS---


    //Post: es crea un nou document en el sistema si aquest no existia previament.
    //      Si l'autor del document no existia es crea l'autor.
    //      Es crea la relacio entre aquest nou document i l'autor.
    public void altaDocument(String autor, String titol, String contingut) throws Exception{
        Autor a = new Autor();
        if (!existeixAutor(autor)) {
            a.setNom(autor);
            autors.put(autor, a);
        }
        else {
            a = autors.get(autor);
            if (a.conteTitol(titol)) {
                throw new Exception("Document ja existeix");
            }
        }
        Document d = new Document(autor, titol, contingut);
        a.afegirDocument(d);
        documents.afegirDocument(d);
    }

    //Post: S'elimina el document d i la seva relacio amb l'autor.
    //      Si l'autor del document eliminat nomes tenia aquest document s'elimina l'autor.
    public void baixaDocument(String autor, String titol) throws Exception{

        if (documents.getDocument(autor,titol) != null) {
            Document d = documents.getDocument(autor,titol);
            desassociaAutor(d);
            documents.baixaDocument(d);
        }
        else {
            throw new Exception("Document no existeix en el sistema");
        }

    }

    //Post: S'actualitza el contingut i la data d'ultima modificació del document si es que s'ha modificat el contingut.
    public void modificarDocument(String nouContingut, String autor, String titol) throws Exception{
        if (Objects.equals(autor, "") || Objects.equals(titol, "")) throw new Exception("Ni autor ni titol no poden ser null");
        else if (documents.getDocument(autor,titol) != null) {
            Document d = documents.getDocument(autor,titol);
            if (!d.getContingut().equals(nouContingut)) d.actualitzaDocument(nouContingut);
        }
        else {
            throw new Exception("Document no existeix en el sistema");
        }
    }


    //---GESTIO EXPRESSIONS BOOLEANES---


    //Post: Es crea una nova expressio booleana si aquesta no existia.
    public void altaExpressioBooleana(String query) throws Exception{
        ctrlExpressioBooleana.altaExpressioBooleana(query);
    }

    //Post: S'elimina l'expressió booleana si aquesta existia.
    public void baixaExpressioBooleana(String query) throws Exception {
        ctrlExpressioBooleana.baixaExpressioBooleana(query);
    }

    //Post:Es modifica l'expressio booleana indicada en queryantiga si aquesta existia per querymodificada.
    public void modificaExpressioBooleana(String queryantiga, String querymodificada) throws Exception{
        ctrlExpressioBooleana.modificaExpressioBooleana(queryantiga,querymodificada);
    }


    //---CONSULTES---

    //Post: es retorna una llista amb el conjunt de titols de l'autor indicat.
    public List<String> titolsAutor(String autor) throws Exception{
        List<String> ls;
        if (existeixAutor(autor)) {
            Autor a = autors.get(autor);
            ls = a.consultaTitols();
        }
        else {
            throw new Exception("Autor no existent");
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
        Collections.sort(ls);
        return ls;
    }

   //Post: Es retorna el contingut del document referenciat per autor i titol si aquest existeix.
    public String obteContingut(String autor, String titol) throws Exception{
        if (existeixDocument(autor,titol)) {
            Document d = documents.getDocument(autor,titol);
            return d.getContingut();
        }
        else {
            throw new Exception("El document no existeix");
        }
    }

    //Post: Es retorna el conjunt de K documents mes semblants al document referenciat per titol autor si existeix.
    //      Si K >= al nombre de documents del sistema retorna null.
    //      L'algorisme per detectar semblançes sera Tf_idf si mode == 0 o Tf si mode == 1.
    public ConjuntDocuments DocumentsSemblants(String autor, String titol, Integer K, Integer mode) throws Exception{
        HashMap<Pair,Document> m = documents.getMap();
        if (K >= m.size()) {
            throw new Exception("El natural K és major als documents del sistema. K ha de ser menor a " + m.size() + ".");
        }
        if (existeixDocument(autor,titol)) {
            ConjuntDocuments cd;
            if (mode == 0) cd = DocumentsSemblants_TfIdf(autor,titol,K);
            else cd = DocumentsSemblants_Tf(autor,titol,K);
            return cd;
        }
        else {
            throw new Exception("El document no existeix");
        }
    }
    //Post: es retorna el conjunt de documents format per documents que contenen almenys una frase que compleix la query booleana.
    public ConjuntDocuments ConsultaBooleana(String query_) throws Exception{
        String query = Normalizer.normalize(query_, Normalizer.Form.NFKD);
        query = Normalizer.normalize(query, Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
        ConjuntDocuments cd = ctrlExpressioBooleana.evalua(query,documents);
        return cd;
    }

    //Post: Es retorna el nombre total de documents al sistema (si es major a 10 es retorna 10)
    public Integer numDocsTotal() {
        HashMap<Pair,Document> m = documents.getMap();
        Integer num = m.size();
        if (num > 10) num = 10;
        return num;
    }

    //Post: Es retorna true si l'autor existeix al sistema
    public Boolean estaAutor(String nom_autor) {
        return existeixAutor(nom_autor);
    }

    public Boolean existeixQuery(String query) {
        return ctrlExpressioBooleana.existeixQuery(query);
    }

    //Post: retorna true si el document existeix. False altrament.
    public boolean existeixDocument(String autor, String titol) {
        if (autors.containsKey(autor)) {
            Autor a = autors.get(autor);
            return a.conteTitol(titol);
        }
        else return false;
    }

    //---METODES ORDENACIO---


    //Post: es retorna un Conjunt de Documents ordenat segons el criteri escollit.
    //      El criteri d'ordenacio es per ordre alfabetic de titol i despres per autor si ordre == 0,
    //      per data de creacio del document si ordre == 1 o per ultima data de modificacio si ordre == 2.
    public ConjuntDocuments ordenaDocuments(ConjuntDocuments cd, int ordre) throws Exception{
        if (ordre != 0 && ordre != 1 && ordre != 2) {
            throw new Exception("El nombre a introduir per escollir ordenació ha de ser 0, 1 o 2");
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


    //Post: es carrega un nou document en el sistema.
    //      Si l'autor del document no existia es carrega l'autor.
    //      Es crea la relacio entre aquest nou document i l'autor.
    private void carregaDocument (String autor, String titol, String contingut, String dC, String dUM) throws Exception {
        Autor a = new Autor();
        if (!existeixAutor(autor)) {
            a.setNom(autor);
            autors.put(autor, a);
        }
        a = autors.get(autor);
        Document d = new Document(autor, titol, contingut,dC,dUM);
        a.afegirDocument(d);
        documents.afegirDocument(d);
    }

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


    //Pre: K < nombre de documents del sistema.
    //Post: Es retorna el conjunt de K documents mes semblants al document referenciat per titol autor si existeix.
    //      Utilitza algorisme Tf_idf.
    private ConjuntDocuments DocumentsSemblants_TfIdf(String autor, String titol, Integer K) {
        Document D = documents.getDocument(autor, titol);
        HashMap<Document, Double> TfIdf = documents.CalculTfIdf(D);
        HashMap<Document, Double> aux = sortMapByValue(TfIdf);
        HashMap<Pair,Document> ret = new HashMap<>(K);

        Iterator<Map.Entry<Document, Double>> it = aux.entrySet().iterator();
        while (it.hasNext() && ret.size()<K) {
            Map.Entry<Document, Double> entry = it.next();
            Pair p = new Pair(entry.getKey().getAutor(),entry.getKey().getTitol());
            ret.put(p,entry.getKey());
        }
        ConjuntDocuments cd = new ConjuntDocuments(ret);
        return cd;
    }

    //Pre: K < nombre de documents del sistema.
    //Post: Es retorna el conjunt de K documents mes semblants al document referenciat per titol autor si existeix.
    //      Utilitza algorisme Tf.
    private ConjuntDocuments DocumentsSemblants_Tf(String autor, String titol, Integer K) {
        Document D = documents.getDocument(autor, titol);
        HashMap<Document, Double> Tf = documents.CalculTf(D);
        HashMap<Document, Double> aux = sortMapByValue(Tf);
        HashMap<Pair,Document> ret = new HashMap<>(K);

        Iterator<Map.Entry<Document, Double>> it = aux.entrySet().iterator();
        while (it.hasNext() && ret.size()<K) {
            Map.Entry<Document, Double> entry = it.next();
            Pair p = new Pair(entry.getKey().getAutor(),entry.getKey().getTitol());
            ret.put(p,entry.getKey());
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
