package kapta.utils.init;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.J2.JFX2ClubBean;
import kapta.utils.bean.J2.JFX2PartyBean;
import kapta.utils.bean.J2.JFX2UserBean;
import kapta.utils.dao.UserDao;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.pagesetter.setterjfx2.*;
import kapta.utils.session.ThreadLocalSession;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class JFX2ReplaceSceneAndInitializePage {

    private String jfx2Profile = "/JFX2/JFX2UserProfile.fxml";

    public void replaceSceneAndInitializePage(ActionEvent ae, String fxml)  {
        JFX2ClubBean clubBean = null;
        JFX2UserBean userBean=null;
        UserModel userModel = null;


        // eeee
        if (ThreadLocalSession.getUserSession().get().getType() ==0){
            userBean =  new JFX2UserBean(ThreadLocalSession.getUserSession().get().getUserBean());

            // eee
             userModel = UserDao.getUserById(userBean.getId());
        }
        else{
            clubBean=  (JFX2ClubBean)(ThreadLocalSession.getUserSession().get().getClubBean());
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fxml.equals(jfx2Profile)) {
            JFX2UserProfileSetter.setter( userBean, loader.getController());
        }
        if(fxml.equals("/JFX2/JFX2UserRequestPage.fxml")){
            JFX2RequestPageSetter.setter( userModel, loader.getController());
        }
        if(fxml.equals("/JFX2/JFX2ClubProfile.fxml")) {
            JFX2ClubProfileSetter.setter( clubBean, loader.getController());
        }

        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void replaceSceneAndInitializePage(ActionEvent ae, String fxml, Object ob) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Questi due sotto mi servono per popolare la pagina del creator di un party o di un evento
        if(fxml.equals(jfx2Profile)) {
            JFX2UserBean um = (JFX2UserBean) ob;
            JFX2UserProfileSetter.setter(um, loader.getController());
        }
        if(fxml.equals("/JFX2/JFX2ClubProfile.fxml")) {

            // eeee
            ClubModel cm = (ClubModel) ob;
            JFX2ClubBean clubBean = new JFX2ClubBean(cm);
            JFX2ClubProfileSetter.setter(clubBean, loader.getController());
        }

        if (fxml.equals("/JFX2/JFX2PartyEventPage.fxml" )) {
            JFX2PartyEventPageSetter.setter(ob, loader.getController());
        }


        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void replaceSceneAndInitializePage(String fxml, Object ob, Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fxml.equals(jfx2Profile)) {
            JFX2UserBean um = (JFX2UserBean) ob;
            JFX2UserProfileSetter.setter(um, loader.getController());
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void contRegister(ActionEvent ae, String fxml, String[] preliminaryInfo, int type) throws MalformedURLException, URISyntaxException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JFX2RegisterSetter.setter(preliminaryInfo, type, loader.getController());
        } catch (InputNullException | EmailValidatorException e) {
           //ignored
        }

        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
