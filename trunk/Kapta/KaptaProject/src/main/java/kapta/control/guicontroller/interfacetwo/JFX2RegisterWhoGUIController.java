package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kapta.utils.init.ReplaceScene;

public class JFX2RegisterWhoGUIController {

    @FXML
    void gotoEventManagerRegister(ActionEvent actionEvent) {
        ReplaceScene.replaceScene(actionEvent,"/JFX2/JFX2RegisterClub.fxml");
    }

    @FXML
    void gotoNormalUser(ActionEvent actionEvent) {
        ReplaceScene.replaceScene(actionEvent,"/JFX2/JFX2RegisterUser.fxml");
    }

    @FXML
    void backToWelcomePane(ActionEvent ae){
        ReplaceScene.replaceScene(ae, "/JFX2/JFX2Welcome.fxml");
    }
}
