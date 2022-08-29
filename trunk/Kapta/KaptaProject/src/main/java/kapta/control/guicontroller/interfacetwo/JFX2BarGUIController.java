package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import kapta.utils.init.JFX2ReplaceSceneAndInitializePage;
import kapta.utils.init.ReplaceScene;
import kapta.utils.mysession.ThreadLocalSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class JFX2BarGUIController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnRequest;

    @FXML
    private HBox hboxBar;

    public void switchInterface(ActionEvent event) {
        ReplaceScene.replaceScene(event, "/JFX1/JFX1Login.fxml");
    }

    public void goToSearch() {
        root.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX2/JFX2UserSearchPage.fxml"));
        Parent newRoot = null;
        try {
            newRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().add(newRoot);
    }

    public void goToSignOut(ActionEvent event){
        ReplaceScene.replaceScene(event, "/JFX2/JFX2Welcome.fxml");
    }

    public void goToProfile(ActionEvent event)  {
        if( ThreadLocalSession.getUserSession().get().getUserBean() !=null) {
            JFX2ReplaceSceneAndInitializePage rsip1 = new JFX2ReplaceSceneAndInitializePage();
            rsip1.replaceSceneAndInitializePage(event, "/JFX2/JFX2UserProfile.fxml");
        }else{
            JFX2ReplaceSceneAndInitializePage rsip2 = new JFX2ReplaceSceneAndInitializePage();
            rsip2.replaceSceneAndInitializePage(event, "/JFX2/JFX2ClubProfile.fxml");
        }
    }

    public void gotoRequestPage(ActionEvent actionEvent) {
       JFX2ReplaceSceneAndInitializePage rsip3 = new JFX2ReplaceSceneAndInitializePage();
       rsip3.replaceSceneAndInitializePage(actionEvent, "/JFX2/JFX2UserRequestPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int type= ThreadLocalSession.getUserSession().get().getType();
        if(type==1){
            hboxBar.getChildren().remove(btnRequest);
            hboxBar.getChildren().remove(btnSearch);
            Button button=new Button();
            button.setPrefSize(150,70);
            button.setStyle("-fx-background-color: #200f54;"+"-fx-text-fill: white;");
            button.setText("Logged as Manager");
            Font font = Font.font("Arial", FontWeight.BOLD, 12);
            button.setFont(font);
            hboxBar.getChildren().add(button);
        }
    }
}
