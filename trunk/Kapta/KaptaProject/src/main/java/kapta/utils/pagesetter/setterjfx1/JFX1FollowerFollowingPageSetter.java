package kapta.utils.pagesetter.setterjfx1;

import kapta.application.FollowerFollowingListApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1FollowerFollowingListGuiController;
import kapta.model.profiles.UserModel;

public class JFX1FollowerFollowingPageSetter {

    private JFX1FollowerFollowingPageSetter(){
        //ignore
    }

    public static void setter(UserModel userModel, JFX1FollowerFollowingListGuiController fflgc, int support){
        new FollowerFollowingListApplicationLayer(fflgc, support, userModel);
    }
}
