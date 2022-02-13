package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import kapta.application.EventApplicationLayer;
import kapta.application.UserProfileApplicationLayer;
import kapta.control.appcontroller.Search;
import kapta.control.guicontroller.interfaceone.item.JFX1EventItemGUIController;
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanout.jfx1.JFX1EventBeanOut;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;
import kapta.utils.GenericObservableList;
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
        GenericObservableList list = new GenericObservableList(this);
        list.addAllUsersToList(Search.searchUserByUsername(input));
        list.addAllEventsToList(Search.searchByEventName(input));
        list.addAllPartyToList(Search.searchByPartyName(input));
    }

    @Override
    public void update(Object ob)  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;

        if(ob instanceof UserModel userModel) {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            JFX1UserBeanOut jfx1UserBeanOut = new JFX1UserBeanOut(userModel);
            JFX1UserItemGUIController uigc = fxmlLoader.getController();
            UserProfileApplicationLayer userProfileApplicationLayer = new UserProfileApplicationLayer(uigc , userModel);
            uigc.setAll(jfx1UserBeanOut, userProfileApplicationLayer);
            this.listView.getItems().add(pane);
        }
        if(ob instanceof PartyModel partyModel){
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1PartyItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            UpdateHandlerUno.handler(fxmlLoader,partyModel);
            this.listView.getItems().add(pane);
        }
        if(ob instanceof EventModel eventModel){
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1EventItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1EventItemGUIController pigc = fxmlLoader.getController();

            JFX1EventBeanOut jfx1EventBeanOut = new JFX1EventBeanOut(eventModel);
            EventApplicationLayer ea = new EventApplicationLayer(eventModel);
            pigc.setAll(jfx1EventBeanOut,ea);
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


