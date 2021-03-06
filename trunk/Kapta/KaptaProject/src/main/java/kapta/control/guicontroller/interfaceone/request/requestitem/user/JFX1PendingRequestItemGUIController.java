package kapta.control.guicontroller.interfaceone.request.requestitem.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.RequestApplicationLayer;
import kapta.utils.bean.beanout.jfx1.JFX1RequestBeanOut;

public class JFX1PendingRequestItemGUIController {
    @FXML
    private Label labelName;
    @FXML
    private Label labelDate;
    @FXML
    private ImageView eventImg;

    private RequestApplicationLayer requestApplicationLayer;

    public void setRequestApplicationLayer(RequestApplicationLayer requestApplicationLayer) {
        this.requestApplicationLayer = requestApplicationLayer;
    }

    public void setEventImageView(Image eventImageView) {
        this.eventImg.setImage(eventImageView);
    }

    public void setLabelEventName(String labelEventName) {
        this.labelName.setText(labelEventName);
    }

    public void setLabelEventDate(String  labelEventDate) {
        this.labelDate.setText(labelEventDate);
    }

    public void goToEventPage(ActionEvent actionEvent) {
        this.requestApplicationLayer.navigateToEventPage(actionEvent,"/JFX1/JFX1EventPage.fxml");
    }

    public void deletePendingRequest(ActionEvent actionEvent) {
        this.requestApplicationLayer.goToDeleteRequest(actionEvent,"/JFX1/JFX1UserRequestPage.fxml");

    }
    public void setAll(JFX1RequestBeanOut jfx1RequestBeanOut, RequestApplicationLayer requestApplicationLayer){
        setRequestApplicationLayer(requestApplicationLayer);
        setLabelEventName(jfx1RequestBeanOut.getEventName());
        setLabelEventDate(jfx1RequestBeanOut.getEventDate());
        setEventImageView(jfx1RequestBeanOut.getEventImage());
    }
}

