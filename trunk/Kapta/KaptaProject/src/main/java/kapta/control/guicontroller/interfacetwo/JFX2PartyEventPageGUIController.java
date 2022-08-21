package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import kapta.control.appcontroller.DeletePartyEventController;
import kapta.control.appcontroller.JoinEventController;
import kapta.control.appcontroller.JoinPartyController;


import kapta.engineering.MangeJoined;
import kapta.engineering.MangeParticipant;

import kapta.utils.bean.RequestBean;
import kapta.utils.bean.UserBean;

import kapta.utils.bean.jfx2.*;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.ExpiredGreenPassException;
import kapta.utils.exception.myexception.GenericException;
import kapta.utils.exception.myexception.InavalidGreenPassException;

import kapta.utils.greenpass.AdapterGreenPass;
import kapta.utils.greenpass.TargetGreenPass;

import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.pagesetter.setterjfx2.JFX2ClubProfileSetter;
import kapta.utils.pagesetter.setterjfx2.JFX2UserProfileSetter;
import kapta.utils.Observer;
import kapta.utils.session.ThreadLocalSession;
import kapta.utils.utils.GetDialogStage;

import java.io.File;
import java.io.IOException;
import java.net.URL;;
import java.util.ResourceBundle;


public class JFX2PartyEventPageGUIController implements Initializable,Observer {
    @FXML
    private HBox hboxGreenPassOn;
    @FXML
    private AnchorPane ap;
    @FXML
    private ImageView imgPic;
    @FXML
    private Label labelName;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelPrice;
    @FXML
    private Label labelEventPriceDouble;
    @FXML
    private Button btnAction;
    @FXML
    private Button btnCreator;
    @FXML
    private ListView<Pane> listViewParticipants;

    private boolean inParty;
    private String[] ret;
    private boolean obbGreenPass=false;

    private String pending = "Pending";
    private String radius = "-fx-background-radius: 28;";
    private String orange = "-fx-background-color: orange;";
    private String red = "-fx-background-color: red;";
    private String leave = "Leave Party";
    private String joinStr = "Join Party";


    // 0 ==> for party , 1  event

    private MangeParticipant man;
    private int type ;
    private JFX2EventBean eventBean;
    private JFX2ClubBean whoIamClub;
    private JFX2UserBean whoIamUser;
    private int typeMe;

    private JFX2PartyBean jfx2PartyBean;
    private JFX2UserBean creatorUser;
    private JFX2ClubBean creatorClub;



    public void setJfx2PartyBean(JFX2PartyBean jfx2PartyBean) {
        this.jfx2PartyBean = jfx2PartyBean;
    }

    public JFX2PartyBean getJfx2PartyBean() {
        return jfx2PartyBean;
    }

    public void setEventBean(JFX2EventBean eventBean) {
        this.eventBean = eventBean;
    }

    public void setCreatorParty(JFX2UserBean creator) {
        this.creatorUser = creator;
    }

    public void setCreatorEvent(JFX2ClubBean creator) {
        this.creatorClub = creator;
    }

    public JFX2UserBean getCreatorParty() {
        return creatorUser;
    }
    public JFX2ClubBean getCreatorClub() {
        return creatorClub;
    }

    public JFX2EventBean getEventBean() {
        return eventBean;
    }

    public boolean isInParty() {
        return inParty;
    }

    public void setInParty(boolean inParty) {
        this.inParty = inParty;
    }



    public void setLabelName(String labelName) {
        this.labelName.setText(labelName);
    }
    public void setImgPic(Image imgPic) {
        this.imgPic.setImage(imgPic);
    }
    public void setLabelDate(String labelDate) {
        this.labelDate.setText(labelDate);
    }
    public void setLabelEventPriceDouble(String labelPrice) {
        this.labelEventPriceDouble.setText(labelPrice);
    }
    public void setBtnCreator(String creator) {
        this.btnCreator.setText(creator);
    }
    public void setObbGreenPass(boolean obbGreenPass) {
        this.obbGreenPass = obbGreenPass;
    }

    public boolean isObbGreenPass() {
        return obbGreenPass;
    }


