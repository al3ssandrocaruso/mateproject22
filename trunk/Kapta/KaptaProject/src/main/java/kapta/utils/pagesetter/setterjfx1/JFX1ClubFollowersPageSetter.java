package kapta.utils.pagesetter.setterjfx1;

import kapta.model.lists.FollowerList;
import kapta.model.profiles.ClubModel;
import kapta.utils.dao.listdao.FollowerListDao;

import kapta.utils.Observer;


public class JFX1ClubFollowersPageSetter {

    private JFX1ClubFollowersPageSetter(){
        //ignored
    }

    public static void setter(ClubModel clubModel, Object controller) {
        new FollowerList(clubModel, FollowerListDao.getFollower(clubModel), (Observer) controller);
    }
}
