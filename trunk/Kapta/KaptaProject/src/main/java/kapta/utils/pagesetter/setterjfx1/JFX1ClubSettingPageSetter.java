package kapta.utils.pagesetter.setterjfx1;

import kapta.application.SettingsApplicationLayer;
import kapta.control.guicontroller.interfaceone.JFX1ClubSettingGUIController;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.beanout.jfx1.JFX1ClubBeanOutSettings;

public class JFX1ClubSettingPageSetter {

    private JFX1ClubSettingPageSetter(){
        //ignored
    }

    public static  void setter(ClubModel cm, JFX1ClubSettingGUIController csgc){
        JFX1ClubBeanOutSettings jfx1ClubBeanOut = new JFX1ClubBeanOutSettings(cm.getUsername(), cm.getAddress(), cm.getCity(), cm.getPassword(), cm.getEmail(), cm.getWebsite(), cm.getProfileImg());
        SettingsApplicationLayer settingsApplicationLayer = new SettingsApplicationLayer(cm.getId());
        csgc.setAll(jfx1ClubBeanOut, settingsApplicationLayer);
    }
}
