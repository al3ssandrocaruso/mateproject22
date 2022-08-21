package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import kapta.utils.bean.jfx2.JFX2UserBean;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;


public class JFX2UserItemGUIController {

    @FXML
    private ImageView imageProfile;

    @FXML
    private Label labelUsername;

    private JFX2UserProfileGUIController upgc;
    private AnchorPane stageToGet;

    private JFX2UserBean jfx2UserBean;



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
            JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage("/JFX2/JFX2UserProfile.fxml",this.jfx2UserBean,stage);
        }else{
            JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(actionEvent, "/JFX2/JFX2UserProfile.fxml", jfx2UserBean);
        }
    }
    public void setAll(JFX2UserBean jfx2UserBean, AnchorPane ap, JFX2UserProfileGUIController upgc){
        setLabelUsername(jfx2UserBean.getUsernameOut());
        setImageProfile(jfx2UserBean.getImageOut());
        setStageToGet(ap);
        this.jfx2UserBean = jfx2UserBean;
        setUpgc(upgc);
    }
}
