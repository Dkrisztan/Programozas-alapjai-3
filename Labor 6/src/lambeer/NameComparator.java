package lambeer;

import java.util.Comparator;

public class NameComparator implements Comparator<Beer> {
    public int compare(Beer beer1, Beer beer2) {
        return beer1.name.compareTo(beer2.name);
    }
}
