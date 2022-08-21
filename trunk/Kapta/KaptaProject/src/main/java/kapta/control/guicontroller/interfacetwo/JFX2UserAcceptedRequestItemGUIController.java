package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.utils.bean.J2.JFX2RequestBean;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;

public class JFX2UserAcceptedRequestItemGUIController {

    @FXML
    private Label labelEventName;
    @FXML
    private Label labelEventDate;
    @FXML
    private Label labelEventPrice;
    @FXML
    private ImageView eventPic;

    private JFX2RequestBean requestBean;

    public void setRequestBean(JFX2RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public JFX2RequestBean getRequestBean() {
        return requestBean;
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
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, "/JFX2/JFX2PartyEventPage.fxml", getRequestBean().getEventBean());
    }

    public void setAll(JFX2RequestBean jfx2RequestBean){

        setRequestBean(jfx2RequestBean);
        setEventPic(jfx2RequestBean.getRelatedEventImgOut());
        setLabelEventName(jfx2RequestBean.getRelatedEventOut());
        setLabelEventDate(jfx2RequestBean.getDateOut());
    }

}
