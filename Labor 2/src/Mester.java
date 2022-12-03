public class Mester extends Jatekos {
    private int level;

    public Mester() {
        level = 0;
    }

    public Mester(int l) {
        level = l;
    }

    public String toString() {
        return "Mester " + level;
    }

    public void lep() {
        System.out.println(this.toString() + " Kör: " + asztal.getKor());
        asztal.emel(((100 + level) * asztal.getTet()) / 100);
    }
}
