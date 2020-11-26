import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Node implements Runnable {
    Thread t; // 쓰레드 객체
    Link link; // 링크 객체
    int retryNum; // 전송 요청 실패횟수
    List<String> list = new ArrayList<>(Arrays.asList("Node1", "Node2", "Node3", "Node0"));
    String target;
    // 노드 초기화, 생성자에서 스레드 생성 및 시작
    public Node(String name, Link link) {
        list.remove(name);
        this.link = link;
        t = new Thread(this, name);
        t.start();
        this.retryNum = 1;
    }
    // Backoff 알고리즘에 의한 랜덤 대기시간 반환(인자는 실패 횟수 retryNum에 따라 점차 증가)
    public int BackoffTimer(int retryNum) {
        int rndom;
        int temp;
        temp=Math.min(retryNum,10);
        rndom=(int)(Math.random()*(Math.pow(2,temp)-1));
        return rndom;
    }
    public void run() {
        Random rand = new Random();
        target = list.get(rand.nextInt(3));

        try {
            Thread.sleep(rand.nextInt(30)); //스레드 별로 임의의 시간에 요청해야 하므로 run 초기에도 랜덤 sleep 시킴
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fw = new FileWriter(t.getName() + ".txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            while(link.isEnd.get()) { // 동작조건은 시작시간으로부터 1분간
                if(!link.requestUseLink(t.getName(), target)){ // link 객체에 사용여부 질의
                    // 사용불가 상태일경우 backoff 반환 시간만큼 대기
                    try {
                        bw.write(System.currentTimeMillis() - link.startTime + " " + "Data Send Request To " + target);
                        bw.newLine();
                        bw.write(System.currentTimeMillis() - link.startTime + " " + t.getName() + " Data Send Request Reject from Link");
                        bw.newLine();
                        int waitTime = BackoffTimer(retryNum++);
                        bw.write(System.currentTimeMillis() - link.startTime + " " + t.getName() + " Exponential Back-off Time: " + waitTime + " msec");
                        bw.newLine();
                        Thread.sleep(waitTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // 사용가능한 상태일 경우 5초대기(데이터전송시간)후 링크 상태를 사용가능으로 변경
                    try {
                        if(t.getName().equals(link.sender)) {
                            bw.write(System.currentTimeMillis() - link.startTime + " " + "Data Send Request To " + target);
                            bw.newLine();
                            bw.write(System.currentTimeMillis() - link.startTime + " " + t.getName() + " Data Send Request Accept from Link");
                            bw.newLine();
                            Thread.sleep(5);
                            bw.write(System.currentTimeMillis() - link.startTime + " " + t.getName() + " Data Send Finished To " + target);
                            bw.newLine();
                        } else {
                            bw.write(System.currentTimeMillis() - link.startTime + " " + "Data Receive Start from " + link.sender);
                            bw.newLine();
                            Thread.sleep(5);
                            bw.write(System.currentTimeMillis() - link.startTime + " " + "Data Receive Finished from " + link.sender);
                            bw.newLine();
                        }
                        link.alertFinish();
                        retryNum = 0;
                        target = list.get(rand.nextInt(3));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        link.isEnd.set(true);
        System.out.println(t.getName() + " Finished");
    }
}
