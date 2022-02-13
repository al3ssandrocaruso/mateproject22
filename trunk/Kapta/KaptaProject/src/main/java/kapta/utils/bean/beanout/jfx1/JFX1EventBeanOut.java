package kapta.utils.bean.beanout.jfx1;

import javafx.scene.image.Image;
import kapta.model.EventModel;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class JFX1EventBeanOut {
    private String eventCreator;
    private String eventName;
    private String eventAddress;
    private String eventTime;
    private String eventDate;
    private String eventDuration;
    private String eventPrice;
    private String eventId;
    private String numParticipants;
    private boolean greenPass;
    private List<UserModel> participantList;
    private Image eventImg;

    public JFX1EventBeanOut(EventModel eventModel) {
        this.setEventCreator(eventModel.getEventCreator());
        this.setEventName(eventModel.getName());
        this.setEventAddress(eventModel.getAddress());
        this.setEventTime(eventModel.getOrario());
        this.setEventDate(eventModel.getDate());
        this.setEventDuration(eventModel.getDuration());
        this.setEventPrice(eventModel.getEventPrice());
        this.setEventId(eventModel.getId());
        this.setGreenPass(eventModel.isGreenPass());
        this.setEventImg(eventModel.getImg());
    }



    public String getEventCreator() {
        return eventCreator;
    }
    public void setEventCreator(ClubModel eventCreator) {
        this.eventCreator = eventCreator.getUsername();
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventAddress() {
        return eventAddress;
    }
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }
    public String getEventTime() {
        return eventTime;
    }
    public void setEventTime(LocalTime eventTime) {
        this.eventTime = String.valueOf(eventTime);
    }
    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = String.valueOf(eventDate);
    }
    public String getEventDuration() {
        return eventDuration;
    }
    public void setEventDuration(Time eventDuration) {
        this.eventDuration = String.valueOf(eventDuration);
    }
    public String getEventPrice() {
        return eventPrice;
    }
    public void setEventPrice(Double eventPrice) {
        this.eventPrice = String.valueOf(eventPrice);
    }
    public String getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = String.valueOf(eventId);
    }
    public boolean isGreenPass() {
        return greenPass;
    }
    public void setGreenPass(boolean greenPass) {
        this.greenPass = greenPass;
    }
    public String getNumParticipants() {
        return numParticipants;
    }
    public void setNumParticipants(int numParticipants) {
        this.numParticipants = String.valueOf(numParticipants);
    }
    public List<UserModel> getParticipantList() {
        return participantList;
    }
    public void setParticipantList(List<UserModel> participantList) {
        this.participantList = participantList;
    }
    public void setEventImg(File eventImg) {
        this.eventImg = ImageConverter.convertFileToFxImage(eventImg);
    }
    public Image getEventImg() {
        return eventImg;
    }
}
