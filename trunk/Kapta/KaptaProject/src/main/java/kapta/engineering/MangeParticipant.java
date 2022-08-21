package kapta.engineering;

import kapta.model.lists.ParticipantsList;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.UserDao;

public class MangeParticipant {
    ParticipantsList pl ;

    public MangeParticipant(ParticipantsList pl){
        this.pl=pl;
    }

    public void addParticipant(UserBean user){
        UserModel userModel = UserDao.getUserByUsername(user.getUsername());
        pl.addParticipant(userModel);
    }

}
