import java.util.Random;

public class Asztal {
    private Jatekos jatekosok[] = new Jatekos[10];
    private double tet;
    private int kor;
    private double goal;

    // A Jatekos hozzaadashoz van hasznalva
    private int len = 0;

    public int getKor() {
        return kor;
    }

    public double getTet() {
        return tet;
    }

    public double getGoal() {
        return goal;
    }

    public void emel(double d) {
        tet += d;
    }

    public void ujJatek() {
        tet = 0;
        kor = 0;
        Random rand = new Random();
        goal = rand.nextDouble(101);
        len = 0;
    }

    public void addJatekos(Jatekos j) {
        j.setAsztal(this);
        jatekosok[len++] = j;
    }

    public void kor() throws NincsJatekos {
        if (len == 0) {
            throw (new NincsJatekos("Jelenleg senki nem ül az asztalnál"));
        }
        for (int i = 0; i < len; i++) {
            jatekosok[i].lep();
        }
        System.out.println("Jelenlegi tét: " + tet);
        kor++;
    }
}
