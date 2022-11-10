import Domini.*;
import CtrlDomini.CtrlDomini;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        String autor;
        String titol;
        String contingut;
        String query;
        CtrlDomini cd = CtrlDomini.getInstance();
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
            System.out.print(menu);
            int opcio = Integer.parseInt(scan.nextLine());
            switch (opcio) {
                case 1:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println("Introdueix un titol:");
                    titol = scan.nextLine();
                    System.out.println("Introdueix un contingut:");
                    contingut = scan.nextLine();
                    cd.altaDocument(autor, titol, contingut);
                    cd.imprimirDocuments();
                    break;
                case 2:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println("Introdueix un titol:");
                    titol = scan.nextLine();
                    cd.baixaDocument(autor, titol);
                    cd.imprimirDocuments();
                    break;
                case 3:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println("Introdueix un titol:");
                    titol = scan.nextLine();
                    System.out.println("Introdueix un contingut:");
                    contingut = scan.nextLine();
                    cd.modificarDocument(contingut, autor, titol);
                    cd.imprimirDocuments();
                    break;
                case 4:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println(cd.titolsAutor(autor));
                    cd.imprimirAutors();
                    break;
                case 5:
                    System.out.println("Introdueix un prefix:");
                    String prefix = scan.nextLine();
                    ArrayList<String> autors = cd.prefixAutor(prefix);
                    System.out.println(autors);
                    break;
                case 6:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println("Introdueix un titol:");
                    titol = scan.nextLine();
                    System.out.println(cd.obteContingut(autor, titol));
                case 7:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println("Introdueix un titol:");
                    titol = scan.nextLine();
                    System.out.println("Introdueix el nombre de documents a retornar:");
                    int k = scan.nextInt();
                    System.out.println(cd.DocumentsSemblants(autor, titol, k));
                case 8:
                    System.out.println("Introdueix una query booleana:");
                    query = scan.nextLine();
                    System.out.println(cd.ConsultaBooleana(query));
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opcio);
            }
        }

    }
}
