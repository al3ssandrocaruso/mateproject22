package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;

import kapta.control.appcontroller.CreatePartyController;
import kapta.control.appcontroller.FollowUserController;
import kapta.control.appcontroller.SettingsPageController;

import kapta.engineering.ManageFollowerFollowingList;
import kapta.engineering.MangeJoined;

import kapta.utils.bean.EventBean;
import kapta.utils.bean.GenericListInfoBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.UserBean;
import kapta.utils.bean.J2.JFX2ClubBean;
import kapta.utils.bean.J2.JFX2EventBean;
import kapta.utils.bean.J2.JFX2PartyBean;
import kapta.utils.bean.J2.JFX2UserBean;
import kapta.utils.Observer;

import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.session.ThreadLocalSession;
import kapta.utils.utils.EndSettingsChanges;
import kapta.utils.utils.FollowUtils;

import java.io.File;
import java.io.IOException;

public class JFX2UserProfileGUIController implements Observer {

    @FXML
    private TextField textFieldPartyName;
    @FXML
    private TextField textFieldPartyAddress;
    @FXML
    private TextField textFieldPartyDate;
    @FXML
    private TextField textFieldBeginTime;
    @FXML
    private ImageView profileImg;
    @FXML
    public TextField textFieldSettingEmail;
    @FXML
    public VBox vboxJoinedList;
    @FXML
    private TextField textFieldSettingUsername;
    @FXML
    private TextField textFieldSettingName;
    @FXML
    private TextField textFieldSettingSecondName;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnJoinedOrFollow;
    @FXML
    private Slider sliderDuration;
    @FXML
    private Label labelDuration;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelInfoName;
    @FXML
    private Label labelInfoSurname;
    @FXML
    private Label labelInfoEmail;
    @FXML
    private VBox vboxForm;
    @FXML
    private Button numberFollower;
    @FXML
    private Button numberFollowing;
    @FXML
    private ListView<Pane> listViewJoined;
    @FXML
    private HBox hboxSettings;

    boolean state = true;
    private boolean itsMyPageOrNot;
    boolean popupFollowingOn = false;
    boolean popupFollowerOn = false;
    private ListView<Pane> followerListView = new ListView<>();
    private ListView<Pane> followingListView = new ListView<>();
    private Popup popupFollowerFollowing;
    private static final String STR_FOLLOW = "Follow";
    private String arial = "Arial";

    private Double duration;
    private File createdPartyImg;


    // tox
    private JFX2UserBean whoIamUser;
    private JFX2ClubBean whoIamClub;
    private int type;

    private JFX2UserBean userBean;
    private File loadedImg;
    ManageFollowerFollowingList m;


    public JFX2UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(JFX2UserBean userBean) {
        this.userBean = userBean;
    }

    public void setProfileImg(Image img){
        this.profileImg.setImage(img);
    }

    public void setLabelInfoName(String labelInfoName) {
        this.labelInfoName.setText(labelInfoName);
    }

    public void setLabelInfoSurname(String labelInfoSurname) {
        this.labelInfoSurname.setText(labelInfoSurname);
    }

    public void setLabelInfoEmail(String labelInfoEmail) {
        this.labelInfoEmail.setText(labelInfoEmail);
    }

    public void setItsMyPageOrNot(boolean itsMyPageOrNot) {
        this.itsMyPageOrNot = itsMyPageOrNot;
    }

    public void setLabelUsername(String username) {
        this.labelUsername.setText(username);
    }

    public void setNumberFollower(String numberFollower) {
        this.numberFollower.setText(numberFollower);
    }

    public void setNumberFollowing(String numberFollowing) {
        this.numberFollowing.setText(numberFollowing);
    }

    public void setTextFieldSettingEmail(String settingEmail) {this.textFieldSettingEmail.setText(settingEmail);}

    public void setTextFieldSettingUsername(String settingUsername) {this.textFieldSettingUsername.setText(settingUsername);}

    public void setTextFieldSettingSecondName(String settingSecondName){this.textFieldSettingSecondName.setText(settingSecondName);}

    public void setTextFieldSettingName(String settingName) {
        this.textFieldSettingName.setText(settingName);
    }

    @FXML
    public void selectDuration() {
        labelDuration.setText(String.valueOf(sliderDuration.getValue()));
        this.duration=sliderDuration.getValue();
    }

    public void saveAction(ActionEvent ae) throws InputNullException, EmailValidatorException {
        String username = textFieldSettingUsername.getText();
        String email = textFieldSettingEmail.getText();
        String name = textFieldSettingName.getText();
        String secondName = textFieldSettingSecondName.getText();

        JFX2UserBean bean = new JFX2UserBean(username, email, name, secondName, userBean.getId());
        SettingsPageController.saveSettings(bean);

        EndSettingsChanges.endChanges2(ae);

    }

