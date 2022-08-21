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
import kapta.control.guicontroller.interfaceone.item.JFX1UserItemGUIController;
import kapta.utils.bean.UserBean;
import kapta.utils.bean.J1.JFX1ClubBean;
import kapta.utils.bean.J1.JFX1PartyBean;
import kapta.utils.bean.J1.JFX1UserBean;
import kapta.utils.decorations.JFX1DecorationPartyOne;
import kapta.utils.decorations.JFX1DecorationPartyTwo;
import kapta.utils.Observer;
import kapta.utils.pagesetter.setterjfx1.JFX1UserProfileSetter;
import kapta.utils.VisualComponent;
import kapta.utils.session.ThreadLocalSession;

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


    private JFX1ClubBean whoIamClub;
    private int type ;

    private JFX1UserBean partyCreator;
    private JFX1PartyBean partyBean;

    public JFX1PartyBean getPartyBean() {
        return partyBean;
    }

    public void setPartyBean(JFX1PartyBean partyBean) {
        this.partyBean = partyBean;
    }

    public void setPartyCreator(JFX1UserBean partyCreator) {
        this.partyCreator = partyCreator;
    }

    public JFX1UserBean getPartyCreator() {
        return partyCreator;
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
        assert parentRoot != null;
        parentRoot.setVisible(true);

        JFX1UserProfileGuiController ctrl=loader.getController();

        JFX1UserProfileSetter.setter(getPartyCreator(), ctrl);
        Stage stage = (Stage) ap.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setAll(JFX1PartyBean jfx1PartyBean,JFX1UserBean creator ) {
        setPartyBean(jfx1PartyBean);
        setPartyCreator(creator);
        setLabelPartyDate(jfx1PartyBean.getPartyDateOut());
        setLabelPartyName(jfx1PartyBean.getPartyNameOut());
        setLabelPartyDuration(jfx1PartyBean.getPartyDurationOut());
        setLabelPartyTime(jfx1PartyBean.getPartyTimeOut());
        setImg((jfx1PartyBean.getPartyImgOut()));
        setBtnUsernameCreator(jfx1PartyBean.getPartyCreator());
        setWhoIam();

    }


    public void display() {
        this.root.getChildren().remove(addedVBox);
        if(this.contents.addUserPanel()!=null) {
            this.root.getChildren().add(this.contents.addUserPanel());
        }
    }

    public void setContents(VisualComponent contents){this.contents= contents;}

    public void actionDecoratePartyOne() {
        JFX1DecorationPartyOne jfx1DecorationPartyOne =new JFX1DecorationPartyOne(this.jfx1UserPanel, getPartyBean());
        this.setContents(jfx1DecorationPartyOne);

        this.display();
    }

    public void actionDecoratePartyTwo() {
        JFX1DecorationPartyTwo jfx1DecorationPartyTwo = new JFX1DecorationPartyTwo(this.jfx1UserPanel, getPartyBean());
        this.setContents(jfx1DecorationPartyTwo);
        this.display();
    }

    public void myStart(){
        this.addedVBox = new VBox();
        this.jfx1UserPanel =new JFX1UserPanel(addedVBox);
        this.setContents(this.jfx1UserPanel);
        setWhoIam();
        display();
        int decoration= chooseDecoration();
        switch (decoration){
            case 1: actionDecoratePartyOne(); break;
            case 2: actionDecoratePartyTwo(); break;
            default: break;
        }
    }

    private  int chooseDecoration(){
        if(this.type ==1) {
            if (this.whoIamClub.getUsername().equals(getPartyCreator().getUsername())) {
                return 1;
            }
        }
        else if( this.type==0){return 2;}
        return -1;
    }

    private void setWhoIam() {
        int typeMe= ThreadLocalSession.getUserSession().get().getType();
        this.type=typeMe;
        if(type==1){
            this.whoIamClub = new JFX1ClubBean (ThreadLocalSession.getUserSession().get().getClubBean());
        }
    }

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        JFX1UserBean partecipant = new JFX1UserBean ((UserBean) ob);
        try{
            pane = fxmlLoader.load(getClass().getResource("/JFX1/JFX1UserItem.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFX1UserItemGUIController uigc = fxmlLoader.getController();
        uigc.setAll(partecipant);

        this.listView.getItems().add(pane);
    }



    @Override
    public void updateFrom(Object ob, Object from) {
        //mi serve averla ma non necessita di implementazione
    }
}
