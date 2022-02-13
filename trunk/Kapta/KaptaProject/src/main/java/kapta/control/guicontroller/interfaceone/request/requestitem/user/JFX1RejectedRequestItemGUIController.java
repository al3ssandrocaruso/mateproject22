package kapta.control.guicontroller.interfaceone.request.requestitem.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.RequestApplicationLayer;
import kapta.utils.bean.beanout.jfx1.JFX1RequestBeanOut;

public class JFX1RejectedRequestItemGUIController {
    @FXML
    private Label EventName;
    @FXML
    private Label EventDate;
    @FXML
    private ImageView ImageView;

    private RequestApplicationLayer requestApplicationLayer;
    public void setRequestApplicationLayer(RequestApplicationLayer requestApplicationLayer) {
        this.requestApplicationLayer = requestApplicationLayer;
    }


    public void setEventImageView(Image eventImageView) {
        this.ImageView.setImage(eventImageView);
    }

    public void setEventDate(String eventDate) {
        this.EventDate.setText(eventDate);
    }

    public void setLabelEventName(String labelEventName) {
        this.EventName.setText(labelEventName);
    }

    public void deleteRequest(ActionEvent actionEvent) {
        this.requestApplicationLayer.goToDeleteRequest(actionEvent,"/JFX1/JFX1UserRequestPage.fxml");
    }
    public void setAll(JFX1RequestBeanOut jfx1RequestBeanOut, RequestApplicationLayer requestApplicationLayer){
        setRequestApplicationLayer(requestApplicationLayer);
        setLabelEventName(jfx1RequestBeanOut.getEventName());
        setEventDate(jfx1RequestBeanOut.getEventDate());
        setEventImageView(jfx1RequestBeanOut.getEventImage());
    }
}
