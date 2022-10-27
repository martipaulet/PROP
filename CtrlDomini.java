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
        if (desassociaAutor(d)) {
            autors.remove(d.getAutor());
        }
        documents.baixaDocument(d);
    }



    public void baixaCjtDocuments() {

    }



    //EXC: Si la parella nouAutor, nouTitol ja existia es rebutja la modificació.
    //post: Actualitzar autor al document modificat. Si no existia es crea el nouAutor.
    //          -> 1) AutorAntic amb més documents: dessasociar document amb l'AutorAntic i fer nova associació
    //          -> 2) AutorAntic amb únic document: l'AutorAntic passa a no tenir documents -> eliminar autor
    //      Actualitzar titol al document modificat.
    //      Actualitzar contingut al document modificat.
    //      Actualitzar dataUltimaModificacio al document modificat.
    //      Actualitzar setFrases i setParaules al document modificat

    public void modificarDocument(String nouAutor, String nouTitol, String nouContingut, Document d, Date novaData) {
        boolean canviAutor = d.getAutor().equals(nouAutor);
        boolean canviTitol = d.getTitol().equals(nouTitol);

        //Cambien els 2: comprobar que no existeix el nou document(nouAutor,nouTitol)
        //Cambia nomes titol: comprobar que l'autor no tenia ja un document amb aquest nouTitol
        //cambia nomes autor: comprobar que nouAutor no tenia ja un document amb aquell titol

        if (canviAutor && canviTitol) {
            if (!existeixDocument(nouAutor,nouTitol)) {
                //eliminar associacio anticAutor amb anticDocument
                //si anticAutor nomes tenia anticDocument eliminar l'autor
                if (desassociaAutor(d)) {
                    autors.remove(d.getAutor());
                }

            }
        }

            if (!existeixAutor(nouAutor)) {
                Autor a = new Autor(nouAutor);
            }
            Autor a = autors.get(nouAutor);

            if (desassociaAutor(d)) {
                autors.remove(d.getAutor());
            }

            d.actualitzaDocument(nouAutor, novaData);
        }


    //post: elimina l'associacio entre el document d i el seu autor.
    //      si l'autor nomes tenia aquell document retorna true. Altrament retorna false

    public boolean desassociaAutor(Document d) {

        return true;

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
