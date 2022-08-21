package kapta.utils.bean.J2;

import javafx.scene.image.Image;
import kapta.model.RequestModel;
import kapta.utils.bean.RequestBean;
import kapta.utils.utils.ImageConverter;

public class JFX2RequestBean extends RequestBean {

    public JFX2RequestBean(RequestModel requestModel){
        super();
    }
    public JFX2RequestBean(Integer numDoses, String vaccinationDate) {
        setNumDosesOut(numDoses);
        setVaccinationDateOut(vaccinationDate);
    }

    public JFX2RequestBean(RequestBean requestBean){
        setHasGreenPass(requestBean.isHasGreenPass());
        setSender(requestBean.getSender());
        setVaccinationDate(requestBean.getVaccinationDate());
        setDoses(requestBean.getDoses());
        setEventName(requestBean.getEventName());
        setRequestId(requestBean.getRequestId());
        setEventId(requestBean.getEventId());
        setStatus(requestBean.getStatus());
        setClubReceiver(requestBean.getClubReceiver());
        setEventImage(requestBean.getEventImage());
        setSenderImage(requestBean.getSenderImage());
        setEventDate(requestBean.getEventDate());
    }

    public void setNumDosesOut(Integer numDoses) {
        this.doses = numDoses;
    }

    public void setVaccinationDateOut(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }



    public Integer getNumDosesOut() {
        return doses;
    }
    public boolean isGreenPassOut() {
        return this.eventBean.isGreenPass();
    }
    public String getRelatedEventOut() {
        return eventBean.getEventName();
    }
    public String getSenderNameOut() {
        return sender;
    }
    public Image getRelatedEventImgOut(){
        return ImageConverter.convertFileToFxImage(eventBean.getEventImg());
    }
    public String getDateOut(){
        return this.eventBean.getEventDate().toString();
    }
}
