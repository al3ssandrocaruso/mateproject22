package kapta.control.guicontroller.interfaceone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kapta.application.PartyApplicationLayer;
import kapta.application.UserProfileApplicationLayer;
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.model.profiles.UserModel;
import kapta.utils.bean.beanout.jfx1.JFX1PartyBeanOut;
import kapta.utils.bean.beanout.jfx1.JFX1UserBeanOut;
import kapta.utils.decorations.JFX1DecorationPartyOne;
import kapta.utils.decorations.JFX1DecorationPartyTwo;
import kapta.utils.Observer;
import kapta.utils.pagesetter.setterjfx1.JFX1UserProfileSetter;
import kapta.utils.VisualComponent;
import java.io.IOException;

public class JFX1PartyPageGUIController implements Observer {

    @FXML
    private ImageView partyImg;

    @FXML
    private VBox root;

    @FXML
    private AnchorPane ap;

    @FXML
    private Label labelPartyName;

    @FXML
    private Label labelPartyDate;

    @FXML
    private Label labelPartyDuration;

    @FXML
    private Label labelPartyTime;

    @FXML

    private Button btnUsernameCreator;
    @FXML
    private ListView<Pane> listView;

    private VBox addedVBox;
    private JFX1UserPanel jfx1UserPanel;
    private VisualComponent contents;
    private PartyApplicationLayer partyApplicationLayer;

    public void setPartyApplication(PartyApplicationLayer partyApplicationLayer) {
        this.partyApplicationLayer = partyApplicationLayer;
    }


    public void setLabelPartyName(String name) {
        this.labelPartyName.setText(name);
    }
    public void setLabelPartyDate(String date) {
        this.labelPartyDate.setText(date);
    }
    public void setLabelPartyDuration(String duration) {
        this.labelPartyDuration.setText(duration);
    }
    public void setLabelPartyTime(String time) {
        this.labelPartyTime.setText(time);
    }
    public void setBtnUsernameCreator(String username) {
        this.btnUsernameCreator.setText("@"+username);
    }
    public void setImg(Image img){this.partyImg.setImage(img);}

    @FXML
    public void goToCreatorPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX1/JFX1UserProfile.fxml"));
        
        Parent parentRoot = null;
        try {
            parentRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parentRoot.setVisible(true);

        JFX1UserProfileGuiController ctrl=loader.getController();

        // da cambiare !!!
        JFX1UserProfileSetter.setter(this.partyApplicationLayer.getPartyModel().getPartyCreator(), ctrl);
        Stage stage = (Stage) ap.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setAll(JFX1PartyBeanOut jfx1PartyBeanOut, PartyApplicationLayer partyApplicationLayer) {
        setLabelPartyDate(jfx1PartyBeanOut.getPartyDate());
        setLabelPartyName(jfx1PartyBeanOut.getPartyName());
        setLabelPartyDuration(jfx1PartyBeanOut.getPartyDuration());
        setLabelPartyTime(jfx1PartyBeanOut.getPartyTime());
        setImg((jfx1PartyBeanOut.getPartyImg()));
        setBtnUsernameCreator(jfx1PartyBeanOut.getPartyCreator());
        setPartyApplication(partyApplicationLayer);
    }


    public void display() {
        this.root.getChildren().remove(addedVBox);
        if(this.contents.addUserPanel()!=null) {
            this.root.getChildren().add(this.contents.addUserPanel());
        }
    }

    public void setContents(VisualComponent contents){this.contents= contents;}

    public void actionDecoratePartyOne() {
        JFX1DecorationPartyOne jfx1DecorationPartyOne =new JFX1DecorationPartyOne(this.jfx1UserPanel, this.partyApplicationLayer);
        this.setContents(jfx1DecorationPartyOne);

        this.display();
    }

    public void actionDecoratePartyTwo() {
        JFX1DecorationPartyTwo jfx1DecorationPartyTwo = new JFX1DecorationPartyTwo(this.jfx1UserPanel, this.partyApplicationLayer);
        this.setContents(jfx1DecorationPartyTwo);
        this.display();
    }

    public void myStart(){
        this.addedVBox = new VBox();
        this.jfx1UserPanel =new JFX1UserPanel(addedVBox);
        this.setContents(this.jfx1UserPanel);
        this.partyApplicationLayer.setWhoIam();
        display();
        int decoration=partyApplicationLayer.chooseDecoration();
        switch (decoration){
            case 1: actionDecoratePartyOne(); break;
            case 2: actionDecoratePartyTwo(); break;
            default: break;
        }
    }

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        UserModel partecipant = (UserModel) ob;
        try{
            pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserItem.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFX1UserBeanOut jfx1UserBeanOut = new JFX1UserBeanOut(partecipant);
        JFX1UserItemGUIController uigc = fxmlLoader.getController();
        UserProfileApplicationLayer userProfileApplicationLayer = new UserProfileApplicationLayer(uigc,partecipant);
        uigc.setAll(jfx1UserBeanOut, userProfileApplicationLayer);

        this.listView.getItems().add(pane);
    }

    @Override
    public void updateFrom(Object ob, Object from) {
        //mi serve averla ma non necessita di implementazione
    }
}
