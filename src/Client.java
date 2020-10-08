public class Client{
    private final Server server = Server.getInstance();

    public void sendMessage(final Message message){
        new Thread(new Runnable() {
            public void run() {
                synchronized (this.server) {
                    this.server.receiveMessage(message);
                }
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}