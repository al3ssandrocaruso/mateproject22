package kapta.control.guicontroller.interfacetwo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import kapta.utils.Observer;
import kapta.utils.bean.RequestBean;
import kapta.utils.bean.jfx2.JFX2RequestBean;

import java.io.IOException;

public class JFX2UserRequestPageGUIController implements Observer {
    @FXML
    private ListView<Pane> listViewAllRequests;

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane anchorPane = null;
        RequestBean requestBean = (RequestBean) ob;
        JFX2RequestBean jfx2RequestBean=new JFX2RequestBean(requestBean);
        if(jfx2RequestBean.getStatus()==0){ //pending
            try {
                anchorPane = fxmlLoader.load(getClass().getResource("/JFX2/JFX2UserPendingRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2UserPendingRequestGUIController pR =fxmlLoader.getController();
            pR.setAll(jfx2RequestBean);
        }
        else if(jfx2RequestBean.getStatus()==1){
            try {
                anchorPane  = fxmlLoader.load(getClass().getResource("/JFX2/JFX2UserAcceptedRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2UserAcceptedRequestItemGUIController aR =fxmlLoader.getController();
            aR.setAll(jfx2RequestBean);
        }
        else if (jfx2RequestBean.getStatus()==2){
            try {
                anchorPane  = fxmlLoader.load(getClass().getResource("/JFX2/JFX2UserRejectedRequestItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX2UserRejectedRequestItemGUIController rr =fxmlLoader.getController();
            rr.setAll(jfx2RequestBean);
        }
        this.listViewAllRequests.getItems().add(anchorPane);
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //devo implementarlo ma non mi serve, quindi lo lascio vuoto
    }
}
