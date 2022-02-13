package kapta.control.guicontroller.interfacetwo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import kapta.application.RequestApplicationLayer;
import kapta.model.RequestModel;
import kapta.utils.bean.beanout.jfx2.JFX2RequestBeanOut;
import kapta.utils.Observer;

import java.io.IOException;

public class JFX2UserRequestPageGUIController implements Observer {
    @FXML
    private ListView<Pane> listViewAllRequests;

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane anchorPane = null;
        RequestModel request = (RequestModel) ob;
        JFX2RequestBeanOut jfx2RequestBeanOut=new JFX2RequestBeanOut(request);
        if(request.getStatus()==0){ //pending
            try {
                anchorPane = fxmlLoader.load(getClass().getResource("/JFX2/JFX2UserPendingRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2UserPendingRequestGUIController pR =fxmlLoader.getController();
            RequestApplicationLayer requestApplicationLayer=new RequestApplicationLayer(request);
            pR.setAll(jfx2RequestBeanOut,requestApplicationLayer);
        }
        else if(request.getStatus()==1){
            try {
                anchorPane  = fxmlLoader.load(getClass().getResource("/JFX2/JFX2UserAcceptedRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2UserAcceptedRequestItemGUIController aR =fxmlLoader.getController();
            RequestApplicationLayer requestApplicationLayer=new RequestApplicationLayer(request);
            aR.setAll(jfx2RequestBeanOut,requestApplicationLayer);
        }
        else if (request.getStatus()==2){
            try {
                anchorPane  = fxmlLoader.load(getClass().getResource("/JFX2/JFX2UserRejectedRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2UserRejectedRequestItemGUIController rr =fxmlLoader.getController();
            RequestApplicationLayer requestApplicationLayer=new RequestApplicationLayer(request);
            rr.setAll(jfx2RequestBeanOut,requestApplicationLayer);
        }
        this.listViewAllRequests.getItems().add(anchorPane);
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //devo implementarlo ma non mi serve, quindi lo lascio vuoto
    }
}
