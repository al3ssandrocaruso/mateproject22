package kapta.control.guicontroller.interfaceone.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.ReplaceSceneAndInitializePage;


public class JFX1UserItemGUIController {
    @FXML
    private ImageView imageViewProfilePic;

    @FXML
    private Label labelUsername;

    @FXML
    private AnchorPane ap;
    private JFX1UserBean userBean;




    private void setImageViewProfilePic(Image im ){this.imageViewProfilePic.setImage(im);}

    private void setLabelUsername(String username) {
        labelUsername.setText(username);
    }




    public void setAll(JFX1UserBean jfx1UserBean){
        this.userBean = jfx1UserBean;
        this.setLabelUsername(jfx1UserBean.getUsernameOut());
        try {
            this.setImageViewProfilePic(jfx1UserBean.getProfileImgOut());
        } catch (SystemException e) {
          //
        }
    }

    public void goToProfile(ActionEvent ae) {
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(ae, "/JFX1/JFX1UserProfile.fxml", userBean);

    }

}
