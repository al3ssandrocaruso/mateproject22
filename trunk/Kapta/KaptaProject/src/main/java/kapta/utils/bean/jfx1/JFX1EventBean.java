package kapta.utils.bean.jfx1;

import javafx.scene.image.Image;
import kapta.model.EventModel;
import kapta.utils.bean.EventBean;
import kapta.utils.exception.myexception.InputDateException2;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.utils.ImageConverter;
import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class JFX1EventBean extends EventBean {

    // this is used from the view
    public JFX1EventBean(String eventName, String eventPrice, String eventAddress, String eventDuration, String eventTimeH, String eventTimeM, LocalDate eventDate) throws InputNullException, InputDateException2 {
        super();
        setEventNameOut(eventName);
        setEventPriceOut(eventPrice);
        setEventAddressOut(eventAddress);
        setEventDurationOut(eventDuration);
        setEventTimeOut(eventTimeH,eventTimeM);
        setEventDateOut(eventDate);
    }

    public JFX1EventBean(EventBean eventBean) {
        super(eventBean);
    }




    public  JFX1EventBean(EventModel eventModel){
        super(eventModel);
    }

    // this is from the in


    public void setEventNameOut(String eventName) throws InputNullException {

        if("".equals(eventName )){
            throw new InputNullException("Event Name");
        }
        this.eventName=eventName;
    }
    public void setEventPriceOut(String eventPrice) throws InputNullException, InputDateException2 {
        if(Objects.equals(eventPrice, "") || eventPrice== null){
            throw new InputNullException("Event Price");
        }
        try{
        Double price = Double.valueOf(eventPrice);
        this.eventPrice = price;
        }catch (NumberFormatException p ){
            throw  new InputDateException2(eventPrice, "Event Price", "$$.$$");
        }
    }
    public void setEventAddressOut(String eventAddress) throws InputNullException {

        if("".equals(eventAddress ) || eventAddress == null){
            throw new InputNullException("Party Address");
        }
        this.eventAddress=eventAddress;
    }
    public void setEventDurationOut(String eventDuration) throws InputNullException, InputDateException2 {

            if ("".equals(eventDuration )|| eventDuration == null ) {
                throw new InputNullException("Party Time ");
            }
        try{
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm"); //e.g 10:55 ==> 10 hours and 55 minutes
        long ms= 0;

            ms = sdf.parse(eventDuration).getTime();
            this.eventDuration = new Time(ms);
        } catch (ParseException | DateTimeException e) {
            throw new InputDateException2(eventDuration,"Event Duration", "hh:mm");
        }

    }
    public void setEventTimeOut(String eventTimeH, String eventTimeM) throws InputNullException, InputDateException2 {
        if("".equals(eventTimeH ) ||"".equals(eventTimeM) ){
            throw new InputNullException("Event  Time ");
        }
        try{
            this.eventOrario = LocalTime.of(Integer.valueOf(eventTimeH),Integer.valueOf(eventTimeM));
        } catch (NumberFormatException | DateTimeException d ){
            throw new InputDateException2("", "Event Time","hh   mm");

        }}
    public void setEventDateOut(LocalDate eventDate) throws InputDateException2, InputNullException {
        try {if(eventDate == null) {
            throw new InputNullException("eventDate");
        }
            this.eventDate = java.sql.Date.valueOf(eventDate);
        }catch (DateTimeParseException d){
            throw new InputDateException2(  eventDate.toString(),"PartyDate"," DD/MM/YYYY ");

        }
    }
    public void setGreenPassOut(boolean greenPass) {this.greenPass = greenPass;}
    public void setEventImgOut(File eventImg) throws InputNullException {
        if (eventImg == null) {
            throw new InputNullException("Party Image");
        }
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
    public Image getEventImgOut() throws SystemException {

        return ImageConverter.convertFileToFxImage(eventImg);

    }
}
