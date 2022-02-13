package kapta.control.guicontroller.interfaceone.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.EventApplicationLayer;
import kapta.utils.bean.beanout.jfx1.JFX1EventBeanOut;

public class JFX1EventItemGUIController {

    @FXML
    private ImageView imageViewEventImage;

    @FXML
    private Label labelEventName;

    @FXML
    private Label labelEventDate;

    @FXML
    private Label labelEventPrice;

    private EventApplicationLayer eventApplicationLayer;

    public void goToEventPage(ActionEvent ae) {
        this.eventApplicationLayer.goToEventPage(ae, "/JFX1/JFX1EventPage.fxml");
    }

    private  void setLabelEventName(String eventName) {
        this.labelEventName.setText(eventName);
    }
    private void setLabelEventDate(String eventDate) {
        this.labelEventDate.setText(eventDate);
    }
    private void setLabelEventPrice(String labelEventPrice) {
        this.labelEventPrice.setText((labelEventPrice) + "€");
    }
    private void setImageViewEventImage (Image img){
        this.imageViewEventImage.setImage(img);
    }
    public void setEventApplication(EventApplicationLayer eventApplicationLayer) {
        this.eventApplicationLayer = eventApplicationLayer;
    }

    public void setAll(JFX1EventBeanOut jfx1EventBeanOut, EventApplicationLayer eventApplicationLayer){
        setLabelEventName(jfx1EventBeanOut.getEventName());
        setLabelEventDate(jfx1EventBeanOut.getEventDate());
        setLabelEventPrice(jfx1EventBeanOut.getEventPrice());
        setImageViewEventImage(jfx1EventBeanOut.getEventImg());
        setEventApplication(eventApplicationLayer);

    }
}
