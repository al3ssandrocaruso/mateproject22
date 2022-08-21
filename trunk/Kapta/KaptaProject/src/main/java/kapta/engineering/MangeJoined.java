package kapta.engineering;

import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.Observer;
import kapta.utils.bean.UserBean;
import kapta.utils.bean.jfx2.JFX2PartyBean;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.JoinedListDAO;
import kapta.utils.dao.listdao.ParticipantListDao;
import kapta.utils.session.ThreadLocalSession;

import java.util.List;

public class MangeJoined {

    private MangeJoined(){

    }

    public  static void adjJoinedList(UserBean us , Observer ob){
        UserModel um = UserDao.getUserById(us.getId());
        JoinedListDAO.getJoined(um, ob);
    }

    public static boolean doIjoinedYet(JFX2PartyBean partyBean) { //fatta funzione in utils, sostituire con quella
        boolean state = false;

        PartyModel partyModel = PartyDao.getPartyById(partyBean.getId());
        List<UserModel> list = ParticipantListDao.getParticipantList(partyModel, null).getParticipants();

        for(UserModel um : list) {
            if ( ThreadLocalSession.getUserSession().get().getUserBean().getId() == um.getId()) {
                state = true;
            }
        }
        return state;
    }

}
