package kapta.control.guicontroller.interfaceone.request.requestitem.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.utils.bean.J1.JFX1RequestBean;
import kapta.utils.init.ReplaceSceneAndInitializePage;


public class JFX1PendingRequestItemGUIController {
    @FXML
    private Label labelName;
    @FXML
    private Label labelDate;
    @FXML
    private ImageView eventImg;

    JFX1RequestBean requestBean;


    public JFX1RequestBean getRequestBean() {
        return requestBean;
    }



    public void setRequestBean(JFX1RequestBean requestBean) {
        this.requestBean = requestBean;
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
        ReplaceSceneAndInitializePage rp = new ReplaceSceneAndInitializePage();
        rp.replaceSceneAndInitializePage(actionEvent, "/JFX1/JFX1EventPage.fxml", getRequestBean().getEventBean());
    }

    public void deletePendingRequest(ActionEvent actionEvent) {
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1UserRequestPage.fxml" );

    }


    public void setAll(JFX1RequestBean jfx1RequestBean){
        setRequestBean(jfx1RequestBean);
        setLabelEventName(jfx1RequestBean.getEventOut());
        setLabelEventDate(jfx1RequestBean.getEventDateOut());
        setEventImageView(jfx1RequestBean.getEventImageOut());
    }
}

