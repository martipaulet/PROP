package Domini;
import java.text.Collator;
import java.util.*;


//ALBERT CARDONA ZAERA

public class OrdreCreacio implements Ordenacio {

    //ordenar per dates de creacio (data més propera abans que data més llunyana)
    //FALTA COMPROBACIÓ

    public Vector<Document> OrdenarDocuments(Vector<Document> vd) {
        Vector<Document> DocOrdenats = new Vector<>();
        for (int i=0; i<vd.size(); ++i){
            Document di = vd.elementAt(i);
            Date data_di = di.getDataCreacio();
            DocOrdenats.add(di);
            if(DocOrdenats.size() > 1){
                Boolean acaba = false;
                for (int j = DocOrdenats.size()-2; j >= 0 && !acaba; --j){
                    Document dj = DocOrdenats.elementAt(j);
                    Date data_dj = dj.getDataCreacio();

                    //data va abans en vector, canvi
                    if(data_di.after(data_dj)){
                        Collections.swap(DocOrdenats, j, j+1);
                    }

                    //data va despres en vector, no canvi
                    else if(data_di.before(data_dj)){
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
                            acaba = true;
                        }

                        //titol_di va despres que titol_dj
                        else if (titol_di.compareTo(titol_dj) < 0){
                            Collections.swap(DocOrdenats, j, j+1);
                        }

                        //titols iguals, comparem autors
                        else {
                            String autor_i = di.getAutor();
                            String autor_j = dj.getAutor();

                            //autor_i va abans que autor_j
                            if (autor_i.compareTo(autor_j) > 0) {
                                acaba = true;
                            }

                            //autor_i va despres que autor_j
                            else Collections.swap(DocOrdenats, j, j+1);
                        }
                    }
                }
            }
        }
        return DocOrdenats;
    }
}
