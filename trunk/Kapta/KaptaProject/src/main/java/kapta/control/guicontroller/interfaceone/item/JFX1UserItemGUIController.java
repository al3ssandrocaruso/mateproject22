package kapta.control.guicontroller.interfaceone.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import kapta.application.UserProfileApplicationLayer;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;


public class JFX1UserItemGUIController {
    @FXML
    private ImageView imageViewProfilePic;

    @FXML
    private Label labelUsername;

    @FXML
    private AnchorPane ap;

    private UserProfileApplicationLayer userProfileApplicationLayer;



    private void setImageViewProfilePic(Image im ){this.imageViewProfilePic.setImage(im);}

    private void setLabelUsername(String username) {
        labelUsername.setText(username);
    }

    public void setUserProfileApplication(UserProfileApplicationLayer userProfileApplicationLayer) {
        this.userProfileApplicationLayer = userProfileApplicationLayer;
    }


    public void setAll(JFX1UserBeanOut jfx1UserBeanOut, UserProfileApplicationLayer userProfileApplicationLayer){
        this.setLabelUsername(jfx1UserBeanOut.getUsername());
        this.setImageViewProfilePic(jfx1UserBeanOut.getProfileImg());
        this.setUserProfileApplication(userProfileApplicationLayer);

    }

    public void goToProfile(ActionEvent ae) {
        this.userProfileApplicationLayer.navigationUserProfile(ae,"/JFX1/JFX1UserProfile.fxml");
    }

}
