package kapta.application;

import javafx.event.ActionEvent;
import kapta.utils.init.ReplaceSceneAndInitializePage;

public class SettingsApplicationLayer {

    private int userClubId;

    public int getId() {
        return userClubId;
    }
    public void setId(int clubId) {
        this.userClubId = clubId;
    }

    public SettingsApplicationLayer(int userClubId){
        this.setId(userClubId);
    }

    public void goToLogin(ActionEvent ae) {
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae, "/JFX1/JFX1Login.fxml");
    }
}