    public void goToCreatorProfile() {
        if(isInParty()){ // the creator is a user
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX2/JFX2UserProfile.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2UserProfileGUIController ctrl=loader.getController();
            JFX2UserProfileSetter.setter(getCreatorParty(), ctrl);
            Stage stage = (Stage) ap.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX2/JFX2ClubProfile.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2ClubProfileGUIController ctrl=loader.getController();
            JFX2ClubProfileSetter.setter(getCreatorClub(), ctrl);
            Stage stage = (Stage) ap.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void greenPassOn(){
        hboxGreenPassOn.toFront();
    }

    private void goToDeleteParty(ActionEvent ae,String fxml){
        DeletePartyEventController.delete(this.jfx2PartyBean);
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, fxml);
    }

    private void actionParty(ActionEvent actionEvent){
        String actionToPerform=btnAction.getText();
        switch (actionToPerform) {
            case "Delete Party": {
                goToDeleteParty(actionEvent, "/JFX2/JFX2UserProfile.fxml");
                break;
            }
            case "Leave Party": {
                btnAction.setText(joinStr);
                btnAction.setStyle("-fx-background-color: #200f54; "+radius);

                JFX2UserBean jfx2UserBean = (JFX2UserBean)ThreadLocalSession.getUserSession().get().getUserBean();
                JoinPartyController.leaveParty( jfx2UserBean,jfx2PartyBean );

                JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
                rsip.replaceSceneAndInitializePage(actionEvent, "/JFX2/JFX2PartyEventPage.fxml", this.jfx2PartyBean);
                break;
            }
            case "Join Party": {
                //Setto l'avviso
                Font font = Font.font("Arial", FontWeight.BOLD, 25);
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(btnAction.getScene().getWindow());
                VBox dialogVbox = new VBox(20);
                dialogVbox.setAlignment(Pos.CENTER);
                Text text = new Text();
                text.setFont(font);


                // eee questo deve essere fatto nello strato applicatino
                this.man.addParticipant(ThreadLocalSession.getUserSession().get().getUserBean());
                int ret1 = JoinPartyController.joinParty( ThreadLocalSession.getUserSession().get().getUserBean(), jfx2PartyBean);

                switch (ret1){
                    case 0:{
                        btnAction.setStyle("-fx-background-color: #ff9105; " + radius);
                        btnAction.setText(leave);
                        break;
                    }
                    case -1:{
                        text.setText("You've have already joined a party in this date yet!");
                        dialogVbox.getChildren().add(text);
                        dialogVbox.setStyle("-fx-background-color: #ff4040");
                        Scene dialogScene = new Scene(dialogVbox, 600, 250);
                        dialog.setScene(dialogScene);
                        dialog.show();
                        break;
                    }
                    case -2:{
                        text.setText("You can't Join the party 'cause it is expired.");
                        dialogVbox.getChildren().add(text);
                        dialogVbox.setStyle("-fx-background-color: #ff4040");
                        Scene dialogScene = new Scene(dialogVbox, 600, 250);
                        dialog.setScene(dialogScene);
                        dialog.show();
                        break;
                    }
                    default: break;

                }
                break;
            }
            default: break;
        }
    }

    private void actionEvent(ActionEvent actionEvent) {
        String actionToPerform=btnAction.getText();
        switch (actionToPerform){
            case "Delete Event": {
                goToDeleteEvent(actionEvent,"/JFX2/JFX2ClubProfile.fxml");
                break;
            }
            case "Pending", "Approved", "Rejected": {
                break;
            }
            case "Join Event": {
                if(isObbGreenPass()){
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());

                    Font font1 = Font.font("Arial", FontWeight.BOLD, 20);
                    Label label1 = new Label("Please, load and verify green pass");
                    label1.setFont(font1);

                    VBox dialogVbox = new VBox(20);

                    dialogVbox.setPadding(new Insets(0,50,0,50));

                    Button btnLoad=new Button("Load File");
                    btnLoad.setFont(font1);
                    btnLoad.setStyle("-fx-background-color: #200f54;" + radius + "-fx-text-fill: white");

                    Button btnSend=new Button("Send");
                    btnSend.setFont(font1);
                    btnSend.setStyle("-fx-background-color: #54E589;" + radius + "-fx-text-fill: white");
                    HBox hBox= GetDialogStage.fill(btnLoad,dialogVbox,dialog,label1);


                    btnLoad.setOnAction(ae ->{
                        File file;
                        Stage stage = (Stage) dialog.getScene().getWindow();
                        FileChooser fileChooser=new FileChooser();
                        file=fileChooser.showOpenDialog(stage);
                        try {

                            TargetGreenPass adapter = new AdapterGreenPass();
                            this.ret = adapter.getInfoGreenPass(file.getAbsolutePath());

                            hBox.getChildren().clear();
                            hBox.getChildren().add(btnSend);
                        }catch (InavalidGreenPassException | GenericException e) {
                            ErrorHandler.getInstance().reportFinalException(e);
                    }});
                    btnSend.setOnAction(ae -> {
                            JFX2RequestBean jfx2RequestBean = new JFX2RequestBean(Integer.valueOf(ret[3]), ret[2]);
                        try {
                            sendRequest(jfx2RequestBean);
                            btnAction.setText(pending);
                            btnAction.setStyle(orange+radius);
                        } catch (ExpiredGreenPassException e) {
                            ErrorHandler.getInstance().reportFinalException(e);
                        }
                        dialog.close();
                    });

                }else {
                    JFX2RequestBean jfx2RequestBean = new JFX2RequestBean(0, "" );
                    try {
                        sendRequest(jfx2RequestBean);
                        btnAction.setText(pending);
                        btnAction.setStyle(orange+radius);
                    } catch (ExpiredGreenPassException e) {
                        ErrorHandler.getInstance().reportFinalException(e);
                    }
                }
                break;
            }
            default:
                break;
        }
    }

