package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import kapta.control.appcontroller.SettingsPageController;
import kapta.utils.bean.jfx1.JFX1ClubBean;
import kapta.utils.exception.myexception.SystemException;
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

    private JFX1ClubBean clubBean;


    public int getClubId(){
        return clubBean.getId();
    }

    public void setClubBean(JFX1ClubBean clubBean) {
        this.clubBean = clubBean;
    }

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

    public void setAll(JFX1ClubBean clubBean){

        setClubBean(clubBean);
        setTextFieldUsername(clubBean.getUsernameOut());
        setTextFieldCity(clubBean.getCityOut());
        setTextFieldAddress(clubBean.getClubAddressOut());
        setTextFieldEmail(clubBean.getEmailOut());
        try {
            this.img.setImage(clubBean.getImageOut());
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        }
    }

    public void saveAction(ActionEvent ae) {

        String username = textFieldUsername.getText();
        String email = textFieldEmail.getText();
        String city = textFieldCity.getText();
        String address = textFieldAddress.getText();
        int id = getClubId();

        JFX1ClubBean bean = new JFX1ClubBean(username, email, city, address, id);
        try {
            SettingsPageController.saveSettings(bean);

            EndSettingsChanges.endChanges1(ae);

        } catch (SystemException systemException) {
            systemException.printStackTrace();
        }

    }



}
