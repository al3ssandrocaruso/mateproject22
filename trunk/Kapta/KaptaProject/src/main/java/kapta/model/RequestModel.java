package kapta.model;

import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;

public class RequestModel {

    private boolean hasGreenPass;
    private UserModel sender;
    private String vaccinationDate;
    private int doses;
    private EventModel event;
    private int requestId;
    private int eventId;
    private int status;
    private ClubModel receiver;


    // Set e Get
    public ClubModel getReceiver() {
        return receiver;
    }
    public void setReceiver(ClubModel receiver) {
        this.receiver = receiver;
    }
    public void setEvent(EventModel event) {
        this.event = event;
    }
    public void setSender(UserModel sender) {
        this.sender = sender;
    }
    public EventModel getEvent() {
        return event;
    }
    public UserModel getSender() {
        return sender;
    }
    public int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getVaccinationDate() {
        return vaccinationDate;
    }
    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
    public int getDoses() {
        return doses;
    }
    public void setDoses(int doses) {
        this.doses = doses;
    }
    public boolean isHasGreenPass() {
        return hasGreenPass;
    }

    public void setHasGreenPass(boolean hasGreenPass) {
        this.hasGreenPass = hasGreenPass;
    }

    public RequestModel(EventModel event, UserModel user) {
        this.sender = user;
        this.event = event;
    }

    public RequestModel(EventModel event, UserModel sender, int status, ClubModel receiver, int requestId,boolean greenPass,String vaccDate,int numDoses) {
        this.setSender(sender);
        this.setEvent(event);
        this.setStatus(status);
        this.setReceiver(receiver);
        this.setRequestId(requestId);
        this.setHasGreenPass(greenPass);
        if(this.hasGreenPass){
            this.setVaccinationDate(vaccDate);
            this.setDoses(numDoses);
        }

    }
    public RequestModel(EventModel event, UserModel sender, int status, ClubModel receiver, boolean greenPass,String vaccDate,int numDoses) {
        this.setSender(sender);
        this.setEvent(event);
        this.setStatus(status);
        this.setReceiver(receiver);
        this.setHasGreenPass(greenPass);
        if(this.hasGreenPass){
            this.setVaccinationDate(vaccDate);
            this.setDoses(numDoses);
        }

    }
}