    public void action(ActionEvent actionEvent){
        if(isInParty()){actionParty(actionEvent);}
        else{actionEvent(actionEvent);}
    }


    public void setAllParty(JFX2PartyBean jfx2PartyBean, int type, MangeParticipant man){

        this.type = type;
        setInParty(true);
        setWhoIam();
        this.jfx2PartyBean=jfx2PartyBean;


        setImgPic(jfx2PartyBean.getPartyImgOut2());
        setLabelDate(jfx2PartyBean.getPartyDateOut2());
        setLabelName(jfx2PartyBean.getPartyNameOut2());

        setBtnCreator("@" + jfx2PartyBean.getPartyCreatorOut2());
        btnAction.setText(joinStr);

        //if(whoIamUser) ??
        if(jfx2PartyBean.getPartyCreatorOut2().equals(whoIamUser.getUsername())){
            setBtnCreator("You");
            btnAction.setText("Delete Party");
            btnAction.setStyle("-fx-background-color: red;"+radius);
        }
        else if(MangeJoined.doIjoinedYet(this.jfx2PartyBean)){
            btnAction.setText(leave);
            btnAction.setStyle("-fx-background-color: #ff9105; "+radius);
        }
        labelPrice.setVisible(false);
        labelEventPriceDouble.setVisible(false);

    }



    //eee non so se il creator potrebbe esssere anche utente a questo punto

    public void setAllEvent( JFX2EventBean eventBean , JFX2ClubBean creator, int type) {
            this.type=type;
            setInParty(false);
            setEventBean(eventBean);
            setWhoIam();
            setCreatorEvent(creator);

            setImgPic(eventBean.getEventImgOut());
            setLabelDate(eventBean.getEventDateOut());
            setLabelName(eventBean.getEventNameOut());
            setLabelEventPriceDouble(eventBean.getEventPriceOut());
            setObbGreenPass(eventBean.isGreenPass());
            if(isObbGreenPass()){
                greenPassOn();
            }

            switch (typeMe) {
                case 1: {
                    // ???
                    if (creatorClub.getUsername().equals(whoIamClub.getUsername())) {
                        setBtnCreator("You");
                        btnAction.setText("Delete Event");
                        btnAction.setStyle(red + radius);
                    }
                    break;
                }
                case 0: {
                    int i = getStatusRequest();
                    switch (i) {
                        case -1: {                               //Non esiste
                            btnAction.setText("Join Event");
                            setBtnCreator("@" + creatorClub.getUsername());
                            break;
                        }
                        case 0: {                                //Pending
                            btnAction.setText(pending);
                            btnAction.setStyle(orange + radius);
                            setBtnCreator("@" + creatorClub.getUsername());
                            break;
                        }
                        case 1: {                                //Approvata
                            btnAction.setText("Approved");
                            btnAction.setStyle("-fx-background-color: green;" + radius);
                            setBtnCreator("@" + creatorClub.getUsername());
                            break;
                        }
                        case 2: {                                //Rejected
                            btnAction.setText("Rejected");
                            btnAction.setStyle(red + radius);
                            setBtnCreator("@" + creatorClub.getUsername());
                            break;
                        }
                        default:
                            break;
                    }
                    break;
                }
                default: break;
            }


    }
    private void sendRequest(RequestBean requestBean) throws ExpiredGreenPassException {
        JoinEventController.sendRequest(requestBean, eventBean);

    }




    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        UserBean userBean = (UserBean) ob;
        try {
            pane = fxmlLoader.load(getClass().getResource("/JFX2/JFX2UserItem.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFX2UserItemGUIController uigc = fxmlLoader.getController();
        JFX2UserBean jfx2UserBean=new JFX2UserBean(userBean);
        uigc.setAll(jfx2UserBean,ap,null);
        listViewParticipants.getItems().add(pane);
    }


    private  void goToDeleteEvent(ActionEvent ae, String fxml){
        DeletePartyEventController.delete(eventBean);
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, fxml);
    }

    private  int getStatusRequest() {

        if (type==1) {
            return JoinEventController.manageRequestInfo(this.eventBean, ThreadLocalSession.getUserSession().get().getUserBean(), creatorClub);
        }
        else {
            return JoinEventController.manageRequestInfo(this.eventBean, ThreadLocalSession.getUserSession().get().getUserBean(), creatorUser);

        }
    }
    private  void setWhoIam() {
        int type= ThreadLocalSession.getUserSession().get().getType();
        this.typeMe = type;
        if(type==1){
            this.whoIamClub= new JFX2ClubBean (ThreadLocalSession.getUserSession().get().getClubBean());
        }
        else if(type==0){this.whoIamUser= new JFX2UserBean (ThreadLocalSession.getUserSession().get().getUserBean());}
    }


    @Override
    public void updateFrom(Object ob, Object from) {
        //dummy
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxGreenPassOn.toBack();
    }
    }

