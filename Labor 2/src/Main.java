public class Main {
    public static void main(String[] args) {

        //! Hány kört játszatunk éppen
        int korok = 10;

        Kezdo p1 = new Kezdo("Pisti");
        Kezdo p2 = new Kezdo("Jani");
        Robot p3 = new Robot();
        Robot p4 = new Robot();
        Mester m = new Mester();
        Nyuszi ny = new Nyuszi("Piros");
        Ember emb = new Ember();

        Asztal asztal = new Asztal();

        //* Helyesen dobja a hibát
        Asztal asztal1 = new Asztal();

        try {
            asztal.ujJatek();
            // asztal.addJatekos(p1);
            // asztal.addJatekos(p2);
            // asztal.addJatekos(p3);
            asztal.addJatekos(ny);
            asztal.addJatekos(m);
            asztal.addJatekos(emb);
            System.out.println("Ez a mostani cél: " + asztal.getGoal());

            for (int i = 0; i < korok; i++) {
                if (asztal.getTet() < asztal.getGoal()) {
                    asztal.kor();
                } else {
                    break;
                }
            }
        } catch(NincsJatekos err) {
            System.out.println(err); //! Jól dob kivételt ha nincs játékos
        }
        asztal = null;
        System.gc();
    }
}
