import java.io.Serializable;

public class Beer implements Serializable {
    public String name;
    public String style;
    public double strength;

    public Beer(String n, String sty, double str) {
        name = n;
        style = sty;
        strength = str;
    }

    // This is needed to make beer creation easier
    public Beer(String[] data) {
        name = data[1];
        style = data[2];
        strength = Double.parseDouble(data[3]);
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public double getStrength() {
        return strength;
    }

    public String toString() {
        return "A sör neve: " + ConsoleColors.YELLOW + name + ConsoleColors.RESET + ", Fajtája: " + ConsoleColors.PURPLE + style + ConsoleColors.RESET + ", Alkoholfokozata: " + ConsoleColors.RED + strength + "%" + ConsoleColors.RESET;
    }

}
