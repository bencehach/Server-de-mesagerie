public class Pinger extends Thread {
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Serv.deleteOldTopics();
            Serv.getMessageFromQueue();
        }
    }
}
