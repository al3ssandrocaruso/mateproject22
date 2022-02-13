package kapta.utils.utils;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kapta.application.SettingsApplicationLayer;
import kapta.application.UserProfileApplicationLayer;

public class EndSettingsChanges {

    //Prima interfaccia
    public static void endChanges1(ActionEvent ae, SettingsApplicationLayer settingsApplicationLayer){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setStyle("-fx-background-color: white;");
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setSpacing(10);
        Button btn=new Button("Sign Out");

        btn.setOnAction(e-> {
            settingsApplicationLayer.goToLogin(ae);
            dialog.close();

        });

        btn.setStyle("-fx-background-radius: 28; " + "-fx-background-color: #200f54;" + "-fx-text-fill: white;");
        Font font = Font.font("Arial", FontWeight.BOLD, 15);
        btn.setFont(font);

        Text text=new Text("You've changed your info! Please, do a signOut and signIn with your new data.");
        text.setFont(font);
        text.setStyle("-fx-background-color: black");
        dialogVbox.getChildren().addAll(text,btn);
        Scene dialogScene = new Scene(dialogVbox, 800, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    //Seconda interfaccia
    public static void endChanges2(ActionEvent ae, UserProfileApplicationLayer userProfileApplicationLayer) {

        Stage s = new Stage();
        Button b = new Button("SignOut");
        b.setStyle("-fx-background-radius: 28; " + "-fx-background-color: #200f54;" + "-fx-text-fill: white;");
        TilePane r = new TilePane();
        new Alert(Alert.AlertType.NONE);

        Font font = Font.font("Arial", FontWeight.BOLD, 15);
        Text text = new Text("You've changed your info! Please, do a signOut and signIn with your new data.");
        text.setFont(font);
        text.setStyle("-fx-background-color: black");

        b.setOnAction(e -> {
                    userProfileApplicationLayer.goToLogin(ae);
                    s.close();
                }
        );

        r.getChildren().addAll(text,b);
        r.setAlignment(Pos.CENTER);

        Scene sc = new Scene(r, 800, 200);
        s.setScene(sc);
        s.show();
    }
}
