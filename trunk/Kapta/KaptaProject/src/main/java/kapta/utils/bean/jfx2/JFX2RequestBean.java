package kapta.utils.bean.jfx2;

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
        super(requestBean);
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
