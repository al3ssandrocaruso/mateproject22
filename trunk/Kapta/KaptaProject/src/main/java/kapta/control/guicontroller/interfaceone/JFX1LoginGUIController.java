package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import kapta.control.appcontroller.LoginController;
// external
import kapta.utils.bean.J1.JFX1LoginBean;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.WrongPasswordException;
import kapta.utils.init.ReplaceScene;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.session.ThreadLocalSession;

import java.net.URL;
import java.util.ResourceBundle;


public class JFX1LoginGUIController implements Initializable {
    @FXML
    public AnchorPane ap;
    @FXML
    private CheckBox checkBoxEventManager;

    @FXML
    private TextField textFieldUsr;

    @FXML
    private PasswordField passwordField;

    @FXML
    void signInAction(ActionEvent ae) {
        int type = 0;
        if (checkBoxEventManager.isSelected()) {
            type = 1;
        }
        try{
            JFX1LoginBean loginBean = new JFX1LoginBean(textFieldUsr.getText(), passwordField.getText(), type);
            LoginController.login(loginBean);
            ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
             if (type == 1) {
                rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1ClubProfile.fxml");
            } else {
            rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1UserProfile.fxml");
            }
        }
        catch (WrongPasswordException e ){
            ErrorHandler.getInstance().reportFinalException(e);
        }
    }

    @FXML
    void signUpAction(ActionEvent ae) {
        ReplaceScene.replaceScene(ae, "/JFX1/JFX1RegisterSuper.fxml");
    }

    @FXML
    void switchInterface(ActionEvent ae) {
        ReplaceScene.replaceScene(ae, "/JFX2/JFX2Welcome.fxml");
    }


    public AnchorPane getAp() {
        return this.ap;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ThreadLocalSession.setIntrfc(1);
    }
}
