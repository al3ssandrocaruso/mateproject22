package kapta.utils.bean.beanout.jfx2;

import javafx.scene.image.Image;
import kapta.model.EventModel;
import kapta.utils.utils.ImageConverter;
import java.io.File;
import java.util.Date;

public class JFX2EventBeanOut {
    private String eventName;
    private String eventPrice;
    private String eventDate;
    private Image eventImg;

    public JFX2EventBeanOut(EventModel eventModel){
        setEventDate(eventModel.getDate());
        setEventImg(eventModel.getImg());
        setEventName(eventModel.getName());
        setEventPrice(eventModel.getEventPrice());
    }

    public void setEventPrice(Double eventPrice) {
        this.eventPrice = eventPrice.toString();
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEventImg(File eventImg) {
        this.eventImg = ImageConverter.convertFileToFxImage(eventImg);
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate.toString();
    }

    public String getEventPrice() {
        return eventPrice;
    }
    public Image getEventImg() {
        return eventImg;
    }
    public String getEventName() {
        return eventName;
    }
    public String getEventDate() {
        return eventDate;
    }


}
