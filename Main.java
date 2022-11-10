import Domini.*;
import CtrlDomini.CtrlDomini;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        String demanaAutor = "Introdueix un autor:\n";
        String demanaTitol = "Introdueix un títol:\n";
        String demanaContingut = "Introdueix el contingut:\n";
        String demanaQuery = "Introdueix una query:\n";
        String autor;
        String titol;
        String contingut;
        String query;
        CtrlDomini cd = CtrlDomini.getInstance();

        for (int i = 0; i < 4; ++i) {
            Autor a = new Autor("Aa"+i);
            System.out.println(a.getNom());
        }
        new Autor("Ab");
        while (!exit) {
            String menu = "-- MENU --\n"+
                    "1. Alta Document\n"+
                    "2. Baixa Document\n"+
                    "3. Modificar Document\n"+
                    "4. Llista de títols autor\n"+
                    "5. Llista d'autors per prefix\n"+
                    "6. Obtenir contingut d'un document\n"+
                    "7. Llistar documents semblants\n"+
                    "8. Llistar documents que compleixen una expressió\n"+
                    "9. Sortir\n";
            int opcio = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcio) {
                case 1:
                    autor = JOptionPane.showInputDialog(demanaAutor);
                    titol = JOptionPane.showInputDialog(demanaTitol);
                    contingut = JOptionPane.showInputDialog(demanaContingut);
                    cd.altaDocument(autor, titol, contingut);
                    break;
                case 2:
                    autor = JOptionPane.showInputDialog(demanaAutor);
                    titol = JOptionPane.showInputDialog(demanaTitol);
                    cd.baixaDocument(autor, titol);
                    break;
                case 3:
                    autor = JOptionPane.showInputDialog(demanaAutor);
                    titol = JOptionPane.showInputDialog(demanaTitol);
                    contingut = JOptionPane.showInputDialog(demanaContingut);
                    cd.modificarDocument(contingut, autor, titol);
                    break;
                case 4:
                    autor = JOptionPane.showInputDialog(demanaAutor);
                    cd.titolsAutor(autor);
                    break;
                case 5:
                    String prefix = JOptionPane.showInputDialog("Introdueix un prefix:\n");
                    ArrayList<String> autors = cd.prefixAutor(prefix);
                    for (String a : autors) {
                        JOptionPane.showMessageDialog(null, a);
                    }
                    break;
                case 6:
                    autor = JOptionPane.showInputDialog(demanaAutor);
                    titol = JOptionPane.showInputDialog(demanaTitol);
                    JOptionPane.showMessageDialog(null, cd.obteContingut(autor, titol));
                case 9:
                    query = JOptionPane.showInputDialog(demanaQuery);
                    JOptionPane.showMessageDialog(null, cd.ConsultaBooleana(query));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opcio);
            }
        }

    }
}
