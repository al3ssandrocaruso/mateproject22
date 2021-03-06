package kapta.utils.pagesetter.setterjfx2;

import kapta.application.EventApplicationLayer;
import kapta.application.PartyApplicationLayer;
import kapta.control.guicontroller.interfacetwo.JFX2PartyEventPageGUIController;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.utils.bean.beanout.jfx1.JFX1EventBeanOut;
import kapta.utils.bean.beanout.jfx1.JFX1PartyBeanOut;
import kapta.utils.dao.listdao.ParticipantListDao;


public class JFX2PartyEventPageSetter {

    private JFX2PartyEventPageSetter(){
        //ignore
    }

    public static  void setter(Object ob, JFX2PartyEventPageGUIController controller)  {
        if(ob instanceof PartyModel partyModel) {
            JFX1PartyBeanOut partyBeanOut=new JFX1PartyBeanOut(partyModel);
            PartyApplicationLayer partyApplication=new PartyApplicationLayer(partyModel);
            partyApplication.setParticipantsList(ParticipantListDao.getParticipantList(partyModel, controller));
            controller.setAllParty(partyBeanOut,partyApplication);
        }
        else if(ob instanceof EventModel eventModel){
            JFX1EventBeanOut eventBeanOut=new JFX1EventBeanOut(eventModel);
            EventApplicationLayer eventApplication= new EventApplicationLayer(eventModel);
            ParticipantListDao.getParticipantList(eventModel, controller);
            controller.setAllEvent(eventBeanOut,eventApplication);
        }
    }
}
