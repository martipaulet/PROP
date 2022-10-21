import java.util.*;

public class Main {
    public static void main(String[] args) {
        Titol t = new Titol("Juego de tronos");
        Autor a = new Autor("George R. Martin");
        String c = "Això és: un exemple, de contingut. És un exemple amb paraules repetides";
        Document d = new Document(a, t, c);
        d.imprimir();
    }
}
