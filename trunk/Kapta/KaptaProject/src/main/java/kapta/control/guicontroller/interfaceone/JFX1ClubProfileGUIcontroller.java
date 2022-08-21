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
import kapta.control.guicontroller.interfaceone.item.JFX1EventItemGUIController;
import kapta.utils.Observer;
import kapta.utils.bean.EventBean;
import kapta.utils.bean.GenericListInfoBean;
import kapta.utils.bean.jfx1.JFX1ClubBean;
import kapta.utils.bean.jfx1.JFX1EventBean;
import kapta.utils.init.ReplaceSceneAndInitializePage;
import kapta.utils.session.ThreadLocalSession;

import java.io.IOException;

public class JFX1ClubProfileGUIcontroller implements Observer {

    private JFX1ClubBean clubBean;


    @FXML
    private ImageView imageViewImgProfile;
    @FXML
    private ListView<Pane> listViewCreatedEvents;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelCreatedEvents;
    @FXML
    private Label labelAddress;
    @FXML
    private Button btnCreateEvent;


    public void setClubBean(JFX1ClubBean clubBean) {
        this.clubBean = clubBean;
    }

    public JFX1ClubBean getClubBean() {
        return clubBean;
    }

    public void setLabelUserName(String username) {
        this.labelUsername.setText("@"+username);
    }
    public void setLabelAddress(String address) {
        this.labelAddress.setText("Address: "+address);
    }
    public void setLabelCreatedEvents(String numCreatedEvents) {
        this.labelCreatedEvents.setText(numCreatedEvents);
    }
    public void setImageViewImgProfile(Image imageViewImgProfile) {this.imageViewImgProfile.setImage(imageViewImgProfile);}

    public void goToCreateEventAction(ActionEvent actionEvent)  {
        navigateToClubCreateEvent(actionEvent);
        }

    private void navigateToClubCreateEvent(ActionEvent actionEvent) {
        ReplaceSceneAndInitializePage rsip =  new ReplaceSceneAndInitializePage();
        rsip.replaceSceneAndInitializePage(actionEvent, "/JFX1/JFX1ClubCreateEvent.fxml",getClubBean() );
    }

    public void setAll(JFX1ClubBean jfx1ClubBean) {
        setLabelUserName(jfx1ClubBean.getUsernameOut());
        setLabelAddress(jfx1ClubBean.getClubAddressOut());

        // !!!
        setLabelCreatedEvents(jfx1ClubBean.getNumCreatedEventsOut());
        setImageViewImgProfile(jfx1ClubBean.getImageOut());
        setClubBean(jfx1ClubBean);

        if(hideBtnCreateEvent()){
            btnCreateEvent.setVisible(false);
        }

    }

    private  boolean hideBtnCreateEvent() {
        return  ThreadLocalSession.getUserSession().get().getUserBean() != null;
    }


    @Override
    public void update(Object ob) {
        JFX1EventBean jfx1EventBean = new JFX1EventBean((EventBean) ob);

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        try{
            pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1EventItem.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFX1EventItemGUIController eigc = fxmlLoader.getController();
        eigc.setAll(jfx1EventBean );

        this.listViewCreatedEvents.getItems().add(pane);

    }

    @Override
    public void updateFrom(Object ob, Object objectFrom) {
        setLabelCreatedEvents( String.valueOf (((GenericListInfoBean) objectFrom).getSize()));
    }
}
