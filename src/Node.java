public class Node implements Runnable {
    Thread t;
    public Node(String name) {
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {

    }
}
