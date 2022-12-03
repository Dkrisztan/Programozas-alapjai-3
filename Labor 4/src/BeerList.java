import java.io.*;
import java.util.*;

public class BeerList implements Serializable {
    ArrayList<Beer> beerList = new ArrayList<Beer>();

    protected void add(String[] data) {
        Beer beer = new Beer(data);
        beerList.add(beer);
    }

    protected void list(BeerList list, String[] data) {
        if (data.length > 1) {
            if (data[1].equals("name")) {
                Collections.sort(beerList, new NameComparator());
            } else if (data[1].equals("style")) {
                Collections.sort(beerList, new StyleComparator());
            } else if (data[1].equals("strength")) {
                Collections.sort(beerList, new StrengthComparator());
            }
        }

        ListIterator<Beer> it = list.beerList.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    protected void search(String[] data) {
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

    protected void find(String[] data) {
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

    protected void delete(BeerList list, String[] data) {
        list.beerList.remove(Collections.binarySearch(list.beerList, new Beer(data[1], null, 0), new NameComparator()));
    }

    protected void save(String[] data) throws Exception {
        FileOutputStream f = new FileOutputStream(data[1]);
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(beerList);
        out.close();
    }

    protected void load(String[] data) throws Exception {
        FileInputStream f = new FileInputStream(data[1]);
        ObjectInputStream is = new ObjectInputStream(f);
        beerList = (ArrayList<Beer>) is.readObject();
        is.close();
    }

}
