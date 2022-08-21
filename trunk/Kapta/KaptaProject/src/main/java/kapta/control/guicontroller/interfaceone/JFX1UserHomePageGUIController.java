package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import kapta.control.guicontroller.interfaceone.item.JFX1EventItemGUIController;
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.engineering.ManageSearch;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.UserBean;
import kapta.utils.bean.jfx1.JFX1EventBean;
import kapta.utils.bean.jfx1.JFX1PartyBean;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.Observer;
import kapta.utils.utils.UpdateHandlerUno;

import java.io.IOException;


public class JFX1UserHomePageGUIController implements Observer {

    @FXML
    public ListView<Pane> listView;

    @FXML
    private TextField searchBar;



    @FXML
    void search()  {
        String input = searchBar.getText();
        ManageSearch.search(this, input);
    }

    @Override
    public void update(Object ob)  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;

        if(ob instanceof UserBean userBean) {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            JFX1UserBean jfx1UserBean = new JFX1UserBean(userBean);
            JFX1UserItemGUIController uigc = fxmlLoader.getController();
            uigc.setAll(jfx1UserBean);
            this.listView.getItems().add(pane);
        }
        // ee non errore ma da notare ...
        if(ob instanceof PartyBean partyBean){
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1PartyItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1PartyBean partyBean1 = new JFX1PartyBean(partyBean);
            UpdateHandlerUno.handler(fxmlLoader, partyBean1);
            this.listView.getItems().add(pane);
        }
        if(ob instanceof EventBean eventBean){
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1EventItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1EventItemGUIController pigc = fxmlLoader.getController();
            JFX1EventBean jfx1EventBean = new JFX1EventBean(eventBean);
            pigc.setAll(jfx1EventBean);
            this.listView.getItems().add(pane);
        }
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //devo implementarlo ma non mi serve, quindi lo lascio vuoto
    }

    @FXML
    void gotoCreatePartyAction(ActionEvent ae) {

        ReplaceSceneAndInitializePage replaceSceneAndInitializePage = new ReplaceSceneAndInitializePage();
        replaceSceneAndInitializePage.replaceSceneAndInitializePage(ae,"/JFX1/JFX1UserCreateParty.fxml");
    }

}


