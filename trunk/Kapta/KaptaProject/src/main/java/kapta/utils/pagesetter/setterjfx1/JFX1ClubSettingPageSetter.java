package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.JFX1ClubSettingGUIController;
import kapta.utils.bean.jfx1.JFX1ClubBean;

public class JFX1ClubSettingPageSetter {

    private JFX1ClubSettingPageSetter(){
        //ignored
    }

    public static  void setter(JFX1ClubBean jfx1ClubBean, JFX1ClubSettingGUIController csgc){
        csgc.setAll(jfx1ClubBean);
    }
}
