package kapta.utils.init;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import kapta.control.guicontroller.interfaceone.JFX1CreateEventGUIController;
import kapta.control.guicontroller.interfaceone.register.JFX1ClubRegisterGUIController;
import kapta.control.guicontroller.interfaceone.register.JFX1UserRegisterGUIController;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.J1.*;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.UserDao;
import kapta.utils.pagesetter.setterjfx1.*;
import kapta.utils.session.ThreadLocalSession;
import kapta.utils.utils.StageShow;

import java.io.IOException;

public class ReplaceSceneAndInitializePage {

    public void replaceSceneAndInitializePage(ActionEvent ae, String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ///eee solo provvisorio
        JFX1UserBean userBean = null;
        JFX1ClubBean clubBean = null;

        ClubModel clubModel = null;
        UserModel userModel =null;
        int type = ThreadLocalSession.getUserSession().get().getType();
        if (type==0){
            // sono di tipo utente
            userBean = new JFX1UserBean (ThreadLocalSession.getUserSession().get().getUserBean());
            userModel = UserDao.getUserById(userBean.getId());
        }
        else if(type == 1 ){
            clubBean= new JFX1ClubBean (ThreadLocalSession.getUserSession().get().getClubBean());
            clubModel= ClubDao.getClubByUserName(clubBean.getUsername());
        }

        /// ==> da usare solo nel login e anche nella barra perchè prendo info dalla sessione
        if (fxml.equals("/JFX1/JFX1UserProfile.fxml")) {
            JFX1UserProfileSetter.setter( userBean, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1ClubProfile.fxml")) {
            JFX1ClubProfileSetter.setter(clubBean, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1ClubRequestPage.fxml")) {
            JFX1ClubRequestPageSetter.setter( clubModel, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1ClubSetting.fxml")) {
            JFX1ClubSettingPageSetter.setter( clubModel ,loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1UserSetting.fxml")) {
            JFX1UserSettingPageSetter.setter( userModel, loader.getController());
        }
        if(fxml.equals("/JFX1/JFX1UserRequestPage.fxml")) {
            JFX1UserRequestPageSetter.setter(loader.getController(), userModel);
        }
        if(fxml.equals("/JFX1/JFX1UserCreateParty.fxml")) {
            JFX1CreatePartySetter.setter(loader.getController(),  userBean);
        }
        StageShow.showStage(ae,root);
    }


    public void replaceSceneAndInitializePage(ActionEvent ae, String fxml, Object ob) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fxml.equals("/JFX1/JFX1UserProfile.fxml")) {
            JFX1UserProfileSetter.setter((JFX1UserBean)  ob, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1EventPage.fxml")) {
            JFX1EventBean eb = new JFX1EventBean((EventBean) ob);
            JFX1EventPageSetter.setter(eb, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1PartyPage.fxml")) {
            JFX1PartyBean pb  = (JFX1PartyBean) ob;
            JFX1PartyPageSetter.setter(pb, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1ClubFollowersList.fxml")) {
            ClubModel clubModel = (ClubModel) ob;
            JFX1ClubFollowersPageSetter.setter(clubModel, loader.getController());
        }
        if(fxml.equals("/JFX1/JFX1ClubCreateEvent.fxml")){
            loader.getController();
        }
        if(fxml.equals("/JFX1/JFX1ClubProfile.fxml")){
            JFX1ClubBean jfx1ClubBean = (JFX1ClubBean) ob;
            JFX1ClubProfileSetter.setter(jfx1ClubBean, loader.getController());
        }
        StageShow.showStage(ae,root);
    }

    public void replaceSceneAndInitializeRegister(ActionEvent ae, String fxml, JFX1ProfileBean profileBean) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fxml.equals("/JFX1/JFX1RegisterUser.fxml")){
            JFX1UserRegisterGUIController urgc = loader.getController();
            urgc.setProfileBean(profileBean);
        }
        if(fxml.equals("/JFX1/JFX1RegisterClub.fxml")){
            JFX1ClubRegisterGUIController crgc = loader.getController();
            crgc.setProfileBean(profileBean);
        }

        StageShow.showStage(ae,root);

    }

    public void replaceSceneAndInitializePage(ActionEvent ae, String fxml, int support, JFX1UserBean owner){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFX1FollowerFollowingPageSetter.setter(owner,loader.getController(),support);
        StageShow.showStage(ae,root);
    }

}
