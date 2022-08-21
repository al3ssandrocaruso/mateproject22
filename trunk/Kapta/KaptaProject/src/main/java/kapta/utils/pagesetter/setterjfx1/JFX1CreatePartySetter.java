package kapta.utils.pagesetter.setterjfx1;


import kapta.control.guicontroller.interfaceone.JFX1CreatePartyGUIController;
import kapta.utils.bean.J1.JFX1UserBean;

public class JFX1CreatePartySetter {

    private JFX1CreatePartySetter(){
        //ignore
    }

    public static void setter(JFX1CreatePartyGUIController jfx1CreatePartyGUIController, JFX1UserBean userBean){
        jfx1CreatePartyGUIController.setUserBean(userBean);
    }
}
