package kapta.utils.init;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import kapta.control.guicontroller.interfaceone.JFX1CreateEventGUIController;
import kapta.control.guicontroller.interfaceone.register.JFX1ClubRegisterGUIController;
import kapta.control.guicontroller.interfaceone.register.JFX1UserRegisterGUIController;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanin.jfx1.JFX1ProfileBean;
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

        /// ==> da usare solo nel login e anche nella barra perchè prendo info dalla sessione
        if (fxml.equals("/JFX1/JFX1UserProfile.fxml")) {
            JFX1UserProfileSetter.setter( ThreadLocalSession.getUserSession().get().getUserModel(), loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1ClubProfile.fxml")) {
            JFX1ClubProfileSetter.setter( ThreadLocalSession.getUserSession().get().getClubModel(), loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1ClubRequestPage.fxml")) {
            JFX1ClubRequestPageSetter.setter( ThreadLocalSession.getUserSession().get().getClubModel(), loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1ClubSetting.fxml")) {
            JFX1ClubSettingPageSetter.setter( ThreadLocalSession.getUserSession().get().getClubModel(), loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1UserSetting.fxml")) {
            JFX1UserSettingPageSetter.setter( ThreadLocalSession.getUserSession().get().getUserModel(), loader.getController());
        }
        if(fxml.equals("/JFX1/JFX1UserRequestPage.fxml")) {
            JFX1UserRequestPageSetter.setter(loader.getController(),  ThreadLocalSession.getUserSession().get().getUserModel());
        }
        if(fxml.equals("/JFX1/JFX1UserCreateParty.fxml")) {
            JFX1CreatePartySetter.setter(loader.getController(),  ThreadLocalSession.getUserSession().get().getUserModel());
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
            JFX1UserProfileSetter.setter((UserModel) ob, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1EventPage.fxml")) {
            JFX1EventPageSetter.setter((EventModel) ob, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1PartyPage.fxml")) {
            PartyModel pm = (PartyModel) ob;
            JFX1PartyPageSetter.setter(pm, loader.getController());
        }
        if (fxml.equals("/JFX1/JFX1ClubFollowersList.fxml")) {
            ClubModel clubModel = (ClubModel) ob;
            JFX1ClubFollowersPageSetter.setter(clubModel, loader.getController());
        }
        if(fxml.equals("/JFX1/JFX1ClubCreateEvent.fxml")){
            ClubModel clubModel = (ClubModel) ob;
            JFX1CreateEventGUIController cegc = loader.getController();
            JFX1CreateEventSetter.setter(clubModel, cegc);
        }
        if(fxml.equals("/JFX1/JFX1ClubProfile.fxml")){
            ClubModel clubModel = (ClubModel) ob;
            JFX1ClubProfileSetter.setter(clubModel, loader.getController());
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

    public void replaceSceneAndInitializePage(ActionEvent ae, String fxml, int support, UserModel owner){
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
