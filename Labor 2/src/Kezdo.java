public class Kezdo extends Jatekos {
    private String nev;

    public Kezdo(String n) {
        nev = n;
    }

    public String toString() {
        return nev;
    }

    public void lep() {
        System.out.println(this.toString() + " Kör: " + asztal.getKor());
        if (asztal.getKor() % 2 != 0) {
            System.out.println("Passz");
        } else {
            asztal.emel(1.0);
        }
    }

}
