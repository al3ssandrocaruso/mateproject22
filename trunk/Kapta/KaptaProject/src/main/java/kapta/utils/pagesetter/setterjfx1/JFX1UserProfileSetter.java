package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.JFX1UserProfileGuiController;
import kapta.engineering.ManageFollowerFollowingList;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.J1.JFX1UserBean;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.dao.listdao.JoinedListDAO;

public class JFX1UserProfileSetter {

    private JFX1UserProfileSetter(){
        //ignored
    }

    public static void setter(JFX1UserBean userBean, JFX1UserProfileGuiController jfx1UserProfileGuiController) {


        UserModel userModel = UserDao.getUserById(userBean.getId());

        //eee
        FollowerList followerList=new FollowerList(userModel, FollowerListDao.getFollower(userModel),jfx1UserProfileGuiController);
        //eee
        FollowingList followingList=new FollowingList(userModel, FollowingListDao.getFollowing(userModel),jfx1UserProfileGuiController);

        ManageFollowerFollowingList man = new  ManageFollowerFollowingList(followingList, followerList);

        jfx1UserProfileGuiController.setAll(userBean, man); // layer application

        //eeee
        JoinedListDAO.getJoined(userModel, jfx1UserProfileGuiController);

    }
}
