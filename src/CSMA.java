import java.util.concurrent.atomic.*;

public class CSMA {
    AtomicLong startTime = new AtomicLong();
    AtomicBoolean isBusy = new AtomicBoolean(false);

    public static void main(String[] args) {
        CSMA clock = new CSMA();

        Link link = new Link(clock);
        Node[] node = new Node[4];

        for(int i = 0; i < 4; i++)
            node[i] = new Node("Node" + i, clock);

        System.out.println("Transmission completed.");
    }
}
