package kapta.utils.bean;


import kapta.model.RequestModel;
import java.io.File;
import java.util.Date;

public  class RequestBean {


    protected String sender;
    protected String vaccinationDate;
    protected int doses;
    protected int requestId;
    protected int status;
    protected String clubReceiver;
    protected File senderImage;


    protected EventBean eventBean = new EventBean();


    public RequestBean (){}

    public RequestBean(RequestModel requestModel){
        setHasGreenPass(requestModel.isHasGreenPass());
        setSender(requestModel.getSender().getUsername());
        setVaccinationDate(requestModel.getVaccinationDate());
        setDoses(requestModel.getDoses());
        setEventName(requestModel.getEvent().getName());
        setRequestId(requestModel.getRequestId());
        setEventId(requestModel.getEvent().getId());
        setStatus(requestModel.getStatus());
        setClubReceiver(requestModel.getReceiver().getUsername());
        setEventImage(requestModel.getEvent().getImg());
        setSenderImage(requestModel.getSender().getProfileImg());
        setEventDate(requestModel.getEvent().getDate());
        System.out.println("qui creazioene con status"+status );
    }


    public boolean isHasGreenPass() {
        return eventBean.isGreenPass();
    }
    public String getSender() {
        return sender;
    }
    public String getVaccinationDate() {
        return vaccinationDate;
    }
    public int getDoses() {
        return doses;
    }
    public String getEventName() {
        return eventBean.getEventName();
    }
    public int getRequestId() {
        return requestId;
    }
    public int getEventId() {
        return eventBean.getEventId();
    }
    public int getStatus() {
        return status;
    }
    public String getClubReceiver() {
        return clubReceiver;
    }

    public File getEventImage() {
        return eventBean.eventImg;
    }
    public File getSenderImage() {
        return senderImage;
    }
    public Date getEventDate(){return  eventBean.getEventDate(); }
    public EventBean getEventBean(){return eventBean; }


    public void setHasGreenPass(boolean hasGreenPass) {
        this.eventBean.greenPass = hasGreenPass;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
    public void setDoses(int doses) {
        this.doses = doses;
    }
    public void setEventName(String eventName) {
        this.eventBean.eventName = eventName;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public void setEventId(int eventId) {
        this.eventBean.eventId = eventId;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setClubReceiver(String clubReceiver) {
        this.clubReceiver = clubReceiver;
    }

    public void setEventImage(File eventImage) {
        this.eventBean.eventImg = eventImage;
    }

    public void setSenderImage(File senderImage) {
        this.senderImage = senderImage;
    }

    public void setEventDate(Date eventDate) {
        this.eventBean.eventDate = eventDate;
    }
}
