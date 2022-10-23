import java.util.*;

 class OrdreAlfabetic implements Ordenacio {

     //ordenar per ordre alfabètic títol
    public void OrdenarDocuments(Vector<Document> vd) {
       /* HashMap<HashMap<String, String>, Document> docs = new HashMap<>();
        for (int i = 0; i < vd.size(); ++i) {
            Document d = vd.elementAt(i);
            String tit = d.getTitol();
            String a = d.getAutor();
            docs.put(docs.put(tit, a), d);

        } */
        Vector<Document> vdReal = new Vector<>();
        for (int i = 0; i < vd.size(); ++i) {
            Document d = vd.elementAt(i);
            String tit_doc = d.getTitol();
            /*INSERTAR EN VECTOR EL DOCUMENTO Y HACER ORDENACIÓN POR INSERCIÓN COMPARANDO TÍTULOS
              SI TÍTULOS FUERAN IGUALES, PASAMOS A COMPARAR AUTOR
             */
        }
    }

}
