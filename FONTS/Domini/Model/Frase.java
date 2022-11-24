//import org.jetbrains.annotations.NotNull;
package Domini.Model;
import java.util.*;

public class Frase { //classe que representa la frase pertanyent a un document en concret.


    //---ATRIBUTS---

    private String frase_; //string que conte la frase.
    private String titolDoc_; //titol del document al que pertany la frase.
    private String autorDoc_; //autor del document al que pertany la frase.
    private final String[] paraules_; //frase separada per paraules.


    //---CONSTRUCTORA---


    //Pre: el document referenciat per titol i autor existeix en el sistema.
    //Post: es crea una instancia de frase amb titoldoc = titol, autordoc = autor, this.frase = frase
    //      es guarda el contingut de la frase separat per paraules.
    public Frase(String frase, String titol, String autor) {
        paraules_ = frase.split("\\W+");
        titolDoc_ = titol;
        autorDoc_ = autor;
        frase_ = frase;
    }


    //---GETTERS/SETTERS---


    //Post: retorna el titol del document a la que pertany la frase.
    public String getTitolDoc() {
        return titolDoc_;
    }

    //Post: retorna l'autor del document a la que pertany la frase.
    public String getAutorDoc() {
        return autorDoc_;
    }

    //Post: retorna el contingut de la frase.
    public String getFrase() {
        return frase_;
    }

    //---CONSULTORA---


    //Pre: l'string query es una paraula simple o una sequencia de paraules.
    //Post: retorna true si la frase conte la query exactament com a paraules. False altrament.
    public boolean conteQuery(String query) {
        String [] q2 = query.split("\\W+");
        boolean conte = false;
        boolean finish = false;
        for (int i = 0; i < paraules_.length; ++i) {
            if (paraules_[i].equals(q2[0])) {
                conte = true;
                for (int j = 1; j < q2.length && !finish; ++j) {
                    if ((i+j) == paraules_.length) {
                        finish = true;
                        conte = false;
                    }
                    else if (!paraules_[i+j].equals(q2[j])) conte = false;
                }
            }
        }
        return conte;
    }
}
