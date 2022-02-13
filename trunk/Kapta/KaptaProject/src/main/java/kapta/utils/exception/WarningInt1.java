package kapta.utils.exception;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kapta.utils.utils.GetDialogStage;

public class WarningInt1 implements Warning {
    public WarningInt1(String message) {
        GetDialogStage.startPopUp(null,message,"blue");
    }
}
