package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.utils.bean.jfx2.JFX2RequestBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;

public class JFX2UserRejectedRequestItemGUIController  {

    @FXML
    private Label labelEventName;

    @FXML
    private Label labelEventDate;

    @FXML
    private Label labelEventPrice;

    @FXML
    private ImageView eventPic;
    public void setEventPic(Image eventPic) {this.eventPic.setImage(eventPic);}

    public void cancelRequest(ActionEvent event) {
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(event, "/JFX2/JFX2UserRequestPage.fxml");
    }
    public void setAll(JFX2RequestBean jfx2RequestBean){

        try {
            setEventPic(jfx2RequestBean.getRelatedEventImgOut());
        } catch (SystemException e) {
            //
        }
        labelEventName.setText(jfx2RequestBean.getRelatedEventOut());
        labelEventDate.setText(jfx2RequestBean.getDateOut());
        labelEventPrice.setText(jfx2RequestBean.getEventBean().getEventPrice().toString());
    }
}
