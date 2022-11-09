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
        ExpressionTree e = new ExpressionTree(query);
        expressions.put(query, e);
    }

    public void baixaExpressioBooleana(String query) {
        expressions.remove(query);
    }

    public void modificaExpressioBooleana(String queryantiga, String querymodificada) {
        ExpressionTree e = expressions.get(queryantiga);
        expressions.remove(queryantiga);
        e.modifica(querymodificada);
        expressions.put(querymodificada, e);
    }

    public Set<Document> evalua(String query, ConjuntDocuments total) {
        if (!expressions.containsKey(query)) {
            altaExpressioBooleana(query);
        }
        ExpressionTree e = expressions.get(query);
        ConjuntDocuments cd = e.calculate(total);
        Set<Document> sd = VecToSec(cd.getVector());
        return sd;
    }

}
