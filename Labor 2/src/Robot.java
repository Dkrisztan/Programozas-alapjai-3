import java.util.ArrayList;

public class Robot extends Jatekos {
    public int robotId;
    public static int counter = 0;

    // TODO robot id needs to be ascending ordered
    //! not working for now

    public Robot() {
        robotId = counter++;
    }

    public String toString() {
        return "Robot" + robotId;
    }

    public void lep() {
        System.out.println(this.toString() + " Kör: " + asztal.getKor());
    }

}
