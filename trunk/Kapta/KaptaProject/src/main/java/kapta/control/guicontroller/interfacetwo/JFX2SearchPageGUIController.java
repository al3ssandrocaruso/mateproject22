package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kapta.control.appcontroller.Search;
import kapta.model.EventModel;
import kapta.model.PartyModel;
import kapta.model.profiles.UserModel;
import kapta.utils.pagesetter.setterjfx2.JFX2PartyEventPageSetter;
import kapta.utils.pagesetter.setterjfx2.JFX2UserProfileSetter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class JFX2SearchPageGUIController{

    @FXML
    private AnchorPane root;
    @FXML
    private TextField searchBar;

    @FXML
    public void search(ActionEvent actionEvent) {
        String input = searchBar.getText();
        List<UserModel> support;
        List<EventModel> support1;
        List<PartyModel> support2;
        support= Search.searchUserByUsername(input);
        if(!support.isEmpty()){
            UserModel userModel=support.get(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX2/JFX2UserProfile.fxml"));
            Parent root1 = null;
            try {
                root1 = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.requireNonNull(root1).setVisible(true); //linea di codice che serve per evitare code smells
            JFX2UserProfileSetter.setter(userModel, loader.getController());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
            return;
        }
        support1= Search.searchByEventName(input);
        support2= Search.searchByPartyName(input);
        if((!support1.isEmpty()) || (!support2.isEmpty())){
            Object ob;
            if((!support1.isEmpty())){ob= support1.get(0);}
            else{ob= support2.get(0);}
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX2/JFX2PartyEventPage.fxml" ));
            Parent parentRoot = null;
            try {
                parentRoot = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //parentRoot.setVisible(true);    //linea di codice che serve per evitare code smells
            JFX2PartyEventPageSetter.setter(ob, loader.getController());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parentRoot);
            stage.setScene(scene);
            stage.show();
            return;
        }
        searchBar.setText("");
        searchBar.setPromptText("no existing. Try another time");
    }


    @FXML
    public void backToPrevious() {
            root.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX2/JFX2UserBar.fxml"));
        Parent newRoot = null;
        try {
            newRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().add(newRoot);
        }
    }
