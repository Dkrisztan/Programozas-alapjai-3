package lambeer;

import java.io.*;
import java.util.*;

public class BeerList implements Serializable {
    static ArrayList<Beer> beerList = new ArrayList<Beer>();

    static HashMap<String, Comparator<Beer>> comps = new HashMap<>();
    static {
        comps.put("name", Comparator.comparing(Beer::getName));
        comps.put("style", Comparator.comparing(Beer::getStyle));
        comps.put("strength", Comparator.comparing(Beer::getStrength));
    }

    protected static void add(String[] data) {
        Beer beer = new Beer(data);
        beerList.add(beer);
    }

    protected static void list(String[] data) {
        if (data.length > 1) {

            // Lista nevekkel
            List<String> lparams = new ArrayList<String>();
            lparams.add("name");
            lparams.add("style");
            lparams.add("strength");
            lparams.set(0, data[1]);

            Comparator<Beer> cmp = Comparators.comps.get(lparams.get(0));
            cmp = comps.get(lparams.get(0)).thenComparing(comps.get(lparams.get(1))).thenComparing(comps.get(lparams.get(2)));
            Collections.sort(beerList, cmp);
        }

        ListIterator<Beer> it = beerList.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    protected static void search(String[] data) {
        boolean err = true;
        for (Beer i : beerList) {
            if (data[1].equals("name")) {
                if (i.getName().equals(data[2])) {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                    System.out.println(i.toString());
                    err = false;
                }
            } else if (data[1].equals("style")) {
                if (i.getStyle().equals(data[2])) {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                    System.out.println(i.toString());
                    err = false;
                }
            } else if (data[1].equals("strength")) {
                if (i.getStrength() == Double.parseDouble(data[2])) {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                    System.out.println(i.toString());
                    err = false;
                }
            }
        }
        for (Beer i : beerList) {
            if (i.getName().equals(data[1])) {
                System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                System.out.println(i.toString());
                err = false;
            }
        }
        if (err) {
            System.out.println(ConsoleColors.RED_BRIGHT + "Nincs ilyen sör a rendszerben!" + ConsoleColors.RESET);
        }
    }

    protected static void find(String[] data) {
        boolean err = true;
        for (Beer i : beerList) {
            if (data[1].equals("name")) {
                if (i.getName().contains(data[2])) {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                    System.out.println(i.toString());
                    err = false;
                }
            } else if (data[1].equals("style")) {
                if (i.getStyle().contains(data[2])) {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                    System.out.println(i.toString());
                    err = false;
                }
            } else if (data[1].equals("strength")) {
                if (i.getStrength() >= Double.parseDouble(data[2])) {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                    System.out.println(i.toString());
                    err = false;
                }
            } else if (data[1].equals("weaker")) {
                if (i.getStrength() <= Double.parseDouble(data[2])) {
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                    System.out.println(i.toString());
                    err = false;
                }
            }
        }
        for (Beer i : beerList) {
            if (i.getName().contains(data[1])) {
                System.out.println(ConsoleColors.GREEN_BRIGHT + "Van ilyen sör a rendszerben!: " + ConsoleColors.RESET);
                System.out.println(i.toString());
                err = false;
            }
        }
        if (err) {
            System.out.println(ConsoleColors.RED_BRIGHT + "Nincs ilyen sör a rendszerben!" + ConsoleColors.RESET);
        }
    }

    protected static void delete(String[] data) {
        beerList.remove(Collections.binarySearch(beerList, new Beer(data[1], null, 0), new NameComparator()));
    }

    protected static void save(String[] data) throws Exception {
        FileOutputStream f = new FileOutputStream(data[1]);
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(beerList);
        out.close();
    }

    protected static void load(String[] data) throws Exception {
        FileInputStream f = new FileInputStream(data[1]);
        ObjectInputStream is = new ObjectInputStream(f);
        beerList = (ArrayList<Beer>) is.readObject();
    }

}
