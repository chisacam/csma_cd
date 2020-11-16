import java.util.concurrent.atomic.AtomicBoolean;

public class Link implements Runnable {
    Thread t; // 링크 스레드 객체
    long startTime; // 링크 객체 생성시간
    AtomicBoolean isBusy = new AtomicBoolean(false); // 링크 사용가능 여부
    AtomicBoolean isEnd = new AtomicBoolean(false); // 통신 종료 여부
    Node[] node = new Node[4];
    public Link() {
        for(int i = 0; i < 4; i++)
            node[i] = new Node("Node" + i, this);
        startTime = System.currentTimeMillis();
        t = new Thread(this, "Link");
        t.start();
    }
    public boolean requestUseLink(String nodeName) { // 노드의 링크 사용 가능여부를 반환
        System.out.println(nodeName + " request to use Link");
        if(isBusy.get()) {
            // 링크가 사용 불가능한 경우 false
            System.out.println(nodeName + " request reject because Link is busy");
            return false;
        } else {
            // 링크가 사용 가능한 경우 true, isBusy 또한 사용가능으로 변경
            System.out.println(nodeName + " request is accept");
            isBusy.set(true);
            return true;
        }
    }
    public void run() {
        while(true){
            if(isEnd.get()) {
                System.out.println("transmission completed.");
                break;
            }
        }

    }
}
