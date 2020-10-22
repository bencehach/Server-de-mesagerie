public class Server {

    public static void main(String[] args) {
        Pinger png = new Pinger();
        png.start();
        Serv serv = new Serv();
        serv.start();
    }
}
