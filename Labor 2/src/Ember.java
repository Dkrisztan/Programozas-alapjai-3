import java.util.Scanner;

public class Ember extends Jatekos {
    public void lep() {
        System.out.println("Most ennyi a tét: " + asztal.getTet());
        System.out.print("Ennyivel növeled a tétet: ");
        Scanner sc = new Scanner(System.in);
        double tetEmel = sc.nextDouble();
        asztal.emel(tetEmel);
    }
}
