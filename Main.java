import java.util.*;

public class Main {
    public static void main(String[] args) {
        Autor a = new Autor("George R. Martin");
        String c = "Això és: un exemple, de contingut. És un exemple amb paraules repetides";
        Document d = new Document(a, "Juego de tronos", c);
        d.imprimir();
    }
}
