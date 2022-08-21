package kapta.utils.decorations;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kapta.control.appcontroller.DeletePartyEventController;
import kapta.utils.VisualComponent;
import kapta.utils.bean.jfx1.JFX1PartyBean;
import kapta.utils.init.ReplaceSceneAndInitializePage;


public class JFX1DecorationPartyOne extends Decorator{

    private String toWrite;
    private JFX1PartyBean partyBean;


    public void setPartyBean(JFX1PartyBean partyBean) {
        this.partyBean = partyBean;
    }

    public JFX1PartyBean getPartyBean() {
        return partyBean;
    }

    public void setToWrite(String toWrite) {
        this.toWrite = toWrite;
    }

    public JFX1DecorationPartyOne(VisualComponent component, JFX1PartyBean partyBean) {
        super(component);
        setPartyBean(partyBean);

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

        button.setOnAction((ActionEvent ae) -> goToDeleteParty(ae,"/JFX1/JFX1UserProfile.fxml"));
        output.setStyle("-fx-background-color: #200f54");
        output.getChildren().add(button);
        return output;
    }
    private void goToDeleteParty(ActionEvent ae,String fxml){
        DeletePartyEventController.delete(getPartyBean());
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, fxml);
    }

    @Override
    public VBox addUserPanel() {
        VBox preliminaryResult=super.addUserPanel();
        this.setToWrite("Delete");
        preliminaryResult=this.applyDecorationPartyOne(preliminaryResult);
        return preliminaryResult;
    }
}
