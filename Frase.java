import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Frase {
    private String[] paraules_;

    public Frase(String paraules) {
        paraules_ = paraules.split("\\W+");
    }
}
