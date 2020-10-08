import java.util.Calendar;
import java.util.Scanner;

public class Topic extends MessageObject{
    private String type;
    public static final float valability = 30;

    public Topic(String sender, String type){
        super(sender);
        this.type = type;
    }

    public String getTopic(){
        return type;
    }

    public boolean isExpired(){
        int currentSec = calendar.get(Calendar.SECOND);
        float expirySec = currentSec + valability;

        return currentSec == expirySec;
    }

    public void writeMessage(){
        Scanner scanner = new Scanner(System.in);

        while(!scanner.next().equals(".")) {
            this.message = scanner.nextLine();
            int minute = this.calendar.get(Calendar.MINUTE);
            int second = this.calendar.get(Calendar.SECOND);
            System.out.println("Topic " + type +"\n" + sender + ": " + message + " " + minute + second);
        }
    }


}