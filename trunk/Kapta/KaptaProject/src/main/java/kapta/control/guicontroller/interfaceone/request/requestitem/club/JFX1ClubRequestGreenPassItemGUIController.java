package kapta.control.guicontroller.interfaceone.request.requestitem.club;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.control.appcontroller.JoinEventController;
import kapta.utils.bean.jfx1.JFX1RequestBean;
import kapta.utils.init.ReplaceSceneAndInitializePage;

public class JFX1ClubRequestGreenPassItemGUIController {

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelEventName;

    @FXML
    private Label labelEventDate;

    @FXML
    private Label labelDateVacc;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label labelDoses;


    JFX1RequestBean requestBean;


    public void setRequestBean(JFX1RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public JFX1RequestBean getRequestBean() {
        return requestBean;
    }


    public void setLabelDoses(String labelDoses) {
        this.labelDoses.setText(labelDoses);
    }

    public void setImageViewProfilePic(Image im ){this.userImageView.setImage(im);}

    public void setLabelDate(String labelDate) {
        this.labelDateVacc.setText(labelDate);
    }

    public void setLabelEventDate(String labelEventDate) {this.labelEventDate.setText(labelEventDate);}

    public void setLabelEventName(String labelEventName) {
        this.labelEventName.setText(labelEventName);
    }

    public void setLabelUsername(String labelUsername) {
        this.labelUsername.setText(labelUsername);
    }

    public String getLabelUsername() {
        return labelUsername.getText();
    }


    public void rejectRequest(ActionEvent actionEvent) {
        JoinEventController.rejectRequest(getRequestBean());
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubRequestPage.fxml" );
    }

    public void acceptRequest(ActionEvent actionEvent) {
        JoinEventController.acceptRequest(getRequestBean());
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubRequestPage.fxml" );
    }
    public void setAll(JFX1RequestBean jfx1RequestBean){
        setRequestBean(jfx1RequestBean);
        setLabelEventName(jfx1RequestBean.getEventNameOut());
        setLabelEventDate(jfx1RequestBean.getEventDateOut());
        setLabelUsername(jfx1RequestBean.getSenderOut());
        setLabelDate(jfx1RequestBean.getVaccinationDateOut());
        setLabelDoses(jfx1RequestBean.getDosesOut());
        setImageViewProfilePic(jfx1RequestBean.getSenderImageOut());
    }
}
