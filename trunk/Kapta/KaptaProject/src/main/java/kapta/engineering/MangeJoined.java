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
import kapta.utils.exception.myexception.SystemException;

import java.util.List;

public class MangeJoined {

    private MangeJoined(){

    }

    public  static void adjJoinedList(UserBean us , Observer ob) throws SystemException {
        UserModel um = UserDao.getUserById(us.getId());
        JoinedListDAO.getJoined(um, ob);
    }

    public static boolean doIjoinedYet(JFX2PartyBean partyBean, UserBean userBean)  { //fatta funzione in utils, sostituire con quella
        boolean state = false;

        try {
            PartyModel partyModel = PartyDao.getPartyById(partyBean.getId());
            List<UserModel> list = ParticipantListDao.getParticipantList(partyModel, null).getParticipants();
            for(UserModel um : list) {
                if ( userBean.getId() == um.getId()) {
                    state = true;
                }
            }

        } catch (SystemException systemException) {
            systemException.printStackTrace();
        }


        return state;
    }



}
