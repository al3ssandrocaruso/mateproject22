package kapta.control.guicontroller.interfaceone;


import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import kapta.utils.utils.GetDialogStage;

import java.io.IOException;

public class JFX1PopUp {

    private JFX1PopUp(){
        //ignored
    }

    public static void makePop(String message)  {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(JFX1PopUp.class.getResource("/JFX1/JFX1Login.fxml")) ;
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Alert");
        GetDialogStage.startPopUp(stage,message,"white");
    }
}
