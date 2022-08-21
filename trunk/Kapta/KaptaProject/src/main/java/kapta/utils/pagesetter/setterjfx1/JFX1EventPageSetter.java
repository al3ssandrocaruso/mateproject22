package kapta.utils.pagesetter.setterjfx1;


import kapta.control.guicontroller.interfaceone.JFX1EventPageGUIController;
import kapta.model.EventModel;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.J1.JFX1ClubBean;
import kapta.utils.bean.J1.JFX1EventBean;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.listdao.ParticipantListDao;

public class JFX1EventPageSetter {

    private JFX1EventPageSetter(){
        //ignore
    }

    public static void setter(JFX1EventBean eventBean, JFX1EventPageGUIController epgc)  {
        EventModel eventModel2 = EventDao.getEventbyEventId(eventBean.getEventId());
        assert eventModel2 != null;
        ClubModel clubModel= eventModel2.getEventCreator();
        JFX1ClubBean clubBeanCreator = new JFX1ClubBean(clubModel);
        epgc.setAll(eventBean, clubBeanCreator);
        ParticipantListDao.getParticipantList(eventModel2, epgc);
    }

}