    public void hidePopUp() {
        popupFollowerFollowing.hide();
    }

    public void setPane(int type) { //check se io sono il visited user o no
        switch(type){
            case 0:{
                if (!this.itsMyPageOrNot) {
                    vboxForm.toBack();
                    vboxJoinedList.toFront();
                    hboxSettings.toBack();
                    // eee solo per ora
                    JFX2UserBean userBeanMe = whoIamUser;
                    if (FollowUtils.doAFollowB(userBeanMe, getUserBean())) {
                        btnJoinedOrFollow.setText("Unfollow");
                    } else {
                        btnJoinedOrFollow.setText(STR_FOLLOW);
                    }
                } else {
                    btnJoinedOrFollow.setText("Joined List");
                    hboxSettings.toFront();
                }
                break;
            }
            case 1:{
                vboxForm.toBack();
                vboxJoinedList.toFront();
                hboxSettings.toBack();
                //Importante -> NON TOGLIERE RIGA QUA SOTTO
                btnJoinedOrFollow.setVisible(false);
                break;
            }
            default:{
                break;
            }
        }


    }

    public void performAction(){
        if (itsMyPageOrNot) { //change pane
            if (state) { //sto in mio profilo

                state = false;
                vboxForm.toBack();
                btnJoinedOrFollow.setMinSize(163, 42);
                btnJoinedOrFollow.setFont(Font.font(arial, FontWeight.BOLD, 20));
                btnJoinedOrFollow.setText("Create Party");
                listViewJoined.getItems().clear();

                MangeJoined.adjJoinedList(getUserBean(),this);

            } else {
                state = true;
                vboxForm.toFront();
                btnJoinedOrFollow.setText("Joined List");
            }
        } else {
            // eee solo per ora
            JFX2UserBean userBean2 = whoIamUser;
            if(btnJoinedOrFollow.getText().equals(STR_FOLLOW)){
                btnJoinedOrFollow.setText("Unfollow");
                FollowUserController.follow(getUserBean(), userBean2);
                m.addUserFollowerList(this.userBean);
            }else{
                btnJoinedOrFollow.setText(STR_FOLLOW);
                FollowUserController.unfollow(getUserBean(), userBean2);
                m.removeUserFollowerList(this.userBean);
            }
        }
    }

    public void showFollowing() {
        if (!popupFollowingOn && !popupFollowerOn) {
            popupFollowingOn = true;

            popupFollowerFollowing = new Popup();

            VBox vBox = new VBox();
            HBox hBox = new HBox();
            Button btnClose = new Button("Close");
            btnClose.setStyle("-fx-background-radius: 28");
            Label label = new Label("Following List");
            label.setFont(new Font(arial, 25));
            label.setTextFill(Color.WHITE);
            followingListView.setMaxWidth(360);
            followingListView.setMaxHeight(400);
            hBox.getChildren().addAll(label, btnClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setSpacing(190);
            HBox.setMargin(label, new Insets(0, 0, 0, 10));
            vBox.getChildren().addAll(hBox,followingListView);
            handleFolList(vBox,btnClose,true);
        }
    }

    public void showFollower() {
        if (!popupFollowerOn && !popupFollowingOn) {
            popupFollowerOn = true;
            popupFollowerFollowing = new Popup();
            VBox vBox = new VBox();
            HBox hBox = new HBox();
            Button btnClose = new Button("Close");
            btnClose.setStyle("-fx-background-radius: 28");
            Label label = new Label("Follower List");
            label.setFont(new Font(arial, 25));
            label.setTextFill(Color.WHITE);
            followerListView.setMaxWidth(360);
            followerListView.setMaxHeight(400);
            hBox.getChildren().addAll(label, btnClose);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setSpacing(190);
            HBox.setMargin(label, new Insets(0, 0, 0, 10));
            vBox.getChildren().addAll(hBox, followerListView);
            handleFolList(vBox,btnClose,false);
        }
    }

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        if(ob instanceof PartyBean partyBean){
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX2/JFX2PartyItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2PartyItemGUIController pigc = fxmlLoader.getController();
            JFX2PartyBean jfx2PartyBean =new JFX2PartyBean(partyBean);
            pigc.setAll(jfx2PartyBean);
            this.listViewJoined.getItems().add(pane);
        }
        else if(ob instanceof EventBean EventBean) {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX2/JFX2EventItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2EventItemGUIController eigc = fxmlLoader.getController();
            JFX2EventBean jfx2EventBean = new JFX2EventBean(EventBean);
            eigc.setAll(jfx2EventBean);
            this.listViewJoined.getItems().add(pane);
        }
    }

