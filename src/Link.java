public class Link implements Runnable {
    Thread t;
    CSMA clock;
    public Link(CSMA clock) {
        this.clock = clock;
        t = new Thread(this, "Link");
        t.start();
    }

    @Override
    public void run() {

    }
}
