package CtrlDomini;
import Domini.*;

import javax.print.Doc;
import java.util.*;

public class CtrlExpressioBooleana {


    /**
     * Attributes
     */
    private HashMap<String, ExpressionTree> expressions;
    private static CtrlExpressioBooleana instance;

    /**
     * Constructor
     */
    public CtrlExpressioBooleana() {
        inicialitzarCtrlExpressioBooleana();
    }
    private void inicialitzarCtrlExpressioBooleana() {
        expressions = new HashMap<>();
    }
    public static CtrlExpressioBooleana getInstance() {
        if (instance == null) instance = new CtrlExpressioBooleana();
        return instance;
    }


    /**
     * Public Functions
     */
    public void altaExpressioBooleana(String query) {
        if (!expressions.containsKey(query)) {
            ExpressionTree e2 = new ExpressionTree(query);
            expressions.put(query, e2);
        }
        else System.out.println("La query booleana ja existeix en el sistema");
    }

    public void baixaExpressioBooleana(String query) {
        if (expressions.containsKey(query)) {
            expressions.remove(query);
        }
        else {
            System.out.println("La query:" +query+ "no existeix en el sistema per tant no es pot donar de baixa");
        }
    }

    public void modificaExpressioBooleana(String queryantiga, String querymodificada) {
        if (expressions.containsKey(queryantiga) && !expressions.containsKey(querymodificada)) {
            ExpressionTree e = expressions.get(queryantiga);
            e.modifica(querymodificada);
            expressions.remove(queryantiga);
            expressions.put(querymodificada, e);
        }
        else if (!expressions.containsKey(queryantiga)){
            System.out.println("La query: " +queryantiga+ "no existeix en el sistema per tant no es pot modificar");
        }
        else System.out.println("La query "+querymodificada+ "ja existeix al sistema");
    }


    public ConjuntDocuments evalua(String query, ConjuntDocuments total) {
        if (!expressions.containsKey(query)) {
            altaExpressioBooleana(query);
        }
        ExpressionTree e = expressions.get(query);
        ConjuntDocuments cd = e.calculate(total);
        return cd;
    }

}
