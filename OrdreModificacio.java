import java.text.Collator;
import java.util.*;


//ALBERT CARDONA ZAERA

public class OrdreModificacio implements Ordenacio {


    //ordenar per dates de modificació (data més propera abans que data més llunyana)
    //FALTA COMPROBACIÓ

    public void OrdenarDocuments(Vector<Document> vd) {
        Vector<Document> DocOrdenats = new Vector<>();
        for (int i=0; i<vd.size(); ++i){
            Document di = vd.elementAt(i);
            Date data_di = di.getDataUltimaModificacio();
            DocOrdenats.add(di);
            if(DocOrdenats.size() > 1){
                Boolean acaba = false;
                for (int j = DocOrdenats.size()-2; DocOrdenats.size() >= 0 && acaba==false; --j){
                    Document dj = vd.elementAt(j);
                    Date data_dj = dj.getDataUltimaModificacio();

                    //data va abans en vector, canvi
                    if(data_di.after(data_dj)){
                        Collections.swap(DocOrdenats, j, j+1);
                    }

                    //data va despres en vector, no canvi
                    if(data_di.before(data_dj)){
                        acaba=true;
                    }

                    //data igual, ordre alfabetic per titol i/o autor
                    else{
                        Collator comparador = Collator.getInstance();
                        comparador.setStrength(Collator.PRIMARY);
                        String titol_di = di.getTitol();
                        String titol_dj = dj.getTitol();

                        //titol_di va abans que titol_dj
                        if (titol_di.compareTo(titol_dj) > 0){
                            Collections.swap(DocOrdenats, j, j+1);
                        }

                        //titol_di va despres que titol_dj
                        if (titol_di.compareTo(titol_dj) < 0){
                            acaba = true;
                        }

                        //titols iguals, comparem autors
                        else {
                            Autor ai = di.getAutor();
                            String autor_i = ai.getNom();
                            Autor aj = dj.getAutor();
                            String autor_j = aj.getNom();

                            //autor_i va abans que autor_j
                            if (autor_i.compareTo(autor_j) > 0) {
                                Collections.swap(DocOrdenats, j, j+1);
                            }

                            //autor_i va despres que autor_j
                            else acaba = true;
                        }
                    }
                }
            }
        }
        vd = DocOrdenats;
    }
}
