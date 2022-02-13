package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceScene;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class JFX2RegisterClubGUIController {
    @FXML
    private AnchorPane step2;
    @FXML
    private AnchorPane step3;
    @FXML
    private AnchorPane step4;
    @FXML
    private AnchorPane step5;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldAddress;
    @FXML
    private TextField textFieldCity;
    @FXML
    private TextField textFieldWebsite;
    @FXML
    private TextField textFieldEmail;

    private String[] preliminaryInfo;


    public void goAfter(ActionEvent actionEvent) throws MalformedURLException, URISyntaxException {
        setPreInfo();
        JFX2ReplaceSceneAndInitializePage rsip = new JFX2ReplaceSceneAndInitializePage();
        rsip.contRegister(actionEvent,"/JFX2/JFX2ConcludeSubmit.fxml", preliminaryInfo, 1);
    }

    private void setPreInfo(){
        String name = textFieldName.getText();
        String address = textFieldAddress.getText();
        String city = textFieldCity.getText();
        String website = textFieldWebsite.getText();
        String email = textFieldEmail.getText();

        preliminaryInfo = new String[]{name, address, city, website, email};
    }

    public void backToStep1(ActionEvent actionEvent) {ReplaceScene.replaceScene(actionEvent,"/JFX2/JFX2RegisterWho.fxml");}
    public void backToStep2() {
        textFieldName.setText(textFieldName.getText());
        step2.toFront();
    }
    public void gotoStep3() {
        step3.toFront();
    }
    public void backToStep3() {
        textFieldAddress.setText(textFieldAddress.getText());
        step3.toFront();
    }
    public void gotoStep4() {
        step4.toFront();
    }
    public void backToStep4() {
        textFieldCity.setText(textFieldCity.getText());
        step4.toFront();
    }
    public void gotoStep5() {
        step5.toFront();
    }


}
