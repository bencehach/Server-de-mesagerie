import java.util.Calendar;

abstract class MessageObject {
    protected String message;
    protected Integer senderId;
    protected Calendar calendar = Calendar.getInstance();

    public MessageObject(String message, Integer senderId){
        this.message = message;
        this.senderId = senderId;
    }

    public abstract String writeMessage();
}
