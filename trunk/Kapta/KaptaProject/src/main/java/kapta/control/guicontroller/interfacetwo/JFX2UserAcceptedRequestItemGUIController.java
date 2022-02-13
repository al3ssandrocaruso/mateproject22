package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.RequestApplicationLayer;
import kapta.utils.bean.beanout.jfx2.JFX2RequestBeanOut;

public class JFX2UserAcceptedRequestItemGUIController {

    @FXML
    private Label labelEventName;
    @FXML
    private Label labelEventDate;
    @FXML
    private Label labelEventPrice;
    @FXML
    private ImageView eventPic;

    private RequestApplicationLayer requestApplicationLayer;
    public void setRequestApplicationLayer(RequestApplicationLayer requestApplicationLayer) {
        this.requestApplicationLayer = requestApplicationLayer;
    }
    public void setEventPic(Image eventPic) {this.eventPic.setImage(eventPic);}
    public void setLabelEventName(String labelEventName) {
        this.labelEventName.setText(labelEventName);
    }
    public void setLabelEventDate(String labelEventDate) {
        this.labelEventDate.setText(labelEventDate);
    }
    public void setLabelEventPrice(String labelEventPrice) {
        this.labelEventPrice.setText(labelEventPrice);
    }

    public void infoEvent(ActionEvent ae) {
        requestApplicationLayer.navigateToEventPage(ae,"/JFX2/JFX2PartyEventPage.fxml");
    }

    public void setAll(JFX2RequestBeanOut jfx2RequestBeanOut, RequestApplicationLayer requestApplicationLayer){
        setEventPic(jfx2RequestBeanOut.getRelatedEventImg());
        setLabelEventName(jfx2RequestBeanOut.getRelatedEvent());
        setLabelEventDate(jfx2RequestBeanOut.getDate());
        setRequestApplicationLayer(requestApplicationLayer);
    }

}
