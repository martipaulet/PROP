package Domini.DataInterfaces;

import java.util.HashMap;
import java.util.Vector;
import java.util.Map;

public interface CtrlDocuments {


    public void guardaDocuments(Vector<Vector<String>> docs); //cada fila es un document amb les columnes -> autor, titol, contingut, datacreacio, dataUltimaModificacio
    public Vector<Vector<String>> carregaDocuments();
}
