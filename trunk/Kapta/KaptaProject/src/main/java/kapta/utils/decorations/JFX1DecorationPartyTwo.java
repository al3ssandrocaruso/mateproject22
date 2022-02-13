package kapta.utils.decorations;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kapta.application.PartyApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1PartyPageGUIController;
import kapta.utils.VisualComponent;

public class JFX1DecorationPartyTwo extends Decorator {

    Button button=new Button();

    private String radius = "-fx-background-radius: 28;";
    private String white = "-fx-text-fill: white;";
    private String toWrite;
    private PartyApplicationLayer partyApplicationLayer;

    public void setPartyApplication(PartyApplicationLayer partyApplication) {
        this.partyApplicationLayer = partyApplication;
    }

    public PartyApplicationLayer getPartyApplication() {
        return partyApplicationLayer;
    }

    public void setToWrite(String write) {
        this.toWrite = write;
    }


    public JFX1DecorationPartyTwo(VisualComponent component, PartyApplicationLayer partyApplication) {
        super(component);
        setPartyApplication(partyApplication);
        //In generale setto il tasto su Join ma se partecipo già all'evento allora mostro Leave
        this.setToWrite("Join");
        if(!this.partyApplicationLayer.doIjoinedYet()){
            button.setStyle("-fx-background-color: #0073c4;" + radius + white);
            this.setToWrite("Join");
        } else if(this.partyApplicationLayer.doIjoinedYet()) {
            button.setStyle("-fx-background-color: #d00000;" + radius + white);
            this.setToWrite("Leave");
        }
        this.addUserPanel();

    }

    protected VBox applyDecorationPartyTwo(VBox input){
        VBox output=input;
        output.getChildren().clear();
        button.setText(this.toWrite);
        button.setMinHeight(65);
        button.setMinWidth(125);
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        button.setFont(font);

        new JFX1PartyPageGUIController();
        button.setOnAction((ActionEvent ae) -> { //button controller
            if (!this.partyApplicationLayer.doIjoinedYet()) {
                button.setStyle("-fx-background-color: #d00000;" + radius + white);

                //Setto l'avviso
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(button.getScene().getWindow());
                VBox vBox = new VBox(20);
                Text text = new Text();
                text.setFont(font);
                vBox.setAlignment(Pos.CENTER);

                int ret = this.partyApplicationLayer.goToJoinParty();
                switch (ret){
                    case 0:{
                        this.setToWrite("Leave");
                        this.addUserPanel();
                        break;
                    }
                    case -1:{
                        text.setText("You've have already joined a party in this date yet!");
                        vBox.getChildren().add(text);
                        vBox.setStyle("-fx-background-color: #ff4040");
                        Scene dialogScene = new Scene(vBox, 700, 250);
                        dialog.setScene(dialogScene);
                        dialog.show();
                        break;
                    }
                    case -2:{
                        text.setText("You can't Join the party 'cause it is expired.");
                        vBox.getChildren().add(text);
                        vBox.setStyle("-fx-background-color: #ff4040");
                        Scene dialogScene = new Scene(vBox, 600, 250);
                        dialog.setScene(dialogScene);
                        dialog.show();
                        break;
                    }
                    default: break;
                }

            } else {
                button.setStyle("-fx-background-color: #0073c4;" + radius + white);
                this.partyApplicationLayer.goToLeaveParty();
                this.setToWrite("Join");
                this.addUserPanel();
            }
            this.partyApplicationLayer.goToPartyPage(ae,"/JFX1/JFX1PartyPage.fxml");

        });
        output.getChildren().add(button);
        output.setStyle("-fx-background-color: #200f54");
        return output;
    }
    @Override
    public VBox addUserPanel() {
        VBox preliminaryResult=super.addUserPanel();
        preliminaryResult=this.applyDecorationPartyTwo(preliminaryResult);
        return preliminaryResult;
    }
}
