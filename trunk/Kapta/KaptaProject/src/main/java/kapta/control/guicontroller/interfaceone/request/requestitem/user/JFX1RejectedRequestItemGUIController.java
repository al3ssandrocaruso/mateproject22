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
    private Label labelEventName;
    @FXML
    private Label labelEventDate;
    @FXML
    private ImageView eventImageView;

    private RequestApplicationLayer requestApplicationLayer;
    public void setRequestApplicationLayer(RequestApplicationLayer requestApplicationLayer) {
        this.requestApplicationLayer = requestApplicationLayer;
    }


    public void setEventImageView(Image eventImageView) {
        this.eventImageView.setImage(eventImageView);
    }

    public void setEventDate(String eventDate) {
        this.labelEventDate.setText(eventDate);
    }

    public void setLabelEventName(String labelEventName) {
        this.labelEventName.setText(labelEventName);
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
