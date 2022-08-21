package kapta.control.guicontroller.interfaceone;
import javafx.event.ActionEvent;
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
import kapta.control.appcontroller.CreateEventController;
import kapta.control.guicontroller.interfacetwo.FillDialogBox;
import kapta.utils.bean.TokenBean;
import kapta.utils.bean.J1.JFX1ClubBean;
import kapta.utils.bean.J1.JFX1EventBean;
import kapta.utils.bean.J1.JFX1TokenBean;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.TokenException;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.utils.GetDialogStage;
import kapta.utils.utils.GetFontedLabel;
import java.io.File;
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
    private TokenBean token1; //the right token
    private JFX1ClubBean clubBean;
    private File imgEvent;


    private int numSbagliate=1;
    private String arial="Arial";


    public void setClubBean(JFX1ClubBean clubBean) {
        this.clubBean = clubBean;
    }



    @FXML
    void backToHomeAction(ActionEvent ae) {
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1ClubProfile.fxml");
    }

    public void confirmCreateEventAction(ActionEvent actionEvent){
        JFX1EventBean eventBean =new JFX1EventBean(textFieldEventName.getText(),textFieldEventPrice.getText(),textFieldEventAddress.getText(),textFieldEventDuration.getText(), textFieldEventTimeH.getText(),textFieldEventTimeM.getText(),  datePickerEventDate.getValue());
        eventBean.setEventImgOut(getImgEvent());
        eventBean.setGreenPassOut(checkBoxGreenPass.isSelected());
        CreateEventController.createEvent(eventBean);
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(actionEvent, "/JFX1/JFX1ClubProfile.fxml");
    }
    public void submitRequest(ActionEvent ae) {
        goToGenerateToken();
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
            JFX1TokenBean jfx1TokenBeanIn=new JFX1TokenBean(textField.getText());
            try{
                goToCompareToken(jfx1TokenBeanIn);
                this.getBtnConfirmCreateEvent().setVisible(true);
                this.getBtnSubmitRequest().setVisible(false);
                dialog.close();
            }catch (TokenException t){
                numSbagliate = FillDialogBox.fill(label1, button, label2, numSbagliate);
                ErrorHandler.getInstance().reportFinalException(t);
            }
        });
    }

    public void loadEventImage() {
        Stage stage = (Stage) textFieldEventName.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        setImgEvent(fileChooser.showOpenDialog(stage).getAbsoluteFile());
    }

    private  void goToCompareToken(TokenBean tokenBeanIn) throws TokenException {
        CreateEventController.checkToken(this.getToken1(),tokenBeanIn);
    }

    private TokenBean getToken1() {return token1;}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnConfirmCreateEvent.setVisible(false);
    }

    private void goToGenerateToken() {
        setToken1(CreateEventController.generateToken());
    }
    private void setToken1(TokenBean token1) {this.token1 = token1;}
    private File getImgEvent() {return imgEvent;}
    private void setImgEvent(File imgEvent) {this.imgEvent = imgEvent;}
}
