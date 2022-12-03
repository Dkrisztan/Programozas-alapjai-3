public class Nyuszi extends Jatekos {
    private String color = "feher";

    public Nyuszi(String s) {
        color = s;
    }

    public String toString() {
        return color;
    }

    public void lep() {
        System.out.println("Színem: " + this.toString() + ", Kör: " + asztal.getKor());
        if (asztal.getTet() < 50) {
            asztal.emel(5.0);
        } else {
            System.out.println("A jelenlegi tét: " + asztal.getTet() + " Húha!");
        }
    }
}
