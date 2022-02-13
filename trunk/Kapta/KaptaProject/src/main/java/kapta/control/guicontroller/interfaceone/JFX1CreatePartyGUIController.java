package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kapta.application.UserProfileApplicationLayer;
import kapta.control.appcontroller.CreatePartyController;
import kapta.utils.bean.beanin.jfx1.JFX1PartyBean;

public class JFX1CreatePartyGUIController {
    @FXML
    private TextField textFieldPartyName;

    @FXML
    private TextField textFieldPartyAddress;

    @FXML
    private TextField textFieldPartyTimeH;

    @FXML
    private TextField textFieldPartyTimeM;

    @FXML
    private TextField textFieldPartyDuration;

    @FXML
    private DatePicker datePickerPartyDate;


    private UserProfileApplicationLayer userProfileApplicationLayer;

    public void setUserProfileApplication(UserProfileApplicationLayer userProfileApplicationLayer) {
        this.userProfileApplicationLayer = userProfileApplicationLayer;
    }

    @FXML
    public void backToProfileAction(ActionEvent ae) {
        this.userProfileApplicationLayer.navigationUserProfile(ae,"/JFX1/JFX1UserProfile.fxml");}

    @FXML
    public void confirmCreatePartyAction(ActionEvent ae){
        CreatePartyController createPartyController = new CreatePartyController();
        JFX1PartyBean partyBean = new JFX1PartyBean(textFieldPartyName.getText(),textFieldPartyDuration.getText(),textFieldPartyAddress.getText(),textFieldPartyTimeH.getText() , textFieldPartyTimeM.getText(),datePickerPartyDate.getValue(),this.userProfileApplicationLayer.getLoadedImg());
        createPartyController.createAndJoinParty(partyBean);
        this.userProfileApplicationLayer.navigationUserProfile(ae,"/JFX1/JFX1UserProfile.fxml");
       }


    public void loadPartyImage() {
        Stage stage = (Stage) textFieldPartyName.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        this.userProfileApplicationLayer.setLoadedImg(fileChooser.showOpenDialog(stage).getAbsoluteFile());
    }

}
