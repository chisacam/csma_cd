import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Link implements Runnable {
    Thread t;
    long startTime = System.currentTimeMillis();
    AtomicLong system_clock = new AtomicLong();
    AtomicBoolean isBusy = new AtomicBoolean(false);
    Node[] node = new Node[4];
    public Link() {
        for(int i = 0; i < 4; i++)
            node[i] = new Node("Node" + i, this);
        t = new Thread(this, "Link");
        t.start();
    }
    public boolean requestUseLink(String nodeName) {
        System.out.println(nodeName + " request to use Link");
        if(isBusy.get()) {
            System.out.println(nodeName + " request reject because Link is busy");
            return false;
        } else {
            System.out.println(nodeName + " request is accept");
            return true;
        }
    }
    public void run() {
        while(system_clock.getAndIncrement() == 60000){

        }
    }
}
