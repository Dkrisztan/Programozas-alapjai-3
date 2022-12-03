import java.lang.Math;

public class Application {
    public static void main(String[] args) throws Exception {
        Fifo fifo = new Fifo();

        Producer pr1 = new Producer("pr1", 100, fifo);
        Producer pr2 = new Producer("pr2", 100, fifo);
        Producer pr3 = new Producer("pr3", 100, fifo);


        Thread tr1 = new Thread(pr1);
        Thread tr2 = new Thread(pr2);
        Thread tr3 = new Thread(pr3);


        Consumer cr1 = new Consumer(fifo, "cons1", 1000);
        Consumer cr2 = new Consumer(fifo, "cons2", 1000);
        Consumer cr3 = new Consumer(fifo, "cons3", 1000);
        Consumer cr4 = new Consumer(fifo, "cons4", 1000);

        tr1.start();
        tr2.start();
        tr3.start();
        cr1.start();
        cr2.start();
        cr3.start();
        cr4.start();
    }
}
