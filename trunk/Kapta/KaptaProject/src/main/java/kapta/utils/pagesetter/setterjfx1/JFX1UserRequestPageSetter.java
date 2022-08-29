package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.request.JFX1UserRequestPageGUIController;
import kapta.engineering.ManageRequest;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.exception.myexception.SystemException;

public class JFX1UserRequestPageSetter {

    private JFX1UserRequestPageSetter(){
        //ignore
    }

    public static void setter(JFX1UserRequestPageGUIController jfx1UserRequestPageGUIController, JFX1UserBean pageOwner) throws SystemException {

        ManageRequest.setRequestListUser(pageOwner, jfx1UserRequestPageGUIController);
    }
}
