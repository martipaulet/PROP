package Domini.DataInterfaces;

import Domini.Model.Document;
import Domini.Model.Pair;
import java.util.HashMap;
import java.util.Map;

public interface CtrlDocuments {
    public void saveDocuments(HashMap<Pair, Document> m);
    public HashMap<Pair, Document> loadDocuments();
}
