package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kapta.control.appcontroller.RegisterController;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.bean.J2.JFX2ClubBean;
import kapta.utils.bean.J2.JFX2UserBean;
import kapta.utils.init.ReplaceScene;
import javafx.scene.control.TextField;

import java.io.File;

public class JFX2ConcludeSubmitGUIController {
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldPassword;

    private JFX2UserBean jfx2UserBean;
    private JFX2ClubBean jfx2ClubBean;
    private Integer type;
    private File img;

    public Integer getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void concludeSubmit(ActionEvent actionEvent) {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        if(this.getType() == 0){
            jfx2UserBean.setUsernameOut(username);
            jfx2UserBean.setPasswordOut(password);
            jfx2UserBean.setImageOut(img);
            RegisterController.register(jfx2UserBean);
        }
        else {
            jfx2ClubBean.setUsernameOut(username);
            jfx2ClubBean.setPasswordOut(password);
            jfx2ClubBean.setImageOut(img);
            RegisterController.register(jfx2ClubBean);
        }

        ReplaceScene.replaceScene(actionEvent,"/JFX2/JFX2Welcome.fxml");
    }

    public void setAll(GenericUserBean genericUserBean, int type) {
        this.setType(type);
        if(type == 0){setUserBean((JFX2UserBean)genericUserBean);}
        else {setClubBean((JFX2ClubBean) genericUserBean);}
    }

    public void setUserBean(JFX2UserBean jfx2UserBean){
        this.jfx2UserBean = jfx2UserBean;
    }

    public void setClubBean(JFX2ClubBean jfx2ClubBean){
        this.jfx2ClubBean = jfx2ClubBean;
    }

    public void loadImage() {
        Stage stage = (Stage) textFieldPassword.getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        img=fileChooser.showOpenDialog(stage).getAbsoluteFile();
    }
}
