import java.util.Calendar;
import java.util.Scanner;

abstract class MessageObject {
    protected String message, sender;
    protected Calendar calendar = Calendar.getInstance();

    public MessageObject(String sender){
        this.sender = sender;
    }

    public abstract void writeMessage();
}
