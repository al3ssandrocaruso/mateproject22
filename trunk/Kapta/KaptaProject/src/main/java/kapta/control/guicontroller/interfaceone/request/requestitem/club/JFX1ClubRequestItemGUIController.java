package kapta.control.guicontroller.interfaceone.request.requestitem.club;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.RequestApplicationLayer;
import kapta.utils.bean.beanout.jfx1.JFX1RequestBeanOut;

public class JFX1ClubRequestItemGUIController {
    @FXML
    private ImageView userImageView;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelEventName;
    @FXML
    private Label labelEventDate;
    RequestApplicationLayer requestApplicationLayer;

    public void setRequestApplicationLayer(RequestApplicationLayer requestApplicationLayer) {
        this.requestApplicationLayer = requestApplicationLayer;
    }

    public void setImageViewProfilePic(Image im ){this.userImageView.setImage(im);}

    public void setLabelEventDate(String labelEventDate) {
        this.labelEventDate.setText(labelEventDate);
    }

    public void setLabelEventName(String labelEventName) {
        this.labelEventName.setText(labelEventName);
    }

    public void setLabelUsername(String labelUsername) {
        this.labelUsername.setText(labelUsername);
    }

    public Label getLabelUsername() {
        return labelUsername;
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
        setImageViewProfilePic(jfx1RequestBeanOut.getSenderImage());
        setRequestApplicationLayer(requestApplicationLayer);
    }
}
