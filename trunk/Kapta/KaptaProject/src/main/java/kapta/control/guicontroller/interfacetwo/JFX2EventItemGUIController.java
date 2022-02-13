package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.EventApplicationLayer;
import kapta.utils.bean.beanout.jfx2.JFX2EventBeanOut;

public class JFX2EventItemGUIController {
    @FXML
    private ImageView imageViewEventImage;
    @FXML
    private Label labelEventName;
    @FXML
    private Label labelEventData;
    @FXML
    private Label labelEventPrice;

    private EventApplicationLayer eventApplicationLayer;

    public EventApplicationLayer getEventApplication() {return eventApplicationLayer;}
    public void setEventApplication(EventApplicationLayer eventApplicationLayer) {this.eventApplicationLayer = eventApplicationLayer;}

    public void setLabelEventName(String eventName) {
        this.labelEventName.setText(eventName);
    }
    public void setImageViewEventImage(Image imageViewEventImage) {
        this.imageViewEventImage.setImage(imageViewEventImage);
    }
    public void setLabelEventData(String eventDate) {
        this.labelEventData.setText(eventDate);
    }
    public void setLabelEventPrice(String eventPrice) {
        this.labelEventPrice.setText(eventPrice+"$");
    }

    public void gotoEventInfo(ActionEvent ae) {
        this.eventApplicationLayer.goToEventPage(ae, "/JFX2/JFX2PartyEventPage.fxml");
    }

    public void setAll(JFX2EventBeanOut jfx2EventBeanOut, EventApplicationLayer eventApplicationLayer){
        setImageViewEventImage(jfx2EventBeanOut.getEventImg());
        setLabelEventData(jfx2EventBeanOut.getEventDate());
        setLabelEventPrice(jfx2EventBeanOut.getEventPrice());
        setLabelEventName(jfx2EventBeanOut.getEventName());
        setEventApplication(eventApplicationLayer);
    }
}
