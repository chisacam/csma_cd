public class Node implements Runnable {
    Thread t;
    Link link;
    long startTime;
    public Node(String name, Link link) {
        this.link = link;
        t = new Thread(this, name);
        t.start();
    }

    public void run() {
        // sleep until init time and start while
        // t.wait(random);
        while(link.system_clock.get() == 60000) {
            if(link.requestUseLink(t.getName())) {
                link.system_clock.addAndGet(5);
            } else {
                try {
                    //t.wait(random);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
