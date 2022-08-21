package kapta.utils.bean.jfx2;

import javafx.scene.image.Image;
import kapta.model.EventModel;
import kapta.utils.bean.EventBean;
import kapta.utils.utils.HelperSetDuration;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JFX2EventBean extends EventBean {

    public JFX2EventBean(EventModel eventModel){
        super(eventModel);
    }

    public JFX2EventBean(String eventName, String eventAddress, String eventOrario, String eventDate, Double eventDuration, Double eventPrice, boolean greenpass) {
        setEventNameOut(eventName);
        setEventAddressOut(eventAddress);
        setEventPriceOut(eventPrice);
        setEventOrarioOut(eventOrario);
        setEventDurationOut(eventDuration);
        setEventDateOut(eventDate);
        setGreenPassOut(greenpass);
    }
    public JFX2EventBean(EventBean eventBean) {
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



    public void setEventNameOut(String eventName) {
        this.eventName = eventName;
    }
    public void setEventAddressOut(String eventAddress) {
        this.eventAddress = eventAddress;
    }
    public void setEventOrarioOut(String eventTime) {
        int index= eventTime.indexOf(":"); //formato ora e.g 20:15
        this.eventOrario = LocalTime.of(Integer.valueOf(eventTime.substring(0,index)),Integer.valueOf(eventTime.substring(index+1)));
    }
    public void setEventDateOut(String eventDate){  //Date format e.g 03/09/2023
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(eventDate, formatter);
        this.eventDate = java.sql.Date.valueOf(localDate);
    }
    public void setEventDurationOut(Double partyDuration){
        this.eventDuration = new Time(HelperSetDuration.conv(partyDuration));
    }
    public void setEventPriceOut(Double eventPrice) {
        this.eventPrice = eventPrice;
    }
    public void setGreenPassOut(boolean greenPass) {this.greenPass = greenPass;}
    public void setEventImgOut(File eventImg) {
        this.eventImg = eventImg;
    }
    public void setEventIdOut(int eventId) {
        this.eventId = eventId;
    }


    public String getEventPriceOut() {
        return eventPrice.toString();
    }
    public Image getEventImgOut() {return  ImageConverter.convertFileToFxImage(eventImg);}
    public String getEventNameOut() {return eventName;}
    public String getEventDateOut() {
        return eventDate.toString();
    }



}