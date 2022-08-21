package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.JFX1UserSettingsGUIController;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.jfx1.JFX1UserBean;

public class JFX1UserSettingPageSetter {

    private JFX1UserSettingPageSetter(){
        //ignored
    }

    public static  void setter(UserModel userModel, JFX1UserSettingsGUIController uspgc){
        JFX1UserBean userBean = new JFX1UserBean(userModel);
        uspgc.setAll(userBean);
    }
}
