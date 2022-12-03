import java.util.LinkedList;
import java.util.List;

public class Fifo {
    List<String> str = new LinkedList<String>();

    synchronized public void put(String p) throws Exception {
        System.out.println("put: " + Thread.currentThread().getId());
        while (str.size() >= 10) {
            this.wait();
        }
        str.add(p);
        System.out.println(p);
        this.notify();
    }

    synchronized public String get() throws Exception {
        String t = "";
        System.out.println("get: " + Thread.currentThread().getId());
        while (str.size() == 0) {
            this.wait();
        }

        t = str.get(0);
        str.remove(str.get(0));
        this.notify();

        return t;
    }

}
