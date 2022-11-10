package JUnit;
import java.util.*;
import Domini.Frase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FraseTest {

    private Frase f;

    //@Before
    @Test
    public void FraseTest() {
        Frase f = new Frase("un munt de paraules", "Un munt", "Joan");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("un munt de paraules");
        expected.add("Un munt");
        expected.add("Joan");

        ArrayList<String> actuals = new ArrayList<>();
        actuals.add(f.getFrase());
        actuals.add(f.getTitolDoc());
        actuals.add(f.getAutorDoc());

        assertEquals(expected, actuals);
    }

}
