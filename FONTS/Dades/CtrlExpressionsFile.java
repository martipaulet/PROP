package Dades;

import java.util.ArrayList;
import java.util.List;

public class CtrlExpressionsFile {

    private static CtrlExpressionsFile instance;

    public static CtrlExpressionsFile getInstance() {
        if (instance == null) instance = new CtrlExpressionsFile();
        return instance;
    }

    public void guardaExpressions(List<String> l) {

    }
    public ArrayList<String> carregaExpressions() {
        return null;
    }
}
