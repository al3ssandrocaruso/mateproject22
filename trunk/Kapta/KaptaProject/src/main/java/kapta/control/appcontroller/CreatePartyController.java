package kapta.control.appcontroller;

import kapta.model.PartyEventModel;
import kapta.model.PartyModel;
import kapta.model.lists.JoinedList;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.PartyEventSchedule;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.UserDao;
import kapta.utils.dao.listdao.JoinedListDAO;
import kapta.utils.exception.myexception.BusyForANewPartyException;
import kapta.utils.exception.myexception.PartyExpiredException;
import kapta.utils.exception.myexception.SystemException;
import kapta.utils.mysession.ThreadLocalSession;

import java.time.LocalDate;
import java.util.Date;

public class CreatePartyController {

    private CreatePartyController(){

    }

    public static void createAndJoinParty(PartyBean partyBean) throws SystemException, PartyExpiredException, BusyForANewPartyException {
        UserModel creat = UserDao.getUserById( ThreadLocalSession.getUserSession().get().getUserBean().getId());
        PartyEventSchedule partyEventSchedule = new PartyEventSchedule(partyBean.getDate(), partyBean.getDuration(), partyBean.getOrario());
        JoinedList joinedList= JoinedListDAO.getJoined(creat,null);
        PartyModel pm = new PartyModel(partyBean.getName(),0, partyBean.getAddress(),  partyBean.getImage(), creat,partyEventSchedule  );
        //this PartyModel is the generic of the two interfaces 1 and 2
        pm.setStatus(0);
        for(PartyEventModel pmList : joinedList.getPartyEventJoined()){
            if(pmList.getDate().toString().equals(pm.getDate().toString())){

                throw new BusyForANewPartyException(pm.getDate().toString());
            }
        }
        if(pm.getDate() != null) {
            Date dt = pm.getDate();
            LocalDate today = LocalDate.now();
            java.sql.Date tdy = java.sql.Date.valueOf(today);

            if (dt.compareTo(tdy) < 0) {
                throw new PartyExpiredException();
            }
        }
        PartyDao.saveNewParty(pm);
        pm.setId(PartyDao.getIdByPartyName(pm.getName()));
        partyBean.setId(pm.getId());
        JoinPartyController.joinParty( ThreadLocalSession.getUserSession().get().getUserBean(), partyBean);
        }

}