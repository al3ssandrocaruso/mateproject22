package kapta.control.guicontroller.interfaceone.request.requestitem.club;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.control.appcontroller.JoinEventController;
import kapta.control.guicontroller.interfaceone.JFX1AlertCreator;
import kapta.utils.bean.jfx1.JFX1RequestBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.ReplaceSceneAndInitializePage;

public class JFX1ClubRequestItemGUIController {
    @FXML
    private ImageView userImageView;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelEventName;
    @FXML
    private Label labelEventDate;

    private JFX1RequestBean requestBean;

    public void setRequestBean(JFX1RequestBean r) {
        this.requestBean = r;
    }

    public JFX1RequestBean getRequestBean() {
        return requestBean;
    }


    public void setImageViewProfilePic(Image im ){this.userImageView.setImage(im);}

    public void setLabelEventDate(String labelEventDate) {
        this.labelEventDate.setText(labelEventDate);
    }

    public void setLabelEventName(String labelEventName) {
        this.labelEventName.setText(labelEventName);
    }

    public void setLabelUsername(String labelUsername) {
        this.labelUsername.setText(labelUsername);
    }

    public Label getLabelUsername() {
        return labelUsername;
    }

    public void rejectRequest(ActionEvent actionEvent) {
        try {
            JoinEventController.rejectRequest(getRequestBean());
            ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
            replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubRequestPage.fxml" );
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        }

    }

    public void acceptRequest(ActionEvent actionEvent) {

        try {
            JoinEventController.acceptRequest(getRequestBean());
            ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
            replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubRequestPage.fxml" );
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        }


    }
    public void setAll(JFX1RequestBean jfx1RequestBean ){
        setRequestBean(jfx1RequestBean);
        setLabelEventName(jfx1RequestBean.getEventNameOut());
        setLabelEventDate(jfx1RequestBean.getEventDateOut());
        setLabelUsername(jfx1RequestBean.getSenderOut());
        try {
            setImageViewProfilePic(jfx1RequestBean.getSenderImageOut());
        } catch (SystemException e) {
            //
        }
    }
}
