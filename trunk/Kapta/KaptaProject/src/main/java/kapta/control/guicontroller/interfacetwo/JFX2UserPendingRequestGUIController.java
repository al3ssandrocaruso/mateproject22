package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.RequestApplicationLayer;
import kapta.utils.bean.beanout.jfx2.JFX2RequestBeanOut;

public class JFX2UserPendingRequestGUIController {
    @FXML
    private ImageView eventPic;

    @FXML
    private Label labelEventName;

    @FXML
    private Label labelEventDate;



    private RequestApplicationLayer requestApplicationLayer;


    public void infoPendingEvent(ActionEvent actionEvent) {
        requestApplicationLayer.navigateToEventPage(actionEvent,"/JFX2/JFX2PartyEventPage.fxml");
    }
    public void setAll(JFX2RequestBeanOut jfx2RequestBeanOut,RequestApplicationLayer requestApplicationLayer){
        eventPic.setImage(jfx2RequestBeanOut.getRelatedEventImg());
        this.labelEventName.setText( jfx2RequestBeanOut.getRelatedEvent());
        this.labelEventDate.setText(jfx2RequestBeanOut.getDate());
        this.requestApplicationLayer =requestApplicationLayer;
    }

    public void cancelRequest(ActionEvent actionEvent) {
        requestApplicationLayer.goToDeleteRequest(actionEvent,"/JFX2/JFX2UserRequestPage.fxml");
    }
}
