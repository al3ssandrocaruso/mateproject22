package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import kapta.application.SettingsApplicationLayer;
import kapta.control.appcontroller.SettingsPageController;
import kapta.utils.bean.beanin.UserSettingsBean;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;
import kapta.utils.utils.EndSettingsChanges;

public class JFX1UserSettingsGUIController {

    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSecondName;
    @FXML
    private ImageView img;

    private SettingsApplicationLayer settingsApplicationLayer;

    public void setSettingsApplicationLayer(SettingsApplicationLayer settingsApplicationLayer) {this.settingsApplicationLayer = settingsApplicationLayer;}
    public void setTextFieldUsername(String username) {
        this.textFieldUsername.setText(username);
    }
    public void setTextFieldEmail(String email) {
        this.textFieldEmail.setText(email);
    }
    public void setTextFieldName(String name) {
        this.textFieldName.setText(name);
    }
    public void setTextFieldSecondName(String secondName) {
        this.textFieldSecondName.setText(secondName);
    }

    public void setAll(JFX1UserBeanOut jfx1UserBeanOut, SettingsApplicationLayer settingsApplicationLayer) {
        setSettingsApplicationLayer(settingsApplicationLayer);
        setTextFieldUsername(jfx1UserBeanOut.getUsername());
        setTextFieldName(jfx1UserBeanOut.getName());
        setTextFieldSecondName(jfx1UserBeanOut.getSecondName());
        setTextFieldEmail(jfx1UserBeanOut.getEmail());
        this.img.setImage(jfx1UserBeanOut.getProfileImg());
    }

    public void saveAction(ActionEvent ae) {
        String username = textFieldUsername.getText();
        String email = textFieldEmail.getText();
        String name = textFieldName.getText();
        String secondName = textFieldSecondName.getText();

        UserSettingsBean userSettingsBean = new UserSettingsBean(username, email, name, secondName, settingsApplicationLayer.getId());
        SettingsPageController.saveSettings(userSettingsBean);
        EndSettingsChanges.endChanges1(ae, settingsApplicationLayer);

    }
}
