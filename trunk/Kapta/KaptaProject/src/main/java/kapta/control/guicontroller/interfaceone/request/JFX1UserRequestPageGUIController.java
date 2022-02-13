package kapta.control.guicontroller.interfaceone.request;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import kapta.application.RequestApplicationLayer;
import kapta.control.guicontroller.interfaceone.request.requestitem.user.JFX1AcceptedRequestItemGUIController;
import kapta.control.guicontroller.interfaceone.request.requestitem.user.JFX1PendingRequestItemGUIController;
import kapta.control.guicontroller.interfaceone.request.requestitem.user.JFX1RejectedRequestItemGUIController;
import kapta.model.RequestModel;
import kapta.utils.bean.beanout.jfx1.JFX1RequestBeanOut;
import kapta.utils.Observer;
import java.io.IOException;

public class JFX1UserRequestPageGUIController implements Observer {


    @FXML
    private ListView<Pane> listViewPendingRequest;

    @FXML
    private ListView<Pane> listViewAcceptedRequest;

    @FXML
    private ListView<Pane> listViewRejectedRequest;

    @Override
    public void update(Object ob)  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane anchorPane = null;
        RequestModel requestModel = (RequestModel) ob;
        JFX1RequestBeanOut jfx1RequestBeanOut = new JFX1RequestBeanOut(requestModel);
        if(requestModel.getStatus()==0){
            try {
                anchorPane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserPendingRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1PendingRequestItemGUIController pR =fxmlLoader.getController();
            RequestApplicationLayer requestApplicationLayer=new RequestApplicationLayer(requestModel);
            pR.setAll(jfx1RequestBeanOut,requestApplicationLayer);
            this.listViewPendingRequest.getItems().add(anchorPane );

        }
        else if(requestModel.getStatus()==1){
            try {
                anchorPane  = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserAcceptedRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1AcceptedRequestItemGUIController aR =fxmlLoader.getController();
            RequestApplicationLayer requestApplicationLayer=new RequestApplicationLayer(requestModel);
            aR.setAll(jfx1RequestBeanOut,requestApplicationLayer);

            this.listViewAcceptedRequest.getItems().add(anchorPane);

        }
        else if (requestModel.getStatus()==2){
            try {
                anchorPane  = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserRejectedRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1RejectedRequestItemGUIController rr =fxmlLoader.getController();
            RequestApplicationLayer requestApplicationLayer=new RequestApplicationLayer(requestModel);
            rr.setAll(jfx1RequestBeanOut,requestApplicationLayer);
            this.listViewRejectedRequest.getItems().add(anchorPane );

        }


    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //mi serve averla ma non necessita di implementazione
    }
}
