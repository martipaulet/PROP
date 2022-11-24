package Domini.Model;
import java.text.Collator;
import java.util.*;

 public class OrdreAlfabetic implements Ordenacio {


     //Post: retorna un conjunt de documents ordenat alfabeticament per titol i despres per autor.
     public ConjuntDocuments OrdenarDocuments(ConjuntDocuments cd) throws Exception {
         HashMap<Pair,Document> vd = cd.getMap();
         Vector<Document> vdReal = new Vector<>();
         Collator comparador = Collator.getInstance();
         comparador.setStrength(Collator.PRIMARY);
         for (Pair key: vd.keySet()) {
             Document d = vd.get(key);
             String tit_doc = d.getTitol();
             vdReal.add(d);
             if (vdReal.size() > 1) {
                 boolean posicio = false;
                 for (int j = vdReal.size() - 2; j >= 0 && !posicio; --j) {
                     Document dcomp = vdReal.elementAt(j);
                     String tit = dcomp.getTitol();
                     //tit_doc va antes alfabéticamente
                     if (comparador.compare(tit_doc, tit) < 0) {
                         Collections.swap(vdReal, j, j+1);
                     }
                     //tit_doc va después alfabéticamente
                     else if (comparador.compare(tit_doc, tit) > 0) {
                         posicio = true;
                     }
                     //Titulos iguales, comparar autor
                     else {
                         String autor1 = d.getAutor();
                         String autor2 = dcomp.getAutor();
                         if (comparador.compare(autor1, autor2) < 0) {
                             Collections.swap(vdReal, j, j+1);
                         }
                         else posicio = true;
                     }
                 }
             }
         }
         ConjuntDocuments cdordenat = new ConjuntDocuments();
         for (int i = 0; i<vdReal.size(); ++i){
             cdordenat.afegirDocument(vdReal.elementAt(i));
         }

         return cdordenat;
     }
}
