package kapta.utils.pagesetter.setterjfx1;

import kapta.application.UserProfileApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1CreatePartyGUIController;
import kapta.model.profiles.UserModel;

public class JFX1CreatePartySetter {
    public static void setter(JFX1CreatePartyGUIController jfx1CreatePartyGUIController, UserModel userModel){
        UserProfileApplicationLayer userProfileApplication = new UserProfileApplicationLayer(jfx1CreatePartyGUIController, userModel);
        jfx1CreatePartyGUIController.setUserProfileApplication(userProfileApplication);
    }
}
