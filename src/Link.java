public class Link implements Runnable {
    Thread t;
    public Link() {
        t = new Thread(this, "Link");
        t.start();
    }

    @Override
    public void run() {

    }
}
