package kapta.control.appcontroller;

import kapta.model.lists.FollowerList;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;
import kapta.utils.session.ThreadLocalSession;


public class FollowUserController {

    private FollowUserController(){
        //ignored
    }

    public static void follow(UserModel visitedUser, UserModel user, FollowerList followerList) {
            FollowerListDao.addToFollowerList(visitedUser, user);
            FollowingListDao.addToFollowingList(user, visitedUser);
            followerList.addUser( ThreadLocalSession.getUserSession().get().getUserModel());

    }

    public static void unfollow(UserModel visitedEventModel, UserModel user, FollowerList followerList)  {

        FollowerListDao.removeFromFollowerList(visitedEventModel,user);
        FollowingListDao.removeFromFollowingList(user, visitedEventModel);
        followerList.removeUser( ThreadLocalSession.getUserSession().get().getUserModel());
        }
}
