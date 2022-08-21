package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import kapta.control.appcontroller.CreatePartyController;
import kapta.utils.bean.J1.JFX1PartyBean;
import kapta.utils.bean.J1.JFX1UserBean;
import kapta.utils.init.ReplaceSceneAndInitializePage;

import java.io.File;

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


    private JFX1UserBean userBean;
    private File image;



    public void setUserBean(JFX1UserBean userBean ) {
        this.userBean = userBean;
    }

    @FXML
    public void backToProfileAction(ActionEvent ae) {
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(ae, "/JFX1/JFX1UserProfile.fxml", getUserBean());

    }

    @FXML
    public void confirmCreatePartyAction(ActionEvent ae){
        CreatePartyController createPartyController = new CreatePartyController();
        JFX1PartyBean partyBean = new JFX1PartyBean(textFieldPartyName.getText(),textFieldPartyDuration.getText(),textFieldPartyAddress.getText(),textFieldPartyTimeH.getText() , textFieldPartyTimeM.getText(),datePickerPartyDate.getValue(),this.image);
        createPartyController.createAndJoinParty(partyBean);
        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(ae, "/JFX1/JFX1UserProfile.fxml", getUserBean());

       }


    public void loadPartyImage() {
        Stage stage = (Stage) textFieldPartyName.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        this.image = fileChooser.showOpenDialog(stage).getAbsoluteFile();
    }



    private JFX1UserBean getUserBean() {
        return userBean;
    }

}
