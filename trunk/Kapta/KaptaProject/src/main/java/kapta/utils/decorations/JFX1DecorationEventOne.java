package kapta.utils.decorations;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import kapta.control.appcontroller.DeletePartyEventController;
import kapta.control.guicontroller.interfaceone.JFX1AlertCreator;
import kapta.utils.VisualComponent;
import kapta.utils.bean.jfx1.JFX1EventBean;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.ReplaceSceneAndInitializePage;


public class JFX1DecorationEventOne extends Decorator {

    private String toWrite;
    private JFX1EventBean eventBean;


    public void setToWrite(String toWrite) {
        this.toWrite = toWrite;
    }

    public JFX1DecorationEventOne(VisualComponent component, JFX1EventBean jfx1EventBean) {
        super(component);
        this.eventBean=jfx1EventBean;

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

        button.setOnAction((ActionEvent ae) -> goToDeleteEvent(ae, "/JFX1/JFX1ClubProfile.fxml"));
        output.setStyle("-fx-background-color: #200f54;");
        output.getChildren().add(button);
        return output;
    }

    private void goToDeleteEvent(ActionEvent ae, String fxml){
        try {
            DeletePartyEventController.delete(this.eventBean);
            ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
            rsip.replaceSceneAndInitializePage(ae, fxml);
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        }

    }


    @Override
    public VBox addUserPanel() {
        VBox preliminaryResult=super.addUserPanel();
        this.setToWrite("Delete");
        preliminaryResult=this.applyDecorationPartyOne(preliminaryResult);
        return preliminaryResult;
    }

}
