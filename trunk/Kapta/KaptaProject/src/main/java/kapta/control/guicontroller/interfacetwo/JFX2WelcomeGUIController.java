package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kapta.utils.init.ReplaceScene;

public class JFX2WelcomeGUIController {

    @FXML
    void gotoSignInPage(ActionEvent ae) {
        ReplaceScene.replaceScene(ae,"/JFX2/JFX2Login.fxml");
    }

    @FXML
    void gotoRegisterPage(ActionEvent ae) {
        ReplaceScene.replaceScene(ae,"/JFX2/JFX2RegisterWho.fxml");
    }

    @FXML
    void switchInterface(ActionEvent ae)  {
        ReplaceScene.replaceScene(ae, "/JFX1/JFX1Login.fxml");
    }
}
