package lambeer;

import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer> {
    public int compare(Beer beer1, Beer beer2) {
        return Double.compare(beer1.strength, beer2.strength);
    }
}
