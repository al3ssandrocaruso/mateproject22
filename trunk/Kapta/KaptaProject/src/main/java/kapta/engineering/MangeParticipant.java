package kapta.engineering;

import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.lists.ParticipantsList;
import kapta.model.profiles.ClubModel;
import kapta.model.profiles.UserModel;
import kapta.utils.Observer;
import kapta.utils.bean.ClubBean;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.UserBean;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.ParticipantListDao;
import kapta.utils.exception.myexception.SystemException;

public class MangeParticipant {
    ParticipantsList pl ;

    public MangeParticipant(ParticipantsList pl){
        this.pl=pl;
    }

    public void addParticipant(UserBean user) throws SystemException {
        UserModel userModel = UserDao.getUserByUsername(user.getUsername());
        pl.addParticipant(userModel);
    }


    public static ClubBean setParticipantListClub(EventBean eventBean , Observer obs) throws SystemException {
        EventModel eventModel2 = EventDao.getEventbyEventId(eventBean.getEventId());
        ParticipantListDao.getParticipantList(eventModel2, obs);
        ClubModel clubModel= eventModel2.getEventCreator();
        return  new ClubBean(clubModel);
    }

    public static UserBean setParticipantListParty(PartyBean partyBean , Observer obs) throws SystemException {
        PartyModel partyModel = PartyDao.getPartyById(partyBean.getId());
        ParticipantListDao.getParticipantList(partyModel, obs);
        UserModel userModel= partyModel.getPartyCreator();
        return  new UserBean(userModel);
    }

}
