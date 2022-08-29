package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.utils.Observer;
import kapta.utils.bean.GenericListInfoBean;
import kapta.utils.bean.GenericUserBean;
import kapta.utils.bean.UserBean;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.mysession.ThreadLocalSession;

import java.io.IOException;

public class JFX1FollowerFollowingListGuiController implements Observer {
    @FXML
    private ListView<Pane> listViewFollower;
    @FXML
    private ListView<Pane> listViewFollowing;
    @FXML
    private AnchorPane apFollowing;
    @FXML
    private AnchorPane apFollower;


    private GenericUserBean owner;



    public void setOwner(GenericUserBean owner) {
        this.owner = owner;
    }



    public AnchorPane getApFollowing() {return apFollowing;}

    public AnchorPane getApFollower() {return apFollower;}

    public void actionBackToProfile(ActionEvent ae){
        navigationBack(ae);
    }

    public void swapToFollower() {
        this.getApFollowing().toBack();
        this.getApFollower().toFront();

    }

    public void swapToFollowing() {
        this.getApFollowing().toFront();
        this.getApFollower().toBack();
    }

    @Override
    public void update(Object ob) {
        //update
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        JFX1UserBean jfx1UserBean = new JFX1UserBean( (UserBean) ob);
        FXMLLoader fxmlLoader = new FXMLLoader();
        GenericListInfoBean b = (GenericListInfoBean) from;
        Pane pane = null;
        try {
            pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserItem.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFX1UserItemGUIController uigc = fxmlLoader.getController();

        uigc.setAll(jfx1UserBean);
        if(b.getType() ==1){
            listViewFollower.getItems().add(pane);
        }
        if(b.getType()==0){
            listViewFollowing.getItems().add(pane);

        }

    }

    private void navigationBack(ActionEvent actionEvent) {
        int typeMe= ThreadLocalSession.getUserSession().get().getType();
        ReplaceSceneAndInitializePage rp = new ReplaceSceneAndInitializePage();

        //Tutti i casi possibili: sono un utente e sto nel profilo di un altro utente, sto nel mio profilo, sto nel profilo di un club ...
        switch(typeMe){
            case 0:
                if(owner.getUsername().equals( ThreadLocalSession.getUserSession().get().getUserBean().getUsername())){
                    JFX1UserBean jfx1UserBean =new  JFX1UserBean(ThreadLocalSession.getUserSession().get().getUserBean());
                    rp.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1UserProfile.fxml", jfx1UserBean); }
                else {
                    rp.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1UserProfile.fxml", owner);
                }
                break;
            case 1:
                if(owner.getUsername().equals( ThreadLocalSession.getUserSession().get().getClubBean().getUsername())){
                    rp.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubProfile.fxml",  ThreadLocalSession.getUserSession().get().getClubBean());
                } else {
                    rp.replaceSceneAndInitializePage(actionEvent,"/JFX1/JFX1ClubProfile.fxml", owner);
                }
                break;
            default:
        }
    }

}
