package Drivers;

import Domini.Controladors.CtrlDomini;
import Domini.Model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverCtrlDomini {
    public static void main(String[] args) throws Exception {
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
                    "4. Consulta titols autor\n"+
                    "5. Consulta autors prefix\n"+
                    "6. Consulta contingut\n"+
                    "7. Llistar documents semblants\n"+
                    "8. Llistar documents que compleixen una expressio\n"+
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
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Introdueix un autor:");
                        autor = scan.nextLine();
                        System.out.println("Introdueix un titol:");
                        titol = scan.nextLine();
                        cd.baixaDocument(autor, titol);
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Introdueix un autor:");
                        autor = scan.nextLine();
                        System.out.println("Introdueix un titol:");
                        titol = scan.nextLine();
                        System.out.println("Introdueix un contingut:");
                        contingut = scan.nextLine();
                        cd.modificarDocument(contingut, autor, titol);
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Introdueix un autor:");
                        autor = scan.nextLine();
                        System.out.println(cd.titolsAutor(autor));
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Introdueix un prefix:");
                        String prefix = scan.nextLine();
                        ArrayList<String> autors = cd.prefixAutor(prefix);
                        System.out.println(autors);
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 6:
                    try {
                        System.out.println("Introdueix un autor:");
                        autor = scan.nextLine();
                        System.out.println("Introdueix un titol:");
                        titol = scan.nextLine();
                        System.out.println(cd.obteContingut(autor, titol));
                    } catch (Exception e){
                        System.err.println(e.toString());
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Introdueix un autor:");
                        autor = scan.nextLine();
                        System.out.println("Introdueix un titol:");
                        titol = scan.nextLine();
                        System.out.println("Introdueix el nombre de documents (enter positiu) a retornar:");
                        int k = Integer.parseInt(scan.nextLine());
                        System.out.println("Introdueix el mode per fer la cerca:");
                        System.out.println("0 per utilitzar Tf_idf");
                        System.out.println("1 per utilitzar Tf");
                        int mode = Integer.parseInt(scan.nextLine());
                        ConjuntDocuments c = cd.DocumentsSemblants(autor, titol, k, mode);
                        System.out.println("Introdueix un nombre per escollir el tipus d'ordenacio:");
                        System.out.println("0 per ordenacio alfabetica per titol");
                        System.out.println("1 per ordenacio segons data de creacio mes propera");
                        System.out.println("2 per ordenacio segons data de modificacio mes propera");
                        int ordre = Integer.parseInt(scan.nextLine());
                        ConjuntDocuments cdfi = cd.ordenaDocuments(c, ordre);
                        if (cdfi != null) cdfi.imprimir();
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 8:
                    try {
                        System.out.println("Introdueix una query booleana:");
                        query = scan.nextLine();
                        ConjuntDocuments c2 = cd.ConsultaBooleana(query);
                        System.out.println("Introduex un nombre per escollir el tipus d'ordenacio:");
                        System.out.println("0 per ordenacio alfabetica per títol");
                        System.out.println("1 per ordenacio segons data de creacio més propera");
                        System.out.println("2 per ordenacio segons data de modificacio més propera");
                        int ordre2 = Integer.parseInt(scan.nextLine());
                        ConjuntDocuments cdfi2 = cd.ordenaDocuments(c2, ordre2);
                        cdfi2.imprimir();
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 9:
                    try {
                        System.out.println("Introdueix una query booleana:");
                        query = scan.nextLine();
                        cd.altaExpressioBooleana(query);
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 10:
                    try {
                        System.out.println("Introdueix una query booleana:");
                        query = scan.nextLine();
                        cd.baixaExpressioBooleana(query);
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
                    break;
                case 11:
                    try {
                        System.out.println("Introdueix la query booleana a modificar:");
                        String queryaux = scan.nextLine();
                        System.out.println("Introdueix la query booleana nova:");
                        query = scan.nextLine();
                        cd.modificaExpressioBooleana(queryaux,query);
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }
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
