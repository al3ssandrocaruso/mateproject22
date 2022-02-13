package kapta.utils.utils;

import javafx.fxml.FXMLLoader;
import kapta.application.PartyApplicationLayer;
import kapta.control.guicontroller.interfaceone.item.JFX1PartyItemGUIController;
import kapta.model.PartyModel;
import kapta.utils.bean.beanout.jfx1.JFX1PartyBeanOut;

public class UpdateHandlerUno {
    private UpdateHandlerUno(){
        //ignored
    }
    public static void handler(FXMLLoader fxmlLoader, PartyModel partyModel){
        JFX1PartyItemGUIController pigc = fxmlLoader.getController();
        JFX1PartyBeanOut jfx1PartyBeanOut = new JFX1PartyBeanOut(partyModel);
        PartyApplicationLayer pa = new PartyApplicationLayer(partyModel, pigc);
        pigc.setAll(jfx1PartyBeanOut, pa);
    }
}
