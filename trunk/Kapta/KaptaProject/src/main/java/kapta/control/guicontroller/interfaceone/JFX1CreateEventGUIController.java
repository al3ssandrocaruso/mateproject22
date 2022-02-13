package kapta.control.guicontroller.interfaceone;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import kapta.application.ClubProfileApplicationLayer;
import kapta.control.appcontroller.CreateEventController;
import kapta.utils.bean.beanin.jfx1.JFX1EventBean;
import kapta.utils.bean.beanin.jfx1.JFX1TokenBeanIn;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.TokenException;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.utils.GetDialogStage;
import kapta.utils.utils.GetFontedLabel;

import java.net.URL;
import java.util.ResourceBundle;


public class JFX1CreateEventGUIController implements Initializable {
    @FXML
    private TextField textFieldEventName;
    @FXML
    private TextField textFieldEventPrice;
    @FXML
    private TextField textFieldEventAddress;
    @FXML
    private TextField textFieldEventDuration;
    @FXML
    private TextField textFieldEventTimeH;
    @FXML
    private TextField textFieldEventTimeM;
    @FXML
    private DatePicker datePickerEventDate;
    @FXML
    private CheckBox checkBoxGreenPass;

    public Button getBtnConfirmCreateEvent() {
        return btnConfirmCreateEvent;
    }

    @FXML
    private Button btnConfirmCreateEvent;

    public Button getBtnSubmitRequest() {
        return btnSubmitRequest;
    }

    @FXML
    private Button btnSubmitRequest;

    private int numSbagliate=0;
    private String arial="Arial";
    private ClubProfileApplicationLayer clubProfileApplicationLayer;

    public ClubProfileApplicationLayer getClubProfileApplication() {
        return clubProfileApplicationLayer;
    }

    public void setClubProfileApplication(ClubProfileApplicationLayer clubProfileApplicationLayer) {
        this.clubProfileApplicationLayer = clubProfileApplicationLayer;
    }

    @FXML
    void backToHomeAction(ActionEvent ae) {
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1ClubProfile.fxml");
    }

    public void confirmCreateEventAction(ActionEvent actionEvent){
        JFX1EventBean eventBean =new JFX1EventBean(textFieldEventName.getText(),textFieldEventPrice.getText(),textFieldEventAddress.getText(),textFieldEventDuration.getText(), textFieldEventTimeH.getText(),textFieldEventTimeM.getText(), clubProfileApplicationLayer.getImgEvent(), datePickerEventDate.getValue(), checkBoxGreenPass.isSelected());
        CreateEventController.createEvent(eventBean);
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(actionEvent, "/JFX1/JFX1ClubProfile.fxml");
    }
    public void submitRequest(ActionEvent ae) {
        getClubProfileApplication().goToGenerateToken();
        final Stage dialog = GetDialogStage.startDialog(ae);
        Label label1= GetFontedLabel.getFonted("An email has been sent to you",arial);
        Label label2= GetFontedLabel.getFonted("Please, insert your token to confirm",arial);
        VBox dialogVbox = new VBox(20);

        TextField textField = new TextField();
        textField.setPromptText("insert your token");
        textField.setStyle("-fx-background-color: #e8e7fc;" +
                "-fx-background-radius: 28;" +
                "-fx-text-fill: #200f54;" +
                "-fx-padding: 10;" +
                "-fx-font-size: 25");

        dialogVbox.setPadding(new Insets(0,50,0,50));

        Button button=new Button("Verify");
        Font font1 = Font.font(arial, FontWeight.BOLD, 25);
        button.setFont(font1);
        button.setStyle("-fx-background-color: #200f54;" + "-fx-background-radius: 28;" + "-fx-text-fill: white");

        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.getChildren().addAll(label1,label2,textField,button);

        Scene dialogScene = new Scene(dialogVbox, 500, 400);
        dialog.setResizable(false);
        dialog.setScene(dialogScene);
        dialog.show();

        button.setOnAction(e->{
            JFX1TokenBeanIn jfx1TokenBeanIn=new JFX1TokenBeanIn(textField.getText());
            try{
                getClubProfileApplication().goToCompareToken(jfx1TokenBeanIn);
                this.getBtnConfirmCreateEvent().setVisible(true);
                this.getBtnSubmitRequest().setVisible(false);
                dialog.close();
            }catch (TokenException t){
                ErrorHandler.getInstance().reportFinalException(t);
                label1.setVisible(false);
                button.setVisible(false);
                label2.setText("Retry in ");
                numSbagliate++;
                Timeline tm = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    int i = 5*numSbagliate;
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        label2.setText("Retry in "+i);
                        if(i==0){
                            label1.setText("An email has been sent to you");
                            label2.setText("Please, insert your token to confirm");
                            button.setVisible(true);
                            label1.setVisible(true);
                        }
                        i--;
                    }
                }));

                tm.setCycleCount((5*numSbagliate)+1);
                tm.play();

            }
        });
    }

    public void loadEventImage() {
        Stage stage = (Stage) textFieldEventName.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        this.clubProfileApplicationLayer.setImgEvent(fileChooser.showOpenDialog(stage).getAbsoluteFile());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnConfirmCreateEvent.setVisible(false);
    }

}
