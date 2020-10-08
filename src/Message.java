import java.util.Calendar;
import java.util.Scanner;

public class Message extends MessageObject{
    private String receiver, sender;

    Message(String sender) {
        super(sender);
    }

    public void setReceiver(){
        Scanner scanner = new Scanner(System.in);
        this.receiver = scanner.nextLine();
    }

    public void writeMessage(){
        Scanner scanner = new Scanner(System.in);

        while(!scanner.next().equals(".")) {
            this.message = scanner.nextLine();
            int minute = this.calendar.get(Calendar.MINUTE);
            int second = this.calendar.get(Calendar.SECOND);
            System.out.println(sender + ": " + message + " " + minute + second);
        }
    }

}