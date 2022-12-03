import static java.lang.Thread.sleep;

public class Producer implements Runnable {
    public String n = "";
    public int sleepTime = 0;
    public Fifo f = new Fifo();

    public Producer(String str, int time, Fifo fifo) {
        n = str;
        sleepTime = time;
        f = fifo;
    }

    public void go() throws Exception {
        int i = 0;
        while (true) {
            try {
                f.put("produced" + " " + n + " " + i + " " + System.currentTimeMillis() % 100000);
                i++;
                sleep(sleepTime);
            } catch (InterruptedException err) {
                System.out.println(err);
            }
        }
    }

    public void run() {
        try {
            go();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
