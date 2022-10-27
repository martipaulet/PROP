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
     *   Opcions Menú
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
        if (!existeixAutor(autor)) { a = new Autor(autor); }
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

    public void baixaDocument(Document d) {
        desassociaAutor(d);
        documents.baixaDocument(d);
    }



    public void baixaCjtDocuments() {

    }



    //pre: nomes es pot modificar el contingut d'un document
    //post: S'actualizta el contingut i la data de ultima modificació del document si es que s'ha modificat
    public void modificarDocument(String nouContingut, Document d, Date novaData) {
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



}
