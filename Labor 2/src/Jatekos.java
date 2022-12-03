public abstract class Jatekos {
    protected Asztal asztal;

    public void setAsztal(Asztal a) {
        asztal = a;
    }

    public void lep() {
        System.out.println("Kor: " + asztal.getKor() + ", Tet: " + asztal.getTet());
    }

    public void finalize() {
        System.out.println(this.toString());
    }
}
