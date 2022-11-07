package CtrlDomini;
import Domini.*;
import javax.print.Doc;
import java.util.*;


public class CtrlDomini {

    //atributs
    private Map<String, Autor> autors;    //nom autor->Autor
    private ConjuntDocuments documents;

    //creadora
    public CtrlDomini() {
        inicialitzarCtrlDomini();
    }

    public void inicialitzarCtrlDomini() {
        autors = new HashMap<String, Autor> ();
        documents = new ConjuntDocuments();
    }


     /*
     *   ------------
     *   GESTIO ALTA, BAIXA, MODIFICACIO DE DOCUMENTS
     *   ------------
     * */


    //pre: ---
    //post: es crea un nou document en el sistema si no existia. En cas contrari es retorna una excepció
    //      Si l'autor del document no existia es crea.
    //      Es crea la relació entre aquest nou document i l'autor
    public void nouDocument(String autor, String titol, String contingut) {
        //comprovem si existeix l'autor per si cal crear-lo.
        //si ja existia l'autor comprovem que no existeixi el titol sino el document sería un ja existent.
        Autor a = null;
        if (!existeixAutor(autor)) a = new Autor(autor);
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


    //pre: el document d ja existeix.
    //post: S'elimina el document d i la seva relació amb l'autor.
    //      Si l'autor del document eliminat nomes tenia aquest document tambe s'eimina l'autor

    public void baixaDocument(String autor, String titol) {
        Document d = documents.getDocument(autor,titol);
        desassociaAutor(d);
        documents.baixaDocument(d);
    }



    public void baixaCjtDocuments() {

    }


    //pre: nomes es pot modificar el contingut d'un document
    //post: S'actualizta el contingut i la data de ultima modificació del document si es que s'ha modificat
    public void modificarDocument(String nouContingut, String autor, String titol, Date novaData) {
        Document d = documents.getDocument(autor,titol);
        if (!d.getContingut().equals(nouContingut)) {
            d.actualitzaDocument(nouContingut, novaData);
        }
    }


    //post: elimina l'associacio entre el document d i el seu autor.
    //      si l'autor nomes tenia aquell document retorna true. Altrament retorna false

    public void desassociaAutor(Document d) {
        Autor tmp = autors.get(d.getAutor());
        tmp.eliminaDocument(d);
        if(!tmp.teDocuments()) autors.remove(d.getAutor());
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

    public List<String> prefixAutor(String prefix) {
        List<String> ls = new ArrayList<String>();
        boolean algun = false;
        for (String nomAutor:autors.keySet()) {
            Autor a = autors.get(nomAutor);
            if (a.tePrefix(prefix)) {
                if (!algun) algun = true;
                ls.add(a.getNom());
            }
        }
        if (!algun) System.out.println("cap autor amb el prefix" + prefix);
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

    //falten consultes de documents pero falta definir tf-idf per a fer-les


    /*
     *   ------------
     *   ORDENAR CONSULTES
     *   ------------
     * */


    public boolean existeixAutor(String autor) {
        return autors.containsKey(autor);
    }


    public boolean existeixDocument(String autor, String titol) {
        if (autors.containsKey(autor)) {
            Autor a = autors.get(autor);
            return a.conteTitol(titol);
        }
        else return false;
    }

    //PRE: El numero de cops que la paraula apareix a cada document ja està calculat previament
    public Vector<Document> DocumentsSemblants(Document D, Integer K) {
        HashMap<String, Integer> Idf = documents.CalculCopsParaules(D);
        HashMap<Document, Integer> TiIdf = new HashMap<>();

    }



}
