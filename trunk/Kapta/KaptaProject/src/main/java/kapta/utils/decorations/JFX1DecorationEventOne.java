package kapta.utils.decorations;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kapta.application.EventApplicationLayer;
import kapta.utils.VisualComponent;


public class JFX1DecorationEventOne extends Decorator {

    private String toWrite;

    public void setEventApplication(EventApplicationLayer eventApplication) {
        this.eventApplication = eventApplication;
    }

    private EventApplicationLayer eventApplication;

    public void setToWrite(String toWrite) {
        this.toWrite = toWrite;
    }

    public JFX1DecorationEventOne(VisualComponent component, EventApplicationLayer eventApplication) {
        super(component);
        setEventApplication(eventApplication);

    }

    protected VBox applyDecorationPartyOne(VBox input){
        VBox output = input;
        output.getChildren().clear();
        Button button=new Button(this.toWrite);
        button.setMinHeight(65);
        button.setMinWidth(125);
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        button.setFont(font);
        button.setStyle("-fx-background-color: #d00000;" + "-fx-background-radius: 28;" + "-fx-text-fill: white;");

        button.setOnAction((ActionEvent ae) -> eventApplication.goToDeleteEvent(ae, "/JFX1/JFX1ClubProfile.fxml"));
        output.setStyle("-fx-background-color: #200f54;");
        output.getChildren().add(button);
        return output;
    }


    @Override
    public VBox addUserPanel() {
        VBox preliminaryResult=super.addUserPanel();
        this.setToWrite("Delete");
        preliminaryResult=this.applyDecorationPartyOne(preliminaryResult);
        return preliminaryResult;
    }
}
