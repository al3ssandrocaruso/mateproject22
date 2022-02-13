package kapta.control.appcontroller;

import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanin.PartyBean;
import kapta.utils.dao.PartyDao;


import static kapta.utils.session.ThreadLocalSession.userSession;

public class CreatePartyController {
    public void createAndJoinParty(PartyBean partyBean) {
        UserModel creat = userSession.get().getUserModel();
        PartyModel pm = new PartyModel(partyBean.getName(),0, partyBean.getAddress(),partyBean.getDuration(),partyBean.getOrario(),partyBean.getDate(),  partyBean.getImage(), creat);
        //this PartyModel is the generic of the two interfaces 1 and 2
        pm.setStatus(0);
        pm.setId(PartyDao.getIdByPartyName(pm.getName()));
        PartyDao.saveNewParty(pm);
        pm.setId(PartyDao.getIdByPartyName(pm.getName()));
        JoinPartyController.joinParty(userSession.get().getUserModel(), pm);
        }

}