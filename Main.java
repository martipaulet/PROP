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
                    "4. Consulta títols autor\n"+
                    "5. Consulta autors prefix\n"+
                    "6. Consulta contingut\n"+
                    "7. Llistar documents semblants\n"+
                    "8. Llistar documents que compleixen una expressió\n"+
                    "9. Alta Expressio Booleana\n"+
                    "10. Baixa Expressio Booleana\n"+
                    "11. Modificar Expressio Booleana\n"+
                    "12. Sortir\n";
            System.out.print(menu);
            int opcio = Integer.parseInt(scan.nextLine());
            switch (opcio) {
                case 1:
                    try {
                        System.out.println("Introdueix un autor:");
                        autor = scan.nextLine();
                        System.out.println("Introdueix un titol:");
                        titol = scan.nextLine();
                        System.out.println("Introdueix un contingut:");
                        contingut = scan.nextLine();
                        cd.altaDocument(autor, titol, contingut);
                        break;
                    }
                    catch(Exception e) {
                        e.toString();
                    }
                case 2:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println("Introdueix un titol:");
                    titol = scan.nextLine();
                    cd.baixaDocument(autor, titol);
                    break;
                case 3:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println("Introdueix un titol:");
                    titol = scan.nextLine();
                    System.out.println("Introdueix un contingut:");
                    contingut = scan.nextLine();
                    cd.modificarDocument(contingut, autor, titol);
                    break;
                case 4:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println(cd.titolsAutor(autor));
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
                    if (cd.obteContingut(autor,titol) != null) {
                        System.out.println(cd.obteContingut(autor, titol));
                    }
                    break;
                case 7:
                    System.out.println("Introdueix un autor:");
                    autor = scan.nextLine();
                    System.out.println("Introdueix un titol:");
                    titol = scan.nextLine();
                    System.out.println("Introdueix el nombre de documents a retornar:");
                    int k = scan.nextInt();
                    System.out.println("Introdueix el mode per fer la cerca:");
                    System.out.println("0 per utilitzar Tf_idf");
                    System.out.println("1 per utilitzar Tf");
                    int mode = scan.nextInt();
                    ConjuntDocuments c = cd.DocumentsSemblants(autor, titol, k, mode);
                    if (c != null) c.imprimir();
                    break;
                case 8:
                    System.out.println("Introdueix una query booleana:");
                    query = scan.nextLine();
                    ConjuntDocuments c2 = cd.ConsultaBooleana(query);
                    c2.imprimir();
                    break;
                case 9:
                    System.out.println("Introdueix una query booleana:");
                    query = scan.nextLine();
                    cd.altaExpressioBooleana(query);
                    break;
                case 10:
                    System.out.println("Introdueix una query booleana:");
                    query = scan.nextLine();
                    cd.baixaExpressioBooleana(query);
                    break;
                case 11:
                    System.out.println("Introdueix la query booleana a modificar:");
                    String queryaux = scan.nextLine();
                    System.out.println("Introdueix la query booleana nova:");
                    query = scan.nextLine();
                    cd.modificaExpressioBooleana(queryaux,query);
                    break;
                case 12:
                    exit = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opcio);
            }
        }

    }
}
