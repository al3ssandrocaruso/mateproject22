package kapta.utils.utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GetDialogStage {
    private GetDialogStage(){
        //ignore
    }
    public static Stage startDialog(ActionEvent ae){
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(((Node) ae.getSource()).getScene().getWindow());
        return  dialog;
    }
}
