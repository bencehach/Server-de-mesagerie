import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Serv extends Thread {

    private static final int MAXIMUM_STORED_MESSAGES = 10;
    private static final int TOPIC_MAXSIZE = 10;
    private static Queue<MessageObject> messageList = new LinkedList<>();
    private static Queue<MessageObject> topicMessageList = new LinkedList<>();
    private static ArrayList<User> userList = new ArrayList<>();

    public static synchronized void deleteOldTopics() {

        MessageObject aux = topicMessageList.peek();

        if ((aux != null) && (((Topic) aux).isExpired()))
            topicMessageList.remove();
    }

    public static synchronized int addMessage(Message message)
    {
        int size = messageList.size();
        if (size >= MAXIMUM_STORED_MESSAGES) {
            return 0;
        }

        Optional<User> optionalUser =
                userList.stream()
                        .filter(user -> Math.toIntExact(user.getId()) == message.getReceiverId())
                        .findAny();

        if (optionalUser.isPresent()) {
            messageList.add(message);
            return 1;
        }

        return 2;
    }


    public synchronized static void getMessageFromQueue()
    {
        Message message = (Message) messageList.peek();

        if (message != null) {
            messageList.remove();

            for (User user : userList)
            {
                if (user.getThreadId() == message.getReceiverId()) {
                    try {
                        user.printToUser(message.writeMessage());
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void printAllTopicsToRequester(int id) {

        for (User user : userList)
        {
            if (user.getId() == id) {
                try {
                    user.printToUser("All topics messages");

                    for (MessageObject message : topicMessageList) {
                        user.printToUser(message.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printTopicToRequester(int id, String targetTopic) {

        for (User user : userList) {
            if (user.getId() == id) {
                try {
                    user.printToUser("All messages from the topic: " + targetTopic);

                    for (MessageObject message : topicMessageList)
                    {
                        if (targetTopic.equalsIgnoreCase(((Topic) message).getTopic()))
                            user.printToUser(message.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9991);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                User worker = new User(this, clientSocket);

                userList.add(worker);
                System.out.println("New connection. Client ID :" + worker.getId());

                worker.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTopicMessage(Topic topic) {

        if (topicMessageList.size() < TOPIC_MAXSIZE) {
            topicMessageList.add(topic);
        }
    }
}
