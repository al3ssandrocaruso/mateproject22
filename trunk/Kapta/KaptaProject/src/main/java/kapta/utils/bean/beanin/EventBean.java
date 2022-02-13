package kapta.utils.bean.beanin;

import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public abstract class EventBean {
    protected String eventName;
    protected Double eventPrice;
    protected String eventAddress;
    protected Time eventDuration;
    protected LocalTime eventOrario;
    protected File eventImg;
    protected Date eventDate;
    protected boolean greenPass;
    protected int eventId;

    public String getEventName() {
        return eventName;
    }
    public Double getEventPrice() {
        return eventPrice;
    }
    public String getEventAddress() {
        return eventAddress;
    }
    public Time getEventDuration() {
        return eventDuration;
    }
    public LocalTime getEventOrario() {
        return eventOrario;
    }
    public File getEventImg() {
        return eventImg;
    }
    public Date getEventDate() {
        return eventDate;
    }
    public boolean isGreenPass() {
        return greenPass;
    }
    public int getEventId() {
        return eventId;
    }
}
