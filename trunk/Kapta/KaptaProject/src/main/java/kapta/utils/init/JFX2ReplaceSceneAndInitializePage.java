package kapta.utils.init;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kapta.control.guicontroller.interfacetwo.JFX2AlertCreator;
import kapta.utils.bean.jfx2.JFX2ClubBean;
import kapta.utils.bean.jfx2.JFX2UserBean;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.mysession.ThreadLocalSession;
import kapta.utils.pagesetter.setterjfx2.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class JFX2ReplaceSceneAndInitializePage {

    private String jfx2Profile = "/JFX2/JFX2UserProfile.fxml";

    public void replaceSceneAndInitializePage(ActionEvent ae, String fxml)  {
        JFX2ClubBean clubBean = null;
        JFX2UserBean userBean=null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;

        try {
            root = loader.load();

            if (ThreadLocalSession.getUserSession().get().getType() == 0) {
                userBean = new JFX2UserBean(ThreadLocalSession.getUserSession().get().getUserBean());
            } else {
                clubBean = new JFX2ClubBean(ThreadLocalSession.getUserSession().get().getClubBean());
            }

            if (fxml.equals(jfx2Profile)) {
                JFX2UserProfileSetter.setter(userBean, loader.getController());
            }
            if (fxml.equals("/JFX2/JFX2UserRequestPage.fxml")) {
                JFX2RequestPageSetter.setter(userBean, loader.getController());
            }
            if (fxml.equals("/JFX2/JFX2ClubProfile.fxml")) {
                JFX2ClubProfileSetter.setter(clubBean, loader.getController());
            }

            Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (SystemException | IOException systemException) {
            JFX2AlertCreator.createAlert(systemException);
        }
    }

    public void replaceSceneAndInitializePage(ActionEvent ae, String fxml, Object ob) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        JFX2ClubBean clubBean = new JFX2ClubBean(ThreadLocalSession.getUserSession().get().getClubBean());

        try {
            root = loader.load();


            if (fxml.equals(jfx2Profile)) {
                JFX2UserBean um = (JFX2UserBean) ob;
                JFX2UserProfileSetter.setter(um, loader.getController());
            }
            if (fxml.equals("/JFX2/JFX2ClubProfile.fxml")) {

                JFX2ClubProfileSetter.setter(clubBean, loader.getController());
            }

            if (fxml.equals("/JFX2/JFX2PartyEventPage.fxml")) {
                JFX2PartyEventPageSetter.setter(ob, loader.getController());
            }


            Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (SystemException | IOException systemException) {
            JFX2AlertCreator.createAlert(systemException);
        }
    }
    public void replaceSceneAndInitializePage(String fxml, Object ob, Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();

            if (fxml.equals(jfx2Profile)) {
                JFX2UserBean um = (JFX2UserBean) ob;
                JFX2UserProfileSetter.setter(um, loader.getController());
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (SystemException | IOException systemException) {
            JFX2AlertCreator.createAlert(systemException);
        }
    }

    public void contRegister(ActionEvent ae, String fxml, String[] preliminaryInfo, int type) throws MalformedURLException, URISyntaxException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
            JFX2RegisterSetter.setter(preliminaryInfo, type, loader.getController());
        } catch (InputNullException | EmailValidatorException | IOException e) {
           //ignored
        }

        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
