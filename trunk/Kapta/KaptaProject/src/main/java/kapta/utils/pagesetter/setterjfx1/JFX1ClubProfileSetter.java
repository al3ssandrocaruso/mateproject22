package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.JFX1ClubProfileGUIcontroller;
import kapta.engineering.ManageCreated;
import kapta.utils.bean.jfx1.JFX1ClubBean;
import kapta.utils.exception.myexception.SystemException;



public class JFX1ClubProfileSetter {

    private JFX1ClubProfileSetter(){
        //ignore
    }



    public static  void setter(JFX1ClubBean clubBean, JFX1ClubProfileGUIcontroller jfx1ClubProfileGUIController) throws SystemException {
            ManageCreated.manageCreated(clubBean,jfx1ClubProfileGUIController);
            jfx1ClubProfileGUIController.setAll(clubBean);
    }
}
