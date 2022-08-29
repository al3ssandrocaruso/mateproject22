package kapta.utils.pagesetter.setterjfx2;

import kapta.control.guicontroller.interfacetwo.JFX2PartyEventPageGUIController;
import kapta.engineering.MangeParticipant;
import kapta.utils.bean.jfx2.JFX2ClubBean;
import kapta.utils.bean.jfx2.JFX2EventBean;
import kapta.utils.bean.jfx2.JFX2PartyBean;
import kapta.utils.exception.myexception.SystemException;


public class JFX2PartyEventPageSetter {

    private JFX2PartyEventPageSetter(){
        //ignore
    }

    public static  void setter(Object ob, JFX2PartyEventPageGUIController controller) throws SystemException {
        if(ob instanceof JFX2PartyBean jfx2PartyBean) {
                MangeParticipant.setParticipantListParty(jfx2PartyBean, controller);
                controller.setAllParty(jfx2PartyBean, 0);
        }
        else if(ob instanceof JFX2EventBean eventBean){
            JFX2ClubBean own = new JFX2ClubBean( MangeParticipant.setParticipantListClub(eventBean,controller));
            controller.setAllEvent(eventBean,own, 1);
        }
    }
}
