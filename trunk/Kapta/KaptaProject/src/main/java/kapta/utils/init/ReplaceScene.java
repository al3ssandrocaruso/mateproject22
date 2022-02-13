package kapta.utils.init;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kapta.MainApp;

import java.io.IOException;

public class ReplaceScene{

    private ReplaceScene(){
        //ignored
    }

    public static void replaceScene(ActionEvent ae, String fxml){

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml));
        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
    }
}
