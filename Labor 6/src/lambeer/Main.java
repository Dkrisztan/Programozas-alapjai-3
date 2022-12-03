package lambeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line = null;

        // We create a lambeer.BeerList
        BeerList list = new BeerList();

        HashMap<String, Command> commands = new HashMap<String, Command>();
        commands.put("add", BeerList::add);
        commands.put("list", BeerList::list);
        commands.put("search", BeerList::search);
        commands.put("find", BeerList::find);
        commands.put("delete", BeerList::delete);
        commands.put("save", BeerList::save);
        commands.put("load", BeerList::load);
        commands.put("exit", (nr) -> System.exit(0));

        while ((line = br.readLine()) != null) {
            String data[] = line.split(" ");
            if (commands.containsKey(data[0])) {
                commands.get(data[0]).execute(data);
            } else {
                System.out.println("Nem létezik az adott parancs!");
            }
        }
        br.close();
    }
}
