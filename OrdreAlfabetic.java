import java.text.Collator;
import java.util.*;

 class OrdreAlfabetic implements Ordenacio {

     //ordenar per ordre alfabètic títol
     //FALTA COMPROBACIÓ
    public void OrdenarDocuments(Vector<Document> vd) {
        Vector<Document> vdReal = new Vector<>();
        Collator comparador = Collator.getInstance();
        comparador.setStrength(Collator.PRIMARY);
        for (int i = 0; i < vd.size(); ++i) {
            Document d = vd.elementAt(i);
            String tit_doc = d.getTitol();
            vdReal.add(d);
            if (vdReal.size() > 1) {
                Boolean posicio = false;
                for (int j = vdReal.size() - 2; vdReal.size() >= 0; --j) {
                    while (posicio == false) {
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
                            Autor a1 = d.getAutor();
                            String autor1 = a1.getNom();
                            Autor a2 = dcomp.getAutor();
                            String autor2 = a2.getNom();
                            if (comparador.compare(autor1, autor2) < 0) {
                                Collections.swap(vdReal, j, j+1);
                            }
                            else posicio = true;
                        }
                    }
                }
            }
        }
        vd = vdReal;
    }

}
