package Domini;
import java.util.*;

public class ExpressioBooleana {
    private String query;
    private ExpressionTree expressio;

    public ExpressioBooleana(String e) {
        query = e;
        expressio = new ExpressionTree(e);
    }



    public void modificaExpressioBooleana(String e) {
        query = e;
        expressio.modifica(e);
    }
}
