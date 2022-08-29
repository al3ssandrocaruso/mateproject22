package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.JFX1UserSettingsGUIController;
import kapta.utils.bean.jfx1.JFX1UserBean;

public class JFX1UserSettingPageSetter {

    private JFX1UserSettingPageSetter(){
        //ignored
    }

    public static  void setter(JFX1UserBean userBean, JFX1UserSettingsGUIController uspgc){
        uspgc.setAll(userBean);
    }
}
