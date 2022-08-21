package kapta.utils.bean.jfx1;

import javafx.scene.image.Image;
import kapta.model.RequestModel;
import kapta.utils.bean.RequestBean;
import kapta.utils.utils.ImageConverter;

public class JFX1RequestBean  extends RequestBean {


    // this is used form the view
    public JFX1RequestBean(Integer numDoses, String vaccinationDate) {
        setNumDosesOut(numDoses);
        setVaccinationDateOut(vaccinationDate);
    }

    public  JFX1RequestBean (RequestModel requestModel){
        super(requestModel) ;
    }

    public JFX1RequestBean(RequestBean requestBean) {
        super(requestBean);
    }


    public void setNumDosesOut(Integer numDosesOut){
        this.doses= numDosesOut.intValue();
    }

    public void setVaccinationDateOut(String vaccinationDateOut){
        this.vaccinationDate= vaccinationDateOut;
    }


    public boolean isHasGreenPassOut() {
        return this.eventBean.isGreenPass();
    }
    public String getSenderOut() {
        return sender;
    }
    public String getVaccinationDateOut() {
        return vaccinationDate;
    }
    public String  getDosesOut() {
        return String.valueOf(doses);
    }
    public String getEventOut() {
        return this.eventBean.getEventName();
    }
    public int getRequestIdOut() {
        return requestId;
    }
    public int getEventIdOut() {
        return this.eventBean.getEventId();
    }
    public String getStatusOut() {
        return String.valueOf(status);
    }
    public String getClubReceiverOut() {
        return clubReceiver;
    }

    public Image getEventImageOut(){
        return ImageConverter.convertFileToFxImage(eventBean.getEventImg());
    }

    public Image getSenderImageOut(){
        return ImageConverter.convertFileToFxImage(senderImage);
    }

    public String getEventDateOut(){
        return this.eventBean.getEventDate().toString();
    }
    public String getEventNameOut(){
        return this.eventBean.getEventName();
    }





    // a request has not set out !!




}
