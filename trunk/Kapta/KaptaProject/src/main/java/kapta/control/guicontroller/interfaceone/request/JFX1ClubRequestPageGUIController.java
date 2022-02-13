package kapta.control.guicontroller.interfaceone.request;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import kapta.application.RequestApplicationLayer;
import kapta.control.guicontroller.interfaceone.request.requestitem.club.JFX1ClubRequestGreenPassItemGUIController;
import kapta.control.guicontroller.interfaceone.request.requestitem.club.JFX1ClubRequestItemGUIController;
import kapta.model.RequestModel;
import kapta.utils.bean.beanout.jfx1.JFX1RequestBeanOut;
import kapta.utils.Observer;

import java.io.IOException;

public class JFX1ClubRequestPageGUIController implements Observer {
    @FXML
    private ListView<Pane> listViewRequests;

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        RequestModel requestModel = (RequestModel) ob;
        JFX1RequestBeanOut jfx1RequestBeanOut = new JFX1RequestBeanOut(requestModel);
        RequestApplicationLayer requestApplicationLayer;
        if(!requestModel.getEvent().isGreenPass()) {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1ClubRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1ClubRequestItemGUIController crq = fxmlLoader.getController();
            requestApplicationLayer=new RequestApplicationLayer(requestModel);
            crq.setAll(jfx1RequestBeanOut,requestApplicationLayer);
            listViewRequests.getItems().add(pane);
        } else {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1RequestGreenPassItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1ClubRequestGreenPassItemGUIController crigc = fxmlLoader.getController();
            requestApplicationLayer=new RequestApplicationLayer(requestModel);
            crigc.setAll(jfx1RequestBeanOut,requestApplicationLayer);
            listViewRequests.getItems().add(pane);
        }
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //devo implementarlo ma non mi serve, quindi lo lascio vuoto
    }
}
