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
import kapta.control.appcontroller.JoinPartyController;
import kapta.control.guicontroller.interfaceone.JFX1AlertCreator;
import kapta.control.guicontroller.interfaceone.JFX1PartyPageGUIController;
import kapta.utils.VisualComponent;
import kapta.utils.bean.jfx1.JFX1PartyBean;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.exception.myexception.BusyForANewPartyException;
import kapta.utils.exception.myexception.PartyExpiredException;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.mysession.ThreadLocalSession;

public class JFX1DecorationPartyTwo extends Decorator {

    Button button=new Button();

    private String radius = "-fx-background-radius: 28;";
    private String white = "-fx-text-fill: white;";
    private String toWrite;
    private JFX1PartyBean partyBean;






    public void setToWrite(String write) {
        this.toWrite = write;
    }


    public JFX1DecorationPartyTwo(VisualComponent component, JFX1PartyBean partyBean) {
        super(component);
        this.partyBean =partyBean ;
        //In generale setto il tasto su Join ma se partecipo già all'evento allora mostro Leave
        this.setToWrite("Join");
        //aaa do I joined yet dovrebbe essere fatta in fase di inizializzazione, potrebbe anche avere a che fare con lo stratto soot .
        try {
            if (!doIjoinedYet()) {
                button.setStyle("-fx-background-color: #0073c4;" + radius + white);
                this.setToWrite("Join");
            } else if (doIjoinedYet()) {
                button.setStyle("-fx-background-color: #d00000;" + radius + white);
                this.setToWrite("Leave");
            }
            this.addUserPanel();
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        }

    }


    private boolean doIjoinedYet() throws SystemException {
        JFX1UserBean userBean =  new JFX1UserBean (ThreadLocalSession.getUserSession().get().getUserBean()) ;
        return JoinPartyController.joinedYetInfo(partyBean, userBean);
    }




    protected VBox applyDecorationPartyTwo(VBox input){
        JFX1UserBean userBean =  new JFX1UserBean  (ThreadLocalSession.getUserSession().get().getUserBean());
        VBox output=input;
        output.getChildren().clear();
        button.setText(this.toWrite);
        button.setMinHeight(65);
        button.setMinWidth(125);
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        button.setFont(font);

        new JFX1PartyPageGUIController();
        button.setOnAction((ActionEvent ae) -> { //button controller

            boolean bool=true;
            try {
                 bool = !doIjoinedYet();
            } catch (SystemException systemException) {
                JFX1AlertCreator.createAlert(systemException);
            }

            if (bool) {
                joinPartyAction(font,userBean );

                } else {
                button.setStyle("-fx-background-color: #0073c4;" + radius + white);
                try {
                    JoinPartyController.leaveParty( userBean, partyBean);
                    this.setToWrite("Join");
                    this.addUserPanel();
                } catch (SystemException e) {
                    JFX1AlertCreator.createAlert(e);
                }
            }

            ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();

                replaceSceneAndInitializePage.replaceSceneAndInitializePage(ae, "/JFX1/JFX1PartyPage.fxml", partyBean);


        });
        output.getChildren().add(button);
        output.setStyle("-fx-background-color: #200f54");
        return output;
    }



    private void joinPartyAction(Font font, JFX1UserBean userBean){
        button.setStyle("-fx-background-color: #d00000;" + radius + white);

        //Setto l'avviso
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(button.getScene().getWindow());
        VBox vBox = new VBox(20);
        Text text = new Text();
        text.setFont(font);
        vBox.setAlignment(Pos.CENTER);
        try {
            JoinPartyController.joinParty( userBean, partyBean);
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        } catch (BusyForANewPartyException e) {
            //eee  I can do the same with the alert creator.
            text.setText("You've have already joined a party in this date yet!");
            vBox.getChildren().add(text);
            vBox.setStyle("-fx-background-color: #ff4040");
            Scene dialogScene = new Scene(vBox, 700, 250);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch (PartyExpiredException e) {
            text.setText("You can't Join the party 'cause it is expired.");
            vBox.getChildren().add(text);
            vBox.setStyle("-fx-background-color: #ff4040");
            Scene dialogScene = new Scene(vBox, 600, 250);
            dialog.setScene(dialogScene);
            dialog.show();
        }

        this.setToWrite("Leave");
        this.addUserPanel();



    }


    @Override
    public VBox addUserPanel() {
        VBox preliminaryResult=super.addUserPanel();
        preliminaryResult=this.applyDecorationPartyTwo(preliminaryResult);
        return preliminaryResult;
    }


}
