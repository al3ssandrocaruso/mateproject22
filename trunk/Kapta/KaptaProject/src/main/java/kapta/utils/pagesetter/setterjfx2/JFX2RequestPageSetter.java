package kapta.utils.pagesetter.setterjfx2;

import kapta.control.guicontroller.interfacetwo.JFX2UserRequestPageGUIController;
import kapta.engineering.ManageRequest;
import kapta.utils.bean.jfx2.JFX2UserBean;
import kapta.utils.exception.myexception.SystemException;

public class JFX2RequestPageSetter {

    private JFX2RequestPageSetter(){
        //ignore
    }

    public static void setter(JFX2UserBean userBean, JFX2UserRequestPageGUIController jfx2UserRequestPageGUIController) throws SystemException {
        ManageRequest.setRequestListUser(userBean,jfx2UserRequestPageGUIController);
    }
}
