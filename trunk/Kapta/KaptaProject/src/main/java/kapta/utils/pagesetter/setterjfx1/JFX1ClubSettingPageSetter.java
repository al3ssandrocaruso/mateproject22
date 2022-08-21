package kapta.utils.pagesetter.setterjfx1;

import kapta.control.guicontroller.interfaceone.JFX1ClubSettingGUIController;
import kapta.model.profiles.ClubModel;
import kapta.utils.bean.J1.JFX1ClubBean;

public class JFX1ClubSettingPageSetter {

    private JFX1ClubSettingPageSetter(){
        //ignored
    }

    public static  void setter(ClubModel cm, JFX1ClubSettingGUIController csgc){
        JFX1ClubBean jfx1ClubBean = new JFX1ClubBean(cm);
        csgc.setAll(jfx1ClubBean);
    }
}
