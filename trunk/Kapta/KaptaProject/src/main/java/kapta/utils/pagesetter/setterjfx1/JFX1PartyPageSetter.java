package kapta.utils.pagesetter.setterjfx1;


import kapta.control.guicontroller.interfaceone.JFX1PartyPageGUIController;
import kapta.engineering.MangeParticipant;
import kapta.utils.bean.jfx1.JFX1PartyBean;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.exception.myexception.SystemException;


public class JFX1PartyPageSetter {

    private JFX1PartyPageSetter(){
        //ignore
    }

    public static void setter(JFX1PartyBean partyBean, JFX1PartyPageGUIController ppgc) throws SystemException {

        JFX1UserBean creator = new JFX1UserBean( MangeParticipant.setParticipantListParty(partyBean,ppgc));
        ppgc.setAll(partyBean,creator);
        ppgc.myStart();

    }
}
