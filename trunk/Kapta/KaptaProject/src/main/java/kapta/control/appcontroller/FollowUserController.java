package kapta.control.appcontroller;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.session.ThreadLocalSession;


public class FollowUserController {

    private FollowUserController(){
        //ignored
    }

   public static void follow(GenericUserBean visitedUserBean, GenericUserBean userBean) {


        UserModel user = UserDao.getUserById(userBean.getId());

        UserModel visitedUser = UserDao.getUserById(visitedUserBean.getId());

        FollowerListDao.addToFollowerList(visitedUser, user);
        FollowingListDao.addToFollowingList(user, visitedUser);
        // eee lo voglio come parametro
        UserDao.getUserById(ThreadLocalSession.getUserSession().get().getUserBean().getId());

    }


    public static void unfollow(GenericUserBean visitedUserBean, GenericUserBean userBean)  {

        UserModel user = UserDao.getUserById(userBean.getId());

        UserModel visitedUserModel = UserDao.getUserById(visitedUserBean.getId());

        FollowerListDao.removeFromFollowerList(visitedUserModel,user);
        FollowingListDao.removeFromFollowingList(user, visitedUserModel);
        // eee lo voglio come parametro
        UserDao.getUserById(ThreadLocalSession.getUserSession().get().getUserBean().getId());

    }
}
