package kapta.engineering;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserModel;
import kapta.utils.Observer;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.exception.myexception.SystemException;

public class ManageFollowerFollowingList {
    private  ManageFollowerFollowingList(){

    }

    public static void  refreshList(UserBean userBean, Observer obs) throws SystemException {
        UserModel userModel = UserDao.getUserByUsername(userBean.getUsername());
        new FollowerList(userModel, FollowerListDao.getFollower(userModel),obs);

    }
    public static void setFollowerListP(GenericUserBean userBean, Observer obs) throws SystemException {
        UserModel userModel = UserDao.getUserById(userBean.getId());
        new FollowerList(userModel, FollowerListDao.getFollower(userModel),obs);
    }

    public static void setFollowingListP(GenericUserBean userBean, Observer obs) throws SystemException {
        UserModel userModel = UserDao.getUserById(userBean.getId());
        new FollowingList(userModel, FollowingListDao.getFollowing(userModel),obs);
    }

}


