package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import kapta.application.SettingsApplicationLayer;
import kapta.control.appcontroller.SettingsPageController;
import kapta.utils.bean.beanin.ClubSettingsBean;
import kapta.utils.bean.beanout.jfx1.JFX1ClubBeanOutSettings;
import kapta.utils.utils.EndSettingsChanges;

public class JFX1ClubSettingGUIController {

    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldCity;
    @FXML
    private TextField textFieldAddress;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private ImageView img;

    private SettingsApplicationLayer settingsApplicationLayer;

    public void setSettingsApplicationLayer(SettingsApplicationLayer settingsApplicationLayer) {this.settingsApplicationLayer = settingsApplicationLayer;}
    public void setTextFieldUsername(String username) {
        this.textFieldUsername.setText(username);
    }
    public void setTextFieldCity(String city) {
        this.textFieldCity.setText(city);
    }
    public void setTextFieldAddress(String address) {
        this.textFieldAddress.setText(address);
    }
    public void setTextFieldEmail(String email) {
        this.textFieldEmail.setText(email);
    }

    public void setAll(JFX1ClubBeanOutSettings clubBeanOut, SettingsApplicationLayer settingsApplicationLayer){
        setSettingsApplicationLayer(settingsApplicationLayer);
        setTextFieldUsername(clubBeanOut.getUsername());
        setTextFieldCity(clubBeanOut.getCity());
        setTextFieldAddress(clubBeanOut.getClubAddress());
        setTextFieldEmail(clubBeanOut.getEmail());
        this.img.setImage(clubBeanOut.getProfileImg());
    }

    public void saveAction(ActionEvent ae) {

        String username = textFieldUsername.getText();
        String email = textFieldEmail.getText();
        String city = textFieldCity.getText();
        String address = textFieldAddress.getText();

        ClubSettingsBean clubSettingsBean = new ClubSettingsBean(username, email, city, address, settingsApplicationLayer.getId());
        SettingsPageController.saveSettings(clubSettingsBean);

        EndSettingsChanges.endChanges1(ae, settingsApplicationLayer);

    }



}
