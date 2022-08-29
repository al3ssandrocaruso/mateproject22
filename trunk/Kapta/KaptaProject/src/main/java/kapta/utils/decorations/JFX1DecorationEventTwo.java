package kapta.utils.decorations;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kapta.control.appcontroller.JoinEventController;
import kapta.control.guicontroller.interfaceone.JFX1AlertCreator;
import kapta.control.guicontroller.interfaceone.JFX1PartyPageGUIController;
import kapta.utils.bean.RequestBean;
import kapta.utils.bean.jfx1.JFX1ClubBean;
import kapta.utils.bean.jfx1.JFX1EventBean;
import kapta.utils.bean.jfx1.JFX1RequestBean;
import kapta.utils.exception.myexception.ExpiredGreenPassException;
import kapta.utils.exception.myexception.InavalidGreenPassException;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.greenpass.AdapterGreenPass;
import kapta.utils.VisualComponent;
import kapta.utils.mysession.ThreadLocalSession;
import kapta.utils.utils.GetDialogStage;

import java.io.File;


public class JFX1DecorationEventTwo extends Decorator{
    private String toWrite;
    private Button button = new Button();
    private String[] ret;

    private JFX1EventBean eventBean;
    private JFX1ClubBean creator;



    private String radius = "-fx-background-radius: 28;";
    private String white = "-fx-text-fill: white;";
    private String black = "-fx-text-fill: black;";
    private String arial = "Arial";


    public void setEventBean(JFX1EventBean eventBean) {
        this.eventBean = eventBean;
    }

    public JFX1EventBean getEventBean() {
        return eventBean;
    }

    public void setCreator(JFX1ClubBean creator) {
        this.creator = creator;
    }

    public JFX1ClubBean getCreator() {
        return creator;
    }

    public JFX1DecorationEventTwo(VisualComponent component, JFX1EventBean eventBean, JFX1ClubBean creator ) {
        super(component);
        setEventBean(eventBean);
        setCreator(creator);

        int support = 0;
        try {
            support = getStatusRequest();
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
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        }


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
            try {

                int support = 0;

                support = getStatusRequest();

                if (support == -1) {
                    if (!getEventBean().isGreenPass()) {
                        this.approvedSendingRequest(null, 0);
                    } else {
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
                        HBox hBox = GetDialogStage.fill(btnLoad, dialogVbox, dialog, label1);

                        btnLoad.setOnAction(actionEvent -> {
                            try {
                                File file;
                                Stage stage = (Stage) dialog.getScene().getWindow();
                                FileChooser fileChooser = new FileChooser();
                                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files", "*.png", "*.jpg"));
                                file = fileChooser.showOpenDialog(stage);

                                AdapterGreenPass adapter = new AdapterGreenPass();
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
                            } catch (InavalidGreenPassException | SystemException e) {
                               JFX1AlertCreator.createAlert(e);
                            }
                        });
                        // to test
                        btnSend.setOnAction(actionEvent -> {
                            this.approvedSendingRequest(ret[2], Integer.valueOf(ret[3]));
                            dialog.close();
                        });

                    }
                }

            } catch (SystemException  e){
                JFX1AlertCreator.createAlert(e);
            }


                }





        );
        output.getChildren().add(button);
        output.setStyle("-fx-background-color: #200f54");
        return output;
    }
    private void approvedSendingRequest(String vacDate, int numDoses){
        JFX1RequestBean jfx1RequestBean = new JFX1RequestBean(numDoses, vacDate);
        try {
            sendRequest(jfx1RequestBean);
            this.setToWrite("Pending");
            button.setStyle("-fx-background-color: #ff9105; "+ radius + white);
        } catch (ExpiredGreenPassException e) {
            JFX1AlertCreator.createAlert(e);
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

    private void sendRequest(RequestBean requestBean) throws ExpiredGreenPassException {
        try {
            JoinEventController.sendRequest(requestBean, getEventBean());
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        }

    }


    private  int getStatusRequest() throws SystemException {
        return JoinEventController.manageRequestInfo(this.eventBean,  ThreadLocalSession.getUserSession().get().getUserBean(), getCreator());
    }



}
