import java.io.*;
import java.net.Socket;

public class User extends Thread {
    private final Socket userSocket;
    private OutputStream outStream;
    private InputStream inputStream;
    private final int threadId;
    private final Serv server;

    public User(Serv serv, Socket userSocket) throws IOException, InterruptedException {
        this.userSocket = userSocket;
        this.server = serv;
        outStream = userSocket.getOutputStream();
        inputStream = userSocket.getInputStream();
        userSocket.getOutputStream();
        this.threadId = (int) this.getId();
    }


    public void printToUser(String message) throws IOException {
        outStream.write((message + "\r\n").getBytes());
    }

    public void run() {
        try {
            processUserInput();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processUserInput() throws IOException, InterruptedException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String userInput;

        this.printToUser("Welcome user! Your id is: " + this.getId() + "\r\n");

        while ((userInput = reader.readLine()) != null) {
            if ("exit".equalsIgnoreCase(userInput)) {
                break;
            }

            String[] splitedUserInput = userInput.split(":");

            if (splitedUserInput.length != 3) {
                printToUser("You entered a wrong format, please use this format for messages(message:{receiverUserId}:message content)\r\n" +
                        "or this format for topics(topic:type:message)\r\n or list:all:topics for listing all the topics");
                continue;
            }
            if ("message".equalsIgnoreCase(splitedUserInput[0])) {

                Message message = new Message(splitedUserInput[2], this.threadId);
                message.setReceiver(Integer.valueOf(splitedUserInput[1]));

                int outcome = Serv.addMessage(message);
                if (outcome == 0) {
                    System.out.println("resource queue full");
                }
                if (outcome == 2) {
                    printToUser("Target user is not valid");
                }
                sleep(300);
            }

            if ("topic".equalsIgnoreCase(splitedUserInput[0]) && "list".equalsIgnoreCase(splitedUserInput[1])
                    && "all".equalsIgnoreCase(splitedUserInput[2])) {
                server.printAllTopicsToRequester(threadId);
                continue;
            }

            if ("topic".equalsIgnoreCase(splitedUserInput[0])
                    && "list".equalsIgnoreCase(splitedUserInput[1]) && !("all".equalsIgnoreCase(splitedUserInput[2]))) {
                server.printTopicToRequester(threadId, splitedUserInput[2]);
                continue;
            }

            if ("topic".equalsIgnoreCase(splitedUserInput[0])) {

                Topic topic = new Topic(splitedUserInput[2], threadId, splitedUserInput[1]);
                this.printToUser(topic.writeMessage());
                server.addTopicMessage(topic);
            }

            String message = "Typed : " + userInput + "\r\n";
            System.out.println("Server received the typed userInput " + message);
        }

        userSocket.close();
    }

    public int getThreadId() {
        return threadId;
    }
}
