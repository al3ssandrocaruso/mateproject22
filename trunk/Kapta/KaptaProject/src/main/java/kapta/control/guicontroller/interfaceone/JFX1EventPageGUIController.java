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
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.utils.bean.UserBean;
import kapta.utils.bean.jfx1.JFX1ClubBean;
import kapta.utils.bean.jfx1.JFX1EventBean;
import kapta.utils.bean.jfx1.JFX1UserBean;
import kapta.utils.decorations.JFX1DecorationEventOne;
import kapta.utils.decorations.JFX1DecorationEventTwo;
import kapta.utils.Observer;
import kapta.utils.VisualComponent;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.session.ThreadLocalSession;

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


    private JFX1EventBean eventBean;
    private JFX1ClubBean whoIamClub;
    private int typeMe;

    private JFX1ClubBean creator;



    //Setter & getter

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

    public void setEventBean(JFX1EventBean eventBean) {
        this.eventBean = eventBean;
    }

    public JFX1EventBean getEventBean() {
        return eventBean;
    }

    public JFX1ClubBean getCreator() {
        return creator;
    }

    public void setCreator(JFX1ClubBean creator) {
        this.creator = creator;
    }

    public void setAll(JFX1EventBean jfx1EventBean, JFX1ClubBean creatorBean){

        setCreator(creatorBean);
        setWhoIam();
        setEventBean(jfx1EventBean);
        greenPassImg.setVisible(false);
        if(eventBean.isGreenPass()){
            greenPassImg.setVisible(true);
        }

        setLabelEventDate(jfx1EventBean.getEventDateOut());
        setLabelEventName(jfx1EventBean.getEventNameOut());
        setLabelDurationEvent(jfx1EventBean.getEventDurationOut());
        setLabelEventPrice(jfx1EventBean.getEventPriceOut());
        setLabelEventTime(jfx1EventBean.getEventTimeOut());
        setBtnUsername(jfx1EventBean.getEventCreatorOut());
        setImg(jfx1EventBean.getEventImgOut());
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
        this.setWhoIam();

        display();

        int i = chooseDecoration();

        if(i==0){
            actionDecorateEventOne();
        }
        else {
            actionDecorateEventTwo();
        }
    }

    private  void setWhoIam() {
        int type= ThreadLocalSession.getUserSession().get().getType();
        this.typeMe =type;
        if(type==1){
            this.whoIamClub= new JFX1ClubBean (ThreadLocalSession.getUserSession().get().getClubBean());
        }
    }





    private  int chooseDecoration(){
        if(typeMe ==1 && this.whoIamClub.getUsernameOut().equals(getCreator().getUsername())) {
                return 0;
            }
        return 1;
    }

    private void actionDecorateEventTwo(){
        JFX1DecorationEventTwo jfx1DecorationEventTwo = new JFX1DecorationEventTwo(this.jfx1UserPanel, this.eventBean, this.creator);
        this.setContents(jfx1DecorationEventTwo);
        this.display();
    }

    private void actionDecorateEventOne() {
        JFX1DecorationEventOne jfx1DecorationEventOne = new JFX1DecorationEventOne(this.jfx1UserPanel, this.eventBean);
        this.setContents(jfx1DecorationEventOne);
        this.display();
    }

    private  void goToClubProfile(ActionEvent ae, String fxml) {
        ReplaceSceneAndInitializePage risp=new ReplaceSceneAndInitializePage();
        risp.replaceSceneAndInitializePage(ae,fxml,getCreator());
    }


    @FXML
    public void goToCreatorProfile(ActionEvent ae){
        goToClubProfile(ae, "/JFX1/JFX1ClubProfile.fxml");
    }


    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;

        if(ob instanceof UserBean userBean) {
            JFX1UserBean jfx1UserBean = new JFX1UserBean(userBean);

            try {
                pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserItem.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JFX1UserItemGUIController uigc = fxmlLoader.getController();

            uigc.setAll(jfx1UserBean);
            this.listView.getItems().add(pane);
        }

    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //devo implementarlo ma non mi serve, quindi lo lascio vuoto
    }
}
