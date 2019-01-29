import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log implements Comparable<Log> {
    private String message;
    private Date time;

    public Log(String message, String time) throws ParseException {
        this.message = message;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.time = format.parse(time);
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public int compareTo(Log log) {
        return time.compareTo(log.getTime());
    }
}