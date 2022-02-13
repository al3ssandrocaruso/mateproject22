package kapta.control.guicontroller.interfaceone.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import kapta.control.appcontroller.RegisterController;
import kapta.utils.bean.beanin.jfx1.JFX1ProfileBean;
import kapta.utils.bean.beanin.jfx1.JFX1UserBean;
import kapta.utils.init.ReplaceScene;

import java.io.File;

public class JFX1UserRegisterGUIController {

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldSecondName;

    @FXML
    private RadioButton radioBtnFemale;

    @FXML
    private RadioButton radioBtnMale;

    private JFX1ProfileBean profileBean;

    @FXML
    void backToSuperLogin(ActionEvent event) {
        ReplaceScene.replaceScene(event ,"/JFX1/JFX1RegisterSuper.fxml");
    }

    @FXML
    void submitAction(ActionEvent event)  {

        int gen = setGender();

        String username = profileBean.getUsername();
        String email = profileBean.getEmail();
        String password = profileBean.getPassword();
        File img = profileBean.getImage();
        String name = textFieldName.getText();
        String secondName = textFieldSecondName.getText();

        JFX1UserBean jfx1UserBean = new JFX1UserBean(username, email, password, img, name, secondName, gen);
        RegisterController.register(jfx1UserBean);
        ReplaceScene.replaceScene(event, "/JFX1/JFX1Login.fxml");
    }

    public void setProfileBean(JFX1ProfileBean profileBean) {
        this.profileBean = profileBean;
    }

    private int setGender(){
        if (radioBtnMale.isSelected()){
            return 0;
        }
        if (radioBtnFemale.isSelected()){
            return 1;
        }
        else{
            return 2;
        }
    }
}
