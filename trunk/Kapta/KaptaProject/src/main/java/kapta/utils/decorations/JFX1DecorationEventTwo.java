package kapta.utils.decorations;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kapta.application.EventApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1PartyPageGUIController;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.ExpiredGreenPassException;
import kapta.utils.exception.myexception.GenericException;
import kapta.utils.exception.myexception.InavalidGreenPassException;
import kapta.utils.greenpass.AdapterGreenPass;
import kapta.utils.greenpass.TargetGreenPass;
import kapta.utils.bean.beanin.jfx1.JFX1RequestBean;
import kapta.utils.VisualComponent;
import kapta.utils.utils.GetDialogStage;

import java.io.File;


public class JFX1DecorationEventTwo extends Decorator{
    private String toWrite;
    private Button button = new Button();
    private String[] ret;

    public void setEventApplication(EventApplicationLayer eventApplication) {
        this.eventApplication = eventApplication;
    }

    private EventApplicationLayer eventApplication;

    private String radius = "-fx-background-radius: 28;";
    private String white = "-fx-text-fill: white;";
    private String black = "-fx-text-fill: black;";
    private String arial = "Arial";


    public JFX1DecorationEventTwo(VisualComponent component, EventApplicationLayer eventApplication) {
        super(component);
        //In generale setto il tasto su Join ma se partecipo già all'evento allora mostro Leave
        this.setEventApplication(eventApplication);
        int support = 0;
        support = eventApplication.getStatusRequest();
        // "dal punto di vista dell'utente"
        switch(support){
            case -1:{ //No Request yet
                button.setFont(Font.font(arial, FontWeight.BOLD, 10));
                fill("#e8e7fc","Request",black);
                break;
            }
            case 0:{ //Pending
                fill("#ff9105","Pending",white);
                break;
            }
            case 1: { //Accepted Request
                fill("#54e589","Accepted",white);
                break;
            }
            case 2: {
                fill("d00000","Rejected",white);
                break;
            }
            default:
                break;
        }
        this.addUserPanel();
    }


    public void setToWrite(String toWrite) {
        this.toWrite = toWrite;
    }

    protected VBox applyDecorationPartyTwo(VBox input){
        Label labelName = new Label("Name: ");
        Label labelSurname = new Label("Surname: ");
        Label labelDate = new Label("Vaccination Date: ");
        Label labelNumVac = new Label("Doses: ");

        VBox output=input;
        output.getChildren().clear();
        button.setText(this.toWrite);
        button.setMinHeight(65);
        button.setMinWidth(125);
        Font font = Font.font(arial, FontWeight.BOLD, 25);
        button.setFont(font);
        new JFX1PartyPageGUIController();

        button.setOnAction((ActionEvent ae) -> {
            int support = 0;
            support = eventApplication.getStatusRequest();
            if(support == -1) {  //No Request yet
                /*
                Prima questa riga di codice qua sotto era dentro un else ma ora (per evitare code smells)
                setto prima a null e poi in caso, se serve il greenPass, sviluppo tutto quanto
                */
                if (!eventApplication.getEventModel().isGreenPass()){ this.approvedSendingRequest(null, 0);}
                else{
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(((Node) ae.getSource()).getScene().getWindow());

                    Font font1 = Font.font(arial, FontWeight.BOLD, 20);
                    Label label1 = new Label("Please, load and verify green pass");
                    label1.setFont(font1);

                    labelName.setFont(font1);
                    labelSurname.setFont(font1);
                    labelDate.setFont(font1);
                    labelNumVac.setFont(font1);

                    VBox dialogVbox = new VBox(20);

                    dialogVbox.setPadding(new Insets(0, 50, 0, 50));

                    Button btnLoad = new Button("Load File");
                    btnLoad.setFont(font1);
                    btnLoad.setStyle("-fx-background-color: #200f54;" + radius + white);

                    Button btnSend = new Button("Send");
                    btnSend.setFont(font1);
                    btnSend.setStyle("-fx-background-color: #54E589;" + radius + white);
                    HBox hBox=GetDialogStage.fill(btnLoad,dialogVbox,dialog,label1);

                    btnLoad.setOnAction(actionEvent -> {
                        try {File file;
                            Stage stage = (Stage) dialog.getScene().getWindow();
                            FileChooser fileChooser = new FileChooser();
                            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
                            file = fileChooser.showOpenDialog(stage);

                            TargetGreenPass adapter = new AdapterGreenPass();
                            this.ret = adapter.getInfoGreenPass(file.getAbsolutePath());

                            labelName.setText(labelName.getText() + ret[0]);
                            labelSurname.setText(labelSurname.getText() + ret[1]);
                            labelDate.setText(labelDate.getText() + ret[2]);
                            labelNumVac.setText(labelNumVac.getText() + ret[3]);

                            hBox.getChildren().clear();
                            hBox.getChildren().add(btnSend);
                            VBox vboxInfo = new VBox();
                            vboxInfo.setAlignment(Pos.CENTER);
                            vboxInfo.getChildren().addAll(labelName, labelSurname, labelDate, labelNumVac);
                            dialogVbox.getChildren().clear();
                            dialogVbox.getChildren().addAll(vboxInfo, hBox);
                        } catch (InavalidGreenPassException | GenericException e) {
                            ErrorHandler.getInstance().reportFinalException(e);
                        }
                    });
                    btnSend.setOnAction(actionEvent -> {
                         this.approvedSendingRequest(ret[2], Integer.valueOf(ret[3]));
                            dialog.close();
                    });

                }
            }
        });
        output.getChildren().add(button);
        output.setStyle("-fx-background-color: #200f54");
        return output;
    }
    private void approvedSendingRequest(String vacDate, int numDoses){
        JFX1RequestBean jfx1RequestBean = new JFX1RequestBean(numDoses, vacDate);
        try {
            this.eventApplication.sendRequest(jfx1RequestBean);
            this.setToWrite("Pending");
            button.setStyle("-fx-background-color: #ff9105; "+ radius + white);
        } catch (ExpiredGreenPassException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        }
        this.addUserPanel();
    }
    @Override
    public VBox addUserPanel() {
        VBox preliminaryResult=super.addUserPanel();
        preliminaryResult=this.applyDecorationPartyTwo(preliminaryResult);
        return preliminaryResult;
    }
    private void fill(String color, String toWrite, String textFill){
        String c="-fx-background-color: "+color+"; ";
        button.setStyle(c+radius + textFill);
        this.setToWrite(toWrite);
    }
}
