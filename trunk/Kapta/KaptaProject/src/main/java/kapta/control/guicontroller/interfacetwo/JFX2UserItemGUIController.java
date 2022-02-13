package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kapta.application.UserProfileApplicationLayer;
import kapta.utils.bean.beanout.jfx2.JFX2UserBeanOut;


public class JFX2UserItemGUIController {

    @FXML
    private ImageView imageProfile;

    @FXML
    private Label labelUsername;

    private JFX2UserProfileGUIController upgc;
    private UserProfileApplicationLayer userProfileApplicationLayer;
    private AnchorPane stageToGet;

    public void setUserProfileApplication(UserProfileApplicationLayer userProfileApplicationLayer) {
        this.userProfileApplicationLayer = userProfileApplicationLayer;
    }

    public AnchorPane getStageToGet() {
        return stageToGet;
    }

    public void setImageProfile(Image imageProfile) {
        this.imageProfile.setImage(imageProfile);
    }

    public void setLabelUsername(String username) {
        this.labelUsername.setText(username);
    }

    public void setStageToGet(AnchorPane stageToGet) {
        this.stageToGet = stageToGet;
    }


    public JFX2UserProfileGUIController getUpgc() {
        return upgc;
    }

    public void setUpgc(JFX2UserProfileGUIController upgc) {
        this.upgc = upgc;
    }

    public void gotoProfile(ActionEvent actionEvent) {
        Stage stage = (Stage) getStageToGet().getScene().getWindow();
        if(getUpgc()!=null){
            getUpgc().hidePopUp();
            userProfileApplicationLayer.navigationUserProfile(stage);
        }else{
            userProfileApplicationLayer.navigationUserProfile(actionEvent,"/JFX2/JFX2UserProfile.fxml");
        }
    }
    public void setAll(JFX2UserBeanOut jfx2UserBeanOut, AnchorPane ap, UserProfileApplicationLayer userProfileApplicationLayer, JFX2UserProfileGUIController upgc){
        setLabelUsername(jfx2UserBeanOut.getUsername());
        setImageProfile(jfx2UserBeanOut.getImage());
        setStageToGet(ap);
        setUserProfileApplication(userProfileApplicationLayer);
        setUpgc(upgc);
    }
}
