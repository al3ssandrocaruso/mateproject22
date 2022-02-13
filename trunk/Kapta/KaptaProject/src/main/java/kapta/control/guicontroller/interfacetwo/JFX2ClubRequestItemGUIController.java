package kapta.control.guicontroller.interfacetwo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class JFX2ClubRequestItemGUIController implements Initializable {
    @FXML
    private VBox vBoxGreenPass;
    @FXML
    private Label labelNumeroDosi;
    @FXML
    private Label labelDataVac;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelEventName;
    @FXML
    private ImageView imageEvent;

    public void setLabelNumeroDosi(String labelNumeroDosi) {
        this.labelNumeroDosi.setText(labelNumeroDosi);
    }

    public void setLabelDataVac(String labelDataVac) {
        this.labelDataVac.setText(labelDataVac);
    }

    public void setLabelUsername(String username) {
        this.labelUsername.setText(username);
    }

    public void setLabelEventName(String labelEventName) {
        this.labelEventName.setText(labelEventName);
    }

    public void setImageProfile(Image imageProfile) {
        this.imageEvent.setImage(imageProfile);
    }

    public void greenPassOn(Integer numDosi, String dataVac){
        vBoxGreenPass.setVisible(true);
        setLabelDataVac(dataVac);
        setLabelNumeroDosi("#doses: "+numDosi);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBoxGreenPass.setVisible(false);
    }
}
