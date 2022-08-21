package kapta.control.guicontroller.interfaceone.request;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import kapta.control.guicontroller.interfaceone.request.requestitem.club.JFX1ClubRequestGreenPassItemGUIController;
import kapta.control.guicontroller.interfaceone.request.requestitem.club.JFX1ClubRequestItemGUIController;
import kapta.utils.Observer;
import kapta.utils.bean.RequestBean;
import kapta.utils.bean.jfx1.JFX1RequestBean;

import java.io.IOException;

public class JFX1ClubRequestPageGUIController implements Observer {
    @FXML
    private ListView<Pane> listViewRequests;

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        JFX1RequestBean jfx1RequestBean = new JFX1RequestBean((RequestBean) ob);
        if(!jfx1RequestBean.getEventBean().isGreenPass()) {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1ClubRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1ClubRequestItemGUIController crq = fxmlLoader.getController();
            crq.setAll(jfx1RequestBean);
            listViewRequests.getItems().add(pane);
        } else {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1RequestGreenPassItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1ClubRequestGreenPassItemGUIController crigc = fxmlLoader.getController();
            crigc.setAll(jfx1RequestBean);
            listViewRequests.getItems().add(pane);
        }
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //devo implementarlo ma non mi serve, quindi lo lascio vuoto
    }
}
