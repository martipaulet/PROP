package Drivers;
import Domini.Autor;
import java.util.*;

public class DriverAutor {
    private Autor prova;

    public void testConstructor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom de l'autor");
        String nom = sc.nextLine();
        prova = new Autor(nom);
        System.out.println("Instància de la classe Autor creada");
    }

    public void testgetNom() {
        String result = prova.getNom();
        System.out.println("L'autor es diu " + result);
    }

    public void testconsultaTitols() {
        List<String> ls = prova.consultaTitols();
    }
    public void testconteTitol() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el titol");
        String titol = sc.nextLine();
        if (prova.conteTitol(titol)) System.out.println("És autor del document amb títol" + titol);
        else System.out.println("No és autor del document amb títol " + titol);
    }
    public void testafegirDocument() {
        Scanner sc = new Scanner(System.in);
        System.out.println
    }
    public void testeliminaDocument() {}
    public void testobteDocument() {}
    public void testtePrefix() {}
    public void testteDocuments() {}
    public void testimprimir() {}
    public static void main (String args[]) {}
}
