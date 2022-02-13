package kapta.utils.pagesetter.setterjfx1;

import kapta.application.UserProfileApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1UserProfileGuiController;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.dao.listdao.JoinedListDAO;

public class JFX1UserProfileSetter {
    public static void setter(UserModel userModel, JFX1UserProfileGuiController jfx1UserProfileGuiController) {

        FollowerList followerList=new FollowerList(userModel, FollowerListDao.getFollower(userModel),jfx1UserProfileGuiController);
        FollowingList followingList=new FollowingList(userModel, FollowingListDao.getFollowing(userModel),jfx1UserProfileGuiController);

        UserProfileApplicationLayer userProfileApplication = new UserProfileApplicationLayer(jfx1UserProfileGuiController, userModel);
        JFX1UserBeanOut userBeanOut= new JFX1UserBeanOut(userModel);

        jfx1UserProfileGuiController.setAll(userBeanOut, userProfileApplication); // layer application

        //set di application
        userProfileApplication.setFollowingList(followingList);
        userProfileApplication.setFollowerList(followerList);
        JoinedListDAO.getJoined(userModel, jfx1UserProfileGuiController);

    }
}
