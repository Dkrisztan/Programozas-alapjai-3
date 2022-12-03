import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Sorszuro {
    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        System.out.println("A pattern: " + args[0]);
        while ((s = br.readLine()) != null) {
            if(s.matches("(.*)" + args[0] + "(.*)")) System.out.println(s);
        }

    }
}
