import java.util.concurrent.atomic.AtomicBoolean;

public class Link implements Runnable {
    Thread t; // 링크 스레드 객체
    long startTime; // 링크 객체 생성시간
    AtomicBoolean isBusy = new AtomicBoolean(false); // 링크 사용가능 여부
    AtomicBoolean isEnd = new AtomicBoolean(false); // 통신 종료 여부
    String sender;
    String receiver;
    Node[] node = new Node[4];
    public Link() {
        for(int i = 0; i < 4; i++)
            node[i] = new Node("Node" + i, this);
        startTime = System.currentTimeMillis();
        t = new Thread(this, "Link");
        t.start();
    }
    public synchronized boolean requestUseLink(String nodeName, String target) { // 노드의 링크 사용 가능여부를 반환
        System.out.println(System.currentTimeMillis() - startTime + " " + nodeName + " Data Send Request To " + target);
        if(isBusy.get()) {
            // 링크가 사용 불가능한 경우 false
            System.out.println(System.currentTimeMillis() - startTime + " " + "Reject: " + nodeName + " Data Send Request To " + target);
            return false;
        } else {
            // 링크가 사용 가능한 경우 true, isBusy 또한 true로 변경해 사용불가로 변경
            System.out.println(System.currentTimeMillis() - startTime + " " + "Accept: " + nodeName + " Data Send Request To " + target);
            isBusy.set(true);
            sender = nodeName;
            receiver = target;
            return true;
        }
    }
    // 노드에서 작업이 끝났음을 링크에게 알림
    public void alertFinish() {
        System.out.println(System.currentTimeMillis() - startTime + " " + sender + " Data Send Finished To " + receiver);
        isBusy.set(false);
    }
    public void run() {
        while(true){
            if(isEnd.get()) {
                System.out.println("transmission completed.");
                break;
            } else {
                if (System.currentTimeMillis() - startTime >= 60000) {
                    isEnd.set(true)
                }
            }
        }

    }

    public static void main(String[] args) {
        Link link = new Link();
        try {
            link.t.join();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
