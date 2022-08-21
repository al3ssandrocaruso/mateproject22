package kapta.utils.pagesetter.setterjfx2;

import kapta.control.guicontroller.interfacetwo.JFX2PartyEventPageGUIController;
import kapta.engineering.MangeParticipant;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.jfx2.JFX2ClubBean;
import kapta.utils.bean.jfx2.JFX2EventBean;
import kapta.utils.bean.jfx2.JFX2PartyBean;
import kapta.utils.dao.ClubDao;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.PartyDao;
import kapta.utils.dao.listdao.ParticipantListDao;


public class JFX2PartyEventPageSetter {

    private JFX2PartyEventPageSetter(){
        //ignore
    }

    public static  void setter(Object ob, JFX2PartyEventPageGUIController controller)  {
        if(ob instanceof JFX2PartyBean jfx2PartyBean) {
            PartyModel pm = PartyDao.getPartyById(jfx2PartyBean.getId());
            MangeParticipant man = new MangeParticipant(ParticipantListDao.getParticipantList(pm, controller));
            controller.setAllParty(jfx2PartyBean, 0,man);
        }
        else if(ob instanceof JFX2EventBean eventBean){
            // here
            EventModel eventModel = EventDao.getEventbyEventId(eventBean.getEventId());
            ClubModel club= ClubDao.getClubByUserName(eventBean.getEventCreator());
            JFX2ClubBean clubBean = new JFX2ClubBean(club);
            ParticipantListDao.getParticipantList(eventModel, controller);
            controller.setAllEvent(eventBean,clubBean, 1);
        }
    }
}
