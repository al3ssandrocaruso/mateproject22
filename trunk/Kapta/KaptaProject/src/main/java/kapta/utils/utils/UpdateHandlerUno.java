package kapta.utils.utils;

import javafx.fxml.FXMLLoader;
import kapta.control.guicontroller.interfaceone.item.JFX1PartyItemGUIController;
import kapta.utils.bean.J1.JFX1PartyBean;


public class UpdateHandlerUno {
    private UpdateHandlerUno(){
        //ignored
    }
    public static void handler(FXMLLoader fxmlLoader, JFX1PartyBean partyBean){
        JFX1PartyItemGUIController pigc = fxmlLoader.getController();
        pigc.setAll(partyBean);
    }
}
