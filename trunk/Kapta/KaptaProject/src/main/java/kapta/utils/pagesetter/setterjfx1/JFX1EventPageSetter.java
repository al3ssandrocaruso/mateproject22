package kapta.utils.pagesetter.setterjfx1;


import kapta.control.guicontroller.interfaceone.JFX1EventPageGUIController;
import kapta.engineering.MangeParticipant;
import kapta.utils.bean.jfx1.JFX1ClubBean;
import kapta.utils.bean.jfx1.JFX1EventBean;
import kapta.utils.exception.myexception.SystemException;

public class JFX1EventPageSetter {

    private JFX1EventPageSetter(){
        //ignore
    }

    public static void setter(JFX1EventBean eventBean, JFX1EventPageGUIController epgc) throws SystemException {
        JFX1ClubBean clubBeanCreator = new JFX1ClubBean( MangeParticipant.setParticipantListClub(eventBean,epgc));
        epgc.setAll(eventBean,  clubBeanCreator);
    }

}
