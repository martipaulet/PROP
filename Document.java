import java.util.*;

public class Document {
    private Date data_creacio;
    private Date data_modificacio;
    private String contingut;
    private HashMap<String, Integer> paraules;

    private Autor autor_doc;
    private Titol titol_doc;
    private Vector<Frase> frases;
    //private ConjuntDocuments DocumentsBD;

    public Document (Autor a, Titol t, String c) {
        //creadora d'un nou document
    }

    public void modificaDocument(/* paràmetres*/) {
        //modifica els paràmetres que seleccioni i passi com paràmetre
    }

    public void getContingut() {
        //mostra el contingut del document
    }

    public void llistaDocuments() {
        //llista documents del conjunt segons criteri ordenacio
        //trucar al criteri d'ordenacio
    }
}
