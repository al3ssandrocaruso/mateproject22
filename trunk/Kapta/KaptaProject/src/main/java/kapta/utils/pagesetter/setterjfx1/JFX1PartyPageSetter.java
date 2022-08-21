package kapta.utils.pagesetter.setterjfx1;


import kapta.control.guicontroller.interfaceone.JFX1PartyPageGUIController;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.J1.JFX1PartyBean;
import kapta.utils.bean.J1.JFX1UserBean;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.listdao.ParticipantListDao;


public class JFX1PartyPageSetter {

    private JFX1PartyPageSetter(){
        //ignore
    }

    public static void setter(JFX1PartyBean partyBean, JFX1PartyPageGUIController ppgc) {

        // ee
        PartyModel partyModel = PartyDao.getPartyById(partyBean.getId());
        ParticipantListDao.getParticipantList(partyModel, ppgc);
        UserModel um = partyModel.getPartyCreator();
        JFX1UserBean creator = new JFX1UserBean(um);
        ppgc.setAll(partyBean,creator);
        ppgc.myStart();

    }
}
