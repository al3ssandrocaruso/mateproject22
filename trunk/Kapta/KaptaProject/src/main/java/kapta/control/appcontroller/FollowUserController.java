package kapta.control.appcontroller;

import kapta.model.lists.FollowerList;
import kapta.model.profiles.UserModel;
import kapta.utils.dao.listdao.FollowerListDao;
import kapta.utils.dao.listdao.FollowingListDao;


import static kapta.utils.session.ThreadLocalSession.userSession;

public class FollowUserController {

    private FollowUserController(){
        //ignored
    }

    public static void follow(UserModel visitedUser, UserModel user, FollowerList followerList) {
            FollowerListDao.addToFollowerList(visitedUser, user);
            FollowingListDao.addToFollowingList(user, visitedUser);
            followerList.addUser(userSession.get().getUserModel());

    }

    public static void unfollow(UserModel visitedEventModel, UserModel user, FollowerList followerList)  {

        FollowerListDao.removeFromFollowerList(visitedEventModel,user);
        FollowingListDao.removeFromFollowingList(user, visitedEventModel);
        followerList.removeUser(userSession.get().getUserModel());
        }
}
