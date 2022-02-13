package kapta.utils.utils;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    public static void startPopUp(Stage stage,String message,String color){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        if(stage!=null){dialog.initOwner(stage);}
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setSpacing(10);
        String style="-fx-background-color: "+color+";";
        dialogVbox.setStyle(style);
        Button btn=new Button("Retry");

        btn.setOnAction(e-> dialog.hide());

        btn.setStyle("-fx-background-radius: 28; " + "-fx-background-color: #200f54;" + "-fx-text-fill: white;");
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        btn.setFont(font);

        Label label=new Label(message);
        label.setFont(font);
        label.setStyle("-fx-background-color: white");
        dialogVbox.getChildren().addAll(label,btn);
        Scene dialogScene = new Scene(dialogVbox, 500, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    public static HBox fill(Button btnLoad,VBox dialogVbox,Stage dialog,Label label1){
        HBox hBox = new HBox(btnLoad);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.getChildren().addAll(label1, hBox);
        dialogVbox.setStyle("--fxbackground-color: white");

        Scene dialogScene = new Scene(dialogVbox, 500, 400);
        dialog.setResizable(false);
        dialog.setScene(dialogScene);
        dialog.show();
        return  hBox;
    }
}
