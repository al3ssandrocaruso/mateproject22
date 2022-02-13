package kapta.utils.bean.beanout.jfx1;

import javafx.scene.image.Image;
import kapta.model.RequestModel;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.util.Date;

public class JFX1RequestBeanOut {

    private String sender;
    private String comment;
    private String eventName;
    private String requestId;
    private String eventId;
    private String statusCheckIn;
    private String eventDate;
    private String vaccinationDate;
    private String numDoses;
    private Image eventImage;
    private Image senderImage;


    public JFX1RequestBeanOut(RequestModel requestModel) {
        this.setSender(requestModel.getSender().getUsername());
        this.setEventName(requestModel.getEvent().getName());
        this.setRequestId(requestModel.getRequestId());
        this.setEventId(requestModel.getEventId());
        this.setStatusCheckIn(requestModel.getStatus());
        this.setEventDate(requestModel.getEvent().getDate());
        this.setVaccinationDate(requestModel.getVaccinationDate());
        this.setNumDoses(requestModel.getDoses());
        this.setEventImage(requestModel.getEvent().getImg());
        this.setSenderImage(requestModel.getSender().getProfileImg());
    }

   public void setEventImage(File eventImage){
        this.eventImage= ImageConverter.convertFileToFxImage(eventImage);
   }
   public void setSenderImage(File senderImage){
        this.senderImage=ImageConverter.convertFileToFxImage(senderImage);
   }

    public Image getEventImage() {
        return eventImage;
    }

    public Image getSenderImage() {
        return senderImage;
    }

    public void setNumDoses(int numDoses) {
        this.numDoses = numDoses +"";
    }

    public String getNumDoses() {
        return numDoses;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate.toString();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = String.valueOf(requestId);
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = String.valueOf(eventId);
    }

    public String getStatusCheckIn() {
        return statusCheckIn;
    }

    public void setStatusCheckIn(int statusCheckIn) {
        this.statusCheckIn = String.valueOf(statusCheckIn);
    }
}
