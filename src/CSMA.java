import java.util.concurrent.atomic.*;

public class CSMA {
    public static void main(String[] args) {
        AtomicLong startTime = new AtomicLong();
        AtomicBoolean isBusy = new AtomicBoolean(false);
        CSMA clock = new CSMA();

        Link link = new Link(clock);
        Node[] node = new Node[4];

        for(int i = 0; i < 4; i++)
            node[i] = new Node("Node" + i, clock);
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
