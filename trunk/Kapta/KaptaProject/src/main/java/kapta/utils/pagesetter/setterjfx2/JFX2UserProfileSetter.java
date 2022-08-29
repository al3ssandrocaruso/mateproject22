package kapta.utils.pagesetter.setterjfx2;

import kapta.control.guicontroller.interfacetwo.JFX2UserProfileGUIController;
import kapta.engineering.ManageFollowerFollowingList;
import kapta.engineering.MangeJoined;
import kapta.utils.bean.jfx2.JFX2UserBean;
import kapta.utils.exception.myexception.SystemException;

public class JFX2UserProfileSetter {

    private JFX2UserProfileSetter(){
        //ignore
    }

    public static void setter(JFX2UserBean userBean, JFX2UserProfileGUIController userProfileGUIController) throws SystemException {

        ManageFollowerFollowingList.setFollowingListP(userBean,userProfileGUIController);
        ManageFollowerFollowingList.setFollowerListP(userBean,userProfileGUIController);
        userProfileGUIController.setAll(userBean);
        MangeJoined.adjJoinedList(userBean,userProfileGUIController);
    }

}
