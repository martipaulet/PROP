import java.util.*;

public class Main {
    public static void main(String[] args) {
        Autor a = new Autor("George R. Martin");
        String c = "Aixo es: un exemple, de contingut. Es un exemple amb paraules repetides";
        Document d = new Document(a, "Juego de tronos", c);
        d.imprimir();
    }
}
