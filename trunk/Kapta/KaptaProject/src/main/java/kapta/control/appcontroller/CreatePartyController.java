package kapta.control.appcontroller;

import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.PartyEventSchedule;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.UserDao;
import kapta.utils.session.ThreadLocalSession;

public class CreatePartyController {
    public void createAndJoinParty(PartyBean partyBean) {
        UserModel creat = UserDao.getUserById( ThreadLocalSession.getUserSession().get().getUserBean().getId());
        PartyEventSchedule partyEventSchedule = new PartyEventSchedule(partyBean.getDate(), partyBean.getDuration(), partyBean.getOrario());
        PartyModel pm = new PartyModel(partyBean.getName(),0, partyBean.getAddress(),  partyBean.getImage(), creat,partyEventSchedule  );
        //this PartyModel is the generic of the two interfaces 1 and 2
        pm.setStatus(0);
        PartyDao.saveNewParty(pm);
        pm.setId(PartyDao.getIdByPartyName(pm.getName()));
        System.out.println("party id ==>"+pm.getId());
        partyBean.setId(pm.getId());
        JoinPartyController.joinParty( ThreadLocalSession.getUserSession().get().getUserBean(), partyBean);
        }

}