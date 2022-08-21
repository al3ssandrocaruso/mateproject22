package kapta.control.guicontroller.interfaceone.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import kapta.control.appcontroller.RegisterController;
import kapta.utils.bean.J1.JFX1ClubBean;
import kapta.utils.bean.J1.JFX1ProfileBean;
import kapta.utils.init.ReplaceScene;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class JFX1ClubRegisterGUIController {

    @FXML
    private TextField textFieldAddress;
    @FXML
    private TextField textFieldCity;
    @FXML
    private TextField textFieldClubName;
    @FXML
    private TextField textFieldWebsite;

    private JFX1ProfileBean profileBean;

    @FXML
    void backToSuperLogin(ActionEvent event){
        ReplaceScene.replaceScene(event ,"/JFX1/JFX1RegisterSuper.fxml");
    }

    @FXML
    void submitAction(ActionEvent event) throws MalformedURLException, URISyntaxException {

        String username = profileBean.getUsername();
        String email = profileBean.getEmail();
        String password = profileBean.getPassword();
        File img = profileBean.getImage();
        String clubName = textFieldClubName.getText();
        String address = textFieldAddress.getText();
        String city = textFieldCity.getText();
        String website = textFieldWebsite.getText();

        JFX1ClubBean jfx1ClubBean = new JFX1ClubBean(username, email, password, clubName, address, city, website);
        jfx1ClubBean.setImageOut(img);
        RegisterController.register(jfx1ClubBean);
        ReplaceScene.replaceScene(event, "/JFX1/JFX1Login.fxml");
    }


    public void setProfileBean(JFX1ProfileBean profileBean){
        this.profileBean = profileBean;
    }

}
