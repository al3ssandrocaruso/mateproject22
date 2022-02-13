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
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanout.jfx1.JFX1EventBeanOut;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;
import kapta.utils.decorations.JFX1DecorationEventOne;
import kapta.utils.decorations.JFX1DecorationEventTwo;
import kapta.utils.Observer;
import kapta.utils.VisualComponent;

import java.io.IOException;

public class JFX1EventPageGUIController implements Observer {

    @FXML
    private ListView<Pane> listView;
    @FXML
    private VBox root;
    @FXML
    private Label labelEventName;
    @FXML
    private Label labelEventDate;
    @FXML
    private Label labelDurationEvent;
    @FXML
    private Label labelEventTime;
    @FXML
    private Button btnUsername;
    @FXML
    private Label labelEventPrice;
    @FXML
    private ImageView greenPassImg;
    @FXML
    private ImageView eventImg;

    private VisualComponent contents;
    private VBox addedVBox;
    private JFX1UserPanel jfx1UserPanel;

    private EventApplicationLayer eventApplicationLayer;

    //Setter & getter
    public void setEventApplication(EventApplicationLayer eventApplicationLayer) {
        this.eventApplicationLayer = eventApplicationLayer;
    }
    public void setLabelEventName(String name) {
        this.labelEventName.setText(name);
    }
    public void setLabelEventDate(String date) {
        this.labelEventDate.setText(date);
    }
    public void setLabelDurationEvent(String duration) {
        this.labelDurationEvent.setText(duration);
    }
    public void setLabelEventTime(String time) {
        this.labelEventTime.setText(time);
    }
    public void setLabelEventPrice(String price) {
        this.labelEventPrice.setText(price);
    }
    public void setBtnUsername(String username) {
        this.btnUsername.setText(username);
    }
    public void setImg(Image img){
        this.eventImg.setImage(img);
    }
    public void setContents(VisualComponent contents){this.contents= contents;}

    public void setAll(JFX1EventBeanOut jfx1EventBeanOut, EventApplicationLayer eventApplicationLayer){
        setEventApplication(eventApplicationLayer);
        greenPassImg.setVisible(false);
        if(this.eventApplicationLayer.getEventModel().isGreenPass()){
            greenPassImg.setVisible(true);
        }

        setLabelEventDate(jfx1EventBeanOut.getEventDate());
        setLabelEventName(jfx1EventBeanOut.getEventName());
        setLabelDurationEvent(jfx1EventBeanOut.getEventDuration());
        setLabelEventPrice(jfx1EventBeanOut.getEventPrice());
        setLabelEventTime(jfx1EventBeanOut.getEventTime());
        setBtnUsername(jfx1EventBeanOut.getEventCreator());
        setImg(jfx1EventBeanOut.getEventImg());
        myStart();
    }

    public void display() {
        this.root.getChildren().remove(addedVBox);
        if(this.contents.addUserPanel()!=null) {
            this.root.getChildren().add(this.contents.addUserPanel());
        }
    }

     private void myStart() {
        this.addedVBox = new VBox();
        this.jfx1UserPanel =new JFX1UserPanel(addedVBox);
        this.setContents(this.jfx1UserPanel);
        this.eventApplicationLayer.setWhoIam();

        display();

        int i = this.eventApplicationLayer.chooseDecoration();

        if(i==0){
            actionDecorateEventOne();
        }
        else {
            actionDecorateEventTwo();
        }
    }

    private void actionDecorateEventTwo(){
        JFX1DecorationEventTwo jfx1DecorationEventTwo = new JFX1DecorationEventTwo(this.jfx1UserPanel, this.eventApplicationLayer);
        this.setContents(jfx1DecorationEventTwo);
        this.display();
    }

    private void actionDecorateEventOne() {
        JFX1DecorationEventOne jfx1DecorationEventOne = new JFX1DecorationEventOne(this.jfx1UserPanel, this.eventApplicationLayer);
        this.setContents(jfx1DecorationEventOne);
        this.display();
    }


    @FXML
    public void goToCreatorProfile(ActionEvent ae){
        this.eventApplicationLayer.goToClubProfile(ae, "/JFX1/JFX1ClubProfile.fxml");
    }


    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;

        if(ob instanceof UserModel userModel) {

            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1UserItemGUIController uigc = fxmlLoader.getController();

            JFX1UserBeanOut jfx1UserBeanOut = new JFX1UserBeanOut(userModel);
            UserProfileApplicationLayer userProfileApplicationLayer = new UserProfileApplicationLayer(uigc, userModel);

            uigc.setAll(jfx1UserBeanOut, userProfileApplicationLayer);
            this.listView.getItems().add(pane);
        }

    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //devo implementarlo ma non mi serve, quindi lo lascio vuoto
    }
}
