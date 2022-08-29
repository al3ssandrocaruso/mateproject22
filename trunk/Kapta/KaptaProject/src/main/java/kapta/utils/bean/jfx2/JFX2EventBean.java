package kapta.utils.bean.jfx2;

import javafx.scene.image.Image;
import kapta.model.EventModel;
import kapta.utils.bean.EventBean;
import kapta.utils.exception.myexception.InputDateException2;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.utils.HelperSetDuration;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JFX2EventBean extends EventBean {

    public JFX2EventBean(EventModel eventModel){
        super(eventModel);
    }

    public JFX2EventBean(String eventName, String eventAddress, String eventOrario, String eventDate, Double eventDuration, Double eventPrice, boolean greenpass) throws InputDateException2, InputNullException {
        setEventNameOut(eventName);
        setEventAddressOut(eventAddress);
        setEventPriceOut(eventPrice);
        setEventOrarioOut(eventOrario);
        setEventDurationOut(eventDuration);
        setEventDateOut(eventDate);
        setGreenPassOut(greenpass);
    }
    public JFX2EventBean(EventBean eventBean) {
       super(eventBean);
    }



    public void setEventNameOut(String eventName) throws InputNullException {
        if("".equals(eventName )){
            throw new InputNullException("Event Name");
        }
        this.eventName = eventName;
    }
    public void setEventAddressOut(String eventAddress) throws InputNullException {
        if("".equals(eventAddress )){
            throw new InputNullException("Event Address");
        }
        this.eventAddress = eventAddress;
    }
    public void setEventOrarioOut(String eventTime) throws InputDateException2 {
        try {
            int index = eventTime.indexOf(":"); //formato ora e.g 20:15
            this.eventOrario = LocalTime.of(Integer.valueOf(eventTime.substring(0, index)), Integer.valueOf(eventTime.substring(index + 1)));
        }
        catch (StringIndexOutOfBoundsException | DateTimeException e ){
            throw new InputDateException2(eventTime,"Event Time","hh:mm");
        }

    }
    public void setEventDateOut(String eventDate) throws InputDateException2 {  //Date format e.g 03/09/2023
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(eventDate, formatter);
            this.eventDate = java.sql.Date.valueOf(localDate);
        }catch (DateTimeParseException D){
            throw new InputDateException2(eventDate,"Event Date","dd/mm/yyyy");
        }
    }
    public void setEventDurationOut(Double partyDuration) throws InputDateException2 {
        this.eventDuration = new Time(HelperSetDuration.conv(partyDuration));
    }
    public void setEventPriceOut(Double eventPrice) {
        this.eventPrice = eventPrice;
    }
    public void setGreenPassOut(boolean greenPass) {this.greenPass = greenPass;}
    public void setEventImgOut(File eventImg) throws InputNullException {
        if(eventImg== null){
            throw new InputNullException("Image");
        }
        this.eventImg = eventImg;
    }


    public String getEventPriceOut() {
        return eventPrice.toString();
    }
    public Image getEventImgOut() throws SystemException {return  ImageConverter.convertFileToFxImage(eventImg);}
    public String getEventNameOut() {return eventName;}
    public String getEventDateOut() {
        return eventDate.toString();
    }



}