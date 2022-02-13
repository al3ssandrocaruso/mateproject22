package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceScene;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class JFX2RegisterUserGUIController {

    @FXML
    private AnchorPane step2;
    @FXML
    private AnchorPane step3;
    @FXML
    private AnchorPane step4;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSecondName;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private RadioButton radioBtnMale;
    @FXML
    private RadioButton radioBtnFemale;

    private String[] preliminaryInfo;

    public void backToStep1(ActionEvent actionEvent) {ReplaceScene.replaceScene(actionEvent,"/JFX2/JFX2RegisterWho.fxml");}
    public void backToStep2() {
        textFieldName.setText(textFieldName.getText());
        step2.toFront();
    }
    public void gotoStep3() {
        step3.toFront();
    }
    public void backToStep3() {
        textFieldSecondName.setText(textFieldSecondName.getText());
        step3.toFront();
    }
    public void gotoStep4() {
        step4.toFront();
    }

    public void gotoStep5(ActionEvent actionEvent) throws MalformedURLException, URISyntaxException {
        setPreInfo();
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.contRegister(actionEvent,"/JFX2/JFX2ConcludeSubmit.fxml", preliminaryInfo, 0);
    }

    private void setPreInfo(){
        String name = textFieldName.getText();
        String secondName = textFieldSecondName.getText();
        String email = textFieldEmail.getText();
        String gender = setGender();

        preliminaryInfo = new String[]{name, secondName, email, gender};
    }

    public String[] getInfo(){
        return preliminaryInfo;
    }

    private String setGender(){
        if (radioBtnMale.isSelected()){
            return "Male";
        }
        if (radioBtnFemale.isSelected()){
            return "Female";
        }
        else{
            return "Other";
        }
    }






}
