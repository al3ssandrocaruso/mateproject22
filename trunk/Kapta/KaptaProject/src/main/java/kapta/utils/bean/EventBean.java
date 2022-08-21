package kapta.utils.bean;

import kapta.model.EventModel;

import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public  class EventBean implements PartyEventBean {
    protected String eventName;
    protected Double eventPrice;
    protected String eventAddress;
    protected Time eventDuration;
    protected LocalTime eventOrario;
    protected File eventImg;
    protected Date eventDate;
    protected boolean greenPass;
    protected int eventId;
    //
    protected String eventCreator;
    protected int numParticipants;

    public EventBean(){

    }

    public EventBean(EventModel eventModel) {
        this.setEventCreator(eventModel.getEventCreator().getUsername());
        this.setEventName(eventModel.getName());
        this.setEventAddress(eventModel.getAddress());
        this.setEventOrario(eventModel.getOrario());
        this.setEventDate(eventModel.getDate());
        this.setEventDuration(eventModel.getDuration());
        this.setEventPrice(eventModel.getEventPrice());
        this.setEventId(eventModel.getId());
        this.setGreenPass(eventModel.isGreenPass());
        this.setEventImg(eventModel.getImg());
    }

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
    public int getNumParticipants() {return numParticipants;}
    public String getEventCreator() {return eventCreator;}


    public void setEventName(String eventName) {this.eventName = eventName;}
    public void setEventPrice(Double eventPrice) {
        this.eventPrice = eventPrice;
    }
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }
    public void setEventDuration(Time eventDuration) {
        this.eventDuration = eventDuration;
    }
    public void setEventOrario(LocalTime eventOrario) {
        this.eventOrario = eventOrario;
    }
    public void setEventImg(File eventImg) {
        this.eventImg = eventImg;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    public void setGreenPass(boolean greenPass) {
        this.greenPass = greenPass;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;}

    public void setEventCreator(String eventCreator) {
        this.eventCreator = eventCreator;
    }
}
