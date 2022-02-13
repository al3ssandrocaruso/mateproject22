package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import kapta.application.FollowerFollowingListApplicationLayer;
import kapta.application.UserProfileApplicationLayer;
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;
import kapta.utils.Observer;
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

    private FollowerFollowingListApplicationLayer followerFollowingListApplicationLayer;

    public void setFollowerFollowingListApplication(FollowerFollowingListApplicationLayer followerFollowingListApplicationLayer) {
        this.followerFollowingListApplicationLayer = followerFollowingListApplicationLayer;
    }

    public AnchorPane getApFollowing() {return apFollowing;}

    public AnchorPane getApFollower() {return apFollower;}

    public void actionBackToProfile(ActionEvent ae){
        followerFollowingListApplicationLayer.navigationBack(ae);
    }

    public void swapToFollower() {
        followerFollowingListApplicationLayer.navigationFollower();
    }

    public void swapToFollowing() {
        followerFollowingListApplicationLayer.navigationFollowing();
    }

    @Override
    public void update(Object ob) {
        //update
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        UserModel userModel = (UserModel) ob;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        try {
            pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserItem.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFX1UserItemGUIController uigc = fxmlLoader.getController();

        JFX1UserBeanOut jfx1UserBeanOut = new JFX1UserBeanOut(userModel);
        UserProfileApplicationLayer userProfileApplicationLayer = new UserProfileApplicationLayer(uigc,  userModel);
        uigc.setAll(jfx1UserBeanOut, userProfileApplicationLayer);
        if(from instanceof FollowerList){
            listViewFollower.getItems().add(pane);
        }
        if(from instanceof FollowingList){
            listViewFollowing.getItems().add(pane);

        }

    }
}
