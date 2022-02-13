package kapta.utils.bean.beanin.jfx2;

import kapta.utils.bean.beanin.EventBean;
import kapta.utils.utils.HelperSetDuration;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class JFX2EventBean extends EventBean {

    public JFX2EventBean(String eventName, String eventAddress, String eventOrario, String eventDate, Double eventDuration, Double eventPrice, boolean greenpass,File eventImg) {
        setEventName(eventName);
        setEventImg(eventImg);
        setEventAddress(eventAddress);
        setEventPrice(eventPrice);
        setEventOrario(eventOrario);
        setEventDuration(eventDuration);
        setEventDate(eventDate);
        setGreenPass(greenpass);
    }

    public JFX2EventBean(String eventName, String eventAddress, String eventOrario, String eventDate, Double eventDuration, Double eventPrice, boolean greenpass) {
        setEventName(eventName);
        setEventAddress(eventAddress);
        setEventPrice(eventPrice);
        setEventOrario(eventOrario);
        setEventDuration(eventDuration);
        setEventDate(eventDate);
        setGreenPass(greenpass);
    }



    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }
    public void setEventOrario(String eventTime) {
        int index= eventTime.indexOf(":"); //formato ora e.g 20:15
        this.eventOrario = LocalTime.of(Integer.valueOf(eventTime.substring(0,index)),Integer.valueOf(eventTime.substring(index+1)));
    }
    public void setEventDate(String eventDate){  //Date format e.g 03/09/2023
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(eventDate, formatter);
        this.eventDate = java.sql.Date.valueOf(localDate);
    }
    public void setEventDuration(Double partyDuration){
        this.eventDuration = new Time(HelperSetDuration.conv(partyDuration));
    }
    public void setEventPrice(Double eventPrice) {
        this.eventPrice = eventPrice;
    }
    public void setGreenPass(boolean greenPass) {this.greenPass = greenPass;}
    public void setEventImg(File eventImg) {
        this.eventImg = eventImg;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
