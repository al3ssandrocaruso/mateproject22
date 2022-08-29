package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.request.JFX1ClubRequestPageGUIController;
import kapta.engineering.ManageRequest;
import kapta.utils.bean.ClubBean;
import kapta.utils.exception.myexception.SystemException;

public class JFX1ClubRequestPageSetter {

    private JFX1ClubRequestPageSetter(){
        //ignored
    }

    public static  void setter(ClubBean clubBean, JFX1ClubRequestPageGUIController crpgc) throws SystemException {
        ManageRequest.setRequestListClub(clubBean,crpgc);
    }
}
