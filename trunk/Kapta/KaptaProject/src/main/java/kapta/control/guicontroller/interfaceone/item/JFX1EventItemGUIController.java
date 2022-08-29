package kapta.control.guicontroller.interfaceone.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.utils.bean.jfx1.JFX1EventBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.ReplaceSceneAndInitializePage;

public class JFX1EventItemGUIController {

    @FXML
    private ImageView imageViewEventImage;

    @FXML
    private Label labelEventName;

    @FXML
    private Label labelEventDate;

    @FXML
    private Label labelEventPrice;



    private JFX1EventBean eventBean;


    public void goToEventPage(ActionEvent ae) {
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1EventPage.fxml", this.eventBean);}


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


    public void setAll(JFX1EventBean jfx1EventBean) throws SystemException {

        this.eventBean=jfx1EventBean;
        setLabelEventName(jfx1EventBean.getEventNameOut());
        setLabelEventDate(jfx1EventBean.getEventDateOut());
        setLabelEventPrice(jfx1EventBean.getEventPriceOut());
        setImageViewEventImage(jfx1EventBean.getEventImgOut());
    }


}
