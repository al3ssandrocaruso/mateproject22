package kapta.utils.pagesetter.setterjfx1;

import kapta.application.SettingsApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1UserSettingsGUIController;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;

public class JFX1UserSettingPageSetter {
    public static  void setter(UserModel userModel, JFX1UserSettingsGUIController uspgc){
        JFX1UserBeanOut userBeanOut = new JFX1UserBeanOut(userModel);
        SettingsApplicationLayer settingsApplicationLayer = new SettingsApplicationLayer(userModel.getId());
        uspgc.setAll(userBeanOut, settingsApplicationLayer);
    }
}
