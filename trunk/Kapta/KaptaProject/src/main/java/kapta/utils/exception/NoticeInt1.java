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

public class NoticeInt1 implements Notice {

    public NoticeInt1(String message) {

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setStyle("-fx-background-color: green;");
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setSpacing(10);
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
}
