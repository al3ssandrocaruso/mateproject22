package kapta.utils.pagesetter.setterjfx1;


import kapta.control.guicontroller.interfaceone.JFX1FollowerFollowingListGuiController;
import kapta.engineering.ManageFollowerFollowingList;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.exception.myexception.SystemException;

public class JFX1FollowerFollowingPageSetter {

    private JFX1FollowerFollowingPageSetter(){
        //ignore
    }

    public static void setter(GenericUserBean ownerBean, JFX1FollowerFollowingListGuiController fflgc) throws SystemException {

        ManageFollowerFollowingList.setFollowerListP(ownerBean, fflgc);
        ManageFollowerFollowingList.setFollowingListP(ownerBean,fflgc);
        fflgc.setOwner(ownerBean);

    }
}
