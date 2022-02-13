package kapta.control.guicontroller.interfaceone.request.requestitem.club;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.RequestApplicationLayer;
import kapta.utils.bean.beanout.jfx1.JFX1RequestBeanOut;

public class JFX1ClubRequestGreenPassItemGUIController {

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelEventName;

    @FXML
    private Label labelEventDate;

    @FXML
    private Label labelDateVacc;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label labelDoses;

    private RequestApplicationLayer requestApplicationLayer;

    public void setRequestApplicationLayer(RequestApplicationLayer requestApplicationLayer) {
        this.requestApplicationLayer = requestApplicationLayer;
    }

    public void setLabelDoses(String labelDoses) {
        this.labelDoses.setText(labelDoses);
    }

    public void setImageViewProfilePic(Image im ){this.userImageView.setImage(im);}

    public void setLabelDate(String labelDate) {
        this.labelDateVacc.setText(labelDate);
    }

    public void setLabelEventDate(String labelEventDate) {this.labelEventDate.setText(labelEventDate);}

    public void setLabelEventName(String labelEventName) {
        this.labelEventName.setText(labelEventName);
    }

    public void setLabelUsername(String labelUsername) {
        this.labelUsername.setText(labelUsername);
    }

    public String getLabelUsername() {
        return labelUsername.getText();
    }


    public void rejectRequest(ActionEvent actionEvent) {
       this.requestApplicationLayer.goToRejectRequest(actionEvent);
    }

    public void acceptRequest(ActionEvent actionEvent) {
        this.requestApplicationLayer.goToAcceptRequest(actionEvent);
    }
    public void setAll(JFX1RequestBeanOut jfx1RequestBeanOut, RequestApplicationLayer requestApplicationLayer){
        setLabelEventName(jfx1RequestBeanOut.getEventName());
        setLabelEventDate(jfx1RequestBeanOut.getEventDate());
        setLabelUsername(jfx1RequestBeanOut.getSender());
        setLabelDate(jfx1RequestBeanOut.getVaccinationDate());
        setLabelDoses(jfx1RequestBeanOut.getNumDoses());
        setImageViewProfilePic(jfx1RequestBeanOut.getSenderImage());
        setRequestApplicationLayer(requestApplicationLayer);
    }
}
