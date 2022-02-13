package kapta.control.guicontroller.interfaceone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import kapta.application.EventApplicationLayer;
import kapta.application.UserProfileApplicationLayer;
import kapta.control.guicontroller.interfaceone.item.JFX1EventItemGUIController;
import kapta.model.EventModel;
import kapta.model.lists.FollowerList;
import kapta.model.lists.FollowingList;
import kapta.model.PartyModel;
import kapta.utils.bean.beanout.jfx1.JFX1EventBeanOut;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;
import kapta.utils.decorations.JFX1DecorationUserOne;
import kapta.utils.decorations.JFX1DecorationUserTwo;
import kapta.utils.Observer;
import kapta.utils.VisualComponent;
import kapta.utils.utils.UpdateHandlerUno;

import java.io.IOException;


public class JFX1UserProfileGuiController implements Observer {

    @FXML
    public ListView<Pane> listView;
    @FXML
    public Button btnFollowers;
    @FXML
    public ImageView imgProfile;
    @FXML
    private VBox root;
    @FXML
    private Button btnFollowing;
    @FXML
    private Label labelUserName;
    @FXML
    private Label labelName;
    @FXML
    private Label labelSecondName;

    private JFX1UserPanel jfx1UserPanel;
    private VBox addedVBox;
    private VisualComponent contents;
    private UserProfileApplicationLayer userProfileApplicationLayer;

    public void setUserProfileApplication(UserProfileApplicationLayer userProfileApplicationLayer) {this.userProfileApplicationLayer = userProfileApplicationLayer;}
    public void setLabelUserName(String username) {
        this.labelUserName.setText(username);
    }
    public void setBtnFollowers(String  numFollowers) {
        this.btnFollowers.setText(numFollowers);
    }
    public void setBtnFollowing(String numFollowing) {this.btnFollowing.setText(numFollowing);}
    public void setImageview(Image im ){this.imgProfile.setImage(im);}
    public void setLabelName(String labelName) {
        this.labelName.setText(labelName);
    }
    public void setLabelSecondName(String labelSecondName) {
        this.labelSecondName.setText(labelSecondName);
    }
    public void setContents(VisualComponent contents){this.contents= contents;}
    public void setRoot(VBox root) {
        this.root = root;
    }

    public VBox getRoot() {
        return root;
    }
    public Label getLabelUserName() {return this.labelUserName;}

    public void setAll(JFX1UserBeanOut jfx1UserBeanOut, UserProfileApplicationLayer userProfileApplicationLayer) {
        setUserProfileApplication(userProfileApplicationLayer);
        setLabelUserName("@" + jfx1UserBeanOut.getUsername());
        setLabelName(jfx1UserBeanOut.getName());
        setLabelSecondName(jfx1UserBeanOut.getSecondName());
        setImageview(jfx1UserBeanOut.getProfileImg());
        myStart();
    }

    public void myStart() {
        this.addedVBox=new VBox();
        this.jfx1UserPanel =new JFX1UserPanel(addedVBox); //new di Concrete Component
        this.setContents(this.jfx1UserPanel);

        display();

        int i = userProfileApplicationLayer.chooseDecoration();
        if(i==0){
            this.actionDecorateOne();
        }
        if(i==1){
            this.actionDecorateTwo();
        }
    }

    public void actionDecorateOne() {
        JFX1DecorationUserOne jfx1DecorationUserOne =new JFX1DecorationUserOne(this.jfx1UserPanel);
        this.setContents(jfx1DecorationUserOne);
        this.display();
    }

    public void actionDecorateTwo() {
        new JFX1DecorationUserTwo(this.jfx1UserPanel, this, userProfileApplicationLayer);
    }

    @Override
    public void update(Object ob)  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        if(ob instanceof PartyModel partyModel){
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1PartyItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            UpdateHandlerUno.handler(fxmlLoader,partyModel);
            this.listView.getItems().add(pane);
        }
        else if(ob instanceof EventModel eventModel) {
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1EventItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1EventItemGUIController eigc = fxmlLoader.getController();
            EventApplicationLayer ea = new EventApplicationLayer(eventModel);
            JFX1EventBeanOut jfx1EventBeanOut = new JFX1EventBeanOut(eventModel);
            eigc.setAll(jfx1EventBeanOut, ea);
            this.listView.getItems().add(pane);
        }
    }

    //Serve chiamarle così le liste così che non diano code smells (già c'è followerList e followingList)
    @Override
    public void updateFrom(Object ob,Object objectFrom) {
        if(objectFrom instanceof FollowerList followerList1){
            setBtnFollowers((followerList1).getSize().toString());
        }else if(objectFrom instanceof FollowingList followingList1){
            setBtnFollowing((followingList1).getSize().toString());
        }
    }

    public void display(){
        root.getChildren().remove(addedVBox);
        if (this.contents.addUserPanel() != null) {
            root.getChildren().add(this.contents.addUserPanel());
        }
    }

    public void gotoFollowerPage(ActionEvent ae) {
       this.userProfileApplicationLayer.navigationFollowerPage(ae);
    }
    public void gotoFollowingPage(ActionEvent ae) {
        this.userProfileApplicationLayer.navigationFollowingPage(ae);
       }
}