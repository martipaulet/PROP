package Domini.Controladors;
import Domini.Model.*;

import javax.print.Doc;
import java.util.*;

public class CtrlExpressioBooleana {


    //---ATRIBUTS---


    private HashMap<String, ExpressionTree> expressions; //Conjunt d'expresions booleanes.
    private static CtrlExpressioBooleana instance;


    //---CONSTRUCTORA---


    //Post: es crea una instancia de CtrlExpressioBooleana.
    public CtrlExpressioBooleana() {
        inicialitzarCtrlExpressioBooleana();
    }

    //Post: S'inicialitzen les variables del CtrlExpressioBooleana.
    private void inicialitzarCtrlExpressioBooleana() {
        expressions = new HashMap<>();
    }

    //Post: Retorna la instancia de CtrlExpressioBooleana. Si no existeix cap instancia de CtrlExpressioBooleana, es crea.
    public static CtrlExpressioBooleana getInstance() {
        if (instance == null) instance = new CtrlExpressioBooleana();
        return instance;
    }


    //---GESTIO EXPRESIONS BOOLEANES---


    //Post: Es crea una nova expressio booleana si aquesta no existia.
    public void altaExpressioBooleana(String query) throws Exception{
        if (!expressions.containsKey(query)) {
            ExpressionTree e2 = new ExpressionTree(query);
            expressions.put(query, e2);
        }
        else throw new Exception("La query booleana ja existeix en el sistema");
    }

    //Post: S'elimina l'expressió booleana si aquesta existia.
    public void baixaExpressioBooleana(String query) throws Exception{
        if (expressions.containsKey(query)) {
            expressions.remove(query);
        }
        else {
            throw new Exception("La query: " +query+ " no existeix en el sistema per tant no es pot donar de baixa");
        }
    }

    //Post: Es modifica l'expressio booleana indicada en queryantiga (si aquesta existia) per querymodificada (si aquesta no existia).
    public void modificaExpressioBooleana(String queryantiga, String querymodificada) throws Exception{
        if (expressions.containsKey(queryantiga) && !expressions.containsKey(querymodificada)) {
            ExpressionTree e = expressions.get(queryantiga);
            e.modifica(querymodificada);
            expressions.remove(queryantiga);
            expressions.put(querymodificada, e);
        }
        else if (!expressions.containsKey(queryantiga)){
            throw new Exception("La query: " +queryantiga+ " no existeix en el sistema per tant no es pot modificar");
        }
        else throw new Exception("La query "+querymodificada+ " ja existeix al sistema");
    }


    //---CONSULTORA---


    //Post: es retorna el conjunt de documents format per documents que contenen almenys una frase que compleix la query booleana.
    public ConjuntDocuments evalua(String query, ConjuntDocuments total) throws Exception{
        if (!expressions.containsKey(query)) {
            altaExpressioBooleana(query);
        }
        ExpressionTree e = expressions.get(query);
        ConjuntDocuments cd = e.calculate(total);
        return cd;
    }

}
