package kapta.utils.decorations;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.VisualComponent;

public class JFX1DecorationUserOne extends Decorator{
    private String toWrite;

    public JFX1DecorationUserOne(VisualComponent component) {
        super(component);
    }

    public void setToWrite(String toWrite) {
        this.toWrite = toWrite;
    }

    protected VBox applyDecorationOne(VBox input){
        VBox output=input;
        output.getChildren().clear();
        Button button=new Button(this.toWrite);
        button.setMinHeight(45);
        button.setMinWidth(223);
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        button.setFont(font);
        button.setStyle("-fx-background-color: #200f54;" + "-fx-background-radius: 28;");

        button.setOnAction((ActionEvent event) -> { //button controller
            try {
                ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
                replaceSceneAndInitializePage.replaceSceneAndInitializePage(event,"/JFX1/JFX1UserCreateParty.fxml");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        button.setStyle("-fx-background-radius: 28; " + "-fx-background-color: #200f54;" + "-fx-text-fill: white;");
        output.getChildren().add(button);
        return output;
    }
    @Override
    public VBox addUserPanel(){
        VBox preliminaryResult=super.addUserPanel();
        this.setToWrite("Create Party");
        preliminaryResult=this.applyDecorationOne(preliminaryResult);
        return preliminaryResult;
    }
}
