package kapta.engineering;

import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.UserDao;

public class ManageFollowerFollowingList {

    private FollowerList followerList;
    private FollowingList followingList;

    public ManageFollowerFollowingList(FollowingList followingList, FollowerList followerList){
        setFollowerList(followerList);
        setFollowingList(followingList);
    }


    public void setFollowerList(FollowerList followerList) {
        this.followerList = followerList;
    }

    public void setFollowingList(FollowingList followingList) {
        this.followingList = followingList;
    }

    public FollowerList getFollowerList() {
        return followerList;
    }

    public FollowingList getFollowingList() {
        return followingList;
    }

    public void removeUserFollowerList(UserBean userBean) {
        UserModel userModel = UserDao.getUserById(userBean.getId());
        followerList.removeUser(userModel);
    }

    public void removeUserFollowingList(UserBean userBean) {
        UserModel userModel = UserDao.getUserById(userBean.getId());
        followingList.removeParticipant(userModel);
    }

    public void addUserFollowerList(UserBean userBean){
        UserModel userModel = UserDao.getUserById(userBean.getId());
        followerList.addUser(userModel);
    }

    public void addUserFollowingList(UserBean userBean){
        UserModel userModel= UserDao.getUserById(userBean.getId());
        followingList.addParticipant(userModel);
    }

}


