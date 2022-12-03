public class Consumer extends Thread {
    public Fifo f = new Fifo();
    public String s = "";
    public int n = 0;

    public Consumer(Fifo fifo, String str, int nr) {
        f = fifo;
        s = str;
        n = nr;
    }

    public void go() throws Exception {
        while (true)
        {
            try {
                System.out.println("consumed " + s + " " + f.get() + " " + System.currentTimeMillis() % 100000);
                Thread.sleep(n);
            } catch(InterruptedException e) {
                e.printStackTrace();
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
