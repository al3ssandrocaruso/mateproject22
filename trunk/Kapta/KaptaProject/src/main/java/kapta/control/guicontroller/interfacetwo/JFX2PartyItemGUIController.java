package kapta.control.guicontroller.interfacetwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kapta.application.PartyApplicationLayer;
import kapta.utils.bean.beanout.jfx1.JFX1PartyBeanOut;


public class JFX2PartyItemGUIController {
    @FXML
    private ImageView imageViewPartyImage;

    @FXML
    private Label labelPartyName;

    @FXML
    private Label labelPartyDate;

    private PartyApplicationLayer partyApplicationLayer;

    public void setPartyApplication(PartyApplicationLayer partyApplicationLayer) {
        this.partyApplicationLayer = partyApplicationLayer;
    }

    public void setImageViewPartyImage(Image imageViewPartyImage) {
        this.imageViewPartyImage.setImage(imageViewPartyImage);
    }

    public void setLabelPartyName(String labelPartyName) {
        this.labelPartyName.setText(labelPartyName);
    }

    public void setLabelPartyDate(String labelPartyDate) {
        this.labelPartyDate.setText(labelPartyDate);
    }

    public void gotoPartyInfo(ActionEvent ae)  {
        this.partyApplicationLayer.goToPartyPage(ae,"/JFX2/JFX2PartyEventPage.fxml");
    }
    public void setAll(JFX1PartyBeanOut jfx1PartyBeanOut, PartyApplicationLayer partyApplicationLayer){
        setPartyApplication(partyApplicationLayer);
        setImageViewPartyImage(jfx1PartyBeanOut.getPartyImg());
        setLabelPartyName(jfx1PartyBeanOut.getPartyName());
        setLabelPartyDate(jfx1PartyBeanOut.getPartyDate());
    }
}
