package CtrlDomini;
import Domini.*;

import java.util.*;

import static java.util.stream.Collectors.toMap;


public class CtrlDomini {


    /**
     * Attributes
     */
    private Map<String, Autor> autors;    //nom autor->Autor
    private ConjuntDocuments documents;
    private static CtrlDomini instance;
    private CtrlExpressioBooleana CtrlExpressioBooleana;


    /**
     * Constructor
     */
    public CtrlDomini() {
        inicialitzarCtrlDomini();
    }
    private void inicialitzarCtrlDomini() {
        autors = new HashMap<> ();
        documents = new ConjuntDocuments();
        CtrlExpressioBooleana = new CtrlExpressioBooleana();
    }
    public static CtrlDomini getInstance() {
        if (instance == null) instance = new CtrlDomini();
        return instance;
    }


     /*
     *   --------------------------------------------
     *   GESTIO ALTA, BAIXA, MODIFICACIO DE DOCUMENTS
     *   --------------------------------------------
     * */


    //pre: ---
    //post: es crea un nou document en el sistema si no existia. En cas contrari es retorna una excepció
    //      Si l'autor del document no existia es crea.
    //      Es crea la relació entre aquest nou document i l'autor
    public void altaDocument(String autor, String titol, String contingut) {
        Autor a = new Autor();
        if (!existeixAutor(autor)) {
            a.setNom(autor);
            autors.put(autor, a);
        }
        else {
            a = autors.get(autor);
            if (a.conteTitol(titol)) {
                System.out.print("Document ja existent"); // Excepcions??
                return;
            }
        }
        Document d = new Document(autor, titol, contingut);
        a.afegirDocument(d);
        documents.afegirDocument(d);
    }


    //pre: el document d ja existeix.
    //post: S'elimina el document d i la seva relació amb l'autor.
    //      Si l'autor del document eliminat nomes tenia aquest document tambe s'eimina l'autor
    public void baixaDocument(String autor, String titol) {
        Document d = documents.getDocument(autor,titol);
        desassociaAutor(d);
        documents.baixaDocument(d);
    }



    public void baixaCjtDocuments(ConjuntDocuments d) {

    }


    //pre: nomes es pot modificar el contingut d'un document
    //post: S'actualizta el contingut i la data de ultima modificació del document si es que s'ha modificat
    public void modificarDocument(String nouContingut, String autor, String titol) {
        Document d = documents.getDocument(autor,titol);
        if (d != null && !d.getContingut().equals(nouContingut)) {
            d.actualitzaDocument(nouContingut);
        }
    }


    /*
     *   ------------
     *   CONSULTES
     *   ------------
     * */

    public List<String> titolsAutor(String autor) {
        List<String> ls = new ArrayList<String>();
        if (existeixAutor(autor)) {
            Autor a = autors.get(autor);
            ls = a.consultaTitols();
        }
        else {
            System.out.println("Autor no existent");
        }
        return ls;
    }

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
        if (!algun) System.out.println("Cap autor amb el prefix" + prefix);
        return ls;
    }

    //si existeix el document retorna el seu contingut
    //en cas contrari retorna null
    public String obteContingut(String autor, String titol) {
        if (existeixDocument(autor,titol)) {
            Document d = documents.getDocument(autor,titol);
            return d.getContingut();
        }
        else {
            return null;
        }
    }

    //CALCUL DOCUMENTS SEMBLANTS 1
    //PRE: El numero de cops que la paraula apareix a cada document ja està calculat previament (TF)
    public ConjuntDocuments DocumentsSemblants_TfIdf(String autor, String titol, Integer K) {
        Document D = documents.getDocument(autor, titol);
        HashMap<Document, Double> TfIdf = documents.CalculTfIdf(D);
        HashMap<Document, Double> aux = sortMapByValue(TfIdf);
        Vector<Document> ret = new Vector<>(K);

        //No se si el bucle agafa el mapa ordenat i no el desordena
        Iterator<Map.Entry<Document, Double>> it = aux.entrySet().iterator();
        while (it.hasNext() && ret.size()<K) {
            Map.Entry<Document, Double> entry = it.next();
            ret.add(entry.getKey());
        }
        ConjuntDocuments cd = new ConjuntDocuments(ret);
        cd.imprimir();
        return cd;
    }

    //CALCUL DOCUMENTS SEMBLANTS 2
    //PRE: El numero de cops que la paraula apareix a cada document ja està calculat previament (TF)
    public ConjuntDocuments DocumentsSemblants_Tf(String autor, String titol, Integer K) {
        Document D = documents.getDocument(autor, titol);
        HashMap<Document, Double> Tf = documents.CalculTf(D);
        HashMap<Document, Double> aux = sortMapByValue(Tf);
        Vector<Document> ret = new Vector<>(K);

        //No se si el bucle agafa el mapa ordenat i no el desordena
        Iterator<Map.Entry<Document, Double>> it = aux.entrySet().iterator();
        while (it.hasNext() && ret.size()<K) {
            Map.Entry<Document, Double> entry = it.next();
            ret.add(entry.getKey());
        }
        ConjuntDocuments cd = new ConjuntDocuments(ret);
        cd.imprimir();
        return cd;
    }


    public ConjuntDocuments ConsultaBooleana(String query) {
        CtrlExpressioBooleana = CtrlExpressioBooleana.getInstance();
        ConjuntDocuments cd = CtrlExpressioBooleana.evalua(query,documents);
        return cd;
    }



    /*
     *   ------------
     *   METODES PRIVATS
     *   ------------
     * */

    //post: elimina l'associacio entre el document d i el seu autor.
    //      si l'autor nomes tenia aquell document retorna true. Altrament retorna false
    private void desassociaAutor(Document d) {
        Autor tmp = autors.get(d.getAutor());
        tmp.eliminaDocument(d);
        if(!tmp.teDocuments()) autors.remove(d.getAutor());
    }


    private boolean existeixAutor(String autor) {
        return autors.containsKey(autor);
    }

    private boolean existeixDocument(String autor, String titol) {
        if (autors.containsKey(autor)) {
            Autor a = autors.get(autor);
            return a.conteTitol(titol);
        }
        else return false;
    }

    //Ordena el hashmap per valor descendentment(-1)
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

    public void imprimirDocuments() {
        documents.imprimir();
    }

    public void imprimirAutors() {
        System.out.println(autors.keySet());
    }

}
