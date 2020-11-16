public class Node implements Runnable {
    Thread t;
    CSMA clock;
    public Node(String name, CSMA clock) {
        this.clock = clock;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {

    }
}
