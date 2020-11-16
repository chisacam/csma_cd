import java.util.concurrent.atomic.AtomicBoolean;

public class CSMA {
    public static void main(String[] args) {
        Link link = new Link();
        Node[] node = new Node[4];
        long startTime = System.currentTimeMillis();
        AtomicBoolean isBusy = new AtomicBoolean(false);
        for(int i = 0; i < 4; i++)
            node[i] = new Node("Node" + Integer.toString(i));
        try {
// wait for stations to complete transmission
            for(int i = 0;i < 4;i++)
                node[i].t.join();
            link.t.join();
        }
        catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted");
        }
        System.out.println("Transmission completed.");
    }
}
