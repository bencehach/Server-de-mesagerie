import java.time.LocalDateTime;
import java.util.Calendar;

public class Topic extends MessageObject {
    private String type;
    public static final long VALABILITY = 60;
    private LocalDateTime creationTime;

    public Topic(String message, Integer senderId, String type) {
        super(message, senderId);
        this.type = type;
        this.creationTime = LocalDateTime.now();
    }

    public String getTopic() {
        return type;
    }

    public boolean isExpired() {
        LocalDateTime expirationTime = this.creationTime.plusSeconds(VALABILITY);
        return LocalDateTime.now().isAfter(expirationTime);
    }

    public String writeMessage() {
        int hour = this.calendar.get(Calendar.HOUR);
        int minute = this.calendar.get(Calendar.MINUTE);
        int second = this.calendar.get(Calendar.SECOND);
        return "Topic: " + type + " from user with id: " + senderId + " - " + this.message + " at " + hour + ":" + minute + ":" + second;
    }

    public String toString() {
        return ("Topic: " + type + " from user with id: " + senderId + " with message: " + message + " created at: " + creationTime + "\r");
    }

}
