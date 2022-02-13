package kapta.utils.pagesetter.setterjfx2;

import kapta.application.UserProfileApplicationLayer;
import kapta.control.guicontroller.interfacetwo.JFX2UserProfileGUIController;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanout.jfx2.JFX2UserBeanOut;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;

public class JFX2UserProfileSetter {

    private JFX2UserProfileSetter(){
        //ignore
    }

    public static void setter(UserModel userModel, JFX2UserProfileGUIController userProfileGUIController) {
        FollowerList followerList=new FollowerList(userModel, FollowerListDao.getFollower(userModel),userProfileGUIController);
        FollowingList followingList=new FollowingList(userModel, FollowingListDao.getFollowing(userModel),userProfileGUIController);

        JFX2UserBeanOut jfx2UserBeanOut = new JFX2UserBeanOut(userModel);
        UserProfileApplicationLayer userProfileApplicationLayer = new UserProfileApplicationLayer(userProfileGUIController, userModel);

        userProfileApplicationLayer.setFollowingList(followingList);
        userProfileApplicationLayer.setFollowerList(followerList);

        userProfileGUIController.setAll(jfx2UserBeanOut, userProfileApplicationLayer);


    }
}
