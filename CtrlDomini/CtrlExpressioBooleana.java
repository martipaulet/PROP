package CtrlDomini;
import Domini.ExpressioBooleana;

import java.util.*;

public class CtrlExpressioBooleana {
    private HashMap<String, ExpressioBooleana> expressions;

    public void inicialitzar_ctrl() {
        expressions = new HashMap<>();
    }

    public void altaExpressioBooleana(String e) {
        ExpressioBooleana eb = new ExpressioBooleana(e);
        expressions.put(e, eb);
    }

    public void baixaExpressioBooleana(String e) {
        expressions.remove(e);
    }

    public void modificaExpressioBooleana(String queryantiga, String querymodificada) {
        ExpressioBooleana eb = expressions.get(queryantiga);
        expressions.remove(queryantiga);
        eb.modificaExpressioBooleana(querymodificada);
        expressions.put(querymodificada, eb);
    }

}
