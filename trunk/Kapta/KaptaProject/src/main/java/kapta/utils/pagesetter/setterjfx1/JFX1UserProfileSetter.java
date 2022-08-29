package kapta.utils.pagesetter.setterjfx1;
import kapta.control.guicontroller.interfaceone.JFX1UserProfileGuiController;
import kapta.engineering.ManageFollowerFollowingList;
import kapta.engineering.MangeJoined;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.exception.myexception.SystemException;

public class JFX1UserProfileSetter {

    private JFX1UserProfileSetter(){
        //ignored
    }

    public static void setter(JFX1UserBean userBean, JFX1UserProfileGuiController jfx1UserProfileGuiController) throws SystemException {

        ManageFollowerFollowingList.setFollowerListP(userBean,jfx1UserProfileGuiController);
        ManageFollowerFollowingList.setFollowingListP(userBean,jfx1UserProfileGuiController);
        jfx1UserProfileGuiController.setAll(userBean);
        MangeJoined.adjJoinedList(userBean,jfx1UserProfileGuiController);
    }
}
