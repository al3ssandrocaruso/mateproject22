package kapta.utils.bean.beanout.jfx2;

import javafx.scene.image.Image;
import kapta.model.RequestModel;
import kapta.utils.utils.ImageConverter;

import java.io.File;
import java.util.Date;

public class JFX2RequestBeanOut {
    String senderName;
    String relatedEvent ;
    String date;
    Image relatedEventImg;
    Integer numDoses;
    boolean greenPass;

    public JFX2RequestBeanOut(RequestModel requestModel) {
        setSenderName(requestModel.getSender().getUsername());
        setRelatedEvent(requestModel.getEvent().getName());
        setRelatedEventImg(requestModel.getSender().getProfileImg());
        setNumDoses(requestModel.getDoses());
        setGreenPass(requestModel.isHasGreenPass());
        setDate(requestModel.getEvent().getDate());
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date+"";
    }

    public void setRelatedEventImg(File image){
        this.relatedEventImg= ImageConverter.convertFileToFxImage(image);
    }

    public void setGreenPass(boolean greenPass) {
        this.greenPass = greenPass;
    }

    public void setNumDoses(Integer numDoses) {
        this.numDoses = numDoses;
    }
    public void setRelatedEvent(String relatedEvent) {
        this.relatedEvent = relatedEvent;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }


    public Integer getNumDoses() {
        return numDoses;
    }

    public boolean isGreenPass() {
        return greenPass;
    }

    public String getRelatedEvent() {
        return relatedEvent;
    }
    public String getSenderName() {
        return senderName;
    }
    public  Image getRelatedEventImg(){
        return relatedEventImg;
    }


}
