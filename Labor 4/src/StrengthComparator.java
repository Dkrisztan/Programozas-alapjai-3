import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer> {
    public int compare(Beer beer1, Beer beer2) {
        if (beer1.strength == beer2.strength) {
            return 0;
        } else if (beer1.strength < beer2.strength) {
            return -1;
        } else {
            return 1;
        }
    }
}
