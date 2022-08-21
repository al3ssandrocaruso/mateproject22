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
import kapta.control.guicontroller.interfaceone.item.JFX1EventItemGUIController;
import kapta.engineering.ManageFollowerFollowingList;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.GenericListInfoBean;
import kapta.utils.bean.PartyBean;
import kapta.utils.bean.J1.JFX1EventBean;
import kapta.utils.bean.J1.JFX1PartyBean;
import kapta.utils.bean.J1.JFX1UserBean;
import kapta.utils.decorations.JFX1DecorationUserOne;
import kapta.utils.decorations.JFX1DecorationUserTwo;
import kapta.utils.Observer;
import kapta.utils.VisualComponent;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.session.ThreadLocalSession;
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
    private JFX1UserBean userBean;
    private int numFollowing;
    private int numFollower;


    ManageFollowerFollowingList man ;




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

    public void setAll(JFX1UserBean jfx1UserBean, ManageFollowerFollowingList man ) {

        this.man = man;
        this.userBean = jfx1UserBean;
        setLabelUserName("@" + jfx1UserBean.getUsernameOut());
        setLabelName(jfx1UserBean.getNameOut());
        setLabelSecondName(jfx1UserBean.getSecondNameOut());
        setImageview(jfx1UserBean.getProfileImgOut());
        myStart();
    }

    public void myStart() {
        this.addedVBox=new VBox();
        this.jfx1UserPanel =new JFX1UserPanel(addedVBox); //new di Concrete Component
        this.setContents(this.jfx1UserPanel);

        display();

        int i = chooseDecoration();
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
        new JFX1DecorationUserTwo(this.jfx1UserPanel, this, userBean, this.man);
    }
    private int chooseDecoration(){
        if(( ThreadLocalSession.getUserSession().get().getUserBean() != null)){
            if((userBean.getUsername()).equals( ThreadLocalSession.getUserSession().get().getUserBean().getUsername())){
                return 0;
            }
            else if(userBean.getType()==0){
                return 1;
            }
        }
        return 2;
    }

    @Override
    public void update(Object ob)  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        if(ob instanceof PartyBean partyBean){
            JFX1PartyBean jfx1PartyBean = new JFX1PartyBean(partyBean);

            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1PartyItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            UpdateHandlerUno.handler(fxmlLoader, jfx1PartyBean);
            this.listView.getItems().add(pane);
        }
        else if(ob instanceof EventBean eventBean) {
            JFX1EventBean jfx1EventBean = new JFX1EventBean(eventBean);
            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1EventItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1EventItemGUIController eigc = fxmlLoader.getController();
            eigc.setAll(jfx1EventBean);
            this.listView.getItems().add(pane);
        }
    }

    //Serve chiamarle così le liste così che non diano code smells (già c'è followerList e followingList)
    @Override
    public void updateFrom(Object ob,Object objectFrom) {
        if(objectFrom instanceof GenericListInfoBean genericListInfoBean){
            {
                if (genericListInfoBean.getType() == 1) {

                    this.numFollower=genericListInfoBean.getSize();
                    setBtnFollowers(String.valueOf(numFollower));
                } else if (genericListInfoBean.getType() == 0) {
                    this.numFollowing=genericListInfoBean.getSize();
                    setBtnFollowing(String.valueOf(numFollowing));

                }
            }}

    }

    public void display(){
        root.getChildren().remove(addedVBox);
        if (this.contents.addUserPanel() != null) {
            root.getChildren().add(this.contents.addUserPanel());
        }
    }

    public void gotoFollowerPage(ActionEvent ae) {
        ReplaceSceneAndInitializePage risp=new ReplaceSceneAndInitializePage();
        risp.replaceSceneAndInitializePage(ae,"/JFX1/JFX1FollowerFollowingListPage.fxml",0,this.userBean);
    }

    public void gotoFollowingPage(ActionEvent ae) {
        ReplaceSceneAndInitializePage rsip = new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(ae,"/JFX1/JFX1FollowerFollowingListPage.fxml", 1, this.userBean);

       }
}