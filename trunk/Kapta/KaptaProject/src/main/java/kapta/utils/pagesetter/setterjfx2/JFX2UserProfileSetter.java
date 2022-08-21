package kapta.utils.pagesetter.setterjfx2;

import kapta.control.guicontroller.interfacetwo.JFX2UserProfileGUIController;
import kapta.engineering.ManageFollowerFollowingList;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserModel;

import kapta.utils.bean.jfx2.JFX2UserBean;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.dao.listdao.JoinedListDAO;

public class JFX2UserProfileSetter {

    private JFX2UserProfileSetter(){
        //ignore
    }

    public static void setter(JFX2UserBean userBean, JFX2UserProfileGUIController userProfileGUIController) {
        //eee
        UserModel userModel = UserDao.getUserByUsername(userBean.getUsername());
        FollowerList followerList=new FollowerList(userModel, FollowerListDao.getFollower(userModel),userProfileGUIController);
        FollowingList followingList=new FollowingList(userModel, FollowingListDao.getFollowing(userModel),userProfileGUIController);
        ManageFollowerFollowingList m = new ManageFollowerFollowingList( followingList, followerList);
        userProfileGUIController.setAll(userBean, m);
        JoinedListDAO.getJoined(userModel, userProfileGUIController);
    }

}
