package kapta.utils.pagesetter.setterjfx2;

import kapta.control.guicontroller.interfacetwo.JFX2ConcludeSubmitGUIController;
import kapta.utils.bean.jfx2.JFX2ClubBean;
import kapta.utils.bean.jfx2.JFX2UserBean;
import kapta.utils.exception.myexception.EmailValidatorException;
import kapta.utils.exception.myexception.InputNullException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class JFX2RegisterSetter {

    private JFX2RegisterSetter(){
        //ignore
    }

    public static void setter(String[] preInfo, int type, JFX2ConcludeSubmitGUIController jfx2ConcludeSubmitGUIController) throws MalformedURLException, URISyntaxException, InputNullException, EmailValidatorException {
        if(type == 0){
            JFX2UserBean jfx2UserBean = new JFX2UserBean(null, preInfo[2], null, preInfo[0], preInfo[1], preInfo[3]);
            jfx2ConcludeSubmitGUIController.setAll(jfx2UserBean, type);
        } else {
            JFX2ClubBean jfx2ClubBean = new JFX2ClubBean(null, preInfo[4], null, preInfo[0], preInfo[1], preInfo[2], preInfo[3]);
            jfx2ConcludeSubmitGUIController.setAll(jfx2ClubBean, type);
        }
    }
}
