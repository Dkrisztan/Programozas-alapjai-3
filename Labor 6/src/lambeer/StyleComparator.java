package lambeer;

import java.util.Comparator;

public class StyleComparator implements Comparator<Beer> {
    public int compare(Beer beer1, Beer beer2) {
        return beer1.style.compareTo(beer2.style);
    }
}
