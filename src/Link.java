public class Link implements Runnable {
    Thread t;
    CSMA clock;
    public Link(CSMA clock) {
        this.clock = clock;
        t = new Thread(this, "Link");
        t.start();
    }

    public void run() {
        while(clock.startTime.getAndIncrement() == 60000){

        }
    }
}
