package kapta.utils.decorations;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kapta.application.PartyApplicationLayer;
import kapta.utils.VisualComponent;


//This class is used to initialize the party page in base of the user who visited this page is the creator of the party
public class JFX1DecorationPartyOne extends Decorator{

    private String toWrite;
    private PartyApplicationLayer partyApplication;

    public void setPartyApplication(PartyApplicationLayer partyApplication) {
        this.partyApplication = partyApplication;
    }

    public void setToWrite(String toWrite) {
        this.toWrite = toWrite;
    }

    public JFX1DecorationPartyOne(VisualComponent component, PartyApplicationLayer partyApplication) {
        super(component);
        setPartyApplication(partyApplication);
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

        button.setOnAction((ActionEvent ae) -> this.partyApplication.goToDeleteParty(ae,"/JFX1/JFX1UserProfile.fxml"));
        output.setStyle("-fx-background-color: #200f54");
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
