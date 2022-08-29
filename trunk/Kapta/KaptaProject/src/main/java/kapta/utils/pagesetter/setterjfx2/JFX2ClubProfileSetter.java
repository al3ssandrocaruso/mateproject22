package kapta.utils.pagesetter.setterjfx2;

import kapta.control.guicontroller.interfacetwo.JFX2ClubProfileGUIController;
import kapta.engineering.ManageCreated;
import kapta.engineering.ManageRequest;
import kapta.utils.bean.jfx2.JFX2ClubBean;
import kapta.utils.exception.myexception.SystemException;


public class JFX2ClubProfileSetter {

    private JFX2ClubProfileSetter(){
        //ignore
    }

    public static void setter(JFX2ClubBean jfx2ClubBean, JFX2ClubProfileGUIController clubProfileGUIController) throws SystemException {


        ManageRequest.setRequestListClub(jfx2ClubBean, clubProfileGUIController);
        ManageCreated.manageCreated(jfx2ClubBean,clubProfileGUIController);
        clubProfileGUIController.setAll(jfx2ClubBean);

    }
}
