package kapta.utils.bean.jfx1;

import javafx.scene.image.Image;
import kapta.model.EventModel;
import kapta.utils.bean.EventBean;
import kapta.utils.utils.ImageConverter;
import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class JFX1EventBean extends EventBean {

    // this is used from the view
    public JFX1EventBean(String eventName, String eventPrice, String eventAddress, String eventDuration, String eventTimeH, String eventTimeM, LocalDate eventDate){
        super();
        setEventNameOut(eventName);
        setEventPriceOut(eventPrice);
        setEventAddressOut(eventAddress);
        setEventDurationOut(eventDuration);
        setEventTimeOut(eventTimeH,eventTimeM);
        setEventDateOut(eventDate);
    }

    public JFX1EventBean(EventBean eventBean) {
        this.setEventCreator(eventBean.getEventCreator());
        this.setEventName(eventBean.getEventName());
        this.setEventAddress(eventBean.getEventAddress());
        this.setEventOrario(eventBean.getEventOrario());
        this.setEventDate(eventBean.getEventDate());
        this.setEventDuration(eventBean.getEventDuration());
        this.setEventPrice(eventBean.getEventPrice());
        this.setEventId(eventBean.getEventId());
        this.setGreenPass(eventBean.isGreenPass());
        this.setEventImg(eventBean.getEventImg());
    }




    public  JFX1EventBean(EventModel eventModel){
        super(eventModel);
    }

    // this is from the in


    public void setEventNameOut(String eventName) {
        this.eventName = eventName;
    }
    public void setEventPriceOut(String eventPrice) {
        Double price = Double.valueOf(eventPrice);
        this.eventPrice = price;
    }
    public void setEventAddressOut(String eventAddress) {
        this.eventAddress = eventAddress;
    }
    public void setEventDurationOut(String eventDuration) {
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
    public void setEventTimeOut(String eventTimeH, String eventTimeM) {
        //LocalTime format: hh:min
        this.eventOrario = LocalTime.of(Integer.valueOf(eventTimeH),Integer.valueOf(eventTimeM));
    }
    public void setEventDateOut(LocalDate eventDate) {
        this.eventDate = java.sql.Date.valueOf(eventDate);
    }
    public void setGreenPassOut(boolean greenPass) {this.greenPass = greenPass;}
    public void setEventImgOut(File eventImg) {
        this.eventImg = eventImg;
    }



    public String getEventCreatorOut() {
        return eventCreator;
    }
    public String getEventNameOut() {
        return eventName;
    }
    public String getEventAddressOut() {
        return eventAddress;
    }
    public String getEventTimeOut() {
        return String.valueOf(eventOrario);
    }
    public String getEventDateOut() {
        return  String.valueOf(eventDate);
    }
    public String getEventDurationOut() {
        return  String.valueOf(eventDuration);
    }
    public String getEventPriceOut() {
        return String.valueOf(eventPrice);
    }
    public String getEventIdOut() {
        return String.valueOf(eventId);
    }
    public boolean isGreenPassOut() {return greenPass;}
    public String getNumParticipantsOut(){return String.valueOf(numParticipants);}
    public Image getEventImgOut() {return ImageConverter.convertFileToFxImage(eventImg);}

}
