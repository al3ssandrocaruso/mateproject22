package kapta.control.guicontroller.interfaceone.request;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import kapta.control.guicontroller.interfaceone.request.requestitem.user.JFX1AcceptedRequestItemGUIController;
import kapta.control.guicontroller.interfaceone.request.requestitem.user.JFX1PendingRequestItemGUIController;
import kapta.control.guicontroller.interfaceone.request.requestitem.user.JFX1RejectedRequestItemGUIController;
import kapta.utils.Observer;
import kapta.utils.bean.RequestBean;
import kapta.utils.bean.J1.JFX1RequestBean;

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
        JFX1RequestBean requestBean = new JFX1RequestBean((RequestBean) ob);

        if(requestBean.getStatus()==0){
            System.out.println("here status 000000 para evento ==> "+ requestBean.getEventNameOut() );
            try {
                anchorPane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserPendingRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1PendingRequestItemGUIController pR =fxmlLoader.getController();
            pR.setAll(requestBean);
            this.listViewPendingRequest.getItems().add(anchorPane );

        }
        else if(requestBean.getStatus()==1){
            System.out.println("here status 1111111 para evento ==> "+ requestBean.getEventNameOut() );
            try {
                anchorPane  = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserAcceptedRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1AcceptedRequestItemGUIController aR =fxmlLoader.getController();
            aR.setAll(requestBean);
            this.listViewAcceptedRequest.getItems().add(anchorPane);

        }
        else if (requestBean.getStatus()==2){
            System.out.println("here status 2222222 para evento ==> "+ requestBean.getEventNameOut() );
            try {
                anchorPane  = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserRejectedRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1RejectedRequestItemGUIController rr =fxmlLoader.getController();
            rr.setAll(requestBean);
            this.listViewRejectedRequest.getItems().add(anchorPane );

        }


    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //mi serve averla ma non necessita di implementazione
    }
}
