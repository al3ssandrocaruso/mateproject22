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
import kapta.application.ClubProfileApplicationLayer;
import kapta.application.EventApplicationLayer;
import kapta.control.guicontroller.interfaceone.item.JFX1EventItemGUIController;
import kapta.model.EventModel;
import kapta.model.lists.CreatedEventList;
import kapta.utils.bean.beanout.jfx1.JFX1ClubBeanOut;
import kapta.utils.bean.beanout.jfx1.JFX1EventBeanOut;
import kapta.utils.Observer;
import java.io.IOException;

public class JFX1ClubProfileGUIcontroller implements Observer {

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

    public ClubProfileApplicationLayer getClubProfileApplication() {
        return clubProfileApplicationLayer;
    }

    public void setClubProfileApplication(ClubProfileApplicationLayer clubProfileApplicationLayer) {
        this.clubProfileApplicationLayer = clubProfileApplicationLayer;
    }

    private ClubProfileApplicationLayer clubProfileApplicationLayer;

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
        clubProfileApplicationLayer.navigateToClubCreateEvent(actionEvent);
        }

    public void setAll(JFX1ClubBeanOut jfx1ClubBeanOut, ClubProfileApplicationLayer clubProfileApplicationLayer) {
        setLabelUserName(jfx1ClubBeanOut.getUsername());
        setLabelAddress(jfx1ClubBeanOut.getClubAddress());
        setLabelCreatedEvents(jfx1ClubBeanOut.getNumCreatedEvents());
        setImageViewImgProfile(jfx1ClubBeanOut.getImage());
        setClubProfileApplication(clubProfileApplicationLayer);

        if(clubProfileApplicationLayer.hideBtnCreateEvent()){
            btnCreateEvent.setVisible(false);
        }

    }

    @Override
    public void update(Object ob) {
        EventModel eventModel=(EventModel) ob;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        try{
            pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1EventItem.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFX1EventItemGUIController eigc = fxmlLoader.getController();
        EventApplicationLayer ea = new EventApplicationLayer(eventModel);
        JFX1EventBeanOut jfx1EventBeanOut = new JFX1EventBeanOut(eventModel);
        eigc.setAll(jfx1EventBeanOut, ea );

        this.listViewCreatedEvents.getItems().add(pane);

    }

    @Override
    public void updateFrom(Object ob, Object objectFrom) {
        setLabelCreatedEvents(((CreatedEventList) objectFrom).getSize().toString());
    }
}
