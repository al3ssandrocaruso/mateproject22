package kapta.control.guicontroller.interfaceone.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kapta.control.appcontroller.RegisterController;
import kapta.utils.bean.beanin.jfx1.JFX1ProfileBean;
import kapta.utils.exception.*;
import kapta.utils.exception.myexception.ConfirmPasswordException;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.exception.myexception.UsernameConflictException;
import kapta.utils.init.ReplaceScene;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import java.io.File;

public class JFX1SuperRegisterGUIController {
    @FXML
    private RadioButton radioButtonUser;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordFieldConfirmPassword;

    private File img;

    @FXML
    protected void backToLoginAction(ActionEvent ae) {
        ReplaceScene.replaceScene(ae , "/JFX1/JFX1Login.fxml");
    }
    @FXML
    protected void continueRegistrationAction(ActionEvent ae) {

        int type;
        if (radioButtonUser.isSelected()) {type = 0;}
        else {type = 1;}

        try {
            JFX1ProfileBean jfx1ProfileBean = new JFX1ProfileBean(textFieldUsername.getText(), passwordField.getText(), textFieldEmail.getText(), this.img, type);
            Trigger.confirmPassword(passwordField.getText(), passwordFieldConfirmPassword.getText());
            RegisterController.startRegister(jfx1ProfileBean);
            ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
            if (type == 0) {
                replaceSceneAndInitializePage.replaceSceneAndInitializeRegister(ae, "/JFX1/JFX1RegisterUser.fxml", jfx1ProfileBean);
            } else {
                replaceSceneAndInitializePage.replaceSceneAndInitializeRegister(ae, "/JFX1/JFX1RegisterClub.fxml", jfx1ProfileBean);
            }
        }
        catch(EmailValidatorException | ConfirmPasswordException | UsernameConflictException| InputNullException e) {
            ErrorHandler.getInstance().reportFinalException(e);
        }

    }

    public void loadImage() {
        Stage stage = (Stage) textFieldEmail.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        img=fileChooser.showOpenDialog(stage).getAbsoluteFile();
    }
}
