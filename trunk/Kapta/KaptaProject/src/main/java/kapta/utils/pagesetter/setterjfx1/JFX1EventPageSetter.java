package kapta.utils.pagesetter.setterjfx1;

import kapta.application.EventApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1EventPageGUIController;
import kapta.model.EventModel;
import kapta.utils.bean.beanout.jfx1.JFX1EventBeanOut;
import kapta.utils.dao.EventDao;
import kapta.utils.dao.listdao.ParticipantListDao;

public class JFX1EventPageSetter {
    public static void setter(EventModel eventModel, JFX1EventPageGUIController epgc)  {
        EventModel eventModel2 = EventDao.getEventbyEventId(eventModel.getId());
        JFX1EventBeanOut eventBeanOut = new JFX1EventBeanOut(eventModel2);
        EventApplicationLayer eventApplication = new EventApplicationLayer(eventModel2);
        epgc.setAll(eventBeanOut, eventApplication);
        ParticipantListDao.getParticipantList(eventModel2, epgc);
    }

}
