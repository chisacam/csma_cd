public class CSMA {
    public static void main(String[] args) {
        Link link = new Link();
        try {
            link.t.join();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
