package kapta.control.guicontroller.interfaceone.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.PartyApplicationLayer;
import kapta.utils.bean.beanout.jfx1.JFX1PartyBeanOut;

public class JFX1PartyItemGUIController {


    @FXML
    private ImageView imageViewPartyImage;

    @FXML
    private Label labelPartyName;

    @FXML
    private Label labelPartyDate;

    private PartyApplicationLayer partyApplicationLayer;

    public void gotoPartyPage(ActionEvent ae) {
        partyApplicationLayer.goToPartyPage(ae,"/JFX1/JFX1PartyPage.fxml");
    }

    private void setImageViewPartyImage(Image image) {
        this.imageViewPartyImage.setImage(image);
    }

    private void setLabelPartyDate(String partyDate) {
        this.labelPartyDate.setText(partyDate);
    }

    public void setPartyApplication(PartyApplicationLayer partyApplicationLayer) {
        this.partyApplicationLayer = partyApplicationLayer;
    }

    private void setLabelPartyName(String partyName) {
        this.labelPartyName.setText(partyName);
    }

    public void setAll(JFX1PartyBeanOut jfx1PartyBeanOut, PartyApplicationLayer partyApplicationLayer){
        setPartyApplication(partyApplicationLayer);
        this.setLabelPartyName(jfx1PartyBeanOut.getPartyName());
        this.setLabelPartyDate(jfx1PartyBeanOut.getPartyDate());
        this.setImageViewPartyImage(jfx1PartyBeanOut.getPartyImg());
    }
}