    @Override
    public void updateFrom(Object ob, Object objectFrom) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        if(ob instanceof UserBean userBean) {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX2/JFX2UserItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2UserItemGUIController uigc = fxmlLoader.getController();
            JFX2UserBean jfx2UserBean=new JFX2UserBean(userBean);
            uigc.setAll(jfx2UserBean,ap,this);
            System.out.println("here0000");
            if(objectFrom instanceof GenericListInfoBean gen) {
                if (gen.getType() == 1) {
                    followerListView.getItems().add(pane);
                    setNumberFollower(String.valueOf(gen.getSize()));
                } else if (gen.getType() == 0) {
                    followingListView.getItems().add(pane);
                    setNumberFollowing(String.valueOf(gen.getSize()));
                }
            }

        }
    }

    public void loadPartyImg() {
        Stage stage = (Stage) vboxJoinedList.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        createdPartyImg=fileChooser.showOpenDialog(stage);
    }

    public void createParty(ActionEvent ae) {
        CreatePartyController createPartyController = new CreatePartyController();
        JFX2PartyBean partyBean = new JFX2PartyBean(textFieldPartyName.getText(),this.duration,textFieldPartyAddress.getText(),textFieldBeginTime.getText() ,textFieldPartyDate.getText(),this.createdPartyImg);
        createPartyController.createAndJoinParty(partyBean);

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(((Node)ae.getSource()).getScene().getWindow());
        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        Label text=new Label("Successful Party Creation!");
        text.setStyle("-fx-font-weight: bold;"+"-fx-font-family: Arial; -fx-font-size: 20;"+"-fx-text-fill: #200f54");
        dialogVbox.getChildren().add(text);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        textFieldPartyName.setText("");
        textFieldPartyAddress.setText("");
        textFieldBeginTime.setText("");
        textFieldPartyDate.setText("");
        labelDuration.setText("0");
        sliderDuration.setValue(0);
        this.duration= Double.valueOf(0);
    }

    public void setAll(JFX2UserBean jfx2UserBean, ManageFollowerFollowingList m){
        setWhoIam();

        this.m=m;
        this.userBean = jfx2UserBean;
        setLabelUsername(jfx2UserBean.getUsernameOut());
        setProfileImg(jfx2UserBean.getImageOut());

        if(itsMyPageOrNot){
            setTextFieldSettingEmail(jfx2UserBean.getEmailOut());
            setTextFieldSettingName(jfx2UserBean.getNameOut());
            setTextFieldSettingUsername(jfx2UserBean.getUsernameOut());
            setTextFieldSettingSecondName(jfx2UserBean.getSurnameOut());
        } else {
            setLabelInfoName(jfx2UserBean.getNameOut());
            setLabelInfoSurname(jfx2UserBean.getSurnameOut());
            setLabelInfoEmail(jfx2UserBean.getEmailOut());
        }
        setItsMyPageOrNot(true);

        int type = this.type;

        switch (type){
            case 0:{
                //se sono un altro UserModel che visita il profilo di un altro userModel
                if(!jfx2UserBean.getUsernameOut().equals(whoIamUser.getUsername())){
                    setItsMyPageOrNot(false);
                }

                break;
            }
            case 1:{
                //se sono un ClubModel che visita il profilo di un altro UserModel
                setItsMyPageOrNot(false);
                break;
            }
            default:
                break;
        }

        setPane(type);
    }
    private void handleFolList(VBox vBox,Button btnClose, boolean support){
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinHeight(400);
        vBox.setMinWidth(400);
        vBox.setStyle("-fx-background-color: #200f54");
        popupFollowerFollowing.setX(687);
        popupFollowerFollowing.setY(140);

        popupFollowerFollowing.getContent().addAll(vBox);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setWidth(30);
        dropShadow.setHeight(30);
        dropShadow.setRadius(14.5);
        dropShadow.setSpread(0.3);
        vBox.setEffect(dropShadow);

        popupFollowerFollowing.show(ap.getScene().getWindow());

        btnClose.setOnAction((ActionEvent event) -> {
            popupFollowerFollowing.hide();
            if(support){popupFollowingOn = false;}
            else {popupFollowerOn=false;}
        });
    }

    // tox


    private File getLoadedImg() {
        return loadedImg;
    }
    private void setLoadedImg(File loadedImg) {
        this.loadedImg = loadedImg;
    }



    private void setWhoIam() {
        int type= ThreadLocalSession.getUserSession().get().getType();
        this.type = type;
        if(type==1){
            this.whoIamClub=(JFX2ClubBean) ThreadLocalSession.getUserSession().get().getClubBean();

        }
        else if(type==0){
            this.whoIamUser= new  JFX2UserBean (  ThreadLocalSession.getUserSession().get().getUserBean());
        }
    }

}



