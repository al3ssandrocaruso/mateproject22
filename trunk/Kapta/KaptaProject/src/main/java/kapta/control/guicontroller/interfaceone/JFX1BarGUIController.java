package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import kapta.utils.init.ReplaceSceneAndInitializePage;

import java.net.URL;
import java.util.ResourceBundle;

import static kapta.utils.session.ThreadLocalSession.userSession;

public class JFX1BarGUIController implements Initializable {

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelType;

    @FXML
    private Button btnHome;

    @FXML
    private VBox vboxBar;

    private ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();

    @FXML
    void profileAction(ActionEvent ae){
        int type=userSession.get().getType();
        //0-> user model
        //1-> club model
        if(type==1){       rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1ClubProfile.fxml");}
        else if(type==0){       rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1UserProfile.fxml");
        }
    }

    @FXML
    void homeAction(ActionEvent ae) {
        rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1UserHomePage.fxml");
    }

    @FXML
    void settingAction(ActionEvent ae) {
        int type=userSession.get().getType();
        //0-> user model
        //1-> club model
        if(type==1){       rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1ClubSetting.fxml");}
        else if(type==0){       rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1UserSetting.fxml");
        }
    }

    @FXML
    void signoutAction(ActionEvent ae) {
        rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1Login.fxml");
    }

    @FXML
    void requestAction(ActionEvent ae){

        if(userSession.get().getType()==0) {
            rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1UserRequestPage.fxml");
        }
        else{
            rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1ClubRequestPage.fxml");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int type=userSession.get().getType();
        //0-> sono un user model
        //1-> sono un club model
        if(type==1){
            this.labelUsername.setText("@" + userSession.get().getClubModel().getUsername());
            this.labelType.setText("Manager");
            vboxBar.getChildren().remove(btnHome);
        }
        else if(type==0){
            this.labelUsername.setText("@" + userSession.get().getUserModel().getUsername());
            this.labelType.setText("User");
        }
    }
}
