package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import kapta.control.appcontroller.CreateEventController;
import kapta.control.appcontroller.JoinEventController;

import kapta.utils.bean.EventBean;
import kapta.utils.bean.GenericListInfoBean;
import kapta.utils.bean.RequestBean;
import kapta.utils.bean.TokenBean;
import kapta.utils.bean.jfx2.JFX2ClubBean;
import kapta.utils.bean.jfx2.JFX2EventBean;
import kapta.utils.bean.jfx2.JFX2RequestBean;
import kapta.utils.bean.jfx2.JFX2TokenBean;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.TokenException;
import kapta.utils.Observer;
import kapta.utils.session.ThreadLocalSession;
import kapta.utils.utils.GetDialogStage;
import kapta.utils.utils.GetFontedLabel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JFX2ClubProfileGUIController implements Observer {

    @FXML
    private Label labelNumCreatedEvents;
    @FXML
    private ListView<Pane> listViewCreatedEvents;
    @FXML
    private TextField textFieldRequest;
    @FXML
    private ListView<Pane> pendingList=new ListView<>();
    @FXML
    private ImageView clubImg;
    @FXML
    private Button btnCreatedEvents;
    @FXML
    private VBox successEventVBox;
    @FXML
    private TextField textFieldEventName;
    @FXML
    private TextField textFieldAddress;
    @FXML
    private TextField textFieldBeginTime;
    @FXML
    private Slider sliderDuration;
    @FXML
    private Label labelDuration;
    @FXML
    private VBox vBoxCreatedEvents;
    @FXML
    private HBox noClubInProfile;
    @FXML
    private TextField textFieldDate;
    @FXML
    private RadioButton radioButtonGreenPass;
    @FXML
    private Spinner<Double> spinnerPrice;
    @FXML
    private Label labelUsername;


    private int numSbagliate=0;
    private boolean state=false;
    private String system="System";

    private Map<Pane,JFX2RequestBean> map=new HashMap<>();
    private Font font = Font.font("Arial",FontWeight.BOLD, 15);

    //Stringhe create per evitare code smells
    private String radius = "-fx-background-radius: 28;";
    private String white = "-fx-text-fill: white";
    private String supportFor = " FOR ";
    private String supportRequestFrom = " REQUEST FROM ";

    private TokenBean token1; //the right token
    private File imgEvent;


    public void setLabelUsername(String username) {
        this.labelUsername.setText(username);
    }
    public void setClubImg(Image clubImg) {
        this.clubImg.setImage(clubImg);
    }

    public void noClubInProfileOn(){
        vBoxCreatedEvents.toFront();
        noClubInProfile.toFront();
        btnCreatedEvents.setVisible(false);
    }
    
    public void confirmCreateEvent(ActionEvent ae) {

        goToGenerateToken();

        Stage dialog=GetDialogStage.startDialog(ae);

        Label label1= GetFontedLabel.getFonted("An email has been sent to you",system);
        Label label2=GetFontedLabel.getFonted("Please, insert your token to confirm",system);
        VBox dialogVbox = new VBox(21);

        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);

        textField.setPromptText("Insert your token");
        textField.setStyle("-fx-background-color: #e8e7fc;" +
                radius +
                "-fx-text-fill: #200f54;" +
                "-fx-padding: 10;" +
                "-fx-font-size: 25");

        dialogVbox.setPadding(new Insets(0,50,0,50));

        Button button=new Button("Verify");
        Font font1 = Font.font(system, FontWeight.BOLD, 25);
        button.setFont(font1);
        button.setStyle("-fx-background-color: #200f54;" + radius + white);

        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.getChildren().addAll(label1,label2,textField,button);
        dialogVbox.setStyle("-fx-background-color: #e8e7fc");

        Scene dialogScene = new Scene(dialogVbox, 500, 400);
        dialog.setResizable(false);
        dialog.setScene(dialogScene);
        dialog.show();

        button.setOnAction(e-> {
            String insertedToken = textField.getText();
            JFX2TokenBean jfx2TokenBeanIn = new JFX2TokenBean(insertedToken);
            try {
                goToCompareToken(jfx2TokenBeanIn);
                //evento creato con successo
                JFX2EventBean eventBean = new JFX2EventBean(textFieldEventName.getText(), textFieldAddress.getText(), textFieldBeginTime.getText(), textFieldDate.getText(), sliderDuration.getValue(), spinnerPrice.getValue(), radioButtonGreenPass.isSelected());
                goToCreateEvent(eventBean);
                //evento creato con successo
                textFieldEventName.setText("");
                textFieldAddress.setText("");
                textFieldBeginTime.setText("");
                textFieldDate.setText("");
                sliderDuration.setValue(0);
                labelDuration.setText("0");
                dialog.close();
                successEventVBox.toFront();
            } catch (TokenException ex) {
                ErrorHandler.getInstance().reportFinalException(ex);
                numSbagliate = FillDialogBox.fill(label1, button, label2, numSbagliate);
            }
        });
    }

    public void setAll(JFX2ClubBean clubBean){
        SpinnerValueFactory<Double> gradesValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,500,0, 0.5);
        this.spinnerPrice.setValueFactory(gradesValueFactory);
        if(clubBean.getImg()!=null){setClubImg(clubBean.getImgOut()); }//usa e aggiorna i bean
        setLabelUsername("@"+clubBean.getUsernameOut());
        if( ThreadLocalSession.getUserSession().get().getUserBean()!=null) {
            noClubInProfileOn();
        }
        btnCreatedEvents.setFont(font);

    }

    public void selectDuration() {
        labelDuration.setText(String.valueOf(sliderDuration.getValue()));
    }

    public void sendSuccessToBack() {
        successEventVBox.toBack();
    }

    public void displaySelected() {
        Pane selected = pendingList.getSelectionModel().getSelectedItem();
        JFX2RequestBean selectedRequest=map.get(selected);
        JFX2RequestBean jfx2RequestBean = new JFX2RequestBean(selectedRequest);
        textFieldRequest.setText(supportRequestFrom+ jfx2RequestBean.getSenderNameOut()+ supportFor +jfx2RequestBean.getRelatedEventOut());
    }

    public void acceptRequest() {
        Pane selected = pendingList.getSelectionModel().getSelectedItem();
        JFX2RequestBean selectedRequest=map.get(selected);
        pendingList.getItems().removeAll(selected);
        JoinEventController.acceptRequest(selectedRequest);
        JFX2RequestBean jfx2RequestBean = new JFX2RequestBean(selectedRequest);
        textFieldRequest.setText(supportRequestFrom + jfx2RequestBean.getSenderNameOut()+ supportFor +jfx2RequestBean.getRelatedEventOut()+ " ACCEPTED");
        textFieldRequest.setStyle("-fx-background-color:  #54e589;" + radius + white);
    }

    public void rejectRequest() {
        Pane selected = pendingList.getSelectionModel().getSelectedItem();
        JFX2RequestBean selectedRequest=map.get(selected);
        pendingList.getItems().removeAll(selected);
        JFX2RequestBean jfx2RequestBean = new JFX2RequestBean(selectedRequest);
        textFieldRequest.setText(supportRequestFrom + jfx2RequestBean.getSenderNameOut()+ supportFor +jfx2RequestBean.getRelatedEventOut()+ " REJECTED");
        textFieldRequest.setStyle("-fx-background-color: #d00000;" + radius + white);
    }


    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        if(ob instanceof RequestBean requestBean) {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX2/JFX2RequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2RequestBean request = new JFX2RequestBean(requestBean);
            map.put(pane, request); //map here ??
            JFX2ClubRequestItemGUIController crigc = fxmlLoader.getController();
            JFX2RequestBean jfx2RequestBean = new JFX2RequestBean(request);
            crigc.setLabelUsername(jfx2RequestBean.getSenderNameOut());
            crigc.setLabelEventName(jfx2RequestBean.getRelatedEventOut());
            crigc.setImageProfile(jfx2RequestBean.getRelatedEventImgOut());
            if (jfx2RequestBean.isGreenPassOut()) {
                crigc.greenPassOn(jfx2RequestBean.getNumDosesOut(),  request.getVaccinationDate());
            }
            pendingList.getItems().add(pane);
        }
        else if(ob instanceof EventBean eventBean){
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX2/JFX2EventItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2EventItemGUIController eigc = fxmlLoader.getController();
            JFX2EventBean jfx2EventBean = new JFX2EventBean(eventBean);
            eigc.setAll(jfx2EventBean);
            this.listViewCreatedEvents.getItems().add(pane);

        }
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        if(from instanceof GenericListInfoBean gen && gen.getType()==2) {
            labelNumCreatedEvents.setText(String.valueOf(gen.getSize()));
        }
    }

    public void loadEventImg() {
        Stage stage = (Stage) successEventVBox.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        setImgEvent(fileChooser.showOpenDialog(stage));
    }


    public void showCreatedEvents() {
        if(!state) {
            vBoxCreatedEvents.toFront();
            state=true;
            btnCreatedEvents.setText("Create New Event");
            btnCreatedEvents.setFont(font);
        }else{
           vBoxCreatedEvents.toBack();
            state=false;
            btnCreatedEvents.setText("Show Created");
            btnCreatedEvents.setFont(font);
        }
    }

    private void goToGenerateToken() {
        setToken1(CreateEventController.generateToken());
    }
    private void goToCompareToken(TokenBean tokenBeanIn) throws TokenException {
        CreateEventController.checkToken(this.getToken1(),tokenBeanIn);
    }
    private void goToCreateEvent(JFX2EventBean eventBean){
        eventBean.setEventImgOut(imgEvent);
    }

    private TokenBean getToken1() {return token1;}
    private  void setToken1(TokenBean token1) {this.token1 = token1;}
    private  void setImgEvent(File imgEvent) {this.imgEvent = imgEvent;}
}
