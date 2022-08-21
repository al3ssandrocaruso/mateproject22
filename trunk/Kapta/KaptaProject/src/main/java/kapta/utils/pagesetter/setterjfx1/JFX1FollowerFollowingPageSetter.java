package kapta.utils.pagesetter.setterjfx1;


import kapta.control.guicontroller.interfaceone.JFX1FollowerFollowingListGuiController;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;

public class JFX1FollowerFollowingPageSetter {

    private JFX1FollowerFollowingPageSetter(){
        //ignore
    }

    public static void setter(GenericUserBean ownerBean, JFX1FollowerFollowingListGuiController fflgc){


        UserModel owner = UserDao.getUserByUsername(ownerBean.getUsername());
        new FollowingList(owner, FollowingListDao.getFollowing(owner), fflgc);
        new FollowerList(owner, FollowerListDao.getFollower(owner), fflgc);
        fflgc.setOwner(ownerBean);

        //new FollowerFollowingListApplicationLayer(fflgc, support, userModel);
    }
}
