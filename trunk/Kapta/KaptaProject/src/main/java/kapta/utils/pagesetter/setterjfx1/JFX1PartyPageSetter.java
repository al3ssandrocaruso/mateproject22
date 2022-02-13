package kapta.utils.pagesetter.setterjfx1;

import kapta.application.PartyApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1PartyPageGUIController;
import kapta.model.lists.ParticipantsList;
import kapta.model.PartyModel;
import kapta.utils.bean.beanout.jfx1.JFX1PartyBeanOut;
import kapta.utils.dao.listdao.ParticipantListDao;


public class JFX1PartyPageSetter {

    private JFX1PartyPageSetter(){
        //ignore
    }

    public static void setter(PartyModel partyModel, JFX1PartyPageGUIController ppgc) {

        ParticipantsList participantsList =ParticipantListDao.getParticipantList(partyModel, ppgc);
        JFX1PartyBeanOut partyBeanOut = new JFX1PartyBeanOut(partyModel);

        PartyApplicationLayer partyApplication= new PartyApplicationLayer( partyModel, participantsList);
        ppgc.setAll(partyBeanOut, partyApplication);
        ppgc.myStart();

    }
}
