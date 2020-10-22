import java.util.Calendar;

public class Message extends MessageObject {

    private Integer receiverId;

    Message(String message, Integer senderId) {
        super(message, senderId);
    }

    public void setReceiver(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getReceiverId() {
        return this.receiverId;
    }

    public String writeMessage() {
        int hour = this.calendar.get(Calendar.HOUR);
        int minute = this.calendar.get(Calendar.MINUTE);
        int second = this.calendar.get(Calendar.SECOND);
        return "New message from client with id: " + senderId + ": " + this.message + " at " + hour + ":" + minute + ":" + second;
    }

}
