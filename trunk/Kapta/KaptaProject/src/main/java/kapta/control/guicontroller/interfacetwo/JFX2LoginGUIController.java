package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import kapta.control.appcontroller.LoginController;
import kapta.utils.bean.jfx2.JFX2LoginBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.exception.myexception.WrongPasswordException;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceScene;
import kapta.utils.mysession.ThreadLocalSession;

import java.net.URL;
import java.util.ResourceBundle;


public class JFX2LoginGUIController implements Initializable {

    @FXML
    private RadioButton radioBtnEventManager;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    public void backToWelcomePane(ActionEvent ae) {
        ReplaceScene.replaceScene(ae,"/JFX2/JFX2Welcome.fxml");
    }

    public void signIn(ActionEvent ae) {
        int tipo=0;
        if(radioBtnEventManager.isSelected()){tipo=1;}
        try {
            JFX2LoginBean loginBean = new JFX2LoginBean(textFieldUsername.getText(), textFieldPassword.getText(), tipo);
            LoginController.login(loginBean);
            JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
            if (tipo == 1) {
                rsip.replaceSceneAndInitializePage(ae, "/JFX2/JFX2ClubProfile.fxml");
            } else {
                rsip.replaceSceneAndInitializePage(ae, "/JFX2/JFX2UserProfile.fxml");
            }
        }
        catch (WrongPasswordException | SystemException e){
            JFX2AlertCreator.createAlert(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ThreadLocalSession.setIntrfc(2);
    }
}

