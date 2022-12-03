import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws Exception {

        // 1. Feladat
        // Beer beer1 = new Beer("Guinness", "stout", 4.2);
        // Beer beer2 = new Beer("Staropramen", "ipa", 5.0);
        //
        // System.out.println(beer1.toString());
        // System.out.println(beer2.toString());

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line = null;

        // We create a BeerList
        BeerList list = new BeerList();

        while ((line = br.readLine()) != null) {
            String data[] = line.split(" ");
            if (data[0].equals("exit")) {
                System.exit(0);
            } else if (data[0].equals("add")) {
                list.add(data);
            } else if (data[0].equals("list")) {
                // Looks confusing for sure, but the parameter for the list function is an ArrayList
                list.list(list, data);
            } else if (data[0].equals("search")) {
                list.search(data);
            } else if (data[0].equals("find")) {
                list.find(data);
            } else if (data[0].equals("delete")) {
                list.delete(list, data);
            } else if (data[0].equals("save")) {
                list.save(data);
            } else if (data[0].equals("load")) {
                list.load(data);
            }
        }
        br.close();
    }
}
