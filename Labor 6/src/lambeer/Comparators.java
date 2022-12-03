package lambeer;

import java.util.Comparator;
import java.util.HashMap;

public class Comparators {
    static HashMap<String, Comparator<Beer>> comps = new HashMap<>();
    static {
        comps.put("name", Comparator.comparing(Beer::getName));
        comps.put("style", Comparator.comparing(Beer::getStyle));
        comps.put("strength", Comparator.comparing(Beer::getStrength));
    }
}
