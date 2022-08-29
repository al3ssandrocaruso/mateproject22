package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import kapta.control.appcontroller.SettingsPageController;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.exception.myexception.SystemException;
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

    private JFX1UserBean userBean;




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

    public void setAll(JFX1UserBean jfx1UserBean) {

        this.userBean=jfx1UserBean;
        setTextFieldUsername(jfx1UserBean.getUsernameOut());
        setTextFieldName(jfx1UserBean.getNameOut());
        setTextFieldSecondName(jfx1UserBean.getSecondNameOut());
        setTextFieldEmail(jfx1UserBean.getEmailOut());
        try {
            this.img.setImage(jfx1UserBean.getProfileImgOut());
        } catch (SystemException e) {
            //
        }
    }

    public void saveAction(ActionEvent ae) {
        String username = textFieldUsername.getText();
        String email = textFieldEmail.getText();
        String name = textFieldName.getText();
        String secondName = textFieldSecondName.getText();

        JFX1UserBean bean = new JFX1UserBean(username, email, name, secondName, this.userBean.getId());
        try {
            SettingsPageController.saveSettings(bean);
            EndSettingsChanges.endChanges1(ae);
        } catch (SystemException e) {
            JFX1AlertCreator.createAlert(e);
        }


    }
}
