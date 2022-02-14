package kapta.control.appcontroller;

import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanin.PartyBean;
import kapta.utils.bean.beanin.PartyEventSchedule;
import kapta.utils.dao.PartyDao;
import kapta.utils.session.ThreadLocalSession;

public class CreatePartyController {
    public void createAndJoinParty(PartyBean partyBean) {
        UserModel creat =  ThreadLocalSession.getUserSession().get().getUserModel();
        PartyEventSchedule partyEventSchedule = new PartyEventSchedule(partyBean.getDate(), partyBean.getDuration(), partyBean.getOrario());
        PartyModel pm = new PartyModel(partyBean.getName(),0, partyBean.getAddress(),  partyBean.getImage(), creat,partyEventSchedule  );
        //this PartyModel is the generic of the two interfaces 1 and 2
        pm.setStatus(0);
        pm.setId(PartyDao.getIdByPartyName(pm.getName()));
        PartyDao.saveNewParty(pm);
        pm.setId(PartyDao.getIdByPartyName(pm.getName()));
        JoinPartyController.joinParty( ThreadLocalSession.getUserSession().get().getUserModel(), pm);
        }

}