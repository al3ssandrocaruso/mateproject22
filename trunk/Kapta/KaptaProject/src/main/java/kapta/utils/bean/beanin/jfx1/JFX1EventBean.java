package kapta.utils.bean.beanin.jfx1;

import kapta.utils.bean.beanin.EventBean;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class JFX1EventBean extends EventBean {

    public JFX1EventBean(String eventName, String eventPrice, String eventAddress, String eventDuration, String eventTimeH, String eventTimeM, File img, LocalDate eventDate, boolean selected){
        setEventName(eventName);
        setEventPrice(eventPrice);
        setEventAddress(eventAddress);
        setEventDuration(eventDuration);
        setEventTime(eventTimeH,eventTimeM);
        setEventImg(img);
        setEventDate(eventDate);
        setGreenPass(selected);
    }

    //Set
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEventPrice(String eventPrice) {
        Double price = Double.valueOf(eventPrice);
        this.eventPrice = price;
    }
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }
    public void setEventDuration(String eventDuration) {
        //This is the duration of the pevent
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm"); //e.g 10:55 ==> 10 hours and 55 minutes
        long ms= 0;
        try {
            ms = sdf.parse(eventDuration).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.eventDuration = new Time(ms);
    }
    public void setEventTime(String eventTimeH, String eventTimeM) {
        //LocalTime format: hh:min
        this.eventOrario = LocalTime.of(Integer.valueOf(eventTimeH),Integer.valueOf(eventTimeM));
    }
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = java.sql.Date.valueOf(eventDate);
    }
    public void setGreenPass(boolean greenPass) {this.greenPass = greenPass;}
    public void setEventImg(File eventImg) {
        this.eventImg = eventImg;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
